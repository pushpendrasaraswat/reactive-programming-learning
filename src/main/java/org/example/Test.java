package org.example;

import org.apache.commons.lang3.StringUtils;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class Test {

    public static void main(String[] args) {
        Locale locale = new Locale.Builder().setLanguage("de").setRegion("at").build();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        //formatter.setMaximumFractionDigits(0);
        System.out.println(formatter.format(0));


        System.out.println("====================================");
        System.out.println(formatPrice(10356565.50));

    }



    public static String formatPrice(double price) {

        Locale locale = new Locale.Builder().setLanguage("at").setRegion("at").build();
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        //formatter.setMaximumFractionDigits(0);

            String formatted = formatter.format(price);
            String symbol = formatter.getCurrency().getSymbol(locale);

            // Different locales put the symbol on opposite sides of the amount
            // http://en.wikipedia.org/wiki/Currency_sign
            // If there is already a space (like the fr_FR locale formats things),
            // then return this as is, otherwise insert a space on either side
            // and trim the result
            System.out.println("formatted: " + formatted);
            if (StringUtils.contains(formatted, " " + symbol) || StringUtils.contains(formatted, symbol + " ")) {
                return formatted;
            }
            else {
                return StringUtils.replaceOnce(formatted, symbol, " " + symbol + " ").trim();
            }
        
    }
}
