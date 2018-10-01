package main;

public class Point {
	private double x;
	private double y;
	private double z;
	/**
	 * @param x Coordonnee en largeur d'un point
	 * @param y	Coordonnee en hauteur d'un point
	 * @param z Coordonnee en profondeur d'un point
	 */
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	/**
	 * @return Renvoie l'ordonnee du point 
	 */
	public double getX() {
		return x;
	}	
	/**
	 * @return Renvoie l'ordonnée du point
	 */
	public double getY() {
		return y;
	}
	/**
	 * @return Renvoie la coordonnée en profondeur du point
	 */
	public double getZ() {
		return z;
	}
}
