package Ajedrez.piezas;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;
import Ajedrez.piezas.Pieza;

public class Peon extends Pieza {

	public Peon(Color c, String filename, int x, int y) {
		super(c, filename, "Peon", x, y);
	}
	public boolean movimientoValido(Pieza[][] casillas, int x, int y) {
		int dif_y = this.pos_y-y;
		int dif_x = this.pos_x-x;
		if( dif_x==0 && Math.abs(dif_y)==2 ) {
			if( this.posInicial ) {
				if( dif_y>0 && casillas[x][y+1]==null && casillas[x][y]==null )
					return true;
				else if( casillas[x][y-1]==null && casillas[x][y]==null )
					return true;
			}	
		}
		else if( dif_x==0 && Math.abs(dif_y)==1 ) {
			if( casillas[x][y]==null )
				return true;
		}
		else if( Math.abs(dif_x)==1 && Math.abs(dif_y)==1 ) {
			if( casillas[x][y]!=null && casillas[x][y].color!=this.color )
				return true;
		}
		return false;
	}
}