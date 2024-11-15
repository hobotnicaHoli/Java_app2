package gui;

import java.awt.Color;

public class Proizvodjac extends Parcela implements Runnable {

	int vreme;
	Baterija baterija;
	Thread thread;
	int jedinice;
	
	public Proizvodjac(char c, Color col, int vreme, Baterija baterija) {
		super(c, col);
		this.vreme = vreme;
		this.baterija = baterija;
		if(thread != null) {
			thread.interrupt();
		}
		thread = new Thread(this);
		//thread.start();
	}
	public int ukupnoVreme() {
		int uk = vreme + (int)(Math.random()*300);
		return uk;
	}
	@Override
	public void run() {
		try {
		while(!Thread.interrupted()) {
			
			Thread.sleep(ukupnoVreme());
			baterija.dodaj(jedinice);
			if(jedinice > 0) {
				setForeground(Color.RED);
				Thread.sleep(300);
			}
			else {
				Thread.sleep(300);
			}
			setForeground(Color.WHITE);
			//System.out.println("Okruzujuce vodene povrsine: " + jedinice);
			
		}
		}catch(InterruptedException e) {}
	}
	public synchronized void finish() {
		if(thread != null) {
			thread.interrupt();
		}
		while(thread != null) {
			try {
				wait();
			}catch(InterruptedException e) {}
		}
	}
}
