package com.example.proyectofinal.Models;

public class Movement {

    private String date;
    private String type;
    private String relatedAccount;
    private double amount;
    private String accountNumber;

    public Movement(String date, String type, String relatedAccount, double amount, String accountNumber) {
        this.date = date;
        this.type = type;
        this.relatedAccount = relatedAccount;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public String getRelatedAccount() {
        return relatedAccount;
    }

    public double getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}
