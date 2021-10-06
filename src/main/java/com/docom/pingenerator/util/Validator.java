package com.docom.pingenerator.util;
import com.docom.pingenerator.exception.InputValidationException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Validator {
    public static void validateMSISDN(String msisdn){
        if(null!=msisdn){
        String regex = Constants.MSISDN_REGX;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(msisdn);
        if(!matcher.matches())
            throwException(Constants.INVALID+" "+Constants.MSISDN +" "+msisdn);
        }else
            throwException(Constants.INVALID+" "+Constants.MSISDN +" "+msisdn);
    }
    public static void validatePin(String pin){
        if(null!=pin) {
            String regex = Constants.PIN_REGX;
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(pin);
            if (!matcher.matches())
               throwException(Constants.INVALID + " " + Constants.PIN + " " + pin + ". " + Constants.DIGIT_PIN_EXPECTED);
        }else
            throwException(Constants.INVALID + " " + Constants.PIN + " " + pin + ". " + Constants.DIGIT_PIN_EXPECTED);
    }

    public static void throwException(String message){
        throw new InputValidationException(message);
    }
}
