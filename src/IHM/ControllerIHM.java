package IHM;

import java.awt.Dimension;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import main.Face;
import main.Model;
import main.Point;
import modeAvance.View;
import tools.Fonctions;
import tools.PlyReader;
/**
 * 
 * @author Mathieu DEGAND - 11/02/2018
 * Cette classe contr�le l'application FXML et ses diff�rents composants
 */
public class ControllerIHM  implements Observer, Runnable  {

	boolean test=true;

	final FileChooser fileChooser = new FileChooser();
	String cheminModele;
	Model m;
	int last = 0;
	double rapport = 0;
	boolean affichageInitialise=false;
	PlyReader pr1;
	Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	Thread t;

	@FXML
	Button leftArrowTranslation;
	@FXML
	Button rightArrowTranslation;
	@FXML
	Button upArrowTranslation;
	@FXML
	Button ouvrir;
	@FXML
	Button fichier;
	@FXML
	Button zoomMoins;
	@FXML
	Button zoomPlus;
	@FXML
	Button rotationIcone;
	@FXML
	Button rotationH;
	@FXML
	Button rotationB;
	@FXML
	Button rotationG;
	@FXML
	Button rotationD;
	@FXML
	Button rotationO;
	@FXML
	Canvas canvas;
	@FXML
	RadioButton radioDefaut;
	@FXML
	RadioButton radioFace;
	@FXML
	RadioButton radioSegment;
	@FXML
	CheckBox boxRotaAuto;

	GraphicsContext gc;
	boolean rota=false;

	/**
	 * Permet d'ouvrir un modele gr�ce au bouton ouvrir
	 */
	public void openModel(ActionEvent e) {
		File modelePLY = fileChooser.showOpenDialog(new Stage());
		fileChooser.setTitle("Choissisez un modele PLY");
		if(getFileExtension(modelePLY).equals("ply")) {
			cheminModele = modelePLY.toString();
			System.out.println(modelePLY.toString());
			cheminModele = cheminModele.replace("\\", "/");
			System.out.println(cheminModele);
			pr1 = new PlyReader(cheminModele);
			m = new Model(pr1.getPoints(),pr1.getFaces());
			m.addObserver(this);
			afficheCanvas(pr1.getFaces());
			if(last==0) {
				fichier.setText(cheminModele.substring(cheminModele.lastIndexOf("/")+1, cheminModele.lastIndexOf(".")));
				last = 1;
			}
		}
	}

	/**
	 * Permet d'ouvrir l'ancien modele avant le courant 
	 */
	public void openLastModel(ActionEvent e) {
		if(!fichier.getText().equals("")) {
			pr1 = new PlyReader(cheminModele.substring(0, cheminModele.lastIndexOf("/")+1)+fichier.getText()+".ply");
			m.deleteObserver(this);
			m = new Model(pr1.getPoints(),pr1.getFaces());
			m.addObserver(this);
			afficheCanvas(pr1.getFaces());
		}
	}

	// Translation

	/**
	 * Pr�paration de la translation Gauche et translation
	 */
	public void translationG(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		vecteur[0]= -20.0;
		translation(polygon,vecteur);
	}

	/**
	 * Pr�paration de la translation Gauche et translation
	 */
	public void translationD(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		vecteur[0]= 20.0;
		translation(polygon,vecteur);
	}

	/**
	 * Pr�paration de la translation Gauche et translation
	 */
	public void translationH(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		vecteur[1]= -20.0;
		translation(polygon,vecteur);
	}

	/**
	 * Pr�paration de la translation Gauche et translation
	 */
	public void translationB(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		vecteur[1]= 20.0;
		translation(polygon,vecteur);
	}

	/**
	 * Raffiche le modele depuis l'origine
	 */
	public void translationOrigine(ActionEvent e) {
		pr1 = new PlyReader(cheminModele);
		m.deleteObserver(this);
		m = new Model(pr1.getPoints(),pr1.getFaces());
		m.addObserver(this);
		afficheCanvas(pr1.getFaces());
	}

	/**
	 * Methode effecutant la translation
	 */
	public void translation(ArrayList<Face> polygon, double[] vecteur) {
		Face ftmp;
		for(int i=0;i<polygon.size();i++) {
			ftmp=new Face(	new Point(Fonctions.translation3D(polygon.get(i).getP1(),vecteur)),
					new Point(Fonctions.translation3D(polygon.get(i).getP2(),vecteur)),
					new Point(Fonctions.translation3D(polygon.get(i).getP3(),vecteur)));
			polygon.set(i,ftmp);
		}
		m.setFaces(polygon);
	}

