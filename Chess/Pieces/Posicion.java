package Ajedrez.piezas;

public class Posicion {

	public int x;
	public int y;

	public Posicion(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Posicion p) {
		if( x==p.x && y==p.y )
			return true;
		return false;
	}
}