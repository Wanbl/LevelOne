package mainApp;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import manager.SceneManager;
import map.MapType;

/**
 * The Launcher class is the main entry point for the Level One game application.
 * It extends the JavaFX Application class and sets up the initial stage and scene.
 */
public class Launcher extends Application {
    private SceneManager sceneManager;

    /**
     * The main method launches the JavaFX application.
     * 
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start method is called when the application is launched.
     * It sets up the primary stage and the initial scene with a start button.
     * 
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Level One");
        sceneManager = new SceneManager(primaryStage);

        StackPane root = new StackPane();
        Button startButton = new Button("Start");
        startButton.setOnAction(e -> {
            try {
                System.out.println("Launcher : start() : Changing scene to DESERT");
                sceneManager.changeScene(MapType.DESERT);
            } catch (Exception e1) {
                System.out.println("Launcher : start() : Exception occurred");
                e1.printStackTrace();
            }
        });

        root.getChildren().add(startButton);
        Scene scene = new Scene(root, 300, 300);

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
