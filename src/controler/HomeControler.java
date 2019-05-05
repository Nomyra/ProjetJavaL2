package controler;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import java.io.IOException;
import java.util.Optional;

public class HomeControler {
    @FXML
    private Button reprendre;
    @FXML
    private Button creatif;
    @FXML
    private Button normal;
    @FXML
    private Label h1;

    public HomeControler(){}

    public void initManager(final JeuxManager jeuxManager) {
        Font minecarftB = Font.loadFont(getClass().getResourceAsStream("/resource/fonts/MINECRAFT-b.ttf"),20);
        Font minecarftN = Font.loadFont(getClass().getResourceAsStream("/resource/fonts/Minecrafter.Alt.ttf"),65);
        h1.setFont(minecarftN);
        reprendre.setFont(minecarftB);
        normal.setFont(minecarftB);
        creatif.setFont(minecarftB);

        /* ---------- Choix du mode de jeux --------------*/
        reprendre.setOnAction(e->{

            try {
                jeuxManager.showReprendreView();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        normal.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Nouvelle partie");
            alert.setHeaderText("Démarrer une nouvelle partie en mode normal ?");
            alert.setContentText("Si vous continuez la partie en cours sera perdu.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    jeuxManager.showNormalView();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });

        creatif.setOnAction(e->{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Nouvelle partie");
            alert.setHeaderText("Démarrer une nouvelle partie en mode créatif ?");
            alert.setContentText("Si vous continuez la partie en cours sera perdu.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                try {
                    jeuxManager.showCreatifView();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        /* ------ */
    }
}
