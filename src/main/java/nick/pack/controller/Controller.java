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
import nick.pack.model.Manufacturer;
import nick.pack.model.Product;
import nick.pack.service.EntityList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


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
    private ComboBox<String> manufacturerBox;

    @FXML
    private ScrollPane salePane;

    @FXML
    private TilePane saleTilePane;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField searchField;

    @FXML
    private ComboBox<String> sortingBox;

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
        searching();
        manufacturerBoxInit();
        sortingBoxInit();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/manufacturer.fxml"));
        Stage stage = new Stage();
        stage.show();
        stage.setTitle("Поставщики");
        stage.setScene(new Scene(loader.load()));
    }

    @FXML
    void costSorting(ActionEvent event) {
        ObservableList<Integer> ascending = FXCollections.observableArrayList();
        ObservableList<Integer> descending = FXCollections.observableArrayList();
        for (Product product : productObservableList){
            ascending.add(product.getCost());
        }
        Collections.sort(ascending);
        System.out.println(ascending);
        for (int i = ascending.size() -1; i >= 0; i--) {
            descending.add(ascending.get(i));
        }
        if (sortingBox.getValue().equals("По возрастанию")){
            ObservableList<Product> resultList = FXCollections.observableArrayList();
            tilePane.getChildren().clear();
            Product[] products = new Product[productObservableList.size()];
            for (int i = 0; i < productObservableList.size(); i++) {
                for (int j = 0; j < ascending.size(); j++) {
                    if (productObservableList.get(i).getCost() == ascending.get(j)){
                        products[j] = productObservableList.get(i);
                        ascending.set(j, 0);
                        break;
                    }
                }
            }
            resultList.addAll(Arrays.asList(products));
            resultList.forEach(x ->{
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
        if (sortingBox.getValue().equals("По убыванию")){
            ObservableList<Product> resultList = FXCollections.observableArrayList();
            tilePane.getChildren().clear();
            Product[] products = new Product[productObservableList.size()];
            for (int i = 0; i < productObservableList.size(); i++) {
                for (int j = 0; j < descending.size(); j++) {
                    if (productObservableList.get(i).getCost() == descending.get(j)){
                        products[j] = productObservableList.get(i);
                        descending.set(j, 0);
                        break;
                    }
                }
            }
            resultList.addAll(Arrays.asList(products));
            System.out.println(resultList);
            resultList.forEach(x ->{
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
        if (sortingBox.getValue().equals("По умолчанию")){
            tilePane.getChildren().clear();
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
    }

    @FXML
    void manufacturerFilter(ActionEvent event) {
        tilePane.getChildren().clear();
        ObservableList<Product> resultList = FXCollections.observableArrayList();
        for (int i = 0; i < productObservableList.size(); i++) {
            if (productObservableList.get(i).getManufacturer().getName().equals(manufacturerBox.getValue())){
                resultList.add(productObservableList.get(i));
            }
        }
        System.out.println(resultList);
        for (int i = 0; i < resultList.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
            try {
                AnchorPane pane = loader.load();
                TileController tileController = loader.getController();
                tileController.setData(resultList.get(i));
                tilePane.getChildren().add(pane);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (manufacturerBox.getValue().equals("Все значения")){
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
    }

    @FXML
    void purchaseHistory(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/history.fxml"));
        Stage stage = new Stage();
        stage.setTitle("История покупок");
        try {
            stage.setScene(new Scene(loader.load(), 400, 400));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.setResizable(true);
        stage.show();
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
    private void searching(){
        ObservableList<Product> resultList = FXCollections.observableArrayList();
        searchField.setOnKeyReleased(x ->{
            resultList.clear();
            tilePane.getChildren().clear();
            for (int i = 0; i < productObservableList.size(); i++) {
                if (productObservableList.get(i).getName().toLowerCase().contains(searchField.getText().toLowerCase())){
                    resultList.add(productObservableList.get(i));
                }
            }
            for (int i = 0; i < resultList.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
                AnchorPane pane = null;
                try {
                    pane = loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                TileController tileController = loader.getController();
                try {
                    tileController.setData(resultList.get(i));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                tilePane.getChildren().add(pane);
            }
        });
    }
    private void manufacturerBoxInit() {
        ObservableList<Manufacturer> manufacturers = FXCollections.observableList(EntityList.getManufacturerList());
        ObservableList<String> namesManufacturers = FXCollections.observableArrayList();
        namesManufacturers.add("Все значения");
        for (int i = 0; i < manufacturers.size(); i++) {
            namesManufacturers.add(manufacturers.get(i).getName());
        }
        manufacturerBox.setItems(namesManufacturers);
        manufacturerBox.setValue("Все значения");
    }
    private void sortingBoxInit(){
        ObservableList<String> observableList = FXCollections.observableArrayList();
        observableList.add("По убыванию");
        observableList.add("По возрастанию");
        observableList.add("По умолчанию");
        sortingBox.setItems(observableList);
        sortingBox.setValue("По умолчанию");
    }
}
