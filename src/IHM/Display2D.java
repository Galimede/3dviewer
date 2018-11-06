package IHM;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Face;
import main.Model;
import main.Point;
import tools.Fonctions;
public class Display2D implements Observer{
	

	GraphicsContext gc;
	Button translationH;
	Button translationB;
	Button translationG;
	Button translationD;
	Button rotation;
	Button zoomPlus;
	Button zoomMoins;
	
	public Display2D(Model modele) {
		modele.addObserver(this);
		Stage primaryStage = new Stage();
		double hScreen=Toolkit.getDefaultToolkit().getScreenSize().getHeight()-85;
		double wScreen=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		HBox root = new HBox();
		root.setPrefSize(wScreen, hScreen);
		Canvas canvas = new Canvas();
		canvas.setHeight(hScreen);
		canvas.setWidth(wScreen-200);
		gc = canvas.getGraphicsContext2D();
		VBox v = new VBox();
		v.setPrefSize(200, 200);
		rotation = new Button("Rotation");
		HBox h= new HBox();
		Label zoom = new Label("Zoom");
		zoomPlus = new Button("+");
		zoomMoins = new Button("-");
		translationH = new Button("Translation haut");
		translationB = new Button("Translation bas");
		translationG = new Button("Translation gauche");
		translationD = new Button("Translation droite");
		translationH.setOnMousePressed(e-> translation(e,modele));
		translationB.setOnMousePressed(e-> translation(e,modele));
		translationG.setOnMousePressed(e-> translation(e,modele));
		translationD.setOnMousePressed(e-> translation(e,modele));
		rotation.setOnMousePressed(e-> rotation(e,modele));
		zoomPlus.setOnMousePressed(e-> zoom(e,modele));
		zoomMoins.setOnMousePressed(e-> zoom(e,modele));
		h.getChildren().addAll(zoomMoins,zoomPlus);
		v.getChildren().addAll(rotation,zoom,h,translationH,translationB,translationG,translationD);
		root.getChildren().addAll(v,canvas);
		affichage(modele.getFaces());
		
		
		Scene main = new Scene(root);
		primaryStage.setScene(main);
		primaryStage.setTitle("Modelisation");
		primaryStage.setMinHeight(hScreen);
		primaryStage.setWidth(wScreen);
		primaryStage.show();
		
	}
	
	private void zoom(MouseEvent e, Model m) {
		ArrayList<Face> polygon= m.getFaces();
		double rapport=0.0;
		if(e.getSource().equals(zoomPlus)) {
			rapport=1.2;
		}
		else {
			rapport=0.8;
		}
		Face ftmp;
		for(int i=0;i<polygon.size();i++) {
			ftmp=new Face(	new Point(Fonctions.homothetie(polygon.get(i).getP1(),rapport)),
							new Point(Fonctions.homothetie(polygon.get(i).getP2(),rapport)),
							new Point(Fonctions.homothetie(polygon.get(i).getP3(),rapport)));
			polygon.set(i,ftmp);
		}
		m.setFaces(polygon);
	}

	private void rotation(MouseEvent e, Model m) {
		ArrayList<Face> polygon= m.getFaces();
		double x=0.0;
		double y=0.0;
		double z=0.0;
		if(e.getSource().equals(rotation)) {
			x= Math.PI/4.0;
		}
		else if(e.getSource().equals(translationG)) {

		}
		else if(e.getSource().equals(translationH)) {
	
		}
		else {
		
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

	private void translation(MouseEvent e, Model m) {
		ArrayList<Face> polygon= m.getFaces();
		double []vecteur= {0.0,0.0,0.0};
		if(e.getSource().equals(translationD)) {
			vecteur[0]= 20.0;
		}
		else if(e.getSource().equals(translationG)) {
			vecteur[0]= -20.0;
		}
		else if(e.getSource().equals(translationH)) {
			vecteur[1]= -20.0;
		}
		else {
			vecteur[1]= 20.0;
		}
		Face ftmp;
		for(int i=0;i<polygon.size();i++) {
			ftmp=new Face(	new Point(Fonctions.translation3D(polygon.get(i).getP1(),vecteur)),
							new Point(Fonctions.translation3D(polygon.get(i).getP2(),vecteur)),
							new Point(Fonctions.translation3D(polygon.get(i).getP3(),vecteur)));
			polygon.set(i,ftmp);
		}
		m.setFaces(polygon);
	}

	public void affichage (ArrayList<Face> faces){
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.setFill(Color.RED);
		int cpt=1;
		double x=700.0;
		double y= 400.0;
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

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable o, Object arg) {
		ArrayList<Face> arg2 = (ArrayList<Face>)arg;
		affichage(arg2);
		
	}

}

