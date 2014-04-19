package domaine;

public class Location extends Etat {
	private int kilometrageDepart;
	private int kilometrageArrivee;

	public Location(int kilometrage) {
		this.kilometrageDepart = kilometrage;
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
}
