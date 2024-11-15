package gui;

import java.awt.Color;
import java.awt.Panel;

public class Hidroelektrana extends Proizvodjac implements Runnable{
	int broj;
	Thread thread;

	public Hidroelektrana(Baterija baterija) {
		super('H', Color.BLUE, 1500, baterija);
		broj = 0;
		if(thread != null) {
			thread.interrupt();
		}
		thread = new Thread(this);
		thread.start();
	}

	public void postaviBrVodenihPovrsina(int k) {
		broj = k;
	}
	
	@Override
	public void run() {
		//super.jedinice = odrediBrVodenihPovrsina(this);
		super.run();
	}
	
	

}
