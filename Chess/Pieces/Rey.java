package Ajedrez.piezas;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;
import Ajedrez.piezas.Pieza;

public class Rey extends Pieza {

	public Rey(Color c, String filename, int x, int y) {
		super(c, filename, "Rey", x, y);
	}
	public boolean movimientoValido(Pieza[][] casillas, int x, int y) {
		int dif_x = this.pos_x-x;
		int dif_y = this.pos_y-y;
		if( Math.abs(dif_x)!=1 && Math.abs(dif_y)!=1 )
			return false;
		else if( casillas[x][y]!=null && casillas[x][y].color==this.color )
			return false;
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if( casillas[i][j]!=null && casillas[i][j].color!=this.color ) {
					if( casillas[i][j].movimientoValido(casillas,x,y) )
						return false;
				}
			}
		}
		return true;
	}
}