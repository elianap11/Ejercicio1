package org.example;

import java.time.LocalDate;
import java.util.Objects;

public class CreditCard {

    private CreditCardBrand brand;
    private String number;
    private String cardHolder;
    private LocalDate expirationDate;

    public CreditCard() {
    }

    public CreditCard(CreditCardBrand brand, String number, String cardHolder, LocalDate expirationDate) {
        this.brand = brand;
        this.number = number;
        this.cardHolder = cardHolder;
        this.expirationDate = expirationDate;
    }

    public CreditCardBrand getBrand() {
        return brand;
    }

    public void setBrand(CreditCardBrand brand) {
        this.brand = brand;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Tarjeta de crédito. Marca: " + brand + ", Número: " + number + ", Nombre: "
                + cardHolder + ", Vencimiento: " + expirationDate + ".";
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditCard that = (CreditCard) o;
        return brand == that.brand && Objects.equals(number, that.number) && Objects.equals(cardHolder, that.cardHolder) && Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, number, cardHolder, expirationDate);
    }
}
