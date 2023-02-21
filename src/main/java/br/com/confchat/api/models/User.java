package br.com.confchat.api.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String photoUrl;
    private String name;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "varchar(255) not null unique default 0")
    private String code;
    private String permitions;
    @Column(unique = true)
    private String email;
    private String password;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public User(int id, String photoUrl, String name, String code, String permitions, String email, String password) {
        this.id = id;
        this.photoUrl = photoUrl;
        this.name = name;
        this.code = code;
        this.permitions = permitions;
        this.email = email;
        this.password = password;
    }
    public User(String name, String email, String password) {
        this.name = name;
        this.permitions = "";
        this.email = email;
        this.password = password;
    }
    public User() {
    }
    public String getPermitions() {
        return permitions;
    }
    public void setPermitions(String permitions) {
        this.permitions = permitions;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
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
}
