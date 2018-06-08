package edu.acc.jweb.moorcat;

public class Order {
    public int id;
    public String first_name;
    public String last_name;
    public String status;
    
    public Order (String first_name, String last_name, String status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.status = status;
    }   
    
    public Order (String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
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
