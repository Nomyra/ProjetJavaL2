
import controler.JeuxManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(new StackPane());

        JeuxManager jeuxManager = new JeuxManager(scene,primaryStage);
        jeuxManager.showHomeView();

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(943);
        primaryStage.setMinHeight(930);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
