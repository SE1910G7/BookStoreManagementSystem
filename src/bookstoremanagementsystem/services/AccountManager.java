/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IAccount;
import bookstoremanagementsystem.models.Accounts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public class AccountManager implements IAccount {

    private List<Accounts> accountsList = new ArrayList<>();
    private final String accountFile = "accounts.txt";
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    public void LoadAccountProfile() {
        accountsList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(accountFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
        System.out.println("Accounts loaded: " + accountsList.size());
    }

    @Override
    public int LogIn(String email, String password) {
        for (Accounts accounts : accountsList) {
            if (accounts.getEmail().equals(email) && accounts.getPassword().equals(password)) {
                if (accounts.getRole().equals("Admin")) {
                    return 1;
                } else if (accounts.getRole().equals("User")) {
                    return 0;
                }
            }
        }
        return -1;
    }

    public void updatePassword(String email, String newPassword) {
        boolean found = false;
        for (Accounts acc : accountsList) {
            if (acc.getEmail().equalsIgnoreCase(email)) {
                acc.setPassword(newPassword);
                found = true;
                break;
            }
        }
        if (found) {
            saveToFile();
            System.out.println("Password updated successfully!");
        } else {
            System.out.println("Account not found!");
        }
    }

    public void deleteAccount(String email) { // Xóa tài khoản
        boolean removed = accountsList.removeIf(acc -> acc.getEmail().equalsIgnoreCase(email));
        if (removed) {
            saveToFile();
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not found!");
        }
    }

    public void displayAccounts() { // Hiển thị danh sách tài khoản đầy đủ
        if (accountsList.isEmpty()) {
            System.out.println("No accounts found!");
            return;
        }
        System.out.println("\n=== ACCOUNT LIST ===");
        for (Accounts acc : accountsList) {
            System.out.println("ID: " + acc.getAccountID()
                    + " | Name: " + acc.getFullName()
                    + " | Email: " + acc.getEmail()
                    + " | Password: " + acc.getPassword()
                    + " | Phone Number: " + acc.getPhoneNumber()
                    + " | Address: " + acc.getAddress()
                    + " | Role: " + acc.getRole());
        }
    }

    public void addAccount(String accountID, String fullName, String email, String password, String phoneNumber, String address, String role) { // Thêm tài khoản mới
        LocalDate createdAt = LocalDate.now();
        accountsList.add(new Accounts(accountID, fullName, email, password, phoneNumber, address, role, createdAt));
        saveToFile();
        System.out.println("Account added successfully!");
    }

    public void saveToFile() { // Lưu danh sách tài khoản vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(accountFile))) {
            for (Accounts acc : accountsList) {
                writer.write(acc.getAccountID() + ", " + acc.getFullName() + ", "
                        + acc.getEmail() + ", " + acc.getPassword() + ", "
                        + acc.getPhoneNumber() + ", \"" + acc.getAddress() + "\", \""
                        + acc.getRole() + "\", " + acc.getCreatedAt().format(fmt));
                writer.newLine();
            }
            System.out.println("Accounts saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving account file: " + e.getMessage());
        }
    }
}
