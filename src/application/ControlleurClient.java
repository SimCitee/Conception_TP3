package application;

import java.util.ArrayList;

import service.ConsoleHelper;
import service.ConsoleTableLayout;
import service.CustomScanner;
import service.InputValidator;
import ui.Interface;
import domaine.Catalogue;
import domaine.Client;
import domaine.ConducteurSecondaire;
import domaine.RegistreClient;
import domaine.Vehicule;

public class ControlleurClient {
	
	private static CustomScanner userInputScanner = CustomScanner.getInstance();
	private static RegistreClient regClient = RegistreClient.getInstance();
	
	public static Client obtenirClient(String noPermisConduire) {
		return regClient.rechercherClient(noPermisConduire);
	}
	
	/*
	 * Saisie du numero de permis de conduire d'un client et recherche celui ci
	 */
	public static Client obtenirClientParId() {
		String input = "";
		Client c = null;
		do {
			System.out.print("Veuillez entrer le numero de permis de conduire du client (entrer 0 pour annuler): ");
			input = Interface.lecture();
			
			c = ControlleurClient.obtenirClient(input);
		} while (c == null && !input.equalsIgnoreCase("0"));
		
		return c;
	}

	public static void fenetrePrincipale() {
		String input;
		CustomScanner userInputScanner = CustomScanner.getInstance();
	
		ConsoleHelper.printScreenSeparator("Accueil");
		System.out.println("1. Afficher les clients");
		System.out.println("2. Ajouter un client");
		System.out.println("3. Modifier un client");
		System.out.println("4. Supprimer un client");
		System.out.println("0. retour");
		
		do{
			input = userInputScanner.getUserInput("Votre choix : ");
			
			if(InputValidator.stringIsInteger(input) && InputValidator.evalRange(Integer.parseInt(input), 0, 4))
				break;
				
			System.out.println("Choix invalide!");
			
		} while(true);
		
		switch(Integer.parseInt(input))
		{
			
			case 0 :
				Interface.menuPrincipal();
				break;
			
			case 1 :
				afficherClients();
				break;
			
			case 2 :
				ajouterClient();
				break;
			
			case 3 :
				modifierClient();
				break;
				
			case 4 :
				supprimerUnClient();
				break;
				
		}

	}

	private static void afficherClients() {
		String estDispoSring;
		RegistreClient registreClient = RegistreClient.getInstance();
		ConsoleTableLayout layout = new ConsoleTableLayout(15, 15, 15, 30, 15, 50);

		//En-tete du tableau
		layout.newLine("No. Permis", "Nom", "Prenom", "Adresse", "No. Telephone", "Conducteurs Secondaire");
		
		for (Client c : registreClient.getListeClients()) {
			layout.newLine(c.getNoPermisConduire(),
					c.getNom(),
					c.getPrenom(),
					c.getAdresse(),
					c.getNoTel(),
					c.getConducteurSecFormate());
					
		}
		userInputScanner.getUserInput("\nAppuyer sur une touche");
		fenetrePrincipale();
	}
	
	private static void ajouterClient() {
		
		
		ConsoleHelper.printScreenSeparator("Accueil > Gestion des clients > Ajouter un client");
		
		//Prend l'input de l'utilisateur
		String noPermis = getNoPermis();
		String nom = getNom();
		String prenom = getPrenom();
		String adresse = getAdresse();
		String noTel = getNoTel();
		String noCarteBancaire = getCarteBancaire();
		ArrayList<ConducteurSecondaire> listeConducteurSec = getConducteurSec();
		
		RegistreClient rc = RegistreClient.getInstance();
		
		rc.ajouterClient(noPermis, nom, prenom, adresse, noTel, noCarteBancaire, listeConducteurSec);

		System.out.println("Client ajoute.");
		userInputScanner.getUserInput("\nAppuyer sur une touche");
		fenetrePrincipale();
		
	}
	
	private static ArrayList<ConducteurSecondaire> getConducteurSec() {
		String choix = "";
		String noPermis = "";
		String nom = "";
		String prenom = "";
		ArrayList<ConducteurSecondaire> conducteurSec = new ArrayList<ConducteurSecondaire>();
		
		choix = userInputScanner.getUserInput("Y a-t-il un conducteur secondaire? (1 pour oui / 0 pour non)");
		
		while (choix.compareTo("1") == 0) {
			noPermis = getNoPermis();
			nom = getNom();
			prenom = getPrenom();
			
			conducteurSec.add(new ConducteurSecondaire(noPermis, nom, prenom));
			
			choix = userInputScanner.getUserInput("Y a-t-il un autre conducteur secondaire? (1 pour oui / 0 pour non)");
		} 
		return conducteurSec;
	}

	private static String getCarteBancaire() {
		return userInputScanner.getUserInput("No. Carte Bancaire (ex. 1234 1234 1234 1234)) : ");
	}

	private static String getNoTel() {
		return userInputScanner.getUserInput("No. de tel. (ex. 123-456-7890)) : ");
	}

	private static String getAdresse() {
		return userInputScanner.getUserInput("Adresse (ex. 1234 Principale A1A 1A1 Trois-Rivieres, QC)) : ");
	}

	private static String getPrenom() {
		return userInputScanner.getUserInput("Prenom : ");
	}

	private static String getNom() {
		return userInputScanner.getUserInput("Nom : ");
	}

	private static String getNoPermis() {
		return userInputScanner.getUserInput("No. Permis (ex. X000000000000)) : ");
	}

	private static void supprimerUnClient() {
	}

	private static void modifierClient() {
		
//		ConsoleHelper.printScreenSeparator("Accueil > Gestion des vehicules > Modifier un vehicule");
//		
//		//Affiche le tableau de vehicules
//		afficherListeVehicules();
//		
//		//Le vehicule choisit par l'utilisateur
//		Vehicule vehicule = getVehiculeSelonIdentifiant();
//		
//		//Si l'utilisateur a annuler l'operation
//		if(vehicule == null)
//			fenetrePrincipale();
//		
//		
//		//Prend l'input de l'utilisateur
//		Integer cote = getCoteVehicule();
//		String marque = getMarque();
//		String modele = getModele();
//		Integer moteur = getForceMoteur();
//		String transmission = getTransmission();
//		Integer nbPlaces = getNbPlaces();
//		String immatriculation = getImmatriculation();
//		boolean estDisponible = getEstDisponible();
//		ArrayList<String> accessoires = getAccessoires();
//		
//		Catalogue c = Catalogue.getInstance();
//		
//		c.modifierVehicule(vehicule, cote, marque, modele, moteur, transmission, nbPlaces, 
//				immatriculation, estDisponible, accessoires);
//		
//		
//		Catalogue.getInstance().afficherCatalogue();
//		fenetrePrincipale();
		
	}


}
