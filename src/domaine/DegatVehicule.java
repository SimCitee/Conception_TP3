package domaine;

public class DegatVehicule {

	private static final String types[] = {"mineur", "moyen", "lourd", "perte"};	// types de degats
	private static final int montanReparations[] = {200, 100, 2500, 5000};	// montant pre-etablie des reparations selon le type de degats
	private int categorieDegat;
	private String details;
	
	public DegatVehicule(int categorieDegat, String details) {
		this.categorieDegat = categorieDegat;
		this.details = details;
	}

	public int getCategorieDegat() {
		return categorieDegat;
	}

	public void setCategorieDegat(int categorieDegat) {
		this.categorieDegat = categorieDegat;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getDegat() {
		if (categorieDegat < types.length) {
			return types[getCategorieDegat()];
		}
		
		return "";
	}
	
	public int getMontantDegat() {
		if (categorieDegat < montanReparations.length) {
			return montanReparations[getCategorieDegat()];
		}
		
		return -1;
	}
}
