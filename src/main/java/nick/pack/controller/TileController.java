package nick.pack.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import nick.pack.model.Product;
import nick.pack.service.CRUD;
import nick.pack.service.EntityList;
import nick.pack.service.ProductService;

import java.io.IOException;

public class TileController {
    private ObservableList<Product> observableList;

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
        observableList = FXCollections.observableArrayList(EntityList.getProductList());
        CRUD<Product> crud = new ProductService();
        delete.setOnAction(x ->{
            for (int i = 0; i < EntityList.getProductList().size(); i++) {
                if (EntityList.getProductList().get(i).getName().equals(nameTxt.getText())){
                    crud.delete(EntityList.getProductList().get(i));
                    EntityList.getProductList().remove(i);
                    i--;
                    pane.setStyle("-fx-background-color: #FFA0A5");
                    isActive.setText("Удаленно");
                }
            }
        });
        update.setOnAction(x ->{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/update.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Обновить значение");
            stage.show();
            try {
                AnchorPane anchorPane = loader.load();
                UpdateController updateController = loader.getController();
                for (int i = 0; i < observableList.size(); i++) {
                    if (observableList.get(i).getName().equals(nameTxt.getText())){
                        updateController.setData(observableList.get(i));
                    }
                }
                stage.setScene(new Scene(anchorPane));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    public void setData(Product product) throws IOException {
        checkActivity(product);
        nameTxt.setText(product.getName());
        costTxt.setText(product.getCost() + " руб");
        Image image = null;
        try{
            image = new Image("/productPhoto/" + product.getImage());
        } catch (Exception e){
            image = new Image("/productPhoto/1781670-1.jpg");
        }

        imageView.setImage(image);
    }
    private void checkActivity(Product product) {
        if (product.getIsActive() == 0) {
            pane.setStyle("-fx-background-color: #A9A9A9");
            isActive.setText("Не активен");
        }
    }
}
