package domaine;

import java.util.ArrayList;

public class RegistreClient {
	private static RegistreClient _instance;
	private static ArrayList<Client> listeClients;
	
	protected RegistreClient() {
		listeClients = new ArrayList<Client>();
	}
	
	public static RegistreClient getInstance() {
		if (_instance == null) {
			_instance = new RegistreClient();
		}
		
		return _instance;
	}

	public ArrayList<Client> getListeContrats() {
		return listeClients;
	}

	public void setListeContrats(ArrayList<Client> listeClients) {
		this.listeClients = listeClients;
	}
	
	public static Client rechercherClient(String noPermisConduire) {
		for(Client c : listeClients)
			if (c.getNoPermisConduire().equalsIgnoreCase(noPermisConduire))
				return c;
		
		return null;
	}
}
