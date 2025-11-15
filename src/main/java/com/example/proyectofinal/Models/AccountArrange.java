package com.example.proyectofinal.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class AccountArrange {

    private ArrayList<Account> accountsList;
    private static final String FILE_NAME = "accounts.txt";

    private final MovementArrange movementArrange = new MovementArrange();

    public AccountArrange() {
        accountsList = new ArrayList<>();
    }

    public ArrayList<Account> getAccountsList() {
        return accountsList;
    }

    public boolean loadAccounts() {
        accountsList.clear();
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return true;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 7) continue;
                Account account = new Account(
                        data[0],
                        data[1],
                        Double.parseDouble(data[2]),
                        data[3],
                        Double.parseDouble(data[4]),
                        Double.parseDouble(data[5]),
                        Double.parseDouble(data[6])
                );
                accountsList.add(account);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveAccounts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Account account : accountsList) {
                writer.println(
                        account.getAccountNumber() + "," +
                                account.getAccountType() + "," +
                                account.getBalance() + "," +
                                account.getClientId() + "," +
                                account.getInterestRate() + "," +
                                account.getOverdraftLimit() + "," +
                                account.getTransactionVolume()
                );
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String generateAccountNumber() {
        String base = String.format("%08d", new Random().nextInt(100000000));
        String accountNumber = "BUQ-" + base;
        while (accountExists(accountNumber)) {
            base = String.format("%08d", new Random().nextInt(100000000));
            accountNumber = "BUQ-" + base;
        }
        return accountNumber;
    }

    public Account getAccount(String accountNumber) {
        for (Account a : accountsList) {
            if (a.getAccountNumber().equals(accountNumber)) return a;
        }
        return null;
    }

    public ArrayList<Account> getClientAccounts(String clientId) {
        ArrayList<Account> list = new ArrayList<>();
        for (Account acc : accountsList) {
            if (acc.getClientId().equals(clientId)) list.add(acc);
        }
        return list;
    }

    public Account createAccount(String clientId, String type, double initialBalance) {
        String accountNumber = generateAccountNumber();
        double interest = 0;
        double overdraft = 0;
        double volume = 0;
        switch (type.toUpperCase()) {
            case "SAVINGS":
            case "SAVING":
                interest = 2.5;
                break;
            case "CHECKING":
                overdraft = 2000000.0;
                break;
            case "BUSINESS":
                volume = 0.0;
                break;
        }
        return new Account(accountNumber, type.toUpperCase(), initialBalance, clientId, interest, overdraft, volume);
    }

    public boolean addAccount(Account account) {
        accountsList.add(account);
        return saveAccounts();
    }

    public boolean deleteAccount(String accountNumber) {
        accountsList.removeIf(acc -> acc.getAccountNumber().equals(accountNumber));
        return saveAccounts();
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account == null) return false;
        if (amount <= 0) return false;
        boolean ok = account.deposit(amount);
        if (!ok) return false;
        movementArrange.addMovement(accountNumber, "DEPOSIT", "N/A", amount);
        return saveAccounts();
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        if (account == null) return false;
        if (amount <= 0) return false;
        boolean ok = account.withdraw(amount);
        if (!ok) return false;
        movementArrange.addMovement(accountNumber, "WITHDRAW", "N/A", amount);
        return saveAccounts();
    }

    public boolean transfer(String originNumber, String destinationNumber, double amount) {
        if (originNumber == null || destinationNumber == null) return false;
        if (originNumber.equals(destinationNumber)) return false;
        if (amount <= 0) return false;

        Account origin = getAccount(originNumber);
        Account destination = getAccount(destinationNumber);
        if (origin == null || destination == null) return false;

        if (!canWithdraw(origin, amount)) return false;

        double originBalanceBefore = origin.getBalance();
        double destinationBalanceBefore = destination.getBalance();

        if (!origin.withdraw(amount)) return false;

        if (!destination.deposit(amount)) {
            origin.setBalance(originBalanceBefore);
            return false;
        }

        movementArrange.addMovement(originNumber, "TRANSFER_OUT", destinationNumber, amount);
        movementArrange.addMovement(destinationNumber, "TRANSFER_IN", originNumber, amount);

        if (!saveAccounts()) {
            // rollback
            origin.setBalance(originBalanceBefore);
            destination.setBalance(destinationBalanceBefore);
            return false;
        }
        return true;
    }

    private boolean canWithdraw(Account acc, double amount) {
        if (acc == null || amount <= 0) return false;
        if ("CHECKING".equalsIgnoreCase(acc.getAccountType())) {
            return acc.getBalance() + acc.getOverdraftLimit() >= amount;
        } else {
            return acc.getBalance() >= amount;
        }
    }

    public double checkBalance(String accountNumber) {
        Account acc = getAccount(accountNumber);
        return (acc != null) ? acc.getBalance() : -1;
    }

    public boolean accountExists(String accountNumber) {
        return getAccount(accountNumber) != null;
    }

    public void applySavingsInterest() {
        for (Account acc : accountsList) {
            if ("SAVINGS".equalsIgnoreCase(acc.getAccountType()) || "SAVING".equalsIgnoreCase(acc.getAccountType())) {
                acc.chargeInterest();
            }
        }
        saveAccounts();
    }

    public String getAccountInfo(String accountNumber) {
        Account acc = getAccount(accountNumber);
        if (acc == null) return "Account not found";
        return "Number: " + acc.getAccountNumber() + "\n"
                + "Type: " + acc.getAccountType() + "\n"
                + "Balance: $" + String.format("%.2f", acc.getBalance()) + "\n"
                + acc.getInfoSpecific();
    }
}
