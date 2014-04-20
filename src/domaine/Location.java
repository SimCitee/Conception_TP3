package domaine;

public class Location extends Etat {
	private int kilometrageDepart;
	private int kilometrageArrivee;

	public Location(int kilometrage) {
		this.kilometrageDepart = kilometrage;
	}
	
	/*
	 * Retourne le kilometrage effectuer entre le debut et la fin de la location
	 * Parametre: aucun
	 * Valeur de retour: intervalle
	 */
	public int getIntervalleKilometrage() {
		return kilometrageArrivee - kilometrageDepart;
	}
	
	
	//Getters & setters
	public int getKilometrageDepart() {
		return kilometrageDepart;
	}

	public void setKilometrageDepart(int kilometrageDepart) {
		this.kilometrageDepart = kilometrageDepart;
	}

	public int getKilometrageArrivee() {
		return kilometrageArrivee;
	}

	public void setKilometrageArrivee(int kilometrageArrivee) {
		this.kilometrageArrivee = kilometrageArrivee;
	}
	
	@Override
	public String toString() {
		return "Location [" +
				"kilometrageDepart=" + this.kilometrageDepart + ", " +
				"kilometrageArrivee=" + this.kilometrageArrivee +
				"]";
	}
}
