package application;

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
		contrat.ajouterPaiement(contrat.calculerTotalFacture(), "Modification de contrat", false);
	}
	
	public static void modifierLocation(Contrat contrat, Vehicule vehicule) {
		contrat.modifierVehicule(vehicule);
		contrat.ajouterPaiement(contrat.calculerTotalFacture(), "Modification de contrat", false);
	}
	
	public static void modifierLocation(Contrat contrat, Vehicule vehicule, Date dateDebut, Date dateFin) {
		contrat.modifierVehicule(vehicule);
		contrat.ajouterPaiement(contrat.calculerTotalFacture(), "Modification de contrat", false);
	}
}
