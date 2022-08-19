package Ajedrez.piezas;

import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Color;
import java.util.ArrayList;
import Ajedrez.piezas.Posicion;

public abstract class Pieza extends ImageIcon {

	private String str;
	public int pos_x;
	public int pos_y;
	public Color color;
	protected boolean posInicial;

	public Pieza(Color c, String filename, String s, int x, int y) {
		//Llama al constructor de ImageIcon 
		//filename es el nombre con el que esta guardada la imagen
		super(filename);
		pos_x = x;
		pos_y = y;
		color = c;
		posInicial = true;
		str = s;
	}
	//Cambia la posicion de la pieza a (x,y)
	public void setPos(int x, int y) {
		pos_x = x;
		pos_y = y;
		posInicial = false;
	}
	public String toString() {
		return str;
	}
	//Metodo abstracto que determina si la pieza se puede mover a la posicion (x,y)
	public abstract boolean movimientoValido(Pieza[][] casillas, int x, int y);
}