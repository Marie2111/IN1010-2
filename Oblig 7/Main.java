import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage teater) throws Exception {
        Pane kulisser = FXMLLoader.load(getClass().getResource("Labyrint.fxml"));
        Scene scene = new Scene(kulisser);

        teater.setTitle("Labyrint - IN1010");
        teater.setScene(scene);
        teater.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
