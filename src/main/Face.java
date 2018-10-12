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
	public Point getP1() {
		return p1;
	}
	/**
	 * @return the p2
	 */
	public Point getP2() {
		return p2;
	}
	/**
	 * @return the p3
	 */
	public Point getP3() {
		return p3;
	}

}
