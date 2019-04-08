package controler;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import modele.Modele;
//import java.util.regex.*;


public class MainController {
   // private static Pattern pattern;
    //private static Matcher matcher;
    @FXML
    private TextField searchField;
    @FXML
    private Button searchButton;
    @FXML
    private Label searchLabel;
    @FXML
    private TableView<Modele> reserve;

    public MainController(){}

    @FXML
    private void initialize() {

        searchButton.setText("Search");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            searchLabel.setText(newValue);
        });
        Modele m = new Modele();
        reserve(m);
    }

    public void reserve(Modele modele){
        for (int i=0; i<modele.categories.size(); i++){
            TableColumn col = new TableColumn(modele.categories.get(i));
            reserve.getColumns().addAll(col);

          /*  Pattern p = Pattern.compile("(^"+modele.categories.get(i)+")");
            Matcher m = p.matcher(modele.reserve.keySet().toString());
            Boolean b = m.lookingAt();
            System.out.println(p);
            System.out.println(m);
            System.out.println(b);*/
        }
    }


}