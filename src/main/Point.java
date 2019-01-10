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
	 * Construit un point a partir de trois coordonnes
	 * @param x Coordonnee en largeur d'un point
	 * @param y	Coordonnee en hauteur d'un point
	 * @param z Coordonnee en profondeur d'un point
	 */
	public Point(double x, double y, double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
	/**
	 * Contruit un point a partir d'une matrice de taille 3x1 contenant les coordonnes de ce dernier
	 * @param point Matrice 3x1 avec les coordonnees du point
	 */
	public Point (double[][]point) {
		x=point[0][0];
		y=point[1][0];
		z=point[2][0];
	}
	/**
	 * Renvoie la coordonnee en X du point
	 * @return l'abcisse du point
	 */
	public double getX() {
		return this.x;
	}	
	/**
	 * @param x the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}
	/**
	 * @param z the z to set
	 */
	public void setZ(double z) {
		this.z = z;
	}
	/**
	 * Renvoie la coordonnee en Y du point
	 * @return l'ordonnee du point
	 */
	public double getY() {
		return this.y;
	}
	/**
	 * Renvoie la coordonnee en Z du point
	 * @return la profondeur du point
	 */
	public double getZ() {
		return this.z;
	}

}
