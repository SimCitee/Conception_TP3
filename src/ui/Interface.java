package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import service.DateUtils;

import application.ControlleurClient;
import application.ControlleurLocation;
import application.ControlleurVehicules;

import domaine.Client;
import domaine.Contrat;
import domaine.Vehicule;

public class Interface {
	
	// Methode permettant d'effectuer une lecture au clavier
	// Parametre : Aucun
	// Valeur de retour : La chaine de caractere lue au clavier
	public static String lecture ()
	{
		String str = "";
		
		try {
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    str = bufferRead.readLine(); 
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
		return str;
	}
	
	// Methode permettant d'effacer tout le texte se trouvant dans la console
	// Parametre : Aucun
	// Valeur de retour : Aucun
	public static void clearConsole()
	{
		System.out.print("\n\n\n\n");
	}
	
	// Methode permettant de valider un entier et s'il se trouve dans un intervalle donnee
	// Parametre : Un nombre potentiel, Debut de l'intervalle, Fin de l'interface
	// Valeur de retour : Valeur boolenne representant si le nombre est valide et s'il respecte l'intervalle donnee
	public static boolean validerEntier(String valeur, int plageDebut, int plageFin)
	{
		int choixUtilisateur;
		
		// Test pour valider si la chaine de caractere correspond a un nombre entier
		try { 
			choixUtilisateur = Integer.parseInt(valeur); 
	    } 
		catch(NumberFormatException e) { 
	        return false; 
	    }
		
		// Test pour valider si le nombre se trouve dans l'intervalle donnee
		if((choixUtilisateur >= plageDebut) && (choixUtilisateur <= plageFin))
			return true;

		return false;
	}
	
	// Methode permettant d'afficher le menu principal
	// Parametre : Aucun
	// Valeur de retour : Aucun
	public static void menuPrincipal()
	{
		
		String choixUtilisateur;
		
		do
		{
			System.out.println("======== Menu principal ========");
			
			System.out.println("\nVeuillez choisir une des options suivantes:");
			System.out.println("1. Gestions des utilisateurs");
			System.out.println("2. Gestion des vehicules");
			System.out.println("3. Gestion des locations");
			System.out.println("0. Quitter");
			
			choixUtilisateur = lecture();
			
			//Le choix est valide, on sort de la validation
		    if (validerEntier(choixUtilisateur, 0, 3)) 
		    	break;
		    
		    clearConsole();
		} while (true);
	
		switch(choixUtilisateur)
		{
		
			case "0" :
				System.exit(0);
				break;
			case "1" :
				clearConsole();
				clearConsole();
				
				break;
				
			case "2" :
				clearConsole();
				clearConsole();
				ControlleurVehicules.fenetrePrincipale();
				break;
				
			case "3" :
				clearConsole();
				clearConsole();
				afficherGestionLocations();
				
				break;
			
			default :
				clearConsole();
				menuPrincipal();
			
		}
		
	}
	
	// Methode permettant de valider si une chaine de caractere correspond a un nombre reel
	// Parametre : Une chaine de caractere
	// Valeur de retour : Retourne vrai si la chaine de caractere est un nombre reel, sinon faux
	public static boolean isNumeric(String s) {  
	    return s.matches("[-+]?\\d*\\.?\\d+");  
	}
	
	// Methode qui affiche le menu de la gestion des locations
	// Parametre : Aucun
	// Valeur de retour : Aucune
	private static void afficherGestionLocations() {
		String choixUtilisateur;
		
		do
		{
			System.out.println("======== Gestion des locations ========\n");
			System.out.println("Veuillez choisir une des options suivantes:");
			System.out.println("1. Afficher les locations");
			System.out.println("2. Ajouter une location");
			System.out.println("3. Modifier une location");
			System.out.println("4. Terminer une location");
			System.out.println("0. Retour au menu principal");
			
			choixUtilisateur = lecture();
			
			//Le choix est valide, on sort de la validation
		    if (validerEntier(choixUtilisateur, 0, 4)) 
		    	break;
		    
		    clearConsole();
		} while (true);
		
		switch(choixUtilisateur)
		{
		
			case "0" :
				menuPrincipal();
				break;
			case "1" :		
				afficherLocation();
				break;
				
			case "2" :
				clearConsole();
				clearConsole();
				saisirLocation();
				break;
				
			case "3" :
				clearConsole();
				clearConsole();
				changerLocation();
				break;
			case "4" :
				clearConsole();
				clearConsole();
				finLocation();
				break;
			
			default :
				clearConsole();
				menuPrincipal();
		}
	}
	
	/*
	 * Effectue la saisie des differents informations necessaire et fait appel au controlleur
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	private static void afficherLocation() {
		
		ArrayList<Contrat> listeContrats =  ControlleurLocation.obtenirListeContrats();
		
		for (Contrat c : listeContrats) {
			System.out.println(c.toString());
		}
		
		System.out.print("Appuyer sur une touche pour retourner au menu.");
		lecture();
		clearConsole();
		afficherGestionLocations();
	}
	
	/*
	 * Effectue la saisie des differents informations necessaire et fait appel au controlleur
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	private static void saisirLocation() {
		Client signataire;
		Vehicule vehiculeLocation;
		Date dateDebut;
		Date dateFin;
		
		signataire = obtenirClient();
		vehiculeLocation = ControlleurVehicules.getVehiculeSelonIdentifiant();
		dateDebut = obtenirDate("Veuillez entrer une date de debut de location");
		dateFin = obtenirDate("Veuillez entrer une date de fin de location");
		
		ControlleurLocation.nouvelleLocation(signataire, vehiculeLocation, dateDebut, dateFin);
		
		System.out.println("Location ajoutee. Appuyez sur une touche pour retourner au menu.");
		lecture();
		afficherGestionLocations();
	}
	
	/*
	 * Effectue la saisie des differents informations necessaire pour la modification et fait appel au controlleur
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	private static void changerLocation() {
		String choixUtilisateur;
		boolean succesModif = false;
		
		Contrat modifContrat = obtenirContrat();
		
		if (modifContrat != null) {
			do
			{
				System.out.println("Veuillez choisir une des options suivantes:");
				System.out.println("1. Modifier le vehicule");
				System.out.println("2. Modifier les dates");
				System.out.println("3. Modifier le vehicule et les dates");
				System.out.println("0. Retour a la gestion des location");
				
				choixUtilisateur = lecture();
				
				//Le choix est valide, on sort de la validation
			    if (validerEntier(choixUtilisateur, 0, 3)) 
			    	break;
			    
			    clearConsole();
			} while (true);
			 
			switch(choixUtilisateur)
			{
			
				case "0" :
					afficherGestionLocations();
					break;
				case "1" :		
					Vehicule vehiculeLocation = ControlleurVehicules.getVehiculeSelonIdentifiant();
					
					if (vehiculeLocation != null) {
						ControlleurLocation.modifierLocation(modifContrat, vehiculeLocation);
						succesModif = true;
					}
					break;
					
				case "2" :
					Date dateDebut = null;
					Date dateFin = null;
					dateDebut = obtenirDate("Veuillez entrer une nouvelle date de debut de location");
					dateFin = obtenirDate("Veuillez entrer une nouvelle date de fin de location");
					ControlleurLocation.modifierLocation(modifContrat, dateDebut, dateFin);
					succesModif = true;
					break;
					
				case "3" :
					Vehicule v = ControlleurVehicules.getVehiculeSelonIdentifiant();
					
					if (v != null) {
						Date debut = null;
						Date fin = null;
						debut = obtenirDate("Veuillez entrer une nouvelle date de debut de location");
						fin = obtenirDate("Veuillez entrer une nouvelle date de fin de location");
						ControlleurLocation.modifierLocation(modifContrat, v, debut, fin);
						succesModif = true;
					}
					
					break;
				default :
					clearConsole();
					afficherGestionLocations();
			}
			
		}
		
		if(succesModif) {
			System.out.print("Succes de la modification. Appuyez sur une touche pour retourner au menu.");
			lecture();
		}
		
		afficherGestionLocations();
	}
	
	/*
	 * Effectue la saisie du contrat et termine celui ci
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	private static void finLocation() {
		String choixUtilisateur;
		
		Contrat contrat = obtenirContrat();
		
		if (contrat != null) {	
			ControlleurLocation.terminerLocation(contrat);
			
			System.out.print("Succes de la terminaison du contrat. Appuyez sur une touche pour retourner au menu.");
			lecture();
		}
		
		afficherGestionLocations();
	}
	
	/*
	 * Saisie du numero de contrat et recherche celui ci
	 */
	private static Contrat obtenirContrat() {
		String input = "";
		Contrat c = null;
		do {
			System.out.print("Veuillez entrer le numero de contrat a modifier (entrer 0 pour annuler): ");
			input = lecture();
			
			c = ControlleurLocation.obtenirContrat(input);
		} while (c == null && !input.equalsIgnoreCase("0"));
		
		return c;
	}
	
	/*
	 * Saisie du numero de permis de conduire d'un client et recherche celui ci
	 */
	private static Client obtenirClient() {
		String input = "";
		Client c = null;
		do {
			System.out.print("Veuillez entrer le numero de permis de conduire du client (entrer 0 pour annuler): ");
			input = lecture();
			
			c = ControlleurClient.obtenirClient(input);
		} while (c == null && !input.equalsIgnoreCase("0"));
		
		return c;
	}
	
	/*
	 * Effectue la saisie d'une date
	 * Parametre: texte informatif a afficher
	 * Valeur de retour: la date saisie
	 */
	private static Date obtenirDate(String info) {
		String input = "";
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			
		do {
			System.out.print(info + " (MM/JJ/AAAA HH:mm:ss)");
			input = lecture();
		} while (DateUtils.isThisDateValid(input) == false);
		
		Date dt = null;
		
		try {
			dt = format.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
	}
	
	
}
