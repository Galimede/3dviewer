package main;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable{
	private ArrayList<Point> points=new ArrayList<>();
	private ArrayList<Face> faces=new ArrayList<>();
	/**
	 * Construit un model a partir d'une liste de faces et de point
	 * @param points
	 * @param faces
	 */
	public Model(ArrayList<Point> points, ArrayList<Face> faces) {
		this.points = points;
		this.faces = faces;
	}
	/**
	 * Renvoie la liste des Points du model
	 * @return une ArrayList de points
	 */
	public ArrayList<Point> getPoints() {
		return points;
	}
	/**
	 * Renvoie la liste des faces du model
	 * @return une ArrayList de faces
	 */
	public ArrayList<Face> getFaces() {
		return faces;
	}
	/**
	 * Attribue une liste de points au model
	 * @param points La liste des points a attribuer au model
	 */
	public void setPoints(ArrayList<Point> points) {
		this.points = points;
	}
	/**
	 * Attribue une liste de faces au model
	 * @param faces La liste de faces a attribuer au model
	 */
	public void setFaces(ArrayList<Face> faces) {
		this.faces = faces;
		setChanged();
		notifyObservers(this.faces);
	}
}
