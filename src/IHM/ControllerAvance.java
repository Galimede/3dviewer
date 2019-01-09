package IHM;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
	private Thread thread;
	private Model model;
	private View view;


	@Override
	public void run() {
		rotation();
		model.setFaces(null);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void rotation() {
		
	}
	
	public void addView(View view) {
		this.view=view;
	}

	

	
	
	
}
