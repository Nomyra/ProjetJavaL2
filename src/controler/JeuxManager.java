package controler;
import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import modele.Modele;

public class JeuxManager {

    private Scene scene;
    public Modele modele;

    public JeuxManager(Scene scene) {
        this.scene = scene;
        modele = new Modele();
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
            controller.initialize(this.modele,"creatif",this);
    }
    public void showNormalGame() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/index.fxml"));
        scene.setRoot(loader.load());
        MainController controller = loader.getController();
       // controller.deleteSauvgarde(this.modele);
        controller.initialize(this.modele,"normal",this);
    }
}