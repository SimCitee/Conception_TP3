package domaine;

import java.util.ArrayList;

//Singleton
public class RegistreContrat {
	private RegistreContrat _instance;
	private ArrayList<Contrat> listeContrats;
	
	private RegistreContrat() {
		listeContrats = new ArrayList<Contrat>();
	}
	
	public RegistreContrat getInstance() {
		if (_instance == null) {
			this._instance = new RegistreContrat();
		}
		
		return _instance;
	}

	public ArrayList<Contrat> getListeContrats() {
		return listeContrats;
	}

	public void setListeContrats(ArrayList<Contrat> listeContrats) {
		this.listeContrats = listeContrats;
	}
}
