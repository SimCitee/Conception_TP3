package application;

import domaine.Client;
import domaine.RegistreClient;

public class ControlleurClient {
	
	private static RegistreClient regClient = RegistreClient.getInstance();
	
	public static Client obtenirClient(String noPermisConduire) {
		return regClient.rechercherClient(noPermisConduire);
	}
}
