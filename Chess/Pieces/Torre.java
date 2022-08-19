package Ajedrez.piezas;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;
import Ajedrez.piezas.Pieza;

public class Torre extends Pieza {

	public Torre(Color c, String filename, int x, int y) {
		super(c, filename, "Torre", x, y);
	}
	public boolean movimientoValido(Pieza[][] casillas, int x, int y) {
		int dif_x = this.pos_x-x;
		int dif_y = this.pos_y-y;
		if( this.pos_x!=x && this.pos_y!=y )
			return false;
		else if( this.pos_x==x ) {
			if( dif_y<=0 ) {
				for (int j=pos_y; j<y; j++) {
					if( casillas[x][j]!=null )
						return false;
				}
			}
			else {
				for(int j=pos_y; j>y; j--) {
					if( casillas[x][j]!=null )
						return false;
				}
			}
		}
		else if( this.pos_y==y ) {
			if( dif_x<=0 ) {
				for (int i=pos_x; i<x; i++) {
					if( casillas[i][y]!=null )
						return false;
				}
			}
			else {
				for(int i=pos_x; i>x; i--) {
					if( casillas[i][y]!=null )
						return false;
				}
			}
		}
		else if( casillas[x][y]!=null && casillas[x][y].color==this.color )
			return false;
		return true;
	}
}