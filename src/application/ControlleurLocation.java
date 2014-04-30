package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import service.DateUtils;
import service.InputValidator;
import ui.Interface;

import domaine.Client;
import domaine.Contrat;
import domaine.Location;
import domaine.RegistreClient;
import domaine.RegistreContrat;
import domaine.Vehicule;

public class ControlleurLocation {
	private static RegistreContrat regContrat = RegistreContrat.getInstance();
	
	private static void nouvelleLocation(Client client, Vehicule vehicule, Date dateDebut, Date dateFin) {
		regContrat.faireContrat(client, vehicule, dateDebut, dateFin);
	}
	
	private static void modifierLocation(Contrat contrat, Date dateDebut, Date dateFin) {
		contrat.modifierDates(dateDebut, dateFin);
		contrat.calculerDifferenceTotal(contrat.FRAIS_MODIFICATION);
	}
	
	private static void modifierLocation(Contrat contrat, Vehicule vehicule) {
		contrat.modifierVehicule(vehicule);
		contrat.calculerDifferenceTotal(contrat.FRAIS_MODIFICATION);
	}
	
	private static void modifierLocation(Contrat contrat, Vehicule vehicule, Date dateDebut, Date dateFin) {
		contrat.modifierVehicule(vehicule);
		contrat.modifierDates(dateDebut, dateFin);
		contrat.calculerDifferenceTotal(contrat.FRAIS_MODIFICATION);
	}
	
	private static void terminerLocation(Contrat contrat, int kilometrage, Date dateRemise) {
		contrat.mettreFinContrat(kilometrage, dateRemise);
		contrat.calculerFraisSupplementaire();
	}
	
	public static Contrat obtenirContrat(String noContrat) {
		return regContrat.rechercherContrat(noContrat);
	}
	
	public static ArrayList<Contrat> obtenirListeContrats() {
		return regContrat.getListeContrats();
	}
	
	/*
	 * Saisie du numero de contrat et recherche celui ci
	 */
	private static Contrat obtenirContratParId() {
		String input = "";
		Contrat c = null;
		do {
			System.out.print("Veuillez entrer le numero du contrat (entrer 0 pour annuler): ");
			input = Interface.lecture();
			
			if (InputValidator.validerEntier(input))
				c = obtenirContrat(input);
		} while (c == null && !input.equalsIgnoreCase("0"));
		
		return c;
	}
	
	/*
	 * Effectue la saisie du contrat et termine celui ci
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	public static void saisirFinLocation() {
		boolean kiloValide = false;
		Contrat contrat = obtenirContratParId();
		int kiloRemise = 0;
		
		if (contrat != null) {	
			
			do {
				kiloRemise = obtenirKilometrage();
				int kiloDepart = ((Location)contrat.getEtat()).getKilometrageDepart();
				kiloValide = kiloRemise <= kiloDepart;
				
				if (!kiloValide)
					System.out.println("Veuillez entrer un kilometrage superieur au kilometrage de depart ("+ kiloDepart +")");
				
			} while (kiloValide == false);
			Date dateRemise = obtenirDate("Veuillez entrer la date de remise: ");
			
			ControlleurLocation.terminerLocation(contrat, kiloRemise, dateRemise);
			
			System.out.print("Succes de la terminaison du contrat. Appuyez sur une touche pour retourner au menu.");
			Interface.lecture();
		}
		
		Interface.afficherGestionLocations();
	}
	
	/*
	 * Effectue la saisie d'une date
	 * Parametre: texte informatif a afficher
	 * Valeur de retour: la date saisie
	 */
	private static Date obtenirDate(String info) {
		boolean dateValide = false;
		String input = "";
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			
		do {
			System.out.print(info + " (MM/JJ/AAAA HH:mm:ss)");
			input = Interface.lecture();
			dateValide = DateUtils.isThisDateValid(input);
			
			if(!dateValide)
				System.out.println("Date invalide. Veuillez entrer une date dans le format specifier.");
			
		} while (dateValide == false);
		
		Date dt = null;
		
		try {
			dt = format.parse(input);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dt;
	}
	
	/*
	 * Effectue la saisie des differents informations necessaire pour la modification et fait appel au controlleur
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	public static void saisirChangementLocation() {
		String choixUtilisateur;
		boolean succesModif = false;
		
		Contrat modifContrat = obtenirContratParId();
		
		if (modifContrat != null) {
			do
			{
				System.out.println("Veuillez choisir une des options suivantes:");
				System.out.println("1. Modifier le vehicule");
				System.out.println("2. Modifier les dates");
				System.out.println("3. Modifier le vehicule et les dates");
				System.out.println("0. Retour a la gestion des location");
				
				choixUtilisateur = Interface.lecture();
				
				//Le choix est valide, on sort de la validation
			    if (InputValidator.validerIntervalleEntier(choixUtilisateur, 0, 3)) 
			    	break;
			    
			    Interface.clearConsole();
			} while (true);
			 
			switch(choixUtilisateur)
			{
			
				case "0" :
					Interface.afficherGestionLocations();
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
					Interface.clearConsole();
					Interface.afficherGestionLocations();
			}
			
		}
		
		if(succesModif) {
			System.out.print("Succes de la modification. Appuyez sur une touche pour retourner au menu.");
			Interface.lecture();
		}
		
		Interface.afficherGestionLocations();
	}
	
	/*
	 * Effectue la saisie des differents informations necessaire et fait appel au controlleur
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	public static void afficherLocations() {
		
		ArrayList<Contrat> listeContrats =  ControlleurLocation.obtenirListeContrats();
		
		for (Contrat c : listeContrats) {
			System.out.println(c.toString());
		}
		
		System.out.print("Appuyer sur une touche pour retourner au menu.");
		Interface.lecture(); 
		Interface.clearConsole();
		Interface.afficherGestionLocations();
	}
	
	/*
	 * Effectue la saisie des differents informations necessaire et fait appel au controlleur
	 * Parametre: aucun
	 * Valeur de retour: aucune
	 */
	public static void saisirLocation() {
		Client signataire;
		Vehicule vehiculeLocation;
		Date dateDebut;
		Date dateFin;
		boolean dateFinValide = true;
		
		signataire = ControlleurClient.obtenirClientParId();
		vehiculeLocation = ControlleurVehicules.getVehiculeSelonIdentifiant();
		dateDebut = obtenirDate("Veuillez entrer une date de debut de location");
		
		// valider la date de fin de location
		do {
			dateFin = obtenirDate("Veuillez entrer une date de fin de location");
			
			dateFinValide = (!DateUtils.isAfterDay(dateFin, dateDebut)) ? false : true;
			
			// erreur si la date de fin n'est pas superieur a la date de debut
			if (!dateFinValide)
				System.out.println("Veuillez entrer une date superieur a celle du debut de la location.");
				
		} while (!dateFinValide);
		ControlleurLocation.nouvelleLocation(signataire, vehiculeLocation, dateDebut, dateFin);
		
		System.out.println("Location ajoutee. Appuyez sur une touche pour retourner au menu.");
		Interface.lecture();
		Interface.afficherGestionLocations();
	}
	
	/*
	 * Saisie du kilometrage du vehicule a la fin du contrat
	 */
	private static int obtenirKilometrage() {
		String input = "";
		do {
			System.out.print("Veuillez entrer le kilometrage du vehicule remis: ");
			input = Interface.lecture();
		} while (!InputValidator.validerEntier(input));
		
		return Integer.parseInt(input);
	}
}
