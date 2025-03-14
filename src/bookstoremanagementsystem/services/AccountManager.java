/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IAccount;
import bookstoremanagementsystem.models.Accounts;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public class AccountManager implements IAccount {
    private List<Accounts> accountsList = new ArrayList<>(); 

    @Override
    public void LoadAccountProfile() {
        String accountFile = "accounts.txt";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try (BufferedReader reader = new BufferedReader(new FileReader(accountFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.replace("Accounts(", "").replace(")", "").trim();

                String[] parts = line.split(", (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (parts.length == 8) {
                    String accountID = parts[0].trim();
                    String fullName = parts[1].trim();
                    String email = parts[2].trim();
                    String password = parts[3].trim();
                    String phoneNumber = parts[4].trim();
                    String address = parts[5].trim().replace("\"", "");
                    String role = parts[6].trim().replace("\"", "");
                    LocalDate createdAt = LocalDate.parse(parts[7].trim(), fmt);

                    accountsList.add(new Accounts(accountID, fullName, email, password, phoneNumber, address, role, createdAt));
                }
            }
        } catch (IOException e) {
            System.err.println("Error occurs when reading account file: " + e.getMessage());
        }
//        return accountsList;
    }

    // admin login = 1, user = 0, no account = -1
    @Override
    public int LogIn(String email, String password) {
        for (Accounts accounts : accountsList) {
            if(accounts.getEmail().equals(email) && accounts.getPassword().equals(password)){
                if(accounts.getRole().equals("Admin")) return 1;
                else if (accounts.getRole().equals("User")) return 0;
            }
        }
        return -1;
    }

}
