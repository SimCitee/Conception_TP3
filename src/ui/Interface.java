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
import service.InputValidator;

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
		    if (InputValidator.validerIntervalleEntier(choixUtilisateur, 0, 3)) 
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
	public static void afficherGestionLocations() {
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
		    if (InputValidator.validerIntervalleEntier(choixUtilisateur, 0, 4)) 
		    	break;
		    
		    clearConsole();
		} while (true);
		
		switch(choixUtilisateur)
		{
		
			case "0" :
				menuPrincipal();
				break;
			case "1" :		
				ControlleurLocation.afficherLocations();
				break;
				
			case "2" :
				clearConsole();
				clearConsole();
				ControlleurLocation.saisirLocation();
				break;
				
			case "3" :
				clearConsole();
				clearConsole();
				ControlleurLocation.saisirChangementLocation();
				break;
			case "4" :
				clearConsole(); 
				clearConsole();
				ControlleurLocation.saisirFinLocation();
				break;
			
			default :
				clearConsole();
				menuPrincipal();
		}
	}
	
}
