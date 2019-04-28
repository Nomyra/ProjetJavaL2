package controler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import modele.Item;
import modele.Modele;

import java.io.IOException;
import java.util.Objects;

public class MatierePremControler extends VBox {
    @FXML
    protected FlowPane matierePrem;

    public MatierePremControler(Modele m) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/matierePrem.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
        show(m);
    }

    /*-----------
    E: Modele modele
    //Affiche les items MATIERES_PREMIERES
    S:
     */
    public void show(Modele modele) {
        for (int x = 0; x < modele.nom.size(); x++) {
            String cle = modele.nom.get(x);
            Item item = modele.reserve.get(cle);
            //Ajoute les items à la réserve
            if (item.categorie.equals("MATIERES_PREMIERES")) {
                ImageView iv = new ImageView("resource/images/items/"+cle+".png");
                iv.setFitWidth(50);iv.setFitHeight(50);
                iv.setId(cle);
                matierePrem.getChildren().add(iv);
            }
        }
    }
}
