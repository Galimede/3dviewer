package main;

public class Point {
	private double[][]matrice= {{0.0,0.0,0.0,0.0},
								{0.0,0.0,0.0,0.0},
								{0.0,0.0,0.0,0.0},
								{0.0,0.0,0.0,1.0}
	};
	
	@Override
	public String toString() {
		return "Point [x=" + matrice[0][0] + ", y=" + matrice[1][1] + ", z=" + matrice[2][2] + "]";
	}
	/**
	 * @param x Coordonnee en largeur d'un point
	 * @param y	Coordonnee en hauteur d'un point
	 * @param z Coordonnee en profondeur d'un point
	 */
	public Point(double x, double y, double z) {
		matrice[0][1]=x;
		matrice[1][1]=y;
		matrice[2][2]=z;
	}
	/**
	 * @return Renvoie l'ordonnée du point 
	 */
	public double getX() {
		return matrice[0][0];
	}	
	/**
	 * @return Renvoie l'ordonnée du point
	 */
	public double getY() {
		return matrice[1][1];
	}
	/**
	 * @return Renvoie la coordonnée en profondeur du point
	 */
	public double getZ() {
		return matrice[2][2];
	}
	/**
	 * @return la matrice 3D du point correspondant
	 */
	public double[][]getMatrice(){
		return matrice;
	}
	/**
	 * Change les coordonnées du point via sa matrice
	 * @param matrice la nouvelle matrice qui sera associe au point
	 */
	public void setMatrice(double[][]matrice){
		this.matrice=matrice;
	}
}
