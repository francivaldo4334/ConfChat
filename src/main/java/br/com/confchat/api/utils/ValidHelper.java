package br.com.confchat.api.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidHelper {
    public static boolean isValidName(String name){
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(RegexStrings.Name);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }
    public static boolean isValidEmail(String email){
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(RegexStrings.Email);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
