package br.com.confchat.api.viewmodels;

public class NewPasswordVM {
    private int code;
    private String newPassword;
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getNewPassword() {
        return newPassword;
    }
    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }    
}
