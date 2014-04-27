package domaine;

import java.util.ArrayList;

import service.ConsoleHelper;

public class RegistreClient {
	
	private static RegistreClient _instance;
	private static ArrayList<Client> listeClients = null;
	
	protected RegistreClient() {
		listeClients = new ArrayList<Client>();
	}
	
	public static RegistreClient getInstance() {
		if (_instance == null) {
			_instance = new RegistreClient();
		}
		
		return _instance;
	}

	public ArrayList<Client> getListeClients() {
		return listeClients;
	}

	public void setListeClients(ArrayList<Client> listeClients) {
		this.listeClients = listeClients;
	}
	
	public Client trouverClient(Integer identifiant) {
		for(Client c : listeClients)
		{
			if(c.getIdentifiant() == identifiant)
				return c;
		}
		
		return null;
	}
	
	public static Client rechercherClient(String noPermisConduire) {
		for(Client c : listeClients)
			if (c.getNoPermisConduire().equalsIgnoreCase(noPermisConduire))
				return c;
		
		return null;
	}

	public void ajouterClient(String noPermis, String nom, String prenom,
			String adresse, String noTel, String noCarteBancaire, ArrayList<ConducteurSecondaire> listeConducteurSec) {
		listeClients.add(new Client(noPermis, nom, prenom, adresse, noTel, noCarteBancaire, listeConducteurSec));
	}

	public void supprimerClient(Client client) {
		listeClients.remove(client);
	}

	public void modifierClient(Client c, String noPermis, String nom, String prenom,
			String adresse, String noTel, String noCarteBancaire,
			ArrayList<ConducteurSecondaire> listeConducteurSec) {
		
		c.setNoPermisConduire(noPermis);
		c.setNom(nom);
		c.setPrenom(prenom);
		c.setAdresse(adresse);
		c.setNoTel(noTel);
		c.setNoCarteBancaire(noCarteBancaire);
		c.setListeConducteurSec(listeConducteurSec);
	}
	
}
