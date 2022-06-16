package nick.pack.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import nick.pack.model.ProductSale;
import nick.pack.service.EntityList;

import java.io.IOException;

public class HistoryController {

    @FXML
    private TilePane tilePane;

    @FXML
    void initialize(){
        ObservableList<ProductSale> observableList = FXCollections.observableList(EntityList.getProductSaleList());
        observableList.forEach(x -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/historyItem.fxml"));
            try {
                AnchorPane item = loader.load();
                HistoryItemController historyItemController = loader.getController();
                historyItemController.setData(x);
                tilePane.getChildren().add(item);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
