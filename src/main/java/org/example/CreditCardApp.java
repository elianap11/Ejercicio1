package org.example;

import exceptions.InvalidCreditCardException;
import exceptions.InvalidDateMonthException;
import exceptions.InvalidTransactionException;

import java.time.DateTimeException;
import java.time.LocalDate;

public class CreditCardApp {

    public static void main(String[] args) throws InvalidDateMonthException {

        try {

            CreditCard card1 = createCreditCard(CreditCardBrand.VISA, "1234-5678-9101-2312",
                    "Georgie Denbrough", LocalDate.of(2023, 12, 31));

            CreditCard card2 = createCreditCard(CreditCardBrand.NARA, "5678-9101-2312-4567",
                    "Beverly Marsh", LocalDate.of(2024, 1, 15));

            CreditCard card3 = createCreditCard(CreditCardBrand.AMEX, "4321-5326-1111-7989",
                    "Stanley Uris", LocalDate.of(2023, 99, 30));


            /*Harcodeado para probar tarjetas iguales
            CreditCard card2 = createCreditCard(CreditCardBrand.VISA, "1234-5678-9101-2312",
                    "Georgie Denbrough", LocalDate.of(2023, 12, 31));*/

            CreditCardService service = new CreditCardService();

            processCreditCard(service, card1, 500);
            processCreditCard(service, card2, 1500);
            processCreditCard(service, card3, 1000);

            System.out.println();

            if (service.compareCards(card1, card2)) {
                System.out.println("Las tarjetas de crédito " + card1.getNumber() + " y " + card2.getNumber() + " son iguales");
            }

        } catch (DateTimeException | InvalidCreditCardException | InvalidDateMonthException | InvalidTransactionException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static CreditCard createCreditCard(CreditCardBrand brand, String number,
                                               String cardHolder, LocalDate expirationDate) throws InvalidCreditCardException, InvalidDateMonthException {

          if (!isValidCardNumber(number)) {
              throw new InvalidCreditCardException("Número de tarjeta de crédito no válido");
          }

          if (expirationDate.isBefore(LocalDate.now())) {
              throw new InvalidDateMonthException("La fecha de vencimiento no puede ser anterior a la fecha actual");
          }

        return new CreditCard(brand, number, cardHolder, expirationDate);
    }


    private static void processCreditCard(CreditCardService service, CreditCard card, double amount) throws InvalidTransactionException {
        System.out.println();
        service.displayCard(card);

        if (amount <= 0) {
            throw new InvalidTransactionException("El monto de la transacción debe ser mayor que cero");
        }

        if (service.validateTransaction(amount)) {
            System.out.println("Transacción válida, no sobrepasa el límite.");
        } else {
            System.out.println("Transacción inválida, sobrepasa el límite.");
        }

        if (service.validateCard(card)) {
            System.out.println("Tarjeta N° " + card.getNumber() + " es válida para operar.");
        } else {
            System.out.println("Tarjeta N° " + card.getNumber() + " no es válida para operar.");
        }

        double rate = service.calculateRate(card, amount);
        System.out.println("Tarjeta " + card.getBrand() + " - " + "Monto: " + amount + " - " + "Tasa: " + rate);
    }

    private static boolean isValidCardNumber(String cardNumber) {
        return cardNumber.matches("\\d{4}-\\d{4}-\\d{4}-\\d{4}");
    }
}
