
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.Modele;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        //Modele m = new Modele();

        AnchorPane root = FXMLLoader.load(getClass().getResource("resource/fxml/index.fxml"));
        Scene scene = new Scene(root);

        primaryStage.setTitle("Table de craft");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        
    }


    public static void main(String[] args) {
        launch(args);
    }



}
