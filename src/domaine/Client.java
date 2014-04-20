package domaine;

public class Client {

	private String noPermisConduire;
	private String nom;
	private String prenom;

	public Client(String noPermisConduire, String nom, String prenom) {
		this.noPermisConduire = noPermisConduire;
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNoPermisConduire() {
		return noPermisConduire;
	}

	public void setNoPermisConduire(String noPermisConduire) {
		this.noPermisConduire = noPermisConduire;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getNomComplet() {
		return this.prenom + " " + this.nom;
	}
	
}
