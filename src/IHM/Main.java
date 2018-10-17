package IHM;

import javafx.application.Application;
import javafx.stage.Stage;
import main.Model;
import tools.PlyReader;


public class Main extends Application{
	@Override
	public void start(Stage primaryStage) throws Exception{
		PlyReader pr1=new PlyReader("ressources/dolphin.ply");
		Model modele=new Model(pr1.getPoints(),pr1.getFaces());;
		new Display2D(modele);
	}

	public static void main(String[] args) {
		launch(args);
	}

	

}
