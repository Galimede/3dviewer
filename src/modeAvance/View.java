package modeAvance;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import IHM.ControllerAvance;
import javafx.application.Application;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import main.Face;
import main.Model;
import main.Point;
import tools.Fonctions;

public class View implements Observer{
	
	Model model;
	//private static boolean rotationAutoActive=false;
	private Thread thread;
	
	GraphicsContext gc;
	
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	ControllerAvance ca;

	
	public View(Model model) {
		this.model=model;
		model.addObserver(this);
		ca= new ControllerAvance(this);
		thread= new Thread(ca);
		thread.start();
		/*this.controller=controller;
		controller.addView(this);*/
	}

	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Face> arg2 = (ArrayList<Face>)arg;
		affichage(arg2);
		
	}
	
	public void rotation() {
		ArrayList<Face> polygon= model.getFaces();
		double x= Math.PI/4.0;
		double y=0.0;
		double z=0.0;
		Face ftmp;
		for(int i=0;i<polygon.size();i++) {
			ftmp=new Face(	new Point(Fonctions.rotation(polygon.get(i).getP1(),x,y,z)),
					new Point(Fonctions.rotation(polygon.get(i).getP2(),x,y,z)),
					new Point(Fonctions.rotation(polygon.get(i).getP3(),x,y,z)));
			polygon.set(i,ftmp);
		}
		model.setFaces(polygon);
	}
	

	public void affichage(ArrayList<Face> faces) {
		gc = ca.canvasAvance.getGraphicsContext2D();
		
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		int cpt=1;
		double x=screenSize.getWidth()/2;
		double y=screenSize.getHeight()/2;
		//gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for (Face f : faces) {
			if(cpt==1) {
				System.out.println("polygon"+ cpt+" "+f.getOp1().toString());
			}
			cpt++;
			gc.strokePolygon(	new double[] {f.getOp1().getX()+x,f.getOp2().getX()+x,f.getOp3().getX()+x},
					new double[] {f.getOp1().getY()+y,f.getOp2().getY()+y,f.getOp3().getY()+y},
					3);
			gc.fillPolygon(	new double[] {f.getOp1().getX()+x,f.getOp2().getX()+x,f.getOp3().getX()+x},
					new double[] {f.getOp1().getY()+y,f.getOp2().getY()+y,f.getOp3().getY()+y},
					3);
		}

	}
}
