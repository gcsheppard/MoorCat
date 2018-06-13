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
        supplierManager.addSupplier("Fat Cat");
        supplierManager.addSupplier("Cat Dancer");
        supplierManager.addSupplier("Hexbug");
        supplierManager.addSupplier("Frisco");
        supplierManager.addSupplier("GoPetClub");
        supplierManager.addSupplier("IRIS");
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
        productManager.addProduct("Fat Cat Eeeks! Catnip Toy, Color Varies", "1.99", 2, 4);
        productManager.addProduct("Cat Dancer Cat Charmer Toy", "4.99", 2, 5);
        productManager.addProduct("Hexbug Nano Robotic Cat Toy, Color Varies", "4.99", 2, 6);
        productManager.addProduct("Frisco Plastic Kennel, Almond & Black, Small", "29.99", 3, 7);
        productManager.addProduct("GoPetClub Soft Portable Pet Home, Green, 32-in", "34.99", 3, 8);
        productManager.addProduct("IRIS Deluxe Pet Travel Carrier, Red, Extra Small", "29.99", 3, 9);
    }
    
    public void addOrders(OrderManager orderManager) {
        orderManager.addOrder("John", "Smith", "Placed");
        orderManager.addOrder("Hilda", "Weintraub", "Placed");
        orderManager.addOrder("Frodo", "Baggins", "Placed");
        orderManager.addProductToOrder(1, 1, 1);
        orderManager.addProductToOrder(1, 4, 1);
        orderManager.addProductToOrder(1, 9, 1);
        orderManager.addProductToOrder(2, 1, 1);
        orderManager.addProductToOrder(2, 7, 2);
        orderManager.addProductToOrder(3, 3, 2);
        orderManager.addProductToOrder(3, 5, 1);
    }
    
    public void addUsers(UserManager userManager) {
        userManager.addUser("admin", "admin", "Management");
        userManager.addUser("work", "work", "Warehouse");
    }
}

