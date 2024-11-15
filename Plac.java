package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Plac extends Panel{

	Panel panel;
	int rows;
	int columns;
	Parcela izabrana;
	
	public Plac(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		panel = new Panel(new GridLayout(rows, columns, 3, 3));
		for(int i = 0; i < rows*columns; i++) {
			double random = Math.random()*100;
			if(random > 30) {
				panel.add(new TravnataPovrs());
			}else {
				panel.add(new VodenaPovrs());
			}	
		}
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Parcela prethodna = izabrana;
				if(prethodna != null) {
				prethodna.setFont(new Font("Serif", Font.BOLD, 14));
				}
				izabrana = (Parcela)e.getSource();
				izabrana.setFont(new Font("Serif", Font.BOLD, 20));
				panel.revalidate();
			}
		});
		}

	public int odrediBrVodenihPovrsina(Hidroelektrana h) {
		int pomocni = 0;
		int broj = 0;
		for(int i = 0; i < columns*rows; i++) {
			if(panel.getComponent(i) == h) {
				pomocni = i;
			}
		}
		if((pomocni+1)%columns > 0) {
		if(panel.getComponent(pomocni+1) instanceof VodenaPovrs) {
			broj++;
		}
		}
		if((pomocni+1) > 0 && pomocni%columns > 0) {
		if(panel.getComponent(pomocni-1) instanceof VodenaPovrs) {
			broj++;
		}
		}
		if((pomocni+1-columns) > 0 && (pomocni)%columns > 0) {
		if(panel.getComponent(pomocni-columns-1) instanceof VodenaPovrs) {
			broj++;
		}
		}
		if((pomocni+1-columns) > 0) {
		if(panel.getComponent(pomocni-columns) instanceof VodenaPovrs) {
			broj++;
		}
		}
		if((pomocni+1)%columns > 0 && (pomocni +1- columns) > 0) {
		if(panel.getComponent(pomocni-columns+1) instanceof VodenaPovrs) {
			broj++;
		}
		}
		if((pomocni+1 + columns) < rows*columns && pomocni%columns > 0) {
		if(panel.getComponent(pomocni+columns-1) instanceof VodenaPovrs) {
			broj++;
		}
		}
		if((pomocni +1+ columns) <= rows*columns) {
		if(panel.getComponent(pomocni+columns) instanceof VodenaPovrs) {
			broj++;
		}
		}
		if((pomocni+1 + columns) < rows*columns && (pomocni+1)%columns > 0) {
		if(panel.getComponent(pomocni+columns+1) instanceof VodenaPovrs) {
			broj++;
		}
		}
		return broj;
	}
	
	public void dodaj(Hidroelektrana h) {
		if(izabrana != null) {
			int index = 0;
			for(int i = 0; i < columns*rows; i++) {
				if (izabrana == panel.getComponent(i)) {
					index = i;
				}
			}
			
			panel.remove(panel.getComponent(index));
			panel.add(h, index);
			h.jedinice = odrediBrVodenihPovrsina(h);
			//System.out.println("Okruzujuce vodene povrsine: " + h.jedinice);
			izabrana = null;
			for(int i = 0; i < columns*rows; i++) {
				if(panel.getComponent(i) instanceof Hidroelektrana) {
					Hidroelektrana pom = (Hidroelektrana)(panel.getComponent(i));
					pom.jedinice = odrediBrVodenihPovrsina(pom);
				}
			}
		}

	}
	
}
