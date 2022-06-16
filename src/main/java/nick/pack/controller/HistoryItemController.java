package nick.pack.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nick.pack.model.ProductSale;

public class HistoryItemController {

    @FXML
    private Label costLbl;

    @FXML
    private Label dateLbl;

    @FXML
    private ImageView imageView;

    @FXML
    private Label nameLbl;

    @FXML
    private Label quantityLbl;

    @FXML
    void initialize(){

    }
    public void setData(ProductSale productSale){
        nameLbl.setText(productSale.getProduct().getName());
        costLbl.setText(String.valueOf(productSale.getProduct().getCost()));
        dateLbl.setText(String.valueOf(productSale.getSaleDate()));
        quantityLbl.setText(String.valueOf(productSale.getQuantity()));
        System.out.println(productSale.getProduct().getImage());
        try {
            imageView.setImage(new Image("/productPhoto/" + productSale.getProduct().getImage()));
        } catch (IllegalArgumentException e){
            imageView.setImage(new Image("/productPhoto/1781670-1.jpg"));
        }

    }
}
