package edu.acc.jweb.moorcat;

public class ShipMethod {
    private int ship_method_id;
    private String ship_method;
    
    public ShipMethod (String ship_method) {
        this.ship_method = ship_method;
    }  
    
    public ShipMethod () {
    }
    
    public int getShip_method_id() {
        return ship_method_id;
    }

    public void setShip_method_id(int ship_method_id) {
        this.ship_method_id = ship_method_id;
    }

    public String getShip_method() {
        return ship_method;
    }

    public void setShip_method(String ship_method) {
        this.ship_method = ship_method;
    }
}
