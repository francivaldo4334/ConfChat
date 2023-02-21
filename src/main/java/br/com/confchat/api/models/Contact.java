package br.com.confchat.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "fk_user",nullable=false)
    private int userId;
    private String code;
    private boolean isFriend;
    @JoinColumn(name = "fk_user",nullable=false)
    private int friendId;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFriendId() {
        return friendId;
    }
    public void setFriendId(int friendId) {
        this.friendId = friendId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public boolean isFriend() {
        return isFriend;
    }
    public void setFriend(boolean isFriend) {
        this.isFriend = isFriend;
    }
}
