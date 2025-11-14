package com.example.proyectofinal.Models;

public class Account {
    private String accountNumber;
    private String accountType;
    private double balance;
    private String clientId;
    private double interestRate;
    private double overdraftLimit;
    private double transactionVolume;

    public Account (String accountNumber , String accountType , double balance, String clientId ,
                    double interestRate, double overdraftLimit, double transactionVolume) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.clientId = clientId;
        this.interestRate = interestRate;
        this.overdraftLimit = overdraftLimit;
        this.transactionVolume = transactionVolume;
    }

    public double getTransactionVolume() {
        return transactionVolume;
    }

    public void setTransactionVolume(double transactionVolume) {
        this.transactionVolume = transactionVolume;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public boolean deposit (double amount) {
        if (amount > 0) {
            this.balance += amount;
        }
        if("BUSINESS".equals(this.accountType)) {
            this.transactionVolume += amount;
        }
        return true;
    }
    public boolean withdraw (double amount) {
        if (amount <= 0) {
            return false;
        }
        if ("CHECKING".equals(this.accountType)) {
            if(this.balance + this.overdraftLimit >= amount){
                this.balance -= amount;
                return true;
            }
            return false;
        }
        if(this.balance >= amount){
            this.balance -= amount;
            if("BUSINESS".equals(this.accountType)) {
                this.transactionVolume += amount;
            }
            return true;
        }
        return false;
    }
    public void chargeInterest () {
        if ("SAVINGS".equals(this.accountType)){
            double interest = this.balance * (this.interestRate/100);
            this.balance += interest;
        }
    }
    public String getInfoSpecific () {
        switch (this.accountType) {
            case "SAVINGS":
                return "Interest rate: " + interestRate + "%";
            case "CHECKING":
                return "Available overdraft limit: $ " + String.format("%.2f", overdraftLimit);
            case "BUSINESS":
                return "Transaction volume: $ " + String.format("%.2f", transactionVolume);
            default:
                return "";
        }
    }
    @Override
    public String toString() {
        return accountNumber + " - " + accountType + " -$ " + String.format("%.2f", balance);
    }

}
