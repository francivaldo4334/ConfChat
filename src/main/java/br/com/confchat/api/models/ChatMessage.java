package br.com.confchat.api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_message")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JoinColumn(name = "fk_user",nullable=false)
    private int userId;
    @JoinColumn(name = "fk_contact",nullable=false)
    private int contactId;
    private boolean visualized;
    private int person;
    private String text;

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
    public int getContactId() {
        return contactId;
    }
    public void setContactId(int contactId) {
        this.contactId = contactId;
    }
    public boolean isVisualized() {
        return visualized;
    }
    public void setVisualized(boolean visualized) {
        this.visualized = visualized;
    }
    public int getPerson() {
        return person;
    }
    public void setPerson(int person) {
        this.person = person;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
}
