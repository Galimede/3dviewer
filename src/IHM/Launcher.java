package IHM;

import java.awt.Dimension;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author mathieu DEGAND - 11/02/2018
 * Classe chargeant le FXML de l'application et lancant l'application
 */

public class Launcher extends Application {

	public void start(Stage stage) throws IOException {

		//Taille par d�faut de la fen�tre
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		double width = screenSize.getWidth();
		double height = screenSize.getHeight();
		
		//Chargement de l'application fxml
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("app.fxml"));
		Parent root = loader.load();
		
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("IHM");
		stage.centerOnScreen();
		stage.setWidth(width);
		stage.setHeight(height);
		stage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}



