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
import nick.pack.service.CRUD;
import nick.pack.service.EntityList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;


public class Controller {
    public static ObservableList<Product> productObservableList;

    static {
        productObservableList = FXCollections.observableList(EntityList.getProductList());
    }
    private ObservableList<Product> fullProductList;
    private ObservableList<Product> copyList;
    ObservableList<Product> resultList;
    private int count = 0;
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
        fullProductList = FXCollections.observableArrayList(EntityList.getProductList());
        copyList = FXCollections.observableArrayList();
        resultList = FXCollections.observableArrayList();
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
        filters();
        manufactureFilter(productObservableList);
        costFilter(productObservableList);
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

    }

    @FXML
    void manufacturerFilter(ActionEvent event) {

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
    private void filters(){
        searchField.setOnKeyReleased(x ->{
            tilePane.getChildren().clear();
            productObservableList.clear();
            copyList.clear();
            for (Product product : fullProductList){
                if (product.getName().toLowerCase().contains(searchField.getText().toLowerCase())){
                    productObservableList.add(product);
                    copyList.add(product);
                }
            }
            System.out.println("CopyList: " + copyList);
            for (Product product : productObservableList){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
                try {
                    AnchorPane item = loader.load();
                    TileController controller = loader.getController();
                    controller.setData(product);
                    tilePane.getChildren().add(item);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }
    private void manufactureFilter(ObservableList<Product> productObservableList){
        manufacturerBox.setOnAction(x ->{
            productObservableList.clear();
            resultList.clear();
            for (Product product : copyList){
                if (product.getManufacturer().getName().equals(manufacturerBox.getValue())){
                    resultList.add(product);
                }
            }
            System.out.println(resultList);
            if (manufacturerBox.getValue().equals("Все значения")){
                resultList.addAll(copyList);
            }
            productObservableList.addAll(resultList);
            tilePane.getChildren().clear();
            for (Product product : productObservableList){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
                try {
                    AnchorPane item = loader.load();
                    TileController controller = loader.getController();
                    controller.setData(product);
                    tilePane.getChildren().add(item);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    private void costFilter(ObservableList<Product> productObservableList){
        if (resultList.size() > 0){
            System.out.println("ResultList: " + resultList);
            sortingBox.setOnAction(x -> {
                tilePane.getChildren().clear();
                ObservableList<Integer> ascending = FXCollections.observableArrayList();
                ObservableList<Integer> descending = FXCollections.observableArrayList();
                for (int i = 0; i < productObservableList.size(); i++) {
                    ascending.add(productObservableList.get(i).getCost());
                }
                Collections.sort(ascending);
                descending.addAll(ascending);
                Collections.reverse(descending);
                if (sortingBox.getValue().equals("По возрастанию")) {
                    System.out.println("ХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХ");
                    System.out.println(productObservableList);
                    Product[] products = new Product[productObservableList.size()];
                    for (int i = 0; i < productObservableList.size(); i++) {
                        for (int j = 0; j < ascending.size(); j++) {
                            if (productObservableList.get(i).getCost() == ascending.get(j)) {
                                products[j] = productObservableList.get(i);
                                ascending.set(j, 0);
                                break;
                            }
                        }
                    }
                    productObservableList.clear();
                    productObservableList.addAll(products);
                }
                if (sortingBox.getValue().equals("По убыванию")) {
                    Product[] products = new Product[productObservableList.size()];
                    System.out.println(descending);
                    for (int i = 0; i < productObservableList.size(); i++) {
                        for (int j = 0; j < descending.size(); j++) {
                            if (productObservableList.get(i).getCost() == descending.get(j)) {
                                products[j] = productObservableList.get(i);
                                descending.set(j, 0);
                                break;
                            }
                        }
                    }
                    System.out.println("ГОВНО:     " + Arrays.toString(products));
                    productObservableList.clear();
                    productObservableList.addAll(products);
                }
                if (sortingBox.getValue().equals("По умолчанию")) {
                    productObservableList.clear();
                    productObservableList.addAll(resultList);
                }
                for (Product product : productObservableList) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
                    try {
                        AnchorPane item = loader.load();
                        TileController controller = loader.getController();
                        controller.setData(product);
                        tilePane.getChildren().add(item);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else {
            sortingBox.setOnAction(x -> {
                count++;
                tilePane.getChildren().clear();
                ObservableList<Integer> ascending = FXCollections.observableArrayList();
                ObservableList<Integer> descending = FXCollections.observableArrayList();
                for (int i = 0; i < productObservableList.size(); i++) {
                    ascending.add(productObservableList.get(i).getCost());
                }
                Collections.sort(ascending);
                descending.addAll(ascending);
                Collections.reverse(descending);
                if (sortingBox.getValue().equals("По возрастанию")) {
                    System.out.println("ХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХХ");
                    System.out.println(productObservableList);
                    Product[] products = new Product[productObservableList.size()];
                    for (int i = 0; i < productObservableList.size(); i++) {
                        for (int j = 0; j < ascending.size(); j++) {
                            if (productObservableList.get(i).getCost() == ascending.get(j)) {
                                products[j] = productObservableList.get(i);
                                ascending.set(j, 0);
                                break;
                            }
                        }
                    }
                    productObservableList.clear();
                    productObservableList.addAll(products);
                }
                if (sortingBox.getValue().equals("По убыванию")) {
                    Product[] products = new Product[productObservableList.size()];
                    System.out.println(descending);
                    for (int i = 0; i < productObservableList.size(); i++) {
                        for (int j = 0; j < descending.size(); j++) {
                            if (productObservableList.get(i).getCost() == descending.get(j)) {
                                products[j] = productObservableList.get(i);
                                descending.set(j, 0);
                                break;
                            }
                        }
                    }
                    System.out.println("ГОВНО:     " + Arrays.toString(products));
                    productObservableList.clear();
                    productObservableList.addAll(products);
                }
                if (sortingBox.getValue().equals("По умолчанию")) {
                    productObservableList.clear();
                    if (resultList.size() > 0) {
                        System.out.println("CCOCOCOCODOSOD" + resultList);
                        productObservableList.addAll(resultList);
                    } else {
                        productObservableList.addAll(fullProductList);
                    }
                }
                for (Product product : productObservableList) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/tile.fxml"));
                    try {
                        AnchorPane item = loader.load();
                        TileController controller = loader.getController();
                        controller.setData(product);
                        tilePane.getChildren().add(item);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }
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