	// Homothethie

	/**
	 * pr�paration de l'homothethie <<plus>>
	 */
	public void homothethiePlus(ActionEvent e) {
		rapport = 1.2;
		homothethie();
	}

	/**
	 * pr�paration de l'homothethie <<moins>>
	 */
	public void homothethieMoins(ActionEvent e) {
		rapport = 0.8;
		homothethie();
	}

	/**
	 * Effectue l'homothehtie
	 */
	private void homothethie() {
		ArrayList<Face> polygon= m.getFaces();
		Face ftmp;
		for(int i=0;i<polygon.size();i++) {
			ftmp=new Face(	new Point(Fonctions.homothetie(polygon.get(i).getP1(),rapport)),
					new Point(Fonctions.homothetie(polygon.get(i).getP2(),rapport)),
					new Point(Fonctions.homothetie(polygon.get(i).getP3(),rapport)));
			polygon.set(i,ftmp);
		}
		m.setFaces(polygon);
	}


	// Rotation 

	public void rotationOrigine(ActionEvent e) {

	}

	/**
	 * Effectue la rotation
	 */
	public void rotation(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double x= Math.PI/4.0;
		double y=0.0;
		double z=0.0;
		System.out.println("test");
		if(e.getSource().equals(rotationH)) {
			x= Math.PI/4.0;
		}
		else if(e.getSource().equals(rotationB)) {
			x= -Math.PI/4.0;
		}
		else if(e.getSource().equals(rotationG)) {
			y= Math.PI/4.0;
		}
		else {
			y= -Math.PI/4.0;
		}

		Face ftmp;
		for(int i=0;i<polygon.size();i++) {
			ftmp=new Face(	new Point(Fonctions.rotation(polygon.get(i).getP1(),x,y,z)),
					new Point(Fonctions.rotation(polygon.get(i).getP2(),x,y,z)),
					new Point(Fonctions.rotation(polygon.get(i).getP3(),x,y,z)));
			polygon.set(i,ftmp);
		}
		m.setFaces(polygon);
	}


	/**
	 * Affiche le modele
	 */
	private void afficheCanvas(ArrayList<Face> faces) {
		gc = canvas.getGraphicsContext2D();
		if(!affichageInitialise) {
			gc.setFill(Color.RED);
			gc.setStroke(Color.BLACK);
			affichageInitialise=true;
		}
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
		rota=false;
	}

	// Affichage du modèle sous différentes formes


	public void affichageDefaut(ActionEvent e) {
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		afficheCanvas(m.getFaces());
	}

	public void affichageFace(ActionEvent e) {
		gc.setFill(Color.RED);
		gc.setStroke(Color.TRANSPARENT);
		afficheCanvas(m.getFaces());
	}

	public void affichageSegment(ActionEvent e) {
		gc.setFill(Color.TRANSPARENT);
		gc.setStroke(Color.BLACK);
		afficheCanvas(m.getFaces());
	}

	// Permet l'affichage du mode avancé dans une nouvelle fenetre

	public void rotationAutoActive(ActionEvent e) throws InterruptedException{
			if(test) {
				threadInitialize();
				t.start();
				test=false;
			}
			else {
				t.interrupt();
				test=true;
			}
		
	}
	
	public void rotation() {
		ArrayList<Face> polygon= m.getFaces();
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
		m.setFaces(polygon);
	}
	
	public void ouvreModeAvance() {
		
	 }
	
	@Override
	public void run() {
		while(true) {
			//while(!rotationAutoActive) System.out.println("test");
			if(!rota)
				rotation();
			//model.setFaces(null);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				break;
			}
		}
		
	}
	
	
	private void threadInitialize() {
		t= new Thread(this);
	}

	/*public void ouvreModeAvance () throws InterruptedException{
		if(test) {
			threadInitialize();
			t.start();
			test=false;
		}
		else {
			t.interrupt();
			test=true;
		}
	}*/




	private static String getFileExtension(File file) {
		int dotIndex = file.toString().lastIndexOf('.');
		return (dotIndex == -1) ? "" : file.toString().substring(dotIndex + 1);
	}

	public void update(Observable arg0, Object arg1) {
		ArrayList<Face> arg2 = (ArrayList<Face>)arg1;
		afficheCanvas(arg2);
	}


}
