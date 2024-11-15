package gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class EnergetskiSistem extends Frame {

	Baterija baterija;
	Plac plac;
	Panel upper = new Panel();
	Button dodaj = new Button("Dodaj");
	public EnergetskiSistem(int rows, int columns, int kap) {
		setBounds(700, 200, 500, 500);
		plac = new Plac(rows, columns);
		baterija = new Baterija(kap);
		upper.add(dodaj);
		add(plac.panel, BorderLayout.CENTER);
		add(upper, BorderLayout.NORTH);
		setResizable(false);
		dodaj.addActionListener((ae)->{
			Hidroelektrana h = new Hidroelektrana(baterija);
			h.postaviBrVodenihPovrsina(6);
			plac.dodaj(h);
			
			revalidate();
		});
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				for(int i = 0; i < plac.columns*plac.rows; i++) {
					if(plac.panel.getComponent(i) instanceof Hidroelektrana) {
						Hidroelektrana h = (Hidroelektrana)(plac.panel.getComponent(i));
						h.thread.interrupt();
					}
				}
			}
		});
		
		setVisible(true);
		
	}
	
	public static void main(String[] args) {
		new EnergetskiSistem(5, 5, 120);
	}
}
