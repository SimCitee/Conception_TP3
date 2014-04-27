import java.util.ArrayList;

import ui.Interface;
import domaine.Catalogue;
import domaine.Client;
import domaine.ConducteurSecondaire;
import domaine.RegistreClient;
import application.ControlleurPrincipal;


public class Program {

	public static void main(String[] args) {

		//Donnees bidon
		Catalogue c = Catalogue.getInstance();
		
		ArrayList<String> a1 = new ArrayList<String>();
		a1.add("AC");
		a1.add("Vitres electrique");
		c.AjouterVehicule(1, "Honda", "Civic", 145, "automatique", 5, "JJJ 777", true, a1);
		
		ArrayList<String> a2 = new ArrayList<String>();
		a2.add("Radio");
		a2.add("Toit ouvrant");
		c.AjouterVehicule(1, "Mazda", "3", 120, "Manuelle", 4, "FFF 123", false, a2);

		ArrayList<ConducteurSecondaire> listeConducteurSec = new ArrayList<ConducteurSecondaire>();
		listeConducteurSec.add(new ConducteurSecondaire("A12341234", "Fernandez", "Roberto"));
	
		// Client
		Client client1 = new Client("1", "Dupre", "Simon", "2021 rue Du Pape", "123-45-67890", "1234123412341234", listeConducteurSec);
		Client client2 = new Client("QWER2222", "Thivierge", "Jonathan", "3182 Cote Rosemont", "819 860 267", "2345234523452345", null);
		
		RegistreClient.getInstance().getListeClients().add(client1);
		RegistreClient.getInstance().getListeClients().add(client2);
		
		//Afficher le menu principal
		Interface.menuPrincipal();

	}
}
