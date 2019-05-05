package controler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Item;
import modele.Modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;


public class ReserveBox extends VBox {
    @FXML
    private HBox hboxCategorie;
    @FXML
    protected FlowPane reservePane;
    @FXML
    private TextField searchField;

    private ArrayList<Item> listItem;

    public ReserveBox(Modele m) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/reserve.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch ( IOException e ) {
            throw new RuntimeException( e );
        }
        reserve(m);

        //recherche d'item
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.equals("")){
                reservePane.getChildren().removeAll(reservePane.getChildren());
            }
            else{
                reservePane.getChildren().removeAll(reservePane.getChildren());
                listItem = m.reserve.rechercher(newValue);
                for (Item item : listItem) {
                    ImageView iv = new ImageView("resource/images/items/" + item.nom + ".png");
                    iv.setFitWidth(50);
                    iv.setFitHeight(50);
                    iv.setId(item.nom);
                    reservePane.getChildren().add(iv);
                }
            }
        });

    }
    /*----------
   E: Modele modele
   // Affiche les onglets catégorie de la reserve
   S:
    */
    private void reserve(Modele modele){
        for (int i=0; i<modele.categories.size(); i++){
            String labelCategorie = modele.categories.get(i);
            Button button = new Button();
            ImageView iv = new ImageView("resource/images/categories/"+labelCategorie+".png");
            iv.setFitWidth(20);iv.setFitHeight(20);
            button.setId("b_categorie");
            button.setGraphic(iv);
            hboxCategorie.getChildren().add(button);
            button.setOnAction(e -> afficheItemReserve(labelCategorie, modele));
        }
    }
    /*-----------
    E: String c, Modele modele
    //Affiche les items de modele.reserve de la catégorie c dans #reserve
    S:
     */
    private void afficheItemReserve(String c, Modele modele) {
        reservePane.getChildren().removeAll(reservePane.getChildren());

        for (int x = 0; x < modele.nom.size(); x++) {
            String cle = modele.nom.get(x);
            Item item = modele.reserve.get(cle);
            //Ajoute les items à la réserve
            if (Objects.equals(item.categorie,c)) {
                ImageView iv = new ImageView("resource/images/items/"+cle+".png");
                iv.setFitWidth(50);iv.setFitHeight(50);
                iv.setId(cle);
                reservePane.getChildren().add(iv);
            }
        }
    }
}
