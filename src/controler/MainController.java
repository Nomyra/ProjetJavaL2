package controler;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import modele.Item;
import modele.Modele;
import modele.Plan;

import java.util.*;

public class MainController {
    @FXML
    private FlowPane reserve;
    @FXML
    private HBox hbCategorie;
    @FXML
    private Pane row0col0;
    @FXML
    private Pane row0col1;
    @FXML
    private Pane row0col2;
    @FXML
    private Pane row1col0;
    @FXML
    private Pane row1col1;
    @FXML
    private Pane row1col2;
    @FXML
    private Pane row2col0;
    @FXML
    private Pane row2col1;
    @FXML
    private Pane row2col2;
    @FXML
    private GridPane gridCraft;
    @FXML
    private Button crafter;
    @FXML
    private Pane resultatPane;
    @FXML
    private FlowPane inventairePane;

    private String id;

    public MainController(){}

    @FXML
    private void initialize() {
        Modele m = new Modele();
        reserve(m);
        Pane[][] tabPane = {{row0col0,row0col1,row0col2},{row1col0,row1col1,row1col2},{row2col0,row2col1,row2col2}};
        crafter.setOnAction(e -> crafte(m,tabPane));

        // click table craft -> suprime item
        row2col2.setOnMouseClicked(e-> {if (row2col2.getChildren().size() > 0){row2col2.getChildren().remove(0);}});
        row2col1.setOnMouseClicked(e-> {if (row2col1.getChildren().size() > 0){row2col1.getChildren().remove(0);}});
        row2col0.setOnMouseClicked(e-> {if (row2col0.getChildren().size() > 0){row2col0.getChildren().remove(0);}});
        row1col0.setOnMouseClicked(e-> {if (row1col0.getChildren().size() > 0){row1col0.getChildren().remove(0);}});
        row1col1.setOnMouseClicked(e-> {if (row1col1.getChildren().size() > 0){row1col1.getChildren().remove(0);}});
        row1col2.setOnMouseClicked(e-> {if (row1col2.getChildren().size() > 0){row1col2.getChildren().remove(0);}});
        row0col2.setOnMouseClicked(e-> {if (row0col2.getChildren().size() > 0){row0col2.getChildren().remove(0);}});
        row0col1.setOnMouseClicked(e-> {if (row0col1.getChildren().size() > 0){row0col1.getChildren().remove(0);}});
        row0col0.setOnMouseClicked(e-> {if (row0col0.getChildren().size() > 0){row0col0.getChildren().remove(0);}});

        resultatPane.setOnMouseClicked(e ->{
            if (resultatPane.getChildren().size() > 0){
                addItemInventaire(resultatPane.getChildren().get(0).getId());
            }
        });
    }

    public void addItemInventaire(String id){
        ImageView iv = new ImageView("resources/images/items/"+id+".png");
        iv.setId(id);
        inventairePane.getChildren().add(iv);
        resultatPane.getChildren().remove(0);
    }

    //Ajoute les catégories
    public void reserve(Modele modele){
        for (int i=0; i<modele.categories.size(); i++){
            String labelCategorie = modele.categories.get(i);
            Button button = new Button();
            ImageView iv = new ImageView("resource/images/categories/"+labelCategorie+".png");
            iv.setFitWidth(20);iv.setFitHeight(20);
            button.setId("b_categorie");
            button.setGraphic(iv);
            hbCategorie.getChildren().add(button);
            button.setOnAction(e -> afficheItemReserve(labelCategorie, modele));
        }
    }

    //Affiche les items par catégories
    public void afficheItemReserve(String c, Modele modele) {
        reserve.getChildren().removeAll(reserve.getChildren());

        for (int x = 0; x < modele.nom.size(); x++) {
            String cle = modele.nom.get(x);
            Item item = modele.reserve.get(cle);
            //Ajoute les items à la réserve
            if (Objects.equals(item.categorie,c)) {
                ImageView iv = new ImageView("resource/images/items/"+cle+".png");
                iv.setFitWidth(50);iv.setFitHeight(50);
                iv.setId(cle);
                reserve.getChildren().add(iv);
                dragAndDropReserve(iv);
            }
        }
    }

    public void dragAndDropReserve(ImageView iv){
        Pane[] tabPane = {row0col0,row0col1,row0col2,row1col0,row1col1,row1col2,row2col0,row2col1,row2col2};
        iv.setOnDragDetected((MouseEvent e)->{
            id = iv.getId();
            Dragboard db = iv.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(iv.getImage());
            db.setContent(content);
            e.consume();
        });

        for (Pane pane : tabPane) {
            pane.setOnDragOver((DragEvent e) -> {
                e.acceptTransferModes(TransferMode.COPY);
                e.consume();
            });

            pane.setOnDragDropped((e) -> {
                Dragboard db = e.getDragboard();
                boolean sucess = false;
                if (db.hasImage()) {
                    ImageView img = new ImageView(db.getImage());
                    img.setFitHeight(50);img.setFitWidth(50);
                    img.setId(id);
                    if(pane.getChildren().size()>0){
                        pane.getChildren().remove(0);
                    }
                    pane.getChildren().add(img);
                    sucess = true;
                }
                e.setDropCompleted(sucess);
                e.consume();
            });
        }
     /*   iv.setOnDragDone(e ->{
           if (e.getTransferMode() == TransferMode.COPY){
                iv.setImage(null);
            }
            e.consume();
        });*/
    }

    public void crafte(Modele m, Pane[][] tabPane) {
        String[][] plan = {{null, null, null}, {null, null, null}, {null, null, null}};

        for (int x=0;x<tabPane.length;x++){
            for(int y=0;y<tabPane.length;y++){
                if(tabPane[x][y].getChildren().size()>0){
                    plan[x][y]=tabPane[x][y].getChildren().get(0).getId();
                }
            }
        }
        Plan p = new Plan(plan);
        Item res = m.plans.chercher(p);
        System.out.println(p+" --> "+res);

        if (res!=null){
            if (resultatPane.getChildren().size()>0){
                addItemInventaire(resultatPane.getChildren().get(0).getId());
            }
            ImageView iv = new ImageView("resource/images/items/"+res.nom+".png");
            iv.setId(res.nom);
            resultatPane.getChildren().add(iv);
        }
    }
}