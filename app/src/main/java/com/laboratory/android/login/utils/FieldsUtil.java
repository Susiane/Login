package com.laboratory.android.login.utils;

/**
 * Created by Susiane on 22/12/2016.
 */

public class FieldsUtil {

    public static boolean isEmpty(String string){
        return string.isEmpty();
    }

    /**
     * Roles to validate password
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password){
        if(password.length() >= 6){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Roles to validate email
     * @param eMailAdress
     * @return
     */
    public static boolean isValidEmail(String eMailAdress) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(eMailAdress);
        return m.matches();
    }



}
