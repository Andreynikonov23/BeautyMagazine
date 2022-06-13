package nick.pack;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/magazine.fxml"));
        stage.setTitle("Салон красоты");
        Image image = new Image("/logo/beauty_logo.ico");
        stage.getIcons().add(image);
        Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(getClass().getResource("/view/css/authentication.css").toExternalForm());
        System.out.println(scene.getStylesheets());
        stage.setScene(scene);
        stage.show();
    }
}