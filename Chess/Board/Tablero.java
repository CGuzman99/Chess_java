package Ajedrez.tablero;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import Ajedrez.piezas.*;

public class Tablero {

	private ArrayList<Pieza> piezasB_perdidas = new ArrayList<>();
	private ArrayList<Pieza> piezasN_perdidas = new ArrayList<>();

	public Pieza[][] casillas = new Pieza[8][8];
	public Color Turno;
	public Pieza pieza_selec;
	public int cor_x;
	public int cor_y;

	public Tablero() {
		this.crearPiezas();
		Turno = Color.WHITE;
	}
	//Crea ls piezas en su respectiva posicion
	private void crearPiezas() {
		for(int x=0; x<8; x++) {
			for(int y=0; y<8; y++) {
				if( x==0 || x==7 ) {
					this.casillas[x][7] = new Torre(Color.WHITE,"TorreB.png",x,7);
					this.casillas[x][0] = new Torre(Color.BLACK,"TorreN.png",x,0);
				}
				else if( x==1 || x==6 ) {
					this.casillas[x][7] = new Caballo(Color.WHITE,"CaballoB.png",x,7);
					this.casillas[x][0] = new Caballo(Color.BLACK,"CaballoN.png",x,0);
				}
				else if( x==2 || x==5 ){
					this.casillas[x][7] = new Alfil(Color.WHITE,"AlfilB.png",x,7);
					this.casillas[x][0] = new Alfil(Color.BLACK,"AlfilN.png",x,0);
				}
				else if( x==3 ){
					this.casillas[x][7] = new Dama(Color.WHITE,"DamaB.png",x,7);
					this.casillas[x][0] = new Dama(Color.BLACK,"DamaN.png",x,0);
				}
				else{
					this.casillas[x][7] = new Rey(Color.WHITE,"ReyB.png",x,7);
					this.casillas[x][0] = new Rey(Color.BLACK,"ReyN.png",x,0);
				}	
			}
			this.casillas[x][6] = new Peon(Color.WHITE,"PeonB.png",x,6);
			this.casillas[x][1] = new Peon(Color.BLACK,"PeonN.png",x,1);
		
		}
	}
	//Determina si una pieza fue seleccinada
	public boolean piezaSeleccionada(int x, int y) {
		if( dentroTablero(x,y) && casillas[x][y]!=null && casillas[x][y].color==Turno ) 
			return true;
		return false;
	}
	//Determina si la posicion (x,y) esta dentro del tablero
	private boolean dentroTablero(int x, int y) {
		if( x>=0 && x<8 && y>=0 && y<8 ) 
			return true;
		return false;
	}
	//Mueve la pieza_selec a la posicion (x,y)
	public void mover(int x, int y) {
		remover(x,y);
		casillas[x][y] = pieza_selec;
		casillas[pieza_selec.pos_x][pieza_selec.pos_y] = null;
		casillas[x][y].setPos(x,y);
		cambiarTurno();
	}
	//Elimina una pieza del tablero
	private void remover(int x, int y) {
		if( casillas[x][y]!=null ) {
			if( Turno==Color.WHITE )
				piezasN_perdidas.add(casillas[x][y]);
			else
				piezasB_perdidas.add(casillas[x][y]);
		}
	}
	//Cambia el turno
	public void cambiarTurno() {
		if( Turno==Color.WHITE )
			Turno = Color.BLACK;
		else
			Turno = Color.WHITE;
		pieza_selec = null;
	}
	//Determina si hay jaque
	public boolean Jaque() {
		Pieza rey = null;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if( casillas[i][j]!=null && casillas[i][j].toString().equals("Rey") && casillas[i][j].color==Turno ) {
					rey = casillas[i][j];
					break;
				}
			}
			if( rey!=null )
				break;
		}
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if( casillas[i][j]!=null && casillas[i][j].color!=Turno ) {
					if( casillas[i][j].movimientoValido(casillas,rey.pos_x,rey.pos_y) )
						return true;
				}
			}
		}
		return false;	
	}
	//Determina si hay jaque mate
	public boolean JaqueMate() {
		boolean jm = false;
		Pieza rey = null;
		if( this.Jaque() ) {
			for(int i=0; i<8; i++) {
				for(int j=0; j<8; j++) {
					if( casillas[i][j]!=null && casillas[i][j].toString().equals("Rey") && casillas[i][j].color==Turno ) {
						rey = casillas[i][j];
						break;
					}
				}
				if( rey!=null )
					break;
			}	
			for(int i=rey.pos_x-1; i<rey.pos_x+2; i++) {
				jm = false;
				for(int j=rey.pos_y-1; j<rey.pos_y+2; j++) {
					if( dentroTablero(i,j) ) {
						for(int x=0; x<8; x++) {
							for(int y=0; y<8; y++) {
								if( casillas[x][y]!=null && casillas[x][y].movimientoValido(casillas,i,j) ) {
									jm = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		return jm;
	}
}