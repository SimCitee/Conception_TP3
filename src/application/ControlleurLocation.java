package application;

import java.util.Date;

import domaine.Client;
import domaine.RegistreClient;
import domaine.RegistreContrat;
import domaine.Vehicule;

public class ControlleurLocation {
	
	private static RegistreClient regClient = RegistreClient.getInstance();
	
	public static void nouvelleLocation(Client client, Vehicule vehicule, Date dateDebut, Date dateFin) {
		
	}
	
	public static Client obtenirClient(String noPermisConduire) {
		return regClient.rechercherClient(noPermisConduire);
	}
}
