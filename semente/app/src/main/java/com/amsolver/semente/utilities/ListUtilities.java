package com.amsolver.semente.utilities;

import java.util.ArrayList;
import java.util.List;

public class ListUtilities {

    public static List<String> getStringListFromCharArray(char[] word) {
        List<String> result = new ArrayList<>();
        for (char c : word) {
            String cadena = Character.toString(c);
            result.add(cadena);
        }
        return result;
    }

    public static int howManyTimesInList(List<String> list, String letter) {
        int times = 0;
        for (String l: list) {
            if (l.equals(letter)) {
                times++;
            }
        }
        return times;
    }
}
