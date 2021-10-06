package com.docom.pingenerator.util;

public class Constants {
    public static final String MSISDN = "MSISDN";
    public static final String PIN = "Pin";
    public static final String INVALID = "Invalid";
    public static final String DIGIT_PIN_EXPECTED = "4 Digit pin expected.";
    public static final String MSISDN_REGX = "^\\+(?:[0-9] ?){1,3}[0-9]{10}$";
    public static final String PIN_REGX = "^[0-9]{4}$";
    public static final String PIN_NOT_FOUND = "Active Pin not found, kindly generate.";
    public static final String RETRIES_MESSAGE = "already has 3 retries. Kindly wait for sometime.";
    public static final String VALIDATED = "Validated";
    public static final String ALREADY_HAS_3_NON_VALIDATED_PINS = "already has 3 non-validated PINs. Kindly validate.";
}
