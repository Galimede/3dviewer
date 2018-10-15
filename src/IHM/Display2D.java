package IHM;

import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import main.Face;
import main.Model;
import tools.PlyReader;

public class Display2D extends Application{
	
	private static Model modele;
	private ArrayList<Line> lignes=new ArrayList<>();
	



	@Override
	public void start(Stage primaryStage) throws Exception {
		final int CENT4 =100*5;
		try {
		for (Face f : modele.getFaces()) {
			double xp1 = f.getP1().getX();
			double xp2 = f.getP2().getX();
			double xp3 = f.getP3().getX();
			double yp1 = f.getP1().getY();
			double yp2 = f.getP2().getY();
			double yp3 = f.getP3().getY();
			lignes.add(new Line(xp1+CENT4,yp1+CENT4,xp2+CENT4,yp2+CENT4));
			lignes.add(new Line(xp1+CENT4,yp1+CENT4,xp3+CENT4,yp3+CENT4));
			lignes.add(new Line(xp3+CENT4,yp3+CENT4,xp2+CENT4,yp2+CENT4));
		}
		}catch (Exception e) {
			System.out.println("l'erreur est :" + e.getMessage());
		}
		Group root = new Group();
		root.getChildren().addAll(lignes);
		/*VBox v = new VBox();
		Label tourner = new Label("Tourner");
		Button b1 = new Button();
		Label zoom = new Label("Zoom");
		Button zoomPlus = new Button();
		Button zoomMoins = new Button();
		Label translater = new Label("Translater");
		Button b2 = new Button();*/
		Scene main = new Scene(root);
		primaryStage.setScene(main);
		primaryStage.setTitle("Dolphin");
		primaryStage.setMinHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-85);
		primaryStage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		primaryStage.show();
		
	}
	
	public static void main(String[] args) throws IOException {
		PlyReader pr1=new PlyReader("ressources/dolphin.ply");
		modele=new Model(pr1.getPoints(),pr1.getFaces());
		Application.launch();
	}

}
