package edu.acc.jweb.moorcat;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

@DataSourceDefinition(
        name = "java:app/jdbc/moorcat",
        className = "com.mysql.jdbc.Driver",
        url = "jdbc:mysql://localhost:3306/moorcat",
        databaseName = "moorcat",
        user = "root",
        password = "cat1508")

@WebListener
public class MoorCatListener implements ServletContextListener {

    @Resource(lookup = "java:app/jdbc/moorcat")
    DataSource dataSource;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //TableManager tableManager = new TableManager(dataSource);
        //tableManager.clearTables();
        
        SupplierManager supplierManager = new SupplierManager(dataSource);
        sce.getServletContext().setAttribute("supplierManager", supplierManager);
        CategoryManager categoryManager = new CategoryManager(dataSource);
        sce.getServletContext().setAttribute("categoryManager", categoryManager);
        ProductManager productManager = new ProductManager(dataSource);
        sce.getServletContext().setAttribute("productManager", productManager);
        OrderManager orderManager = new OrderManager(dataSource);
        sce.getServletContext().setAttribute("orderManager", orderManager);
        UserManager userManager = new UserManager(dataSource);
        sce.getServletContext().setAttribute("userManager", userManager);
        ShipManager shipManager = new ShipManager(dataSource);
        sce.getServletContext().setAttribute("shipManager", shipManager);
        ImageManager imageManager = new ImageManager(dataSource);
        sce.getServletContext().setAttribute("imageManager", imageManager);
        
        DatabaseSetup databaseSetup = new DatabaseSetup(dataSource);
        databaseSetup.addSuppliers(supplierManager);
        databaseSetup.addCategories(categoryManager);
        databaseSetup.addProducts(productManager);
        databaseSetup.addOrders(orderManager);
        databaseSetup.addUsers(userManager);
        databaseSetup.addDepartmentStatuses(userManager);
        databaseSetup.addShipMethods(shipManager);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}