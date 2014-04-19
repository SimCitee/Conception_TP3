package domaine;

public class Paiement {
	private static int compteurPaiement = 0;
	private int noPaiement;
	private double montant;
	private String details;
	private boolean estAcquitte;
	
	public Paiement(double montant, String details, boolean acquitte) {
		this.noPaiement = ++compteurPaiement;
		this.montant = montant;
		this.details = details;
		this.estAcquitte = acquitte;
	}

	public int getNoPaiement() {
		return noPaiement;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public boolean isEstAcquitte() {
		return estAcquitte;
	}

	public void setEstAcquitte(boolean estAcquitte) {
		this.estAcquitte = estAcquitte;
	}
	
	
}
