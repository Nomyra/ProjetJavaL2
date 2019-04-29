package controler;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import modele.Modele;
import modele.Plan;

public class JeuxManager {

    private Scene scene;
    public Modele modele;
    private Stage primaryStage;

    public JeuxManager(Scene scene, Stage primaryStage) {
        this.scene = scene;
        this.modele = new Modele();
        this.primaryStage = primaryStage;
    }

    public void showHomeView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/home.fxml"));
        scene.setRoot(loader.load());
        HomeControler controller = loader.getController();
        controller.initManager(this);
    }
    public void showReprendreView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/index.fxml"));
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
        controller.initialize(this.modele,"reprendre",this);
    }
    public void showCreatifView() throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/index.fxml"));
            scene.setRoot(loader.load());
            MainController controller = loader.getController();
            modele.modeCreatif = true;
            controller.initialize(this.modele,"creatif",this);
    }
    public void showNormalView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/index.fxml"));
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
        modele.modeCreatif = false;
        controller.initialize(this.modele,"normal",this);
    }
    public void showHelpPlanView(Plan plan, String item) throws IOException {
        StackPane helpLayout = new StackPane();
        Scene secondScene = new Scene(helpLayout, 230, 100);

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle(item);
        newWindow.setScene(secondScene);

        // Set position and size.
        newWindow.setX(primaryStage.getX() + 200);
        newWindow.setY(primaryStage.getY() + 100);
        newWindow.setWidth(230);
        newWindow.setHeight(250);
        newWindow.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/helpPlan.fxml"));
        secondScene.setRoot(loader.load());
        HelpPlanControler controller = loader.getController();
        controller.inithelp(plan);
        newWindow.show();
    }

    public void exitProcess(){
        modele.enregistrerEtat();
        System.out.println("cc");
    }
}