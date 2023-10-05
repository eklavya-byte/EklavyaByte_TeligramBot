package org.prem.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathValidator {

    public static String  picturePath(String str) {
        //String input = "/wea Sunny";
        String regex = "/pic\\s+.+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches()) {
            String[] words = str.split("\\s+");
            return words[0];
        } else {
            return "Enter --> \"/pic {flower... etc}\"";
        }
    }


    public static String weatherPath(String str){
        //String input = "/wea Sunny";
        String regex = "/weather\\s+.+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches() || (str.equals("/weather") || str.equals("/weather "))) {
            String[] words = str.split("\\s+");
            return words[0];
        } else {
            return "Enter --> \"/weather {location(city)}\"";
        }

    }



}
