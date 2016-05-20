package models;

/**
 * Created by root on 17/03/16.
 */
public class User {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int id;
    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    String status;

    public void setName(String name) {
        this.name = name;
    }

    String name;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String password;
    String passwordConfig;

    public String getRegisteration_id() {
        return registeration_id;
    }

    public void setRegisteration_id(String registeration_id) {
        this.registeration_id = registeration_id;
    }

    public String getPasswordConfig() {
        return passwordConfig;
    }

    public void setPasswordConfig(String passwordConfig) {
        this.passwordConfig = passwordConfig;
    }

    String registeration_id;

}
