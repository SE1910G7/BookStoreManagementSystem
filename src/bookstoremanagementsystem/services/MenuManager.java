/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IMenu;
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

}
