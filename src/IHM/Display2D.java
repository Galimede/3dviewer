package IHM;

import java.awt.Toolkit;
import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import main.Face;
import main.Model;
import tools.PlyReader;

public class Display2D extends Application{
	
	private static Model modele;
	



	@Override
	public void start(Stage primaryStage) {
		int cpt=1;
		double hScreen=Toolkit.getDefaultToolkit().getScreenSize().getHeight()-85;
		double wScreen=Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		HBox root = new HBox();
		Canvas canvas = new Canvas();
		canvas.setHeight(hScreen);
		canvas.setWidth(wScreen);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
		double x=700;
		double y= 450;
		for (Face f : modele.getFaces()) {
			System.out.println("polygon"+ cpt+ "  "+(f.getP1().getX()+x));
			cpt++;
			gc.strokePolygon(	new double[] {f.getP1().getX()+x,f.getP2().getX()+x,f.getP3().getX()+x},
								new double[] {f.getP1().getY()+y,f.getP2().getY()+y,f.getP3().getY()+y},
								3);
		}
		
		//VBox v = new VBox();
		//Label tourner = new Label("Tourner");
		//Button b1 = new Button();
		//Label zoom = new Label("Zoom");
		//Button zoomPlus = new Button();
		//Button zoomMoins = new Button();
		//Label translater = new Label("Translater");
		//Button b2 = new Button();
		Scene main = new Scene(root);
		primaryStage.setScene(main);
		primaryStage.setTitle("Dolphin");
		primaryStage.setMinHeight(hScreen);
		primaryStage.setWidth(wScreen);
		primaryStage.show();
		
	}
	
	public static void main(String[] args) throws IOException {
		PlyReader pr1=new PlyReader("ressources/pyramid.ply");
		modele=new Model(pr1.getPoints(),pr1.getFaces());
		Application.launch();
	}

}
