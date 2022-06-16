package nick.pack.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import nick.pack.model.Manufacturer;
import nick.pack.service.CRUD;
import nick.pack.service.EntityList;
import nick.pack.service.ManufacturerService;

public class CreateManufacturerController {

    @FXML
    private Button createButton;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField nameField;


    @FXML
    void create(ActionEvent event) {
        Manufacturer manufacturer = new Manufacturer(nameField.getText(), datePicker.getValue());
        CRUD<Manufacturer> crud = new ManufacturerService();
        crud.create(manufacturer);
        createButton.getScene().getWindow().hide();
        ManufacturerController.getManufacturers().add(manufacturer);
    }
}
