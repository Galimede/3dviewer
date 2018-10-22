package IHM;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import main.Face;
import main.Model;
import tools.Math;
public class Display2D implements Observer{
	

	GraphicsContext gc;


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
		Button rotation = new Button("Rotation");
		HBox h= new HBox();
		Label zoom = new Label("Zoom");
		Button zoomPlus = new Button("+");
		Button zoomMoins = new Button("-");
		Button translationH = new Button("Translation haut");
		Button translationB = new Button("Translation bas");
		Button translationG = new Button("Translation gauche");
		Button translationD = new Button("Translation droite");
		translationH.setOnAction(e-> translation(e,modele));
		h.getChildren().addAll(zoomMoins,zoomPlus);
		v.getChildren().addAll(rotation,zoom,h,translationH,translationB,translationG,translationD);
		root.getChildren().addAll(v,canvas);
		affichage(modele.getFaces());
		
		
		Scene main = new Scene(root);
		primaryStage.setScene(main);
		primaryStage.setTitle("Dolphin");
		primaryStage.setMinHeight(hScreen);
		primaryStage.setWidth(wScreen);
		primaryStage.show();
		
	}
	
	private void translation(ActionEvent e, Model m) {
		ArrayList<Face> polygon= m.getFaces();
		double[]vecteur= {100.0,0.0,0.0};
		for(Face f: polygon) {
			f.getP1().setMatrice(Math.translation3D(f.getP1().getMatrice(),vecteur));
			f.getP2().setMatrice(Math.translation3D(f.getP2().getMatrice(),vecteur));
			f.getP3().setMatrice(Math.translation3D(f.getP3().getMatrice(),vecteur));
		}
		m.setFaces(polygon);
		affichage(m.getFaces());
	}

	public void affichage (ArrayList<Face> faces){
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.setStroke(Color.BLACK);
		gc.setFill(Color.RED);
		int cpt=1;
		double x=700;
		double y= 400;
		for (Face f : faces) {
			System.out.println("polygon"+ cpt+ "  "+(f.getP1().getX()+x));
			cpt++;
			gc.strokePolygon(	new double[] {f.getP1().getX()+x,f.getP2().getX()+x,f.getP3().getX()+x},
								new double[] {f.getP1().getY()+y,f.getP2().getY()+y,f.getP3().getY()+y},
								3);
			gc.fillPolygon(	new double[] {f.getP1().getX()+x,f.getP2().getX()+x,f.getP3().getX()+x},
					new double[] {f.getP1().getY()+y,f.getP2().getY()+y,f.getP3().getY()+y},
					3);
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		affichage((ArrayList<Face>)arg);
		
	}

}
