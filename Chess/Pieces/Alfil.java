package Ajedrez.piezas;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;
import Ajedrez.piezas.Pieza;

public class Alfil extends Pieza {

	public Alfil(Color c, String filename, int x, int y) {
			super(c, filename, "Alfil", x, y);
	}
	public boolean movimientoValido(Pieza[][] casillas, int x, int y) {
		int dif_x = this.pos_x-x;
		int dif_y = this.pos_y-y;
		if( Math.abs(dif_x)!=Math.abs(dif_y) )
			return false;
		else if( dif_x<=0 ) {
			for(int i=pos_x; i<x; i++) {
				if( dif_y<0 ) {
					for(int j=pos_y; j<y; j++) {
						if( Math.abs(pos_x-i)==Math.abs(pos_y-j) && casillas[i][j]!=null )
							return false;
					}
				}
				else {
					for(int j=pos_y; j>y; j--) {
						if( Math.abs(pos_x-i)==Math.abs(pos_y-j) && casillas[i][j]!=null )
							return false;
					}
				}
			}
		}
		else if( dif_x>0 ) {
			for(int i=pos_x; i>x; i--) {
				if( dif_y<0 ) {
					for(int j=pos_y; j<y; j++) {
						if( Math.abs(pos_x-i)==Math.abs(pos_y-j) && casillas[i][j]!=null )
							return false;
					}
				}
				else {
					for(int j=pos_y; j>y; j--) {
						if( Math.abs(pos_x-i)==Math.abs(pos_y-j) && casillas[i][j]!=null )
							return false;
					}
				}
			}
		}
		else if( casillas[x][y]!=null && casillas[x][y].color==this.color )
			return false;
		return true;
	}
}