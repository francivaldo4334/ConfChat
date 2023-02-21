package br.com.confchat.api.utils;

import java.util.Random;

public class CodeGenerete {
    public static String generate(){
        // create a string of all characters
    String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";

    // create random string builder
    StringBuilder sb = new StringBuilder();

    // create an object of Random class
    Random random = new Random();

    // specify length of random string
    int length = 7;

    for(int i = 0; i < length; i++) {

      // generate random index number
      int index = random.nextInt(alphabet.length());

      // get character specified by index
      // from the string
      char randomChar = alphabet.charAt(index);

      // append the character to string builder
      sb.append(randomChar);
    }

    String randomString = sb.toString();
    return randomString;
        // byte[] array = new byte[7]; // length is bounded by 7
        // new Random().nextBytes(array);
        // String generatedString = new String(array, Charset.forName("UTF-8"));
        // return generatedString;
    }
}
