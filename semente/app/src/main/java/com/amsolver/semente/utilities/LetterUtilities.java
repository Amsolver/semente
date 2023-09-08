package com.amsolver.semente.utilities;

import java.util.ArrayList;
import java.util.List;

public class LetterUtilities {

    public static String getRandomLetter() {
        List<String> letters = new ArrayList<>();
        letters.add("a");
        letters.add("b");
        letters.add("c");
        letters.add("d");
        letters.add("e");
        letters.add("f");
        letters.add("g");
        letters.add("h");
        letters.add("i");
        letters.add("k");
        letters.add("l");
        letters.add("m");
        letters.add("n");
        letters.add("o");
        letters.add("p");
        letters.add("q");
        letters.add("r");
        letters.add("s");
        letters.add("t");
        letters.add("u");
        letters.add("v");
        letters.add("x");
        letters.add("z");

        int random = (int) (Math.random() * 20) + 1;
        return letters.get(random);
    }
}
