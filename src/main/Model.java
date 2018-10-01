package main;

import java.util.ArrayList;

public class Model {
	private ArrayList<Segment> segments=new ArrayList<>();
	private ArrayList<Point> points=new ArrayList<>();
	private ArrayList<Face> faces=new ArrayList<>();
	/**
	 * @param segments
	 * @param points
	 * @param faces
	 */
	public Model(ArrayList<Segment> segments, ArrayList<Point> points, ArrayList<Face> faces) {
		this.segments = segments;
		this.points = points;
		this.faces = faces;
	}
	/**
	 * @return the segments
	 */
	public ArrayList<Segment> getSegments() {
		return segments;
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
	
	
}
