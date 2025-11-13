package com.example.proyectofinal.Models;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class ManagementAccount {
    private ArrayList<Account> accountsList;
    private static final String FILE = "accounts.txt";

    public ManagementAccount() {
        accountsList = new ArrayList<>();
    }
    public ArrayList<Account> getAccountsList() {
        return accountsList;
    }
    public boolean loadAccounts(){
        accountsList.clear();
        File file = new File(FILE);
        if(!file.exists()){
            return true;
        }
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine())!=null){
                String[] data = line.split(",");
                if(data.length < 7)continue;
                Account account = new Account(
                        data[0], data[1], Double.parseDouble(data[2]),data[3],
                        Double.parseDouble(data[4]),Double.parseDouble(data[5]),Double.parseDouble(data[6])
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
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE))) {

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
        String accountNumber = "BUQ-"+base;
        while(accountExists(accountNumber)) {
            base = String.format("%08d", new Random().nextInt(100000000));
            accountNumber = "BUQ-" + base;
        }
        return accountNumber;
    }
    public Account searchAccount(String accountNumber) {
        for (Account account : accountsList) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    public ArrayList<Account> getClientAccount(String clientId) {
        ArrayList<Account> clientAccounts = new ArrayList<>();

        for (Account account : accountsList) {
            if (account.getClientId().equals(clientId)) {
                clientAccounts.add(account);
            }
        }

        return clientAccounts;
    }
    public Account createAccount(String clientId, String type, double openingBalance) {
        String numberAccount = generateAccountNumber();
        double interest = 0.0;
        double overDraft = 0.0;
        double volume = 0.0;

        switch (type) {
            case "SAVING":
                interest = 2.5;
                break;
            case "CHECKING":
                overDraft = 2000000.0;
                break;
            case "BUSINESS":
                volume = 0.0;
                break;
        }
        return new Account(
                numberAccount,
                type,
                openingBalance,
                clientId,
                interest,
                overDraft,
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
        Account account = searchAccount(accountNumber);

        if (account == null) {
            return false;
        }

        if (account.deposit(amount)) {
            return saveAccounts();
        }

        return false;
    }
    public boolean withdraw(String accountNumber, double amount) {
        Account account = searchAccount(accountNumber);

        if (account == null) {
            return false;
        }

        if (account.withdraw(amount)) {
            return saveAccounts();
        }

        return false;
    }


    public boolean makeTransfers(String sourceAccountNumber, String destinationAccountNumber, double amount) {
        Account sourceAccount = searchAccount(sourceAccountNumber);
        Account destinationAccount = searchAccount(destinationAccountNumber);

        if (sourceAccount == null || destinationAccount == null || amount <= 0) {
            return false;
        }


        if (sourceAccount.withdraw(amount)) {
            if(destinationAccount.deposit(amount)){
                return saveAccounts();
            } else{
                sourceAccount.deposit(amount);
                return false;
            }


        }

        return false;
    }

    public double checkBalance (String accountNumber) {
        Account account = searchAccount(accountNumber);

        if (account != null) {
            return account.getBalance();
        }

        return -1;
    }

    public void applyInterestRate(String accountNumber) {
        for (Account account : accountsList) {
            if ("SAVING".equals(account.getAccountType())) {
                account.chargeInterest();
            }
        }
        saveAccounts();
    }

    public boolean accountExists(String accountNumber) {
        return searchAccount(accountNumber) != null;
    }

    public String getInfoAccount(String accountNumber) {
        Account account= searchAccount(accountNumber);

        if (account == null) {
            return "Account not found";
        }
        StringBuilder info = new StringBuilder();
        info.append("Number: ").append(account.getAccountNumber()).append("\n");
        info.append("Type: ").append(account.getAccountType()).append("\n");
        info.append("Balance: $").append(String.format("%.2f", account.getBalance())).append("\n");;
        info.append(account.getInfoSpecific());

        return info.toString();
    }
}
