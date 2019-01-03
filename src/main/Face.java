package main;
public class Face {
	private Point p1;
	private Point p2;
	private Point p3;
	@Override
	public String toString() {
		return "Face [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]";
	}
	/**
	 * Contruit une face triangulaire a partir de trois point
	 * @param p1 Premier point de la face
	 * @param p2 Deuxieme point de la face
	 * @param p3 Troisieme point de la face
	 */
	public Face(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	/**
	 * Calcule la matrice du premier point de taille 4x1
	 * @return la matrice du premier point
	 */
	public double[][] getP1() {
		double[][]point=new double[4][1];
		point[0][0]=p1.getX();point[1][0]=p1.getY();point[2][0]=p1.getZ();point[3][0]=1.0;	
		return point;
	}
	/**
	 * Recupere l'objet correspondant au premier point
	 * @return Le premier point
	 */
	public Point getOp1() {
		return p1;
	}

	
	/**
	 * Calcule la matrice du deuxieme point de taille 4x1
	 * @return la matrice du deuxieme point
	 */
	public double[][] getP2() {
		double[][]point=new double[4][1];
		point[0][0]=p2.getX();point[1][0]=p2.getY();point[2][0]=p2.getZ();point[3][0]=1.0;	
		return point;
	}
	/**
	 * Recupere l'objet correspondant au deuxieme point
	 * @return Le deuxieme point
	 */
	public Point getOp2() {
		return p2;
	}

	/**
	 * Calcule la matrice du troisieme point de taille 4x1
	 * @return la matrice du troisieme point
	 */
	public double[][] getP3() {
		double[][]point=new double[4][1];
		point[0][0]=p3.getX();point[1][0]=p3.getY();point[2][0]=p3.getZ();point[3][0]=1.0;	
		return point;
	}
	/**
	 * Recupere l'objet correspondant au troisieme point
	 * @return Le troisieme point
	 */
	public Point getOp3() {
		return p3;
	}

}
