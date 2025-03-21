/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IAccount;
import bookstoremanagementsystem.interfaces.IMenu;
import bookstoremanagementsystem.models.Accounts;
import java.util.Scanner;

/**
 *
 * @author kiara
 */
public class MenuManager implements IMenu {

    private String etc = "";

    @Override
    public void showAdminMenu() {
        System.out.printf("\n\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*------------------!! BOOK STORE MANAGER !!-----------------*\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s*\t1. Account Manager%36s*\n", etc, etc);
        System.out.printf("%10s*\t2. Book Manager%39s*\n", etc, etc);
        System.out.printf("%10s*\t3. Author Manager%37s*\n", etc, etc);
        System.out.printf("%10s*\t4. Genre Manager%38s*\n", etc, etc);
        System.out.printf("%10s*\t5. Publisher Manager%34s*\n", etc, etc);
        System.out.printf("%10s*\t6. RentalSlip Manager%33s*\n", etc, etc);
        System.out.printf("%10s*\t7. Order Manager%38s*\n", etc, etc);
        System.out.printf("%10s*\t8. Logout%45s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s\tEnter choice: ", etc);
    }

    @Override
    public void showUserMenu() {
        System.out.printf("\n\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*---------------------!! BOOK STORE !!----------------------*\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s*\t1. Profile%44s*\n", etc, etc);
        System.out.printf("%10s*\t2. View Books%41s*\n", etc, etc);
        System.out.printf("%10s*\t3. Cart%47s*\n", etc, etc);
        System.out.printf("%10s*\t4. View Orders%40s*\n", etc, etc);
        System.out.printf("%10s*\t5. View RentalSlip%36s*\n", etc, etc);
        System.out.printf("%10s*\t6. Logout%45s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s\tEnter choice: ", etc);
    }

    @Override
    public void showWelcomeMenu() {
        System.out.printf("\n\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*---------------------!! BOOK STORE !!----------------------*\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s*\t1. Log in%45s*\n", etc, etc);
        System.out.printf("%10s*\t2. Sign up%44s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s\tEnter choice: ", etc);
    }

    @Override
    public LogInForm showLogInMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("%10s\tEmail: ", etc);
        String userEmail = sc.nextLine();
        System.out.printf("%10s\tPassword: ", etc);
        String userPassword = sc.nextLine();
        LogInForm logIn = new LogInForm(userEmail, userPassword);
        return logIn;
    }

    @Override
    public void showGenreMainMenu() {
        System.out.printf("\n\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*------------------!! BOOK STORE MANAGER !!-----------------*\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*%18s||%3sGenres manager%3s||%17s*\n", etc, etc, etc, etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s*\t1. Genre List%41s*\n", etc, etc);
        System.out.printf("%10s*\t2. Genre Detail%39s*\n", etc, etc);
        System.out.printf("%10s*\t3. Create New Genre%35s*\n", etc, etc);
        System.out.printf("%10s*\t4. Update Genre%39s*\n", etc, etc);
        System.out.printf("%10s*\t5. Delete Genre%39s*\n", etc, etc);
        System.out.printf("%10s*\t6. Back to main menu%34s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s\tEnter choice: ", etc);
    }

    @Override
    public RegisterForm showRegisterMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%10s\tEnter Account ID: ", etc);
        String accountID = scanner.nextLine();
        System.out.printf("%10s\tEnter Full Name: ", etc);
        String fullName = scanner.nextLine();
        System.out.printf("%10s\tEnter Email: ", etc);
        String email = scanner.nextLine();
        System.out.printf("%10s\tEnter Password: ", etc);
        String password = scanner.nextLine();
        System.out.printf("%10s\tEnter Phone Number: ", etc);
        String phoneNumber = scanner.nextLine();
        System.out.printf("%10s\tEnter Address: ", etc);
        String address = scanner.nextLine();
        RegisterForm register = new RegisterForm(accountID, fullName, email, password, phoneNumber, address);
        return register;
    }

    @Override
    public void showAccountMainMenu() {
        System.out.printf("\n\n%10s*************************************************************\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*------------------!! BOOK STORE MANAGER !!-----------------*\n", etc);
        System.out.printf("%10s*%59s*\n", etc, etc);
        System.out.printf("%10s*%17s||%3sAccount manager%3s||%16s*\n", etc, etc, etc, etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s*\t1. Account List%39s*\n", etc, etc);
        System.out.printf("%10s*\t2. Account Detail%37s*\n", etc, etc);
        System.out.printf("%10s*\t3. Create New Account%33s*\n", etc, etc);
        System.out.printf("%10s*\t4. Update Account%37s*\n", etc, etc);
        System.out.printf("%10s*\t5. Back to main menu%34s*\n", etc, etc);
        System.out.printf("%10s*************************************************************\n", etc);
        System.out.printf("%10s\tEnter choice: ", etc);
    }

    @Override
    public String showAccountSearchForm() {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%10s\tEnter Search Detail: ", etc);
        String searchDetail = scanner.nextLine();
        return searchDetail;
    }

    @Override
    public void showAccountUpdateForm() {
        IAccount accountManagement = new AccountManager();
        Scanner scanner = new Scanner(System.in);
        System.out.printf("%10s\tEnter Search Detail: ", etc);
        String searchDetail = scanner.nextLine();
        Accounts acc = accountManagement.searchAccountByEmail(searchDetail);
        
            System.out.println("Update ne " + acc.toString());
        if (acc == null) {
            System.out.printf("%10sNo accounts found!", "");
            return;
        } else {
            System.out.println("----- Press Enter if you do not want to change that data -----");

            System.out.printf("%10s\tEnter Full Name [%s]: ", etc, acc.getFullName());
            String fullName = scanner.nextLine().trim();
            if (fullName.isEmpty()) {
                fullName = acc.getFullName();
            }

            System.out.printf("%10s\tEnter Email [%s]: ", etc, acc.getEmail());
            String email = scanner.nextLine().trim();
            if (email.isEmpty()) {
                email = acc.getEmail();
            }

            System.out.printf("%10s\tEnter Phone Number [%s]: ", etc, acc.getPhoneNumber());
            String phoneNumber = scanner.nextLine().trim();
            if (phoneNumber.isEmpty()) {
                phoneNumber = acc.getPhoneNumber();
            }

            System.out.printf("%10s\tEnter Address [%s]: ", etc, acc.getAddress());
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                address = acc.getAddress();
            }

            acc.setFullName(fullName);
            acc.setEmail(email);
            acc.setPhoneNumber(phoneNumber);
            acc.setAddress(address);

            accountManagement.UpdateAccount(acc);
            
        }

    }

    public class LogInForm {

        private String Email;
        private String Password;

        public LogInForm(String Email, String Password) {
            this.Email = Email;
            this.Password = Password;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

    }

    public class RegisterForm {

        String accountID;
        String fullName;
        String email;
        String password;
        String phoneNumber;
        String address;

        public RegisterForm(String accountID, String fullName, String email, String password, String phoneNumber, String address) {
            this.accountID = accountID;
            this.fullName = fullName;
            this.email = email;
            this.password = password;
            this.phoneNumber = phoneNumber;
            this.address = address;
        }

        public String getAccountID() {
            return accountID;
        }

        public void setAccountID(String accountID) {
            this.accountID = accountID;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

    }

}
