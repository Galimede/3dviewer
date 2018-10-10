package main;

import java.util.ArrayList;

public class Model {
	private ArrayList<Point> points=new ArrayList<>();
	private ArrayList<Face> faces=new ArrayList<>();
	/**
	 * @param segments
	 * @param points
	 * @param faces
	 */
	public Model(ArrayList<Point> points, ArrayList<Face> faces) {
		this.points = points;
		this.faces = faces;
	}
	/**
	 * @return the points
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}
	/**
	 * @return the faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}
	
	public void disp() {
		
	}
}
