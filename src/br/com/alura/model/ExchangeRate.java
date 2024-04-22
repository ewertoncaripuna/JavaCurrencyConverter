package br.com.alura.model;

import java.util.Map;

public class ExchangeRate {

    private final Map<String, Double> conversion_rates;

    public ExchangeRate(Map<String, Double> conversionRates) {
        this.conversion_rates = conversionRates;
    }

    public Map<String, Double> getConversionRates() {
        return conversion_rates;
    }

    }


