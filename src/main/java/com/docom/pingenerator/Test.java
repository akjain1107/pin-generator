package com.docom.pingenerator;

import java.util.regex.Pattern;

public class Test {

    public static void main(String[] args) {
        String regex = "^\\+(?:[0-9] ?){6,14}[0-9]$";
        Pattern pattern = Pattern.compile(regex);
    }
}
