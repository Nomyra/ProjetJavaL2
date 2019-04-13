package controler;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Item;
import modele.Modele;
import java.util.*;

public class MainController {
    @FXML
    private FlowPane reserve;
    @FXML
    private HBox hbCategorie;
    @FXML
    private VBox vboxreserve;

    public MainController(){}

    @FXML
    private void initialize() {
        Modele m = new Modele();
        reserve(m);
    }

    //Ajoute les catégories
    public void reserve(Modele modele){
        for (int i=0; i<modele.categories.size(); i++){
            String labelCategorie = modele.categories.get(i);
            Button button = new Button(labelCategorie);
            button.setId("b_"+labelCategorie);
            hbCategorie.getChildren().add(button);
            button.setOnAction((e) ->{ afficheItem(labelCategorie, modele); });
        }
    }

    //Affiche les items par catégories
    public void afficheItem(String c, Modele modele) {
        reserve.getChildren().removeAll(reserve.getChildren());

        for (int x = 0; x < modele.nom.size(); x++) {
            String cle = modele.nom.get(x);
            Item item = modele.reserve.get(cle);

            if (Objects.equals(item.categorie,c)) {
                ImageView iv = new ImageView("resource/images/items/"+cle+".png");
                iv.setFitWidth(50);
                iv.setFitHeight(50);
                iv.setId(cle);
                reserve.getChildren().add(iv);
            }
        }
    }
}