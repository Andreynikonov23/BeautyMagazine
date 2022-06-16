package nick.pack.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import nick.pack.model.Manufacturer;
import nick.pack.service.CRUD;
import nick.pack.service.EntityList;
import nick.pack.service.ManufacturerService;

import java.io.IOException;
import java.time.LocalDate;

public class ManufacturerController {
    private static ObservableList<Manufacturer> manufacturers;
    static {
        manufacturers = FXCollections.observableList(EntityList.getManufacturerList());
    }


    @FXML
    private TableColumn<Manufacturer, LocalDate> date;

    @FXML
    private Button deleteButton;

    @FXML
    private TableColumn<Manufacturer, Integer> idColumn;

    @FXML
    private TableColumn<Manufacturer, String> nameColumn;

    @FXML
    private TableView<Manufacturer> tableView;

    @FXML
    void initialize(){
        tableView.setItems(manufacturers);
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.setOnMouseClicked(x ->{
            deleteButton.setDisable(false);
        });
        deleteButton.setOnAction(x ->{
            CRUD<Manufacturer> crud = new ManufacturerService();
            crud.delete(tableView.getSelectionModel().getSelectedItem());
            manufacturers.remove(tableView.getSelectionModel().getSelectedItem());
        });
    }
    @FXML
    void createManufacturer(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/addManufacturer.fxml"));
        Stage stage = new Stage();
        stage.show();
        stage.setTitle("Поставщики");
        stage.setScene(new Scene(loader.load()));
    }
    public static ObservableList<Manufacturer> getManufacturers(){
        return manufacturers;
    }
}
