package org.prem.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathVariable {
    public static String pathCheck(String str){
        //String input = "/wea Sunny";
        String regex = "/weather\\s+.+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);

        if (matcher.matches()) {
            String[] words = str.split("\\s+");
            return words[1];
        } else {
            return "Enter --> \"/weather {location(city)}\"";
        }

    }

    public static String picPathCheck(String str){
        String regex = "/pic\\s+.+";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        List<String > search = new ArrayList<>();
        if (matcher.matches()) {
            String[] words = str.split("\\s+");
            if(words.length > 4){
                for(int i =1 ; i<=4;i++){
                    search.add(words[i]);
                }
            }else {
                for(int i=1; i < words.length;i++){
                    search.add(words[i]);
                }
            }
            return String.join("+", search);
        } else {
            return "error";
        }

    }


}
