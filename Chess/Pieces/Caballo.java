package Ajedrez.piezas;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.util.ArrayList;
import Ajedrez.piezas.Pieza;

public class Caballo extends Pieza {

	public Caballo(Color c, String filename, int x, int y) {
		super(c, filename, "Caballo", x, y);
	}
	public boolean movimientoValido(Pieza[][] casillas, int x, int y) {
		int dif_x = this.pos_x-x;
		int dif_y = this.pos_y-y;
		if( ( Math.pow(dif_x,2)+Math.pow(dif_y,2) )!=5 )
			return false;
		else if( casillas[x][y]!=null && casillas[x][y].color==this.color )
			return false;
		return true;
	}
}