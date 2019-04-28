package controler;

import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

import java.io.IOException;

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

        reprendre.setOnAction(e->{
            try {
                jeuxManager.showReprendreView();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        normal.setOnAction(e->{
            try {
                jeuxManager.showNormalGame();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        creatif.setOnAction(e->{
            try {
                jeuxManager.showCreatifView();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
    }
}
