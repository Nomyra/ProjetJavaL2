package controler;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import modele.*;

import java.io.IOException;
import java.util.*;

public class MainController {
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
    private Pane resultatPane;
    @FXML
    private FlowPane inventairePane;
    @FXML
    private Button enregistrer;
    @FXML
    private Button menu;
    @FXML
    private MenuButton aidemenu;
    @FXML
    private Label nbItem;
    @FXML
    private VBox vboxVariable;
    @FXML
    private FlowPane aidePane;

    private String id;
    private Image image;

    private JeuxManager jeuxManager;

    public MainController(){
    }

    public void deleteSauvgarde(Modele m){
        System.out.println("reset");
        m.resetSauvegarde();
    }
    public void initialize(Modele m,String mode,final JeuxManager jm)  throws IOException {
        jeuxManager=jm;
        menu.setOnAction(e->{
            try {
                jeuxManager.showHomeView();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        switch (mode){
            case "creatif":
                showReseveBox(m);
                break;
            case "reprendre":
                showIventaire(m.inventaire,m.nom);
                Boolean table = showTable(m.planEnCours);
                if(table){
                    crafte(m);
                }
                break;
            case "normal":
                showMatierePrem(m);
                break;
        }


        // click table craft -> suprime l'item
        row2col2.setOnMouseClicked(e-> deleteItemTab(row2col2,m));
        row2col1.setOnMouseClicked(e-> deleteItemTab(row2col1,m));
        row2col0.setOnMouseClicked(e-> deleteItemTab(row2col0,m));
        row1col0.setOnMouseClicked(e-> deleteItemTab(row1col0,m));
        row1col1.setOnMouseClicked(e-> deleteItemTab(row1col1,m));
        row1col2.setOnMouseClicked(e-> deleteItemTab(row1col2,m));
        row0col2.setOnMouseClicked(e-> deleteItemTab(row0col2,m));
        row0col1.setOnMouseClicked(e-> deleteItemTab(row0col1,m));
        row0col0.setOnMouseClicked(e-> deleteItemTab(row0col0,m));

        // click resulat du craft -> ajoute l'item à l'invantaire
        resultatPane.setOnMouseClicked(e ->{
            if (resultatPane.getChildren().size() > 0){
                addItemInventaire(resultatPane.getChildren().get(0).getId(),true,m.inventaire,m.resultatCraft);
                deleteItemsTab(m.planEnCours);
                nbItem.setText(null);
            }
        });

        enregistrer.setOnAction(e -> m.enregistrerEtat());

        // Drag and drop detecte
        inventairePane.setOnMouseMoved(e ->{
            for (int i=0; i<inventairePane.getChildren().size();i++){
                dragAndDrop(inventairePane.getChildren().get(i),true,m);
            }
        });

        help(m.categories,m.nom,m.reserve);
    }
    private void help(ArrayList<String> cat, ArrayList<String> keys, TableItems items){
        MenuItem rien = new MenuItem("---");
        aidemenu.getItems().add(rien);
        rien.setOnAction(e->{aidePane.getChildren().removeAll(aidePane.getChildren());});

        for (String c: cat){
            if(!c.equals("MATIERES_PREMIERES")){
                MenuItem menuItem = new MenuItem(c);
                aidemenu.getItems().add(menuItem);
                menuItem.setOnAction(e->showHelpItems(keys,items,c));
            }
        }
    }
    private void showHelpItems(ArrayList<String> keys,TableItems items,String c){
        aidePane.getChildren().removeAll(aidePane.getChildren());
                    /*ScrollBar scrollBar = new ScrollBar();
                    scrollBar.setOrientation(Orientation.VERTICAL);
                    scrollBar.setMin(0);
                    scrollBar.setMax(200);
                    scrollBar.setUnitIncrement(10);
                    scrollBar.setBlockIncrement(15);
                    scrollBar.setValue(50);
                    aidePane.getChildren().add(scrollBar);*/
        for (String key : keys) {
            Item item = items.get(key);
            if (Objects.equals(item.categorie, c)) {
                ImageView iv = newIV(key);
                aidePane.getChildren().add(iv);
                iv.setOnMouseClicked(evt -> {
                    try {
                        jeuxManager.showHelpPlanView(item.plan, item.nom);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                //scrollBar.setValue(iv);

            }
        }
    }

    private void showReseveBox(Modele m) throws IOException {
        ReserveBox reserveBox = new ReserveBox(m);
        vboxVariable.getChildren().add(reserveBox);
        reserveBox.reservePane.setOnMouseMoved(e ->{
            for (int i=0; i<reserveBox.reservePane.getChildren().size();i++){
                dragAndDrop(reserveBox.reservePane.getChildren().get(i),false,m);
            }
        });
    }
    private void showMatierePrem(Modele m) throws IOException {
        MatierePremControler matierePremControler = new MatierePremControler(m);
        vboxVariable.getChildren().add(matierePremControler);
        matierePremControler.matierePrem.setOnMouseMoved(e->{
            for (int i=0; i<matierePremControler.matierePrem.getChildren().size();i++ ){
                dragAndDrop(matierePremControler.matierePrem.getChildren().get(i),false,m);
            }
        });
    }
    /* -------------
    E: Inventaire, ArrayList<String> keys (liste des obj)
    // Affiche l'inventaire si enregistré
    S:
     */
    private void showIventaire(Inventaire inventaire,ArrayList<String> keys){
        if (inventaire.size()>0){
            for(String key: keys){
                if (inventaire.get(key) != null){
                    for (int i=0; i<inventaire.get(key);i++){
                        ImageView iv = newIV(key);
                        inventairePane.getChildren().add(iv);
                    }
                }
            }
        }
    }
    /* --------------
    E: Plan p
    //Affiche les items de la table de craft si enregistré
    S: Boolean s vrai si contient un item
     */
    private Boolean showTable(Plan p){
        Boolean s = false;
        Pane[][] tabPane = {{row0col0,row0col1,row0col2},{row1col0,row1col1,row1col2},{row2col0,row2col1,row2col2}};
        for (int i=0;i<p.getPlan().length;i++){
            for (int j=0;j<p.getPlan().length;j++){
                String item = p.getPlan()[i][j];
                if (!item.equals(" ")){
                    ImageView iv = newIV(item);
                    tabPane[i][j].getChildren().add(iv);
                    s=true;
                }
            }
        }
        return s;
    }
    /* -----------------
    E: Plan p
    // Supprime tout les items de la matrice et du Plan
    S:
     */
    private void deleteItemsTab(Plan p){
        Pane[] tabPane = {row0col0, row0col1, row0col2, row1col0, row1col1, row1col2, row2col0, row2col1, row2col2};
        for (Pane pane : tabPane) {
            if(pane.getChildren().size()>0){
                Integer[]pos=position(pane.getId());
                p.modifierPlan(pos[0],pos[1]);
                pane.getChildren().remove(0);
            }
        }
    }
    /* ---------
    E: Pane box(1 case de la matrice), Modele m
    //Supprime l'item de box et de m.planEnCours
    //craft(m)
    S:
     */
    private void deleteItemTab(Pane box,Modele m){
        if (box.getChildren().size() > 0)
        {
            String id = box.getChildren().get(0).getId();
            // Si l'item provien de l'inventaire
            if(id.contains("//")){
                String[] id2 = id.split("//");
                addItemInventaire(id2[0],false,m.inventaire,m.resultatCraft);
            }
            box.getChildren().remove(0);
            Integer[] pos = position(box.getId());
            m.planEnCours.modifierPlan(pos[0],pos[1]);
            crafte(m);
        }
    }
    /* ----------
    E: String id, Boolean (vrai si c'est un nouveaux item), Inventaire inv, Item res(resultat Craft)
    //ajoute l'Item id/res à l'inventaire
    S:
     */
    private void addItemInventaire(String id, Boolean nouveau, Inventaire inv, Item res){
        if (nouveau) {
            resultatPane.getChildren().remove(0);
            inv.ajouter(res.nom,res.nbFabrique);
            for(int i=0; i<res.nbFabrique;i++){
                ImageView iv = newIV(id);
                inventairePane.getChildren().add(iv);
            }
        }
        else{
            inv.ajouter(id,1);
            ImageView iv = newIV(id);
            inventairePane.getChildren().add(iv);
        }
    }
    /*----
    //E: Node node, Boolean b(vrai si viens de l'inventaire), Modele m
    // drag and drop sur la matrice
    // si b, supprime l'item de l'inventaire et ajoute "//" à l'id
    //S:
    -------*/
    private void dragAndDrop(Node node,Boolean b,Modele m){
        Pane[] tabPane = {row0col0,row0col1,row0col2,row1col0,row1col1,row1col2,row2col0,row2col1,row2col2};

        node.setOnDragDetected((MouseEvent e)->{
            id = node.getId();
            image = new Image("resource/images/items/"+id+".png");

            Dragboard db = node.startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(image);
            db.setContent(content);
            e.consume();
        });

        for (Pane p : tabPane) {
            p.setOnDragOver((DragEvent e) -> {
                e.acceptTransferModes(TransferMode.COPY);
                e.consume();
            });

            p.setOnDragDropped((e) -> {
                Dragboard db = e.getDragboard();
                boolean sucess = false;
                if (db.hasImage()) {
                    ImageView iv = new ImageView(image);
                    iv.setFitHeight(50);iv.setFitWidth(50);
                    iv.setId(id);

                    if(p.getChildren().size()>0){
                        deleteItemTab(p,m);
                    }
                    p.getChildren().add(iv);
                    sucess = true;

                    //Ajout de l'objet dans plans en cours
                    Integer[] pos=position(p.getId());
                    m.planEnCours.modifierPlan(pos[0],pos[1],iv.getId());
                    crafte(m);
                    if(b){
                        iv.setId(id+"//");
                    }
                }
                e.setDropCompleted(sucess);
                e.consume();
            });
        }
        if (b){
            node.setOnDragDone(e ->{
                if (e.getTransferMode() == TransferMode.COPY){
                    node.setId(null);
                    deleteIV();
                    m.inventaire.retirer(id);
                }
                e.consume();
            });
        }
    }
    /* --------------
    E: Model m
    //Recherche une correspondance des plans
    //Affiche l'item crafter
    S:
     */
    private void crafte(Modele m) {
        Item res = m.plans.chercher(m.planEnCours);
        m.setResultatCraft(res);

        if (resultatPane.getChildren().size()>0){
            resultatPane.getChildren().remove(0);
        }
        if (res!=null){
            ImageView iv = new ImageView("resource/images/items/"+res.nom+".png");
            iv.setId(res.nom);
            resultatPane.getChildren().add(iv);
            if(res.nbFabrique>1){
                System.out.println(String.valueOf(res.nbFabrique));
                nbItem.setText(String.valueOf(res.nbFabrique));
                System.out.println(nbItem.getText());
            }
        }
    }
    /* --------------
    E: String id (du panneau de la matrice)
    S: Integer[lig,col]
     */
    private Integer[] position(String id){
        String[] tab = id.split("row");
        String[] position = tab[1].split("col");
        Integer[] x= {Integer.parseInt(position[0]),Integer.parseInt(position[1])};
        return x;
    }
    /* ----------
    E:
    //Supprime les ImageView avec un id null de l'inventaire
    S:
     */
    private void deleteIV(){
        for (int i=0;i<inventairePane.getChildren().size();i++){
            if(inventairePane.getChildren().get(i).getId() == null){
                inventairePane.getChildren().remove(i);
            }
        }
    }
    /* ---------
    E: String s (identifiant de l'item)
    //Cré et initialise une ImageView
    S: ImageView iv
     */
    private ImageView newIV(String s){
        ImageView iv = new ImageView("resource/images/items/"+s+".png");
        iv.setId(s);
        iv.setFitWidth(50);iv.setFitHeight(50);
        return iv;
    }
}