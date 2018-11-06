package main;

import javafx.scene.paint.Color;

public class Face {
	private Point p1;
	private Point p2;
	private Point p3;
	//pour l'affichage des faces plus tard
	private Color couleurFace;
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Face [p1=" + p1 + ", p2=" + p2 + ", p3=" + p3 + "]";
	}
	public Face(Point p1, Point p2, Point p3) {
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
	}
	/**
	 * @return the p1
	 */
	public double[][] getP1() {
		double[][]point=new double[4][1];
		point[0][0]=p1.getX();point[1][0]=p1.getY();point[2][0]=p1.getZ();point[3][0]=1.0;	
		return point;
	}
	public Point getOp1() {
		return p1;
	}
	public Point getOp2() {
		return p2;
	}
	public Point getOp3() {
		return p3;
	}
	/**
	 * @return the p2
	 */
	public double[][] getP2() {
		double[][]point=new double[4][1];
		point[0][0]=p2.getX();point[1][0]=p2.getY();point[2][0]=p2.getZ();point[3][0]=1.0;	
		return point;
	}
	/**
	 * @return the p3
	 */
	public double[][] getP3() {
		double[][]point=new double[4][1];
		point[0][0]=p3.getX();point[1][0]=p3.getY();point[2][0]=p3.getZ();point[3][0]=1.0;	
		return point;
	}
	

}
