package IHM;

import java.awt.Toolkit;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import main.Model;
import main.Segment;

public class Display2D extends Application{
	
	private Model modele;
	private ArrayList<Line> lignes=new ArrayList<>();
	



	@Override
	public void start(Stage primaryStage) throws Exception {
		//for (Segment s : modele.getSegments()) {
		//	lignes.add(new Line(s.getP1().getX(), s.getP1().getY(), s.getP2().getX(), s.getP2().getY()));
		//}
		Group root = new Group();
		//root.getChildren().addAll(lignes);
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
		primaryStage.setMinHeight(Toolkit.getDefaultToolkit().getScreenSize().getHeight()-85);
		primaryStage.setWidth(Toolkit.getDefaultToolkit().getScreenSize().getWidth());
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		
		Application.launch();
	}

}
