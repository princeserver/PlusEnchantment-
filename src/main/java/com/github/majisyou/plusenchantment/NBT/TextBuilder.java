package com.github.majisyou.plusenchantment.NBT;

public class TextBuilder {

    public static String textplus(String... texts) {
        StringBuilder text = new StringBuilder();
        for (String plusString : texts) {
            text.append(plusString);
        }
        return text.toString();
    }


}
