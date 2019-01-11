package IHM;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.Face;
import main.Model;
import main.Point;
import tools.Fonctions;
import tools.PlyReader;
/**
 * 
 * @author Mathieu DEGAND Guillaume DUBOIS - 11/02/2018
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
	boolean affichageSegment =false;
	double xSouris;
	double ySouris;
	/**
	 * Permet d'ouvrir un fichier ply et d'initialiser le modele
	 * 
	 * @param e
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
		canvas.setOnMouseDragged(r->controleSouris(r));
		canvas.setOnScroll(s->controleSouris(s));
		xSouris=canvas.getWidth()/2;
		ySouris=canvas.getHeight()/2;
	}

	private void controleSouris(ScrollEvent s) {
		if(s.getDeltaY()>0) {
			rapport=1.1;
			homothethie();
		}
		else {
			rapport=0.9;
			homothethie();
		}
		
	}

	private void controleSouris(MouseEvent e) {

		double []vecteur= {0.0,0.0,0.0};
		if(e.isPrimaryButtonDown()) {
			ArrayList<Face> polygon= m.getFaces();
			vecteur[0]=e.getX()-xSouris;
			vecteur[1]=e.getY()-ySouris;
			translation(polygon,vecteur);
			xSouris=e.getX();
			ySouris=e.getY();
		}
		else {
			vecteur[0]=(e.getX()-xSouris)*0.02;
			vecteur[1]=(e.getY()-ySouris)*0.02;
			rotation(vecteur[0],vecteur[1]);
			xSouris=e.getX();
			ySouris=e.getY();
		}
	}

	/**
	 * Permet d'ouvrir l'ancien modele avant le courant 
	 * 
	 * @param e
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
	 * Preparation de la translation Gauche et translation
	 * 
	 * @param e
	 */
	public void translationG(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		vecteur[0]= -20.0;
		translation(polygon,vecteur);
	}

	/**
	 * Preparation de la translation Gauche et translation
	 * 
	 * @param e
	 */
	public void translationD(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		vecteur[0]= 20.0;
		translation(polygon,vecteur);
	}

	/**
	 * Preparation de la translation Gauche et translation
	 * 
	 * @param e
	 */
	public void translationH(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		vecteur[1]= -20.0;
		translation(polygon,vecteur);
	}

	/**
	 * Preparation de la translation Gauche et translation
	 * 
	 * @param e
	 */
	public void translationB(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		vecteur[1]= 20.0;
		translation(polygon,vecteur);
	}

	/**
	 * Raffiche le modele depuis l'origine
	 * 
	 * @param e
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
	 * 
	 * @param polygon
	 * @param vecteur
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
	 * controlleur de l'homothethie <<plus>>
	 * 
	 * @param e
	 */
	public void homothethiePlus(ActionEvent e) {
		rapport = 1.2;
		homothethie();
	}

	/**
	 * controlleur de l'homothethie <<moins>>
	 * 
	 * @param e
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
		m.setFacesZ(polygon);
	}


	// Rotation 

	public void rotationOrigine(ActionEvent e) {

	}

	/**
	 * Effectue la rotation
	 * 
	 * @param e
	 */
	public void rotation(ActionEvent e) {
		ArrayList<Face> polygon= m.getFaces();
		double x=0.0;
		double y=0.0;
		double z=0.0;
		if(e.getSource().equals(rotationH)) {
			x= Math.PI/8.0;
		}
		else if(e.getSource().equals(rotationB)) {
			System.out.println("test");
			x= -Math.PI/8.0;
		}
		else if(e.getSource().equals(rotationG)) {
			y= Math.PI/8.0;
		}
		else {
			y= -Math.PI/8.0;
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
		double x=canvas.getWidth()/2;
		double y=canvas.getHeight()/2;
		//gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		for (Face f : faces) {
			if(cpt==1) {
				System.out.println("polygon"+ cpt+" "+f.getOp1().toString());
			}
			cpt++;
			gc.strokePolygon(new double[] {f.getOp1().getX()+x+this.perspective(f.getOp1().getX(), x, f.getOp1().getZ()),
										   f.getOp2().getX()+x+this.perspective(f.getOp2().getX(), x, f.getOp2().getZ()),
										   f.getOp3().getX()+x+this.perspective(f.getOp3().getX(), x, f.getOp3().getZ())},
							 new double[] {f.getOp1().getY()+y+this.perspective(f.getOp1().getY(), y, f.getOp1().getZ()),
									 	   f.getOp2().getY()+y+this.perspective(f.getOp2().getY(), y, f.getOp2().getZ()),
									 	   f.getOp3().getY()+y+this.perspective(f.getOp3().getY(), y, f.getOp3().getZ())},
							3);
			gc.fillPolygon(new double[] {f.getOp1().getX()+x+this.perspective(f.getOp1().getX(), x, f.getOp1().getZ()),
										 f.getOp2().getX()+x+this.perspective(f.getOp2().getX(), x, f.getOp2().getZ()),
										 f.getOp3().getX()+x+this.perspective(f.getOp3().getX(), x, f.getOp3().getZ())},
						   new double[] {f.getOp1().getY()+y+this.perspective(f.getOp1().getY(), y, f.getOp1().getZ()),
								   		 f.getOp2().getY()+y+this.perspective(f.getOp2().getY(), y, f.getOp2().getZ()),
								   		 f.getOp3().getY()+y+this.perspective(f.getOp3().getY(), y, f.getOp3().getZ())},
						   	3);
		}
		ombre(faces);
		rota=false;
	}
	
	private void ombre(ArrayList<Face> faces) {
		double x=canvas.getWidth()/2;
		double y=canvas.getHeight()/2;
		if(!affichageSegment)
			gc.setFill(Color.BLACK);
		int ombre=400;
		Face max = Collections.max(faces, new FaceComparator());
		System.out.println(max.getOp1().getY());
		for (Face f : faces) {
			if(testOmbre(max.getOp1().getY()+y,800)) {
				gc.strokePolygon(new double[] {f.getOp1().getX()+x+this.perspective(f.getOp1().getX(), x, f.getOp1().getZ()),
						f.getOp2().getX()+x+this.perspective(f.getOp2().getX(), x, f.getOp2().getZ()),
						f.getOp3().getX()+x+this.perspective(f.getOp3().getX(), x, f.getOp3().getZ())},
						new double[] {y+ombre+this.perspective(f.getOp1().getY()+y+ombre, y, f.getOp1().getZ()),
								y+ombre+this.perspective(f.getOp2().getY()+y+ombre, y, f.getOp2().getZ()),
								y+ombre+this.perspective(f.getOp3().getY()+y+ombre, y, f.getOp3().getZ())},
						3);
				if(!affichageSegment)
					gc.fillPolygon(new double[] {f.getOp1().getX()+x+this.perspective(f.getOp1().getX(), x, f.getOp1().getZ()),
							f.getOp2().getX()+x+this.perspective(f.getOp2().getX(), x, f.getOp2().getZ()),
							f.getOp3().getX()+x+this.perspective(f.getOp3().getX(), x, f.getOp3().getZ())},
							new double[] {y+ombre+this.perspective(f.getOp1().getY()+y+ombre, y, f.getOp1().getZ()),
									y+ombre+this.perspective(f.getOp2().getY()+y+ombre, y, f.getOp2().getZ()),
									y+ombre+this.perspective(f.getOp3().getY()+y+ombre, y, f.getOp3().getZ())},
							3);
			}
		}

		if(!affichageSegment)
			gc.setFill(Color.RED);

	}
	
	
	private double perspective(double d, double center, double z) {
        return  0.6 * (center -d) * (z * -0.0005);
    }
	
	private boolean testOmbre(double d, int i ) {
		return d<i;
	}


	/**
	 * permet l'affichage par defaut du modele
	 * @param e
	 */
	public void affichageDefaut(ActionEvent e) {
		affichageSegment=false;
		gc.setFill(Color.RED);
		gc.setStroke(Color.BLACK);
		afficheCanvas(m.getFaces());

	}
	
	/**
	 * permet d'afficher seulement les faces du modele
	 * @param e
	 */
	public void affichageFace(ActionEvent e) {
		affichageSegment=false;
		gc.setFill(Color.RED);
		gc.setStroke(Color.TRANSPARENT);
		afficheCanvas(m.getFaces());
	
	}
	
	/**
	 * permet d'afficher seulement les segments du modele
	 * @param e
	 */
	public void affichageSegment(ActionEvent e) {
		affichageSegment=true;
		gc.setFill(Color.TRANSPARENT);
		gc.setStroke(Color.BLACK);
		afficheCanvas(m.getFaces());
	}

	/**
	 * controlleur de la rotation automatique
	 * 
	 * @param e
	 * @throws InterruptedException
	 */
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
	
	private void rotation(double d1, double d2) {
		ArrayList<Face> polygon= m.getFaces();
		double x=d1;
		double y=d2;
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
	

	
	/**
	 * methode du thread de la rotation automatique
	 */
	@Override
	public void run() {
		while(true) {
			if(!rota)
				rotation(Math.PI/8.0,0.0);
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


	private static String getFileExtension(File file) {
		int dotIndex = file.toString().lastIndexOf('.');
		return (dotIndex == -1) ? "" : file.toString().substring(dotIndex + 1);
	}

	/**
	 * update automatique du mvc
	 * 
	 */
	public void update(Observable arg0, Object arg1) {
		ArrayList<Face> arg2 = (ArrayList<Face>)arg1;
		afficheCanvas(arg2);
	}
	
	private class FaceComparator implements Comparator<Face>{

		@Override
		public int compare(Face o1, Face o2) {
			return (int) (o1.getOp1().getY()-o2.getOp1().getY());
		}
		
	}


}
