/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IAccount;
import bookstoremanagementsystem.models.Accounts;
import bookstoremanagementsystem.services.MenuManager.RegisterForm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author nhulnt
 */
public class AccountManager implements IAccount {

    private List<Accounts> accountsList = new ArrayList<>();
    private Accounts accountAdmin;

    private final String accountFile = "accounts.txt";

    @Override
    public void LoadAccountProfile() {
        accountsList.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(accountFile))) {
            if (accountsList.size() > 0) {
                accountsList.clear();
            }
            try {
                //add user account
                File f = new File(accountFile);
                if (!f.exists()) {
                    return;
                }
                FileInputStream fi = new FileInputStream(f);
                ObjectInputStream fo = new ObjectInputStream(fi);
                Accounts acc;
                while ((acc = (Accounts) (fo.readObject())) != null) {
                    accountsList.add(acc);
                }
                fo.close();
                fi.close();

            } catch (FileNotFoundException e) {
                System.out.println("Not found the file load.");
            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(AccountManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException e) {
//            System.err.println("Error occurs when reading account file: " + e.getMessage());
        }
//        System.out.println("Accounts loaded: " + accountsList.size());
    }

    @Override
    public int LogIn(String email, String password) {
        for (Accounts accounts : accountsList) {
            if (accounts.getEmail().equals(email) && accounts.getPassword().equals(password)) {
                if (accounts.getRole().equals("User")) {
                    return 0;
                } else if (accounts.getRole().equals("Admin")) {
                    return 1;
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

    public void deleteAccount(String email) {
        boolean removed = accountsList.removeIf(acc -> acc.getEmail().equalsIgnoreCase(email));
        if (removed) {
            saveToFile();
            System.out.println("Account deleted successfully!");
        } else {
            System.out.println("Account not found!");
        }
    }

    @Override
    public void showAccountList() {
        LoadAccountProfile();
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*--------------------!! ACCOUNT LIST !!--------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", "ID", "Name", "Email");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (accountsList.isEmpty()) {
            System.out.printf("%10sNo accounts found!", "");
            return;
        }
        for (Accounts acc : accountsList) {
            if (acc.getRole().equals("User")) {
                System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", acc.getAccountID(), acc.getFullName(), acc.getEmail());
                System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", "", "Phone: " + acc.getPhoneNumber(), "Address: " + acc.getAddress());
                System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", "", "Role: " + acc.getRole(), "Password: " + acc.getPassword());
                System.out.printf("%10s-------------------------------------------------------------\n", "");
            }
        }
    }

    @Override
    public void registerAccount(RegisterForm registerForm) {
        LoadAccountProfile();
        LocalDate createdAt = LocalDate.now();
        accountsList.add(new Accounts(registerForm.getAccountID(), registerForm.getFullName(), registerForm.getEmail(), registerForm.getPassword(), registerForm.getPhoneNumber(), registerForm.getAddress(), "User", createdAt));
        saveToFile();
        System.out.printf("%10sAccount added successfully!", "");
    }

    @Override
    public void saveToFile() {
        try {
            FileOutputStream f = new FileOutputStream(accountFile);
            ObjectOutputStream fo = new ObjectOutputStream(f);
            for (Accounts account : accountsList) {
                fo.writeObject(account);
            }
            //fo.writeObject(accountAdmin);
            fo.close();
            f.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public List<Accounts> searchAccount(String searchDetail) {
        List<Accounts> list = new ArrayList<>();

        for (Accounts acc : accountsList) {
            if (acc.getAccountID().toLowerCase().contains(searchDetail.toLowerCase())
                    || acc.getFullName().toLowerCase().contains(searchDetail.toLowerCase())
                    || acc.getEmail().toLowerCase().contains(searchDetail.toLowerCase())
                    || acc.getPhoneNumber().toLowerCase().contains(searchDetail.toLowerCase())) {
                list.add(acc);
            }
        }
        return list;
    }

    @Override
    public void showAccountListByList(List<Accounts> list) {
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*-------------------!! SEARCH ACCOUNT !!-------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", "ID", "Name", "Email");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (list.isEmpty()) {
            System.out.printf("%10sNo accounts found!", "");
            return;
        }
        for (Accounts acc : list) {
            if (acc.getRole().equals("User")) {
                System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", acc.getAccountID(), acc.getFullName(), acc.getEmail());
                System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", "", "Phone: " + acc.getPhoneNumber(), "Address: " + acc.getAddress());
                System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", "", "Role: " + acc.getRole(), "Password: " + acc.getPassword());
                System.out.printf("%10s-------------------------------------------------------------\n", "");
            }
        }
    }

    @Override
    public Accounts searchAccountByEmail(String email) {
        LoadAccountProfile();
        for (Accounts acc : accountsList) {
            if (acc.getEmail().equalsIgnoreCase(email)) {
                return acc;
            }
        }
        return null;
    }

    @Override
    public void UpdateAccount(Accounts model) {
        for (int i = 0; i < accountsList.size(); i++) {
            if (accountsList.get(i).getAccountID().equals(model.getAccountID())) {
                accountsList.set(i, model);
                System.out.printf("%10sAccount updated successfully!\n", "");
            }
        }
        saveToFile();
    }

    @Override
    public void showAccountDetails(Accounts account) {
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*------------------!! ACCOUNT DETAILS !!------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");

        System.out.printf("%10s| %-15s: %-40s |\n", "", "Account ID", account.getAccountID());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Full Name", account.getFullName());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Email", account.getEmail());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Phone Number", account.getPhoneNumber());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Address", account.getAddress());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Created At", account.getCreatedAt());

        System.out.printf("%10s*************************************************************\n", "");
    }

    @Override
    public boolean isvalidPhoneNumber(String phoneNumber) {
        LoadAccountProfile();
        String phoneRegex = "^[0-9]{10}$";
        Pattern phonePattern = Pattern.compile(phoneRegex);
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        if (phonePattern.matcher(phoneNumber).matches()) {

            for (Accounts accounts : accountsList) {
                if (accounts.getPhoneNumber().equals(phoneNumber)) {
                    return false;
                }
            }
        }
        return phonePattern.matcher(phoneNumber).matches();
    }

    @Override
    public boolean isvalidEmail(String email) {
        LoadAccountProfile();
        String emailregex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        Pattern emailPattern = Pattern.compile(emailregex);
        if (email == null || email.isEmpty()) {
            return false;
        }
        if (emailPattern.matcher(email).matches()) {

            for (Accounts accounts : accountsList) {
                if (accounts.getEmail().equals(email)) {
                    return false;
                }
            }
        }
        return emailPattern.matcher(email).matches();
    }
}
