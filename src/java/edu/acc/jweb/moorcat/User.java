package edu.acc.jweb.moorcat;

public class User {
    
    private String username;
    private String password;
    private String department;
    
    public User () {
    }

    public User (String username, String password, String department) {
        this.username = username;
        this.password = PasswordHash.hashPassword(password);
        this.department = department;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
