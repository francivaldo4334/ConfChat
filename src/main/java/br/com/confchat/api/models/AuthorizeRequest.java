package br.com.confchat.api.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
@Entity
@Table(name = "authorize_request")
public class AuthorizeRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "fk_user",nullable=false)
    private int userId;
    @Temporal(TemporalType.DATE)
    private Date createdIn;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public Date getCreatedIn() {
        return createdIn;
    }
    public void setCreatedIn(Date createdIn) {
        this.createdIn = createdIn;
    }
}
