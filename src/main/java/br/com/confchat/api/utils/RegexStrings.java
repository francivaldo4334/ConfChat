package br.com.confchat.api.utils;

public abstract class RegexStrings {
    public static String Email = "^[A-Za-z0-9_\\-]+(?:[.][A-Za-z0-9_\\-]+)*@[A-Za-z0-9_]+(?:[-.][A-Za-z0-9_]+)*\\.[A-Za-z0-9_]+$";
    public static String Name = "^[ ]*(.+[ ]+)+.+[ ]*$";
}
