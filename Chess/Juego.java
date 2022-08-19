package Ajedrez;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Color;
import Ajedrez.tablero.*;
import Ajedrez.piezas.*;

public class Juego extends JFrame implements Runnable {

	Thread thread;
	PanelTablero pt;

	public Juego() {
		thread = new Thread(this);
		pt = new PanelTablero();
	}

	public static void main(String[] args) {
		Juego juego = new Juego();
		JFrame frame = new JFrame("Ajedrez en Java");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(juego.pt);
		frame.setSize(600,600);
		frame.setVisible(true);
		juego.thread.start();
		juego.run();
		JOptionPane.showMessageDialog(frame,"Jaque Mate");
		juego.thread.interrupt();
	}

	public void run() {
		while( !pt.tablero.JaqueMate() );
	}
}