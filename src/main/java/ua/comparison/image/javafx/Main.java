package ua.comparison.image.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Integer count=0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Image Comparison.");
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);

        Pane root = new Pane();
        Button button = new Button();
        button.setText("Show the difference");
        button.setTranslateX(250);
        button.setTranslateY(250);
        button.setPrefSize(150, 20);
        button.setOnAction(event -> {
            button.setText("You Clicked "+count+" times");
            count++;
        });
        Scene scene = new Scene(root);

        root.getChildren().addAll(button);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
