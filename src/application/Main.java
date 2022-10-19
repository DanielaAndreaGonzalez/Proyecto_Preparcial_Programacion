package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Universidad");
		mostrarVentanaPrincipal();
	}
	
//	@Override
//	public void start(Stage primaryStage) {
//		try {
//			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("views/Principal.fxml"));
//			Scene scene = new Scene(root,500,500);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public void mostrarVentanaPrincipal()
	{
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/Principal.fxml"));
			AnchorPane rootLayout = (AnchorPane) loader.load();
			
			PrincipalViewController principalController = loader.getController();
			principalController.setAplicacion(this);
			
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
