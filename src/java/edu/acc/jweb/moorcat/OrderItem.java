package edu.acc.jweb.moorcat;

import java.math.BigDecimal;

public class OrderItem {
    private int product_id;
    private String name;
    private BigDecimal price;
    private String category;
    private String supplier;
    private int quantity;
    private int picked;
    
    public OrderItem () {
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPicked() {
        return picked;
    }

    public void setPicked(int picked) {
        this.picked = picked;
    }

}
