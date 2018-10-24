package main;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
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
	/**
	 * @param points the points to set
	 */
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	/**
	 * @param faces the faces to set
	 */
	public void setFaces(ArrayList<Face> faces) {
		this.faces = faces;
		System.out.println(""+faces.get(0).getP1().toString());
		setChanged();
		notifyObservers(this.faces);
	}
	
	
}
