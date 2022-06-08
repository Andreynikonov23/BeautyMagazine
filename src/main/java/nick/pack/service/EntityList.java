package nick.pack.service;

import nick.pack.model.Manufacturer;
import nick.pack.model.Product;
import nick.pack.model.ProductSale;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EntityList {
    private static List<Product> productList;
    private static List<ProductSale> productSaleList;
    private static List<Manufacturer> manufacturerList;


    public static List<Product> getProductList(){
        CRUD<Product> productCRUD = new ProductService();
        productList = productCRUD.read();
        return productList;
    }
    public static List<ProductSale> getProductSaleList(){
        CRUD<ProductSale> productSaleCRUD = new ProductSaleService();
        productSaleList = productSaleCRUD.read();
        return productSaleList;
    }
    public static List<Manufacturer> getManufacturerList(){
        CRUD<Manufacturer> manufacturerCRUD = new ManufacturerService();
        manufacturerList = manufacturerCRUD.read();
        return manufacturerList;
    }
}
