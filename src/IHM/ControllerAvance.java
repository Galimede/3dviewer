package IHM;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.CheckBox;
import javafx.scene.paint.Color;
import main.Face;
import main.Model;
import main.Point;
import modeAvance.View;
import tools.Fonctions;

/**
 * 
 * @author Mathieu DEGAND - 12/16/2018
 * Classe controleur (Partie du design pattern MVC) pour l'affichage dit avance
 */
public class ControllerAvance implements Runnable {

	private static boolean rotationAutoActive=false;
	/*private Thread thread;
	private Model model;*/
	private View view;
	@FXML
	private CheckBox boxRotaAuto;
	@FXML
	public Canvas canvasAvance; 

	public ControllerAvance(View v) {
		view=v;
		//cb.setOnAction(e->rotationActive(e));
	}
	
	public void rotationActive(ActionEvent e) {
		//rotationAutoActive=true;
	}

	@Override
	public void run() {
		while(true) {
			while(!rotationAutoActive) System.out.println("test");
			view.rotation();
			//model.setFaces(null);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/*public void rotation() {
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
	}
	
	public void addView(View view) {
		this.view=view;
	}

	public void affichage(ArrayList<Face> arg2) {
		view.gc = canvas.getGraphicsContext2D();
		
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

	}*/
		
	}


