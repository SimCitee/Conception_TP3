package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domaine.Catalogue;
import domaine.TypeVehicule;
import domaine.Vehicule;

import util.ConsoleHelper;
import util.ConsoleTableLayout;
import util.CustomScanner;
import util.InputValidator;

public class ControlleurVehicules {
	
	private static CustomScanner userInputScanner = CustomScanner.getInstance();
	public static void fenetrePrincipale()
	{
		String input;
		CustomScanner userInputScanner = CustomScanner.getInstance();
	
		ConsoleHelper.printScreenSeparator("Accueil");
		System.out.println("1. Afficher les vehicules");
		System.out.println("2. Ajouter un vehicule");
		System.out.println("3. Modifier un vehicule");
		System.out.println("4. Supprimer un vehicule");
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
				ControlleurPrincipal.fenetrePrincipale();
				break;
			
			case 1 :
				afficherVehicules();
				break;
			
			case 2 :
				ajouterVehicule();
				
				break;
			
			case 3 :
				modifierVehicule();
				break;
				
			case 4 :
				supprimerUnVehicule();
				break;
				
		}
	}
	
	private static void ajouterVehicule()
	{
		
		ConsoleHelper.printScreenSeparator("Accueil > Gestion des véhicules > Ajouter un véhicule");
		
		//Prend l'input de l'utilisateur
		Integer cote = getCoteVehicule();
		String marque = getMarque();
		String modele = getModele();
		Integer moteur = getForceMoteur();
		String transmission = getTransmission();
		Integer nbPlace = getNbPlaces();
		String immatriculation = getImmatriculation();
		boolean estDisponible = getEstDisponible();
		ArrayList<String> accessoires = getAccessoires();
		
		Catalogue c = Catalogue.getInstance();
		
		//Ajoute le véhicule au catalogue
		c.AjouterVehicule(cote, marque, modele, moteur, transmission, nbPlace, immatriculation, 
				estDisponible, accessoires);
		
		c.afficherCatalogue();
		fenetrePrincipale();
		
	}
	
	private static void modifierVehicule()
	{
		ConsoleHelper.printScreenSeparator("Accueil > Gestion des véhicules > Modifier un véhicule");
		
		//Affiche le tableau de véhicules
		afficherListeVehicules();
		
		//Le véhicule choisit par l'utilisateur
		Vehicule vehicule = getVehiculeSelonIdentifiant();
		
		//Si l'utilisateur à annuler l'opération
		if(vehicule == null)
			fenetrePrincipale();
		
		
		//Prend l'input de l'utilisateur
		Integer cote = getCoteVehicule();
		String marque = getMarque();
		String modele = getModele();
		Integer moteur = getForceMoteur();
		String transmission = getTransmission();
		Integer nbPlaces = getNbPlaces();
		String immatriculation = getImmatriculation();
		boolean estDisponible = getEstDisponible();
		ArrayList<String> accessoires = getAccessoires();
		
		Catalogue c = Catalogue.getInstance();
		
		c.modifierVehicule(vehicule, cote, marque, modele, moteur, transmission, nbPlaces, 
				immatriculation, estDisponible, accessoires);
		
		
		Catalogue.getInstance().afficherCatalogue();
		fenetrePrincipale();
	}
	
	private static void supprimerUnVehicule()
	{
		ConsoleHelper.printScreenSeparator("Accueil > Gestion des véhicules > Supprimer un véhicule");
		
		//Affiche le tableau de véhicules
		afficherListeVehicules();
		
		//Le véhicule choisit par l'utilisateur
		Vehicule vehicule = getVehiculeSelonIdentifiant();
		
		//Si l'utilisateur à annuler l'opération
		if(vehicule == null)
			fenetrePrincipale();
		
		//Supprime le véhicule
		Catalogue.getInstance().supprimerVehicule(vehicule);
		
		Catalogue.getInstance().afficherCatalogue();
		fenetrePrincipale();
	}
	
	//Affiche la liste des véhicules
	private static void afficherVehicules()
	{
		afficherListeVehicules();
		userInputScanner.getUserInput("\nAppuyer sur une touche");
		fenetrePrincipale();
	}
	
	//Demande à l'utilisateur la cote du véhicule
	private static Integer getCoteVehicule()
	{
		String coteOuType;				//Input utilisateur
		Integer cote;					//Cote choisie (existante ou nouvelle)
		boolean estUnType = false;		//Si l'utilisateur à entré un nouveau type ou une cote existante
		
		TypeVehicule typeVehicule = TypeVehicule.getInstance();
		
		System.out.println("Type de véhicule (ex. sport, utilitaire,...)");
		ConsoleTableLayout layout = new ConsoleTableLayout(15, 15);
		
		HashMap<Integer, String> coteTypeMap = typeVehicule.getCoteType();
		
		
		//Affiche le tableau des cotes/types de véhicules
		layout.newLine("Cote", "Type");
		for (Map.Entry<Integer, String> entry : coteTypeMap.entrySet()) {
			layout.newLine(entry.getKey().toString(), entry.getValue());
			
		}
		
		coteOuType = userInputScanner.getUserInput("\nEntrez une cote ou un nouveau type");
		
		
		if(InputValidator.stringIsInteger(coteOuType))
		{
			//Vérifie si la cote exite. Si elle n'existe pas, l'utilisateur à entré un nouveau type
			if(typeVehicule.getType(Integer.parseInt(coteOuType)) == null)
			{
				estUnType = true;
			}
			else
			{
				//L'utilisateur a entré une cote
				estUnType = false;
			}
		}
		else
		{
			estUnType = true;
		}
		
		//Si l'utilisateur a entré un nouveau type
		if(estUnType)
		{
			cote = typeVehicule.ajouterType(coteOuType);
		}
		//Si l'utilisateur à entré une cote déjà existante
		else
		{
			cote = Integer.parseInt(coteOuType);
		}
		
		return cote;
	}
	
	//Demande a l'utilisateur la marque du véhicule
	private static String getMarque()
	{
		return userInputScanner.getUserInput("Marque (ex. Honda, Ford,...)) : ");
	}
	
	private static String getModele()
	{
		return userInputScanner.getUserInput("Modèle (ex. Civic, Escape,...)) : ");
	}
	
	//Demande à l'utilisateur la force du moteur
	private static Integer getForceMoteur()
	{
		String forceMoteur;
		do
		{
			forceMoteur = userInputScanner.getUserInput("Force du moteur (nb. de chevaux)) : ");
			
			//Doit être un entier
			if(InputValidator.stringIsInteger(forceMoteur))
			{
				break;
			}
			else
			{
				System.out.println("Choix invalide. La force du moteur doit être un nombre entier");
			}
			
		} while(true);
		
		return Integer.parseInt(forceMoteur);
	}
	
	//Demande à l'utilisateur le type de transmission du véhicule
	private static String getTransmission()
	{
		return userInputScanner.getUserInput("Type de transmission (ex. Automatique, manuelle, dual,...) : ");
	}
	
	//Demande à l'utilisateur le nombre de place maximale du véhicule
	private static Integer getNbPlaces()
	{
		String nbPlaces;
		//Nombre de places
		do
		{
			nbPlaces = userInputScanner.getUserInput("Nomber place(s) : ");
			
			//Doit �tre un entier
			if(InputValidator.stringIsInteger(nbPlaces))
			{
				break;
			}
			else
			{
				System.out.println("Choix invalide. Le nombre de places doit être un entier");
			}
			
		} while(true);
		
		return Integer.parseInt(nbPlaces);
	}
	
	//Demande à l'utilisateur le # d'immatriculation du véhicule
	private static String getImmatriculation()
	{
		return userInputScanner.getUserInput("Immatriculation (ex. AJC 878) : ");
	}
	
	//Demande à l'utilisateur si le véhicule est disponible
	private static boolean getEstDisponible()
	{
		String estDisponible;
		do
		{
			estDisponible = userInputScanner.getUserInput("Est disponible (0 = non, autre entier=oui) : ");
			
			//Doit être un entier
			if(InputValidator.stringIsInteger(estDisponible))
			{
				break;
			}
			else
			{
				System.out.println("Choix invalide. Le nombre doit etre un entier");
			}
			
		} while(true);
		
		if(estDisponible.equals("0"))
		{
			return false;
		}
		
		return true;
		
	}
	
	//Demande a l'utilisateur les accessoires du véhicule
	private static ArrayList<String> getAccessoires()
	{
		String accessoires;				//Input utilisateur
		String accessoirceArray[];		//Input "splitté" selon les virgules
		ArrayList<String> arrayList = new ArrayList<String>();	//Arraylist d'accessoires
		//Accessoires
		accessoires = userInputScanner.getUserInput("Accessoires (séparer par des virgules (ex. AC,Radio,toit ouvrant,etc.)) : ");
				
		accessoirceArray = accessoires.split(",");
		
		for (int i = 0; i < accessoirceArray.length; i++) {
			arrayList.add(accessoirceArray[i]);
		}
		//List<String> accessoiresArray = Arrays.asList(accessoires.split(","));
		
		return (ArrayList<String>) arrayList;
	}
	
	//Demande à l'utilisateur de choisir un véhicule dans la liste en fonction de son identifiant
	private static Vehicule getVehiculeSelonIdentifiant()
	{
		Catalogue catalogue = Catalogue.getInstance();
		String identifiantStr;
		Vehicule vehicule = null;
		do
		{
			identifiantStr = userInputScanner.getUserInput("\nChoisir un identifiant de véhicule (0 pour quitter)");
			
			//L'utilisateur annule l'opération
			if(identifiantStr.equals("0"))
				break;
			
			//Valide si un entier est entré
			if(InputValidator.stringIsInteger(identifiantStr))
			{
				vehicule = catalogue.trouverVehicule(Integer.parseInt(identifiantStr));
				
				if(vehicule != null)
					break;
				
				System.out.println("L'identifiant invalide!");
			}
			else
			{
				System.out.println("L'identifiant du véhicule doit être un entier!");
			}
		
		}while(true);
		
		
		
		return vehicule;
	}
	
	private static void afficherListeVehicules()
	{
		String estDispoSring;
		Catalogue catalogue = Catalogue.getInstance();
		ConsoleTableLayout layout = new ConsoleTableLayout(15, 15, 15, 15, 15, 15, 15, 15, 15, 15);
		
		//En-tête du tableau
		layout.newLine("Identifiant", "Cote", "Marque", "Modele", "Force moteur", 
				"Transmission", "Nb. places", "Immatriculation", "Disponible", "Accessoires");
		
		
		for (Vehicule v : catalogue.getListeVehicules()) {
			if(v.getEstDisponible())
				estDispoSring = "Oui";
			else
				estDispoSring = "Non";
					
			layout.newLine(v.getIdentifiant().toString(),
					v.getCoteClassification().toString(),
					v.getMarque(),
					v.getModele(),
					v.getForceMoteur().toString(),
					v.getTransmission(),
					v.getNbPlaces().toString(),
					v.getImmatriculation(),
					estDispoSring,
					v.getAccessoiresFormate());
			
		}
	}
	
	
	
	
}
