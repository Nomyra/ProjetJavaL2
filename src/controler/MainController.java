package controler;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import modele.*;

import java.io.IOException;
import java.util.*;

public class MainController {
    @FXML
    private GridPane gridCraft;
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
    private Button menu;
    @FXML
    private MenuButton aidemenu;
    @FXML
    private Label nbItem;
    @FXML
    private VBox vboxVariable;
    @FXML
    private FlowPane aidePane;
    @FXML
    private Label hinventaire;
    @FXML
    private Label aideLabel;


    private String id;
    private Image image;
    private Integer posXfin;
    private Integer posYfin;
    private JeuxManager jeuxManager;


    public MainController(){
    }

    /* -----------------
    E: Modele m
    // Remet à zero l'état du jeux
    S:
     */
    public void deleteSauvgarde(Modele m){
        // supprime la sauvegarde
        m.resetSauvegarde();
        // supprime les items de l'invantaire
        m.inventaire.clear();
        //supprime les items du plan en cours
        String[][] plan = m.planEnCours.getPlan();
        for (int i=0;i<plan.length;i++ ){
            for (int j=0;j<plan.length;j++){
                if(!plan[i][j].equals(" ")){
                    m.planEnCours.modifierPlan(i,j);
                }
            }
        }
    }

    public void initialize(Modele m,String mode,final JeuxManager jm)  throws IOException {
        jeuxManager=jm;

        //affiche menu démarer et enregistre la partie
        menu.setOnAction(e->{
            try {
                m.enregistrerEtat();
                jeuxManager.showHomeView();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        //Chargement des éléments selon le mode de jeux
        switch (mode){
            case "creatif":
                deleteSauvgarde(m);
                showReseveBox(m);
                break;
            case "reprendre":
                showIventaire(m.inventaire,m.nom);
                Boolean table = showTable(m.planEnCours);
                if(table){
                    crafte(m);
                }
                if(m.modeCreatif){
                    showReseveBox(m);
                }
                else {
                    showMatierePrem(m);
                }
                break;
            case "normal":
                deleteSauvgarde(m);
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

        // Drag and drop detecte
        inventairePane.setOnMouseMoved(e ->{
            for (int i=0; i<inventairePane.getChildren().size();i++){
                dragAndDrop(inventairePane.getChildren().get(i),true,m);
            }
        });
        //DaD sur la table de craft
        gridCraft.setOnMouseMoved(e->{
            Pane[] tabPane = {row0col0,row0col1,row0col2,row1col0,row1col1,row1col2,row2col0,row2col1,row2col2};
            for (Pane pane : tabPane) {
                if (pane.getChildren().size()==1){
                  dragAndDropOnTable(pane,m,tabPane);
                }
            }
        });
        //init menu help
        help(m.categories,m.nom,m.reserve);

        //Font style
        Font minecarftB = Font.loadFont(getClass().getResourceAsStream("/resource/fonts/MINECRAFT-b.ttf"),20);
        Font minecarftBpetit = Font.loadFont(getClass().getResourceAsStream("/resource/fonts/MINECRAFT-b.ttf"),15);
        Font minecarftN = Font.loadFont(getClass().getResourceAsStream("/resource/fonts/Minecrafter.Alt.ttf"),25);
        hinventaire.setFont(minecarftN);
        aideLabel.setFont(minecarftB);
        menu.setFont(minecarftB);
    }

    /* -----------------
    E: ArrayList<String> cat(catégorie), ArrayList<String> keys, TableItems items
    // init les onglets(cat) de l'aide et evt click sur les onglets
    S:
     */
    private void help(ArrayList<String> cat, ArrayList<String> keys, TableItems items){
        MenuItem rien = new MenuItem("---");
        aidemenu.getItems().add(rien);
        rien.setOnAction(e->{aidePane.getChildren().removeAll(aidePane.getChildren());});

        for (String c: cat){
            MenuItem menuItem = new MenuItem(c);
            aidemenu.getItems().add(menuItem);
            menuItem.setOnAction(e->showHelpItems(keys,items,c));
        }
    }
    /* -----------------
    E: ArrayList<String> keys,TableItems items,String c (categorie)
    // Affiche les items de c et evt click sur l'item -> affiche la view d'aide
    S:
     */
    private void showHelpItems(ArrayList<String> keys,TableItems items,String c){
        aidePane.getChildren().removeAll(aidePane.getChildren());
        for (String key : keys) {
            Item item = items.get(key);
            if (Objects.equals(item.categorie, c) && item.fabricable) {
                ImageView iv = newIV(key);
                aidePane.getChildren().add(iv);
                iv.setOnMouseClicked(evt -> {
                    try {
                        jeuxManager.showHelpPlanView(item.plan, item.nom);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
    /* -----------------
        E: Modele m
        // Ajoute la view reserve au jeux
        S:
    */
    private void showReseveBox(Modele m) throws IOException {
        ReserveBox reserveBox = new ReserveBox(m);
        vboxVariable.getChildren().add(reserveBox);
        reserveBox.reservePane.setOnMouseMoved(e ->{
            for (int i=0; i<reserveBox.reservePane.getChildren().size();i++){
                dragAndDrop(reserveBox.reservePane.getChildren().get(i),false,m);
            }
        });
    }
    /* -----------------
        E: Modele m
        // Ajoute la view des matières première
        S:
    */
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
    E: Inventaire, ArrayList<String> keys (liste des cles)
    // Affiche les items présent dans l'inventaire
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
    //Affiche les items présent dans la table de craft
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
            // Si l'item provient de l'inventaire
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
    /* -------------
    E: Pane pane (source), Modele m, Pane[] panes (listes des panes de la grille -> destinations)
    // Drag and drop sur la table de craft -> modification du plan + craft
    S:--
     */
    public void dragAndDropOnTable(Pane pane,Modele m,Pane[] panes){
        Integer[] posDep=position(pane.getId());

        pane.setOnDragDetected((MouseEvent e)->{
            id = pane.getChildren().get(0).getId();
            if(id.contains("//")){
                String[] id2 = id.split("//");
                image = new Image("resource/images/items/"+id2[0]+".png");
            }
            else{
                image = new Image("resource/images/items/"+id+".png");
            }
            Dragboard db = pane.getChildren().get(0).startDragAndDrop(TransferMode.COPY);
            ClipboardContent content = new ClipboardContent();
            content.putImage(image);
            db.setContent(content);
            e.consume();
        });

        for (Pane p : panes) {
            p.setOnDragOver((DragEvent e) -> {
                if(p.getChildren().size()==0){
                    e.acceptTransferModes(TransferMode.COPY);
                    e.consume();
                }
            });

            p.setOnDragDropped((e) -> {
                Dragboard db = e.getDragboard();
                boolean sucess = false;
                if (db.hasImage()) {
                    posXfin =position(p.getId())[0];
                    posYfin =position(p.getId())[1];

                    ImageView iv = new ImageView(image);
                    iv.setFitHeight(50);iv.setFitWidth(50);
                    iv.setId(id);

                    p.getChildren().add(iv);
                    iv.setId(id);
                    sucess = true;
                }
                e.setDropCompleted(sucess);
                e.consume();
            });
        }
        pane.setOnDragDone(e ->{
            if (e.getTransferMode() == TransferMode.COPY){
                pane.getChildren().remove(0);
                m.planEnCours.modifierPlan(posDep[0],posDep[1]);
                if (id.contains("//")){
                    String[] id2 = id.split("//");
                    m.planEnCours.modifierPlan(posXfin,posYfin,id2[0]);
                }
                else{
                    m.planEnCours.modifierPlan(posXfin,posYfin,id);
                }
                crafte(m);
            }
            e.consume();
        });
    }
    /* --------------
    E: Model m
    //Recherche une correspondance des plans
    //Affiche l'item crafter
    S:
     */
    private void crafte(Modele m) {
        nbItem.setText(null);
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