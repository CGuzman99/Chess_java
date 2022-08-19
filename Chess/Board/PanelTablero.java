package Ajedrez.tablero;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import Ajedrez.tablero.Tablero;
import Ajedrez.piezas.*;

public class PanelTablero extends JPanel implements MouseListener {

	private static final int inicio_tab_x = 100;
	private static final int inicio_tab_y = 100;
	private static final int alto_tab = 400;
	private static final int ancho_tab = 400;

	private String s="";
	public Tablero tablero;

	public PanelTablero() {
		super();
		super.setSize(600,600);
		tablero = new Tablero();
		this.addMouseListener(this);
	}
	//Imprime los lementos de la ventana usando Graphics2D
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("Georgia",Font.BOLD,13));
		//ImageIcon img = new ImageIcon("tablero.png");
		Pieza p;
		g2d.setColor(Color.BLACK);
		g2d.drawString(s,20,20);
		//Imprime el turno
		if( tablero.Turno==Color.WHITE )
			g2d.drawString("Turno: piezas blancas",225,50);
		else
			g2d.drawString("Turno: piezas negras",225,50);			
		//g2d.drawImage(img.getImage(),inicio_tab_x,inicio_tab_y,null);
		//Dibuja las casillas y las etiquetas de las filas y las columnas
		for(int i=0; i<8; i++) {
			g2d.setColor(Color.BLACK);
			g2d.drawString(String.valueOf(i+1),75,(475-(i*50)));
			g2d.drawString(String.valueOf((char) (i+65)),(120+(i*50)),525);
			for(int j=0; j<8; j++) {
				g2d.setColor(Color.DARK_GRAY);
				if( ((i+j)%2)==0 )
					g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillRect(inicio_tab_x+(i*50),inicio_tab_y+(j*50),50,50);
			}
		}
		//Imprime las piezas
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				p = tablero.casillas[i][j];
				if( tablero.casillas[i][j]!=null )
					g.drawImage(p.getImage(),inicio_tab_x+(p.pos_x*50),inicio_tab_y+(p.pos_y*50),null);
			}
		}
	}
	//Lee los eventos del mouse
	@Override
	public void mousePressed(MouseEvent ev) {
		int x = ev.getPoint().x;
		int y = ev.getPoint().y;
		x = (x-inicio_tab_x-(x%50))/50;
		y = (y-inicio_tab_y-(y%50))/50;
		if( tablero.piezaSeleccionada(x,y) ) {
			tablero.pieza_selec = tablero.casillas[x][y];
			tablero.cor_x = x;
			tablero.cor_y = y;
		}
	}
	@Override
	public void mouseReleased(MouseEvent ev) {
		int x = ev.getPoint().x;
		int y = ev.getPoint().y;
		x = (x-(x%50)-inicio_tab_x)/50;
		y = (y-(y%50)-inicio_tab_y)/50;
		if( tablero.Jaque() ) {
			if( tablero.pieza_selec!=null && tablero.pieza_selec.toString().equals("Rey") ) {
				if( tablero.pieza_selec.movimientoValido(tablero.casillas,x,y) ) {
					tablero.mover(x,y);
					this.repaint();
				}
			}
		}
		else {
			if( tablero.pieza_selec!=null ) {
				if( tablero.pieza_selec.movimientoValido(tablero.casillas,x,y) ) {
					tablero.mover(x,y);
					this.repaint();
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent ev) {
		int x = ev.getPoint().x;
		int y = ev.getPoint().y;
		x = (x-(x%50)-inicio_tab_x)/50;
		y = (y-(y%50)-inicio_tab_y)/50;
		if( tablero.piezaSeleccionada(x,y) ) {
			tablero.pieza_selec = tablero.casillas[x][y];
			tablero.cor_x = x;
			tablero.cor_y = y;
		}
		else {
			if( tablero.Jaque() ) {
				if( tablero.pieza_selec.toString().equals("Rey") && tablero.pieza_selec.movimientoValido(tablero.casillas,x,y) ) {
					tablero.mover(x,y);
					this.repaint();
				}
			}
			else {
				if( tablero.pieza_selec!=null && tablero.pieza_selec.movimientoValido(tablero.casillas,x,y) ) {
					tablero.mover(x,y);
					this.repaint();
				}
			}
		}
	}
	@Override
	public void mouseEntered(MouseEvent ev) {}
	@Override
	public void mouseExited(MouseEvent ev) {}
}