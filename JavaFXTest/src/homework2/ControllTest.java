package homework2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllTest extends Application{



	@Override
	public void start(Stage primaryStage) throws Exception {
		 Parent root = FXMLLoader.load(getClass().getResource("ControllTest.fxml"));
	      Scene scene = new Scene(root);
	      primaryStage.setTitle("Controll객체연습");
	      primaryStage.setScene(scene);
	      primaryStage.show();		
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
