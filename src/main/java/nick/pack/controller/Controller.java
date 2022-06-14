package nick.pack.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;
import nick.pack.model.Product;
import nick.pack.service.EntityList;

import java.io.IOException;


public class Controller {
    public static ObservableList<Product> productObservableList;

    static {
        productObservableList = FXCollections.observableList(EntityList.getProductList());
    }
    @FXML
    private Button buttonAdd;

    @FXML
    private Button buttonAddManufacturer;

    @FXML
    private Button historyButton;

    @FXML
    private Button buttonUpdate;

    @FXML
    private ComboBox<?> manufacturerBox;

    @FXML
    private ScrollPane salePane;

    @FXML
    private TilePane saleTilePane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<?> sortingBox;

    @FXML
    private TilePane tilePane;



    @FXML
    void initialize() throws IOException {
        productObservableList.forEach(x -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
            AnchorPane pane = null;
            try {
                pane = loader.load();
                TileController tileController = loader.getController();
                tileController.setData(x);
                tilePane.getChildren().add(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    @FXML
    void add(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.show();
        stage.setTitle("Добавить новый продукт");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/create.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
    }

    @FXML
    void addManufacturer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addManufacturer.fxml"));
        Stage stage = new Stage();
        stage.show();
        stage.setTitle("Добавить поставщика");
        stage.setScene(new Scene(loader.load()));
    }

    @FXML
    void costSorting(ActionEvent event) {

    }

    @FXML
    void manufacturerFilter(ActionEvent event) {

    }

    @FXML
    void purchaseHistory(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }
    @FXML
    void updateTiles(ActionEvent event) {
        System.out.println(productObservableList);
        tilePane.getChildren().clear();
        productObservableList.forEach(x -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
            AnchorPane pane = null;
            try {
                pane = loader.load();
                TileController tileController = loader.getController();
                System.out.println(x.getImage());
                tileController.setData(x);
                tilePane.getChildren().add(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


}
