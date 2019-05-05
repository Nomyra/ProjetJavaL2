package controler;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import modele.Plan;

public class HelpPlanControler {
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

    /* ----------
    E: Plan plan
    // remplie la matrice avec les items du plan
     */
    public void inithelp(Plan plan){
        Pane[][] tabPane = {{row0col0,row0col1,row0col2},{row1col0,row1col1,row1col2},{row2col0,row2col1,row2col2}};
        for (int i=0;i<plan.getPlan().length;i++){
            for (int j=0;j<plan.getPlan().length;j++){
                String item = plan.getPlan()[i][j];
                if (!item.equals(" ")){
                    ImageView iv = new ImageView("resource/images/items/"+item+".png");
                    iv.setId(item);
                    iv.setFitWidth(50);iv.setFitHeight(50);
                    tabPane[i][j].getChildren().add(iv);
                }
            }
        }
    }
}
