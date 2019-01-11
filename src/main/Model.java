package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Observable;

import tools.Fonctions;

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
		Collections.sort(this.faces);
		setChanged();
		notifyObservers(this.faces);
	}
	
	public void setFacesZ(ArrayList<Face> faces) {
		ArrayList<Point> pointsTmp=new ArrayList<Point>();
		for(Face f : faces ) {
			pointsTmp.add(f.getOp1());
			pointsTmp.add(f.getOp2());
			pointsTmp.add(f.getOp3());
		}
		boolean init=false;
		double minX=-1,minY=-1,maxX=-1,maxY=-1;
		for(Point p :pointsTmp) {
			if(!init) {
				minX=p.getX();minY=p.getY();maxY=p.getY();maxY=p.getX();
				init=true;
			}
			if(p.getX()<minX){minX=p.getX();};
			if(p.getX()>maxX){maxX=p.getX();};
			if(p.getY()<minY){minY=p.getY();};
			if(p.getY()>maxY){maxY=p.getY();};
		}
		double []vecteur=new double[3];
		vecteur[0]=-(minX+maxX)/2;vecteur[1]=-(minY+maxY)/2;vecteur[2]=0;
		faces=Fonctions.translation3D(faces, vecteur);
		this.faces = faces;
		Collections.sort(this.faces);
		setChanged();
		notifyObservers(this.faces);
	}
}
