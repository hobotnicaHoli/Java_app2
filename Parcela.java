package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Parcela extends Label {
	char id;
	Color pozadina;

	public Parcela(char id, Color poz) {
		this.id = id;
		pozadina = poz;
		setBackground(poz);
		setText();
		setAlignment(CENTER);
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getParent().dispatchEvent(e);
			}
		});
	}
	
	public void setText() {
		setForeground(Color.WHITE);
		setFont(new Font("Serif", Font.BOLD, 14));
		super.setText(""+id);
	}
	
	public void promeniPozadinu(Color color) {
		setBackground(color);
	}
	
}
