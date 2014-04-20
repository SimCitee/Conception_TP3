package application;

import java.util.ArrayList;
import java.util.Date;

import domaine.Client;
import domaine.Contrat;
import domaine.RegistreClient;
import domaine.RegistreContrat;
import domaine.Vehicule;

public class ControlleurLocation {
	private static RegistreContrat regContrat = RegistreContrat.getInstance();
	
	public static void nouvelleLocation(Client client, Vehicule vehicule, Date dateDebut, Date dateFin) {
		regContrat.faireContrat(client, vehicule, dateDebut, dateFin);
	}
	
	public static void modifierLocation(Contrat contrat, Date dateDebut, Date dateFin) {
		contrat.modifierDates(dateDebut, dateFin);
		contrat.ajouterPaiement(contrat.calculerTotalFacture() + contrat.FRAIS_MODIFICATION, "Modification de contrat", false);
	}
	
	public static void modifierLocation(Contrat contrat, Vehicule vehicule) {
		contrat.modifierVehicule(vehicule);
		contrat.ajouterPaiement(contrat.calculerTotalFacture() + contrat.FRAIS_MODIFICATION, "Modification de contrat", false);
	}
	
	public static void modifierLocation(Contrat contrat, Vehicule vehicule, Date dateDebut, Date dateFin) {
		contrat.modifierVehicule(vehicule);
		contrat.modifierDates(dateDebut, dateFin);
		contrat.ajouterPaiement(contrat.calculerTotalFacture() + contrat.FRAIS_MODIFICATION, "Modification de contrat", false);
	}
	
	public static void terminerLocation(Contrat contrat) {
		contrat.changerStatut();
	}
	
	public static Contrat obtenirContrat(String noContrat) {
		return regContrat.rechercherContrat(noContrat);
	}
	
	public static ArrayList<Contrat> obtenirListeContrats() {
		return regContrat.getListeContrats();
	}
}
