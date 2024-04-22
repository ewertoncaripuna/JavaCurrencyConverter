package br.com.alura.service;

import br.com.alura.model.Converter;

import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    public static void getUserInputAndConvert() {

        Scanner scanner = new Scanner(System.in);
        boolean next = true;
        Map<String, Double> conversionRates = FileManager.openFile(FileManager.nameWithDate());

        do {
            System.out.print("Please enter the currency you want to convert: ");
            String sourceCurrency = scanner.next().toUpperCase();

            if (!conversionRates.containsKey(sourceCurrency)) {
                System.out.println("Invalid source currency.");
                next = false;
                continue;
            }

            String targetCurrency;
            boolean validTargetCurrency;

            do {
                System.out.print("Enter the code of the target currency: ");
                targetCurrency = scanner.next().toUpperCase();
                validTargetCurrency = conversionRates.containsKey(targetCurrency);

                if (!validTargetCurrency) {
                    System.out.println("Invalid target currency.");
                    continue;
                }

                if (sourceCurrency.equals(targetCurrency)) {
                    System.out.println("Source and target currencies must be different.");
                    validTargetCurrency = false;
                }

            } while (!validTargetCurrency);

            System.out.print("Enter the amount to convert: ");
            double amount = scanner.nextDouble();

            Converter.convert(sourceCurrency, targetCurrency, amount, conversionRates);

            System.out.print("Do you want to convert another currency? (Y/N): ");
            String response = scanner.next().toUpperCase();

            if (response.equals("N")) {
                next = false;
            }

        } while (next);
    }
}
