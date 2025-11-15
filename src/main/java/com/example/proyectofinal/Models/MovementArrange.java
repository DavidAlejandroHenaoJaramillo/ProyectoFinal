package com.example.proyectofinal.Models;

import java.io.*;
import java.util.ArrayList;

public class MovementArrange {

    private static final String FILE_NAME = "movements.txt";


    public void saveMovement(Movement m) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {

            writer.println(
                    m.getDate() + "," +
                            m.getType() + "," +
                            m.getRelatedAccount() + "," +
                            m.getAmount() + "," +
                            m.getAccountNumber()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void addMovement(String accountNumber, String type, String relatedAccount, double amount) {

        Movement m = new Movement(
                java.time.LocalDate.now().toString(),  // Fecha actual
                type,
                relatedAccount,
                amount,
                accountNumber
        );

        saveMovement(m);
    }



    public ArrayList<Movement> loadAllMovements() {
        ArrayList<Movement> list = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists())
            return list;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;
            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");
                if (data.length < 5) continue;

                Movement m = new Movement(
                        data[0],
                        data[1],
                        data[2],
                        Double.parseDouble(data[3]),
                        data[4]
                );

                list.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }


    public ArrayList<Movement> getMovementsOfAccount(String accountNumber) {

        ArrayList<Movement> all = loadAllMovements();
        ArrayList<Movement> result = new ArrayList<>();

        for (Movement m : all) {
            if (m.getAccountNumber().equals(accountNumber)) {
                result.add(m);
            }
        }

        return result;
    }
}
