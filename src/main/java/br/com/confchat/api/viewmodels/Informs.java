package br.com.confchat.api.viewmodels;

import java.util.Collection;
import br.com.confchat.api.models.*;

public class Informs {
    private Collection<Notice> notices;
    private Collection<ChatMessage> chats;
    public Collection<Notice> getNotices() {
        return notices;
    }
    public void setNotices(Collection<Notice> notices) {
        this.notices = notices;
    }
    public Collection<ChatMessage> getChats() {
        return chats;
    }
    public void setChats(Collection<ChatMessage> chats) {
        this.chats = chats;
    }
}
