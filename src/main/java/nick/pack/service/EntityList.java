package nick.pack.service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import nick.pack.model.Manufacturer;
import nick.pack.model.Product;
import nick.pack.model.ProductSale;
import nick.pack.model.Users;

import java.util.ArrayList;
import java.util.List;

public class EntityList {
    private static List<Product> productList;
    private static List<ProductSale> productSaleList;
    private static List<Manufacturer> manufacturerList;
    private static List<Users> usersList;


    public static List<Product> getProductList(){
        if (productList == null){
            initProductList();
        }
        return productList;
    }
    public static List<ProductSale> getProductSaleList(){
        if (productSaleList == null){
            initProductSaleList();
        }
        return productSaleList;
    }
    public static List<Manufacturer> getManufacturerList(){
        if(manufacturerList == null){
            initManufacturerList();
        }
        return manufacturerList;
    }
    public static List<Users> getUsersList(){
        if (usersList == null){
            initUsersList();
        }
        return usersList;
    }


    private static void initProductList(){
        CRUD<Product> productCRUD = new ProductService();
        productList = new ArrayList<>(productCRUD.read());
    }
    private static void initProductSaleList(){
        CRUD<ProductSale> productSaleCRUD = new ProductSaleService();
        productSaleList = new ArrayList<>(productSaleCRUD.read());
    }
    private static void initManufacturerList(){
        CRUD<Manufacturer> manufacturerCRUD = new ManufacturerService();
        manufacturerList = new ArrayList<>(manufacturerCRUD.read());
    }
    private static void initUsersList(){
        CRUD<Users> crud = new UsersService();
        usersList = new ArrayList<>(crud.read());
    }
}
