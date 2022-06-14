package nick.pack.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nick.pack.model.Manufacturer;
import nick.pack.model.Product;
import nick.pack.service.CRUD;
import nick.pack.service.EntityList;
import nick.pack.service.ProductService;

import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UpdateController {
    private ObservableList<Manufacturer> observableList;
    private ObservableList<String> isActiveList;
    private File file;
    private Path path1;
    private Path path2;
    private Product product;

    @FXML
    private Button buttonSelect;

    @FXML
    private Button buttonUpdate;

    @FXML
    private TextField cost;

    @FXML
    private ComboBox<String> isActiveBox;

    @FXML
    private ComboBox<Manufacturer> manufacturerBox;

    @FXML
    private TextField nameField;

    @FXML
    void initialize(){
        isActiveList = FXCollections.observableArrayList();
        isActiveList.add("Да");
        isActiveList.add("Нет");
        isActiveBox.setItems(isActiveList);
        observableList = FXCollections.observableArrayList(EntityList.getManufacturerList());
        manufacturerBox.setItems(observableList);
    }

    @FXML
    void selectImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("C:\\Users\\Андрей\\IdeaProjects\\BeautyMagazine\\src\\main\\resources\\productPhoto"));
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("*.jpg", "*.jpg");
        fileChooser.setSelectedExtensionFilter(extensionFilter);
        file = fileChooser.showOpenDialog(new Stage());
        String path = file.getPath();
        int index = path.lastIndexOf("\\");
        path = path.substring(0, index);
        if (!path.equals("C:\\Users\\Андрей\\IdeaProjects\\BeautyMagazine\\src\\main\\resources\\productPhoto")){
            path1 = Path.of(file.getPath());
            path2 = Path.of("C:\\Users\\Андрей\\IdeaProjects\\BeautyMagazine\\src\\main\\resources\\productPhoto\\" + file.getName());
            buttonSelect.setText(file.getName());
        } else {
            buttonSelect.setText(file.getName());
        }
    }

    @FXML
    void update(ActionEvent event) throws IOException {
        CRUD<Product> crud = new ProductService();
        if (path1 !=null){
            Files.copy(path1, path2);
        }
        EntityList.getProductList().forEach(x ->{
            if (x.equals(product)){
                x.setName(nameField.getText());
                x.setCost(Integer.parseInt(cost.getText()));
                x.setImage(buttonSelect.getText());
                if (isActiveBox.getValue().equals("Да")){
                    x.setIsActive(1);
                } else
                    x.setIsActive(0);
                x.setManufacturer(manufacturerBox.getValue());
                crud.update(x);
            }
        });
        buttonUpdate.getScene().getWindow().hide();
    }
    public void setData(Product product){
        nameField.setText(product.getName());
        cost.setText(String.valueOf(product.getCost()));
        buttonSelect.setText(product.getImage());
        if (product.getIsActive() == 0){
            isActiveBox.setValue("Нет");
        } else {
            isActiveBox.setValue("Да");
        }
        this.product = product;
    }
}
