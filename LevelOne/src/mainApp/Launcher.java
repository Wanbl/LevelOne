package mainApp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import manager.SceneManager;
import map.MapType;

public class Launcher extends Application {
	private SceneManager sceneManager;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Level One");
		sceneManager = new SceneManager(primaryStage);
		
		StackPane root = new StackPane();
		Button startButton = new Button("Start");
		startButton.setOnAction(e -> {
			try {
				sceneManager.changeScene(MapType.DESERT);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
        });
		
		root.getChildren().add(startButton);
		Scene scene = new Scene(root, 300, 300);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
