package com.myproject.shoesstore.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author Tadaboh;
 */
public class ValidationUtil {

    public static final String ONLY_NUMBER_REGEX = "[0-9]+";
    public static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$";

    public static boolean checkNullOrEmpty(String value) {
        if (value == null || value.trim().length() == 0 || value.equals("null")) {
            return true;
        }
        return false;
    }

    public static boolean isCheck(String value, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    public static boolean isCheckDigits(String value) {
        return value.matches(".*\\d.*");
    }

    public static boolean isCheckLetters(String value) {
        return value.matches(".*[a-zA-Z].*");
    }

    public static boolean isCheckSpecialCharacters(String value) {
        return value.matches(".*[@!#$%].*");
    }


    public static boolean checkEmail(String value) {
        if (checkNullOrEmpty(value) || isCheckSpecialCharacters(value)) {

            return false;
        }
        return isCheck(value, EMAIL_REGEX);
    }

   

   
    public static boolean validDouble(double number) {
        String checkNumber = String.valueOf(number);

        if (!isCheckLetters(checkNumber) || !checkNullOrEmpty(checkNumber) || !isCheckSpecialCharacters(checkNumber)) {

            return false;

        }

        return true;

    }
    
    public static boolean validInteger(int number) {
        String checkNumber = String.valueOf(number);

        if (!isCheckLetters(checkNumber) || !checkNullOrEmpty(checkNumber) || !isCheckSpecialCharacters(checkNumber)) {

            return false;

        }

        return true;

    }
    

    public static boolean validPhone( String value) {
        if (!isCheckDigits(value) || value.length() != 10) {
            
            return true;
        }
        return false;
    }

    public static boolean validEmail(String value) {
        if (!checkEmail(value) || isCheckSpecialCharacters(value)) {
            return false;
        }
        return true;
    }

    public static boolean validAge(Integer value) {
        String age = String.valueOf(value);
        if (value < 1 && value > 100 || isCheckLetters(age) || isCheckLetters(age)) {
            return false;
        }
        return true;
    }


    public static boolean validName( String value) {
        if (checkNullOrEmpty(value)|| isCheckSpecialCharacters(value) ){
            return true;
        }
        return false;
    }

    public static boolean validFieldName(String value) {
        if (checkNullOrEmpty(value)|| isCheckSpecialCharacters(value)) {
            return false;
        }
        return true;

    }
}
