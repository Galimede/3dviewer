package IHM;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javafx.application.Application;
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
		Button translation = new Button("Translation");
		h.getChildren().addAll(zoomMoins,zoomPlus);
		v.getChildren().addAll(rotation,zoom,h,translation);
		root.getChildren().addAll(v,canvas);
		affichage(modele.getFaces());
		
		
		Scene main = new Scene(root);
		primaryStage.setScene(main);
		primaryStage.setTitle("Dolphin");
		primaryStage.setMinHeight(hScreen);
		primaryStage.setWidth(wScreen);
		primaryStage.show();
		
	}
	
	public void affichage (ArrayList<Face> faces){
		gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
		gc.setStroke(Color.BLACK);
		int cpt=1;
		double x=700;
		double y= 400;
		for (Face f : faces) {
			System.out.println("polygon"+ cpt+ "  "+(f.getP1().getX()+x));
			cpt++;
			gc.strokePolygon(	new double[] {f.getP1().getX()+x,f.getP2().getX()+x,f.getP3().getX()+x},
								new double[] {f.getP1().getY()+y,f.getP2().getY()+y,f.getP3().getY()+y},
								3);
		}
		
	}

	@Override
	public void update(Observable o, Object arg) {
		affichage((ArrayList<Face>)arg);
		
	}

}
