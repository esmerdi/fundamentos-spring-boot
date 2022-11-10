package com.fundamentos.springboot.fundamentos.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@ConfigurationProperties(prefix = "user")
@ConstructorBinding//Nos permite inyectar como dependencia nuestra clase UserPojo, en cualquier parte de nuestra App.
public class UserPojo {
    private String email;
    private String password;
    private int edad;

    public UserPojo(String email, String password, int edad) {
        this.email = email;
        this.password = password;
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}
