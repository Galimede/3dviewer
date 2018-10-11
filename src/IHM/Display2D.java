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
		for (Face f : modele.getFaces()) {
			lignes.add(new Line(f.getP1().getX()+CENT4,f.getP1().getY()+CENT4,f.getP2().getX()+CENT4,f.getP2().getY()+CENT4));
			lignes.add(new Line(f.getP1().getX()+CENT4,f.getP1().getY()+CENT4,f.getP3().getX()+CENT4,f.getP3().getY()+CENT4));
			lignes.add(new Line(f.getP3().getX()+CENT4,f.getP3().getY()+CENT4,f.getP2().getX()+CENT4,f.getP2().getY()+CENT4));
		}
		Group root = new Group();
		root.getChildren().addAll(lignes);
		VBox v = new VBox();
		Label tourner = new Label("Tourner");
		Button b1 = new Button();
		Label zoom = new Label("Zoom");
		Button zoomPlus = new Button();
		Button zoomMoins = new Button();
		Label translater = new Label("Translater");
		Button b2 = new Button();
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
