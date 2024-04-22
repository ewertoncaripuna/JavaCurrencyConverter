package br.com.alura.model;

import java.util.Map;

public class Converter {

    public static void convert(String originalCurrency, String targetCurrency, double amount, Map<String, Double> conversionRates) {

        if (conversionRates.containsKey(originalCurrency) && conversionRates.containsKey(targetCurrency)) {
            double originalRate = conversionRates.get(originalCurrency);
            double targetRate = conversionRates.get(targetCurrency);

            double convertedValue = (amount / originalRate) * targetRate;
            System.out.println("*****************************************");
            System.out.println("Converted value: " + String.format("%.3f",convertedValue) + " " + targetCurrency);
            System.out.println(originalCurrency + ": " + conversionRates.get(originalCurrency));
            System.out.println(targetCurrency + ": " + conversionRates.get(targetCurrency));
            System.out.println("*****************************************");
        } else {
            System.out.println("Original or target currency not found in exchange rates.");
        }
    }
}