package utils;

import java.util.regex.Pattern;

public class ValidateUtils {
    public static final String NAME_ACCOUNT = "^[A-z_](\\w|_){5,31}$";
    public static final String NAME_SIGN_IN = "^([A-Z]+[a-z]*[ ]?)+$";

    //    public static final String PASSWORD_REGEX = "^([a-zA-Z0-9]{8,})$";
    public static final String PASSWORD = "^[0-9]{8,16}";
    public static final String PHONE_NUMBER ="^[0]{1}[1-9]{1}[0-9]{8}";
    public static final String EMAIL = "^[A-Za-z0-9]+\\.{0,1}[A-Za-z0-9]*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)$";

    public static boolean isNameAccountInvalid(String name) {
        return Pattern.compile(NAME_ACCOUNT).matcher(name).matches();
    }
    public static boolean isNameInValid(String name) {
        return Pattern.compile(NAME_SIGN_IN).matcher(name).matches();
    }
        public static boolean isNamePasswordInValid(String name) {
        return Pattern.compile(PASSWORD).matcher(name).matches();
    }

    public static boolean isPhoneNumberInValid(String name) {
        return Pattern.compile(PHONE_NUMBER).matcher(name).matches();
    }
    public static boolean isEmailInValid(String name) {
        return Pattern.compile(EMAIL).matcher(name).matches();
    }

}
