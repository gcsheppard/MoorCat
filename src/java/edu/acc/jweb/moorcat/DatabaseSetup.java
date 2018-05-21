package edu.acc.jweb.moorcat;

import javax.sql.DataSource;

public class DatabaseSetup {
    private final DataSource dataSource;
    
    public DatabaseSetup (DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public void addSuppliers(SupplierManager supplierManager) {
        supplierManager.addSupplier("Purina");
        supplierManager.addSupplier("Chicken Soup");
        supplierManager.addSupplier("Royal Canin");
    }
    
    public void addCategories(CategoryManager categoryManager) {
        categoryManager.addCategory("Food");
        categoryManager.addCategory("Toys");
        categoryManager.addCategory("Carriers");
    }
    
    public void addProducts(ProductManager productManager) {
        productManager.addProduct("Cat Chow Indoor Dry Cat Food, 16-lb bag", "12.99", 1, 1);
        productManager.addProduct("Chicken Soup for the Soul Weight & Mature Care Dry Cat Food, 15-lb bag", "29.99", 1, 2);
        productManager.addProduct("Royal Canin Veterinary Diet Calm Formula Dry Cat Food, 8.8-lb bag", "49.99", 1, 3);
    }
    
}
