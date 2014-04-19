package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader; 
import java.util.ArrayList;
import java.util.Iterator;

import application.ControlleurLocation;
import application.ControlleurVehicules;

import domaine.Client;
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
			System.out.println("1. Ajouter une location");
			System.out.println("2. Modifier une location");
			System.out.println("3. Terminer une location");
			System.out.println("0. Retour au menu principal");
			
			choixUtilisateur = lecture();
			
			//Le choix est valide, on sort de la validation
		    if (validerEntier(choixUtilisateur, 0, 3)) 
		    	break;
		    
		    clearConsole();
		} while (true);
		
		switch(choixUtilisateur)
		{
		
			case "0" :
				menuPrincipal();
				break;
			case "1" :		
				saisirLocation();
				break;
				
			case "2" :
				clearConsole();
				clearConsole();
				
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
	
	private static void saisirLocation() {
		Client signataire;
		Vehicule vehiculeLocation;
		
		signataire = obtenirClient();
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
			
			c = ControlleurLocation.obtenirClient(input);
		} while (c == null && !input.equalsIgnoreCase("0"));
		
		return c;
	}
	
	
}
