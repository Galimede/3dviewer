package main;

public class Point {
	private double x;
	private double y;
	private double z;
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + ", z=" + z+ "]";
	}
	/**
	 * @param x Coordonnee en largeur d'un point
	 * @param y	Coordonnee en hauteur d'un point
	 * @param z Coordonnee en profondeur d'un point
	 */
	public Point(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	public Point (double[][]point) {
		x=point[0][0];
		y=point[1][0];
		z=point[2][0];
	}
	/**
	 * @return Renvoie l'ordonnée du point 
	 */
	public double getX() {
		return this.x;
	}	
	/**
	 * @return Renvoie l'ordonnée du point
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * @return Renvoie la coordonnée en profondeur du point
	 */
	public double getZ() {
		return this.z;
	}

}
