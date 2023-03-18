package br.com.confchat.api.viewmodels;

public class UserVM {
    private String photoUrl;
    private String code;
    private String permitions;
    private String email;
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhotoUrl() {
        return photoUrl;
    }
    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPermitions() {
        return permitions;
    }
    public void setPermitions(String permitions) {
        this.permitions = permitions;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UserVM(String photoUrl, String code, String permitions, String email) {
        this.photoUrl = photoUrl;
        this.code = code;
        this.permitions = permitions;
        this.email = email;
    }
}
