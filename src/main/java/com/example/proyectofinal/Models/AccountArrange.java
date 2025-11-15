package com.example.proyectofinal.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;


public class AccountArrange {

    private ArrayList<Account> accountsList;
    private static final String FILE_NAME = "accounts.txt";

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

        } catch (IOException e) {
            e.printStackTrace();
            return false;

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

    public Account findAccount(String accountNumber) {
        for (Account account : accountsList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public ArrayList<Account> getClientAccounts(String clientId) {
        ArrayList<Account> clientAccounts = new ArrayList<>();

        for (Account account : accountsList) {
            if (account.getClientId().equals(clientId)) {
                clientAccounts.add(account);
            }
        }

        return clientAccounts;
    }

    public Account createAccount(String clientId, String type, double initialBalance) {
        String accountNumber = generateAccountNumber();
        double interest = 0.0;
        double overdraft = 0.0;
        double volume = 0.0;

        switch (type) {
            case "SAVINGS":
                interest = 2.5;
                break;

            case "CHECKING":
                overdraft = 2000000.0;
                break;

            case "BUSINESS":
                volume = 0.0;
                break;
        }

        return new Account(
                accountNumber,
                type,
                initialBalance,
                clientId,
                interest,
                overdraft,
                volume
        );
    }

    public boolean addAccount(Account account) {
        accountsList.add(account);
        return saveAccounts();
    }

    public boolean deleteAccount(String accountNumber) {
        accountsList.removeIf(c -> c.getAccountNumber().equals(accountNumber));
        return saveAccounts();
    }

    public boolean deposit(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);

        if (account == null)
            return false;

        if (account.deposit(amount))
            return saveAccounts();

        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = findAccount(accountNumber);

        if (account == null)
            return false;

        if (account.withdraw(amount))
            return saveAccounts();

        return false;
    }

    public boolean transfer(String originNumber, String destinationNumber, double amount) {

        Account origin = findAccount(originNumber);
        Account destination = findAccount(destinationNumber);

        if (origin == null || destination == null)
            return false;

        double originalBalance = origin.getBalance();

        try {
            if (origin.withdraw(amount)) {

                if (destination.deposit(amount)) {

                    if (!saveAccounts()) {
                        origin.setBalance(originalBalance);
                        return false;
                    }

                    return true;

                } else {
                    origin.deposit(amount);
                    return false;
                }
            }

        } catch (Exception e) {
            origin.setBalance(originalBalance);
            return false;
        }

        return false;
    }

    public double checkBalance(String accountNumber) {
        Account account = findAccount(accountNumber);

        if (account != null)
            return account.getBalance();

        return -1;
    }

    public Account getAccount(String accountNumber) {
        return findAccount(accountNumber);
    }

    public void applySavingsInterest() {
        for (Account account : accountsList) {
            if ("SAVINGS".equals(account.getAccountType())) {
                account.chargeInterest();
            }
        }
        saveAccounts();
    }

    public boolean accountExists(String accountNumber) {
        return findAccount(accountNumber) != null;
    }

    public String getAccountInfo(String accountNumber) {
        Account account = findAccount(accountNumber);

        if (account == null) {
            return "Account not found";
        }

        StringBuilder info = new StringBuilder();
        info.append("Number: ").append(account.getAccountNumber()).append("\n");
        info.append("Type: ").append(account.getAccountType()).append("\n");
        info.append("Balance: $").append(String.format("%.2f", account.getBalance())).append("\n");
        info.append(account.getInfoSpecific());

        return info.toString();
    }
}
