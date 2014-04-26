package application;

import ui.Interface;
import domaine.Client;
import domaine.RegistreClient;

public class ControlleurClient {
	
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
}
