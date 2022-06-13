package nick.pack.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import nick.pack.model.Product;
import java.io.IOException;

public class TileController {

    @FXML
    private AnchorPane pane;

    @FXML
    private Button delete;

    @FXML
    private ImageView imageView;

    @FXML
    private Button update;

    @FXML
    private Label nameTxt;

    @FXML
    private Label costTxt;

    @FXML
    private Label isActive;



    @FXML
    void initialize(){
    }


    public void setData(Product product) throws IOException {
        checkActivity(product);
        nameTxt.setText(product.getName());
        costTxt.setText(product.getCost() + " руб");
        Image image = new Image("/productPhoto/" + product.getImage());
        imageView.setImage(image);
    }
    private void checkActivity(Product product){
        if (product.getIsActive() == 0){
            pane.setStyle("-fx-background-color: #A9A9A9");
            isActive.setText("Не активен");
        }
    }
}
