package nick.pack.controller;

import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import nick.pack.model.Manufacturer;
import nick.pack.model.Product;
import nick.pack.service.CRUD;
import nick.pack.service.EntityList;
import nick.pack.service.ProductService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateController {
    private ObservableList<Manufacturer> observableList;
    private File image;
    private Path path1;
    private Path path2;

    @FXML
    private TextField costField;

    @FXML
    private Label errorLbl;

    @FXML
    private Button createButton;

    @FXML
    private Button selectImage;

    @FXML
    private ComboBox<Manufacturer> manufacturerBox;

    @FXML
    private TextField nameField;

    @FXML
    void initialize(){
        observableList = FXCollections.observableArrayList(EntityList.getManufacturerList());
        manufacturerBox.setItems(observableList);
    }

    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Выбрать изображение");
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("*.jpg", "*.jpg");
        chooser.getExtensionFilters().add(extensionFilter);
        image = chooser.showOpenDialog(new Stage());
        path1 = Path.of(image.toURI());
        path2 = Path.of("C:\\Users\\Андрей\\IdeaProjects\\BeautyMagazine\\src\\main\\resources\\productPhoto\\" + image.getName());
        selectImage.setText(image.getName());
    }

    @FXML
    void create(ActionEvent event) throws IOException {
        if (!(nameField.getText().isEmpty() || costField.getText().isEmpty() || createButton.getText().equals("Выбрать") || manufacturerBox.getValue() == null)) {
            Files.copy(path1, path2);
            Product product = new Product(nameField.getText(), Integer.parseInt(costField.getText()), image.getName(), manufacturerBox.getValue(), 1);
            CRUD<Product> crud = new ProductService();
            crud.create(product);
            EntityList.getProductList().add(product);
            Controller.productObservableList.add(product);
            createButton.getScene().getWindow().hide();
        } else
            errorLbl.setText("Введите все данные");
    }
}
