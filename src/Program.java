import java.util.ArrayList;

import ui.Interface;

import domaine.Catalogue;
import domaine.Client;
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
		
		// Client
		Client client1 = new Client("1", "Dupre", "Simon");
		Client client2 = new Client("QWER2222", "Thivierge", "Jonathan");
		
		RegistreClient.getInstance().getListeContrats().add(client1);
		RegistreClient.getInstance().getListeContrats().add(client2);
		
		//Afficher le menu principal
		Interface.menuPrincipal();

	}

}
