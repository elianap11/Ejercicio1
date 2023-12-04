package org.example;

import java.time.LocalDate;
import java.util.Locale;

public class CreditCardService {

    private static final double TRANSACTION_LIMIT = 1000;

    public void displayCard(CreditCard creditCard) {
        System.out.println(creditCard);
    }

    public boolean validateTransaction(double amount) {
        return amount < TRANSACTION_LIMIT;
    }

    public boolean validateCard(CreditCard creditCard) {
        return creditCard.getExpirationDate().isAfter(LocalDate.now());
    }

    public boolean compareCards(CreditCard creditCard1, CreditCard creditCard2) {
        return creditCard1.equals(creditCard2);
    }

    public double calculateRate(CreditCard creditCard, double amount) {

        double rate = 0;

        switch (creditCard.getBrand()) {
            case VISA:
                rate = (double) LocalDate.now().getYear() / LocalDate.now().getMonthValue() / 100;
                break;
            case NARA:
                rate = LocalDate.now().getDayOfMonth() * 0.5;
                break;
            case AMEX:
                rate = LocalDate.now().getMonthValue() * 0.1;
                break;
            default:
                throw new IllegalArgumentException("Marca de tarjeta de crédito inválida: " + creditCard.getBrand());
        }

        return rate;

    }


}

