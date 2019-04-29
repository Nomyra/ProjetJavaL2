
import controler.JeuxManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private JeuxManager jeuxManager;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Scene scene = new Scene(new StackPane());

        jeuxManager = new JeuxManager(scene,primaryStage);
        jeuxManager.showHomeView();

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(943);
        primaryStage.setMinHeight(930);
        primaryStage.show();
    }
    @Override
    public void stop(){
        jeuxManager.exitProcess();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
