package edu.acc.jweb.moorcat;

import java.util.Date;

public class Order {
    private int id;
    private String first_name;
    private String last_name;
    private String status;
    
    public Order (String first_name, String last_name, String status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.status = status;
    }    
    
    public Order () {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}