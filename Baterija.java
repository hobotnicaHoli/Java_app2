package gui;

public class Baterija {

	private int trenutnaEnergija;
	private int kapacitet;
	
	public Baterija(int kap) {
		kapacitet = trenutnaEnergija = kap;
	}
	
	public void dodaj(int kol) {
		trenutnaEnergija += kol;
		if(trenutnaEnergija > kapacitet) {
			trenutnaEnergija = kapacitet;
		}
	}
	
	public void isprazni() {
		trenutnaEnergija = 0;
	}
	
	public boolean puna() {
		return trenutnaEnergija == kapacitet;
	}
}
