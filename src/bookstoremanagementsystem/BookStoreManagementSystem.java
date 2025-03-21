/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem;

import bookstoremanagementsystem.models.CartItems;
import bookstoremanagementsystem.services.CartItemsManager;
import java.util.InputMismatchException;
import bookstoremanagementsystem.interfaces.IAccount;
import bookstoremanagementsystem.interfaces.IAuthors;
import bookstoremanagementsystem.interfaces.IGenres;
import bookstoremanagementsystem.interfaces.IMenu;
import bookstoremanagementsystem.models.Accounts;
import bookstoremanagementsystem.models.BookAuthors;
import bookstoremanagementsystem.models.OrderStatus;
import bookstoremanagementsystem.models.Orders;
import bookstoremanagementsystem.services.AccountManager;
import bookstoremanagementsystem.services.AuthorManager;
import bookstoremanagementsystem.services.BookAuthorsManager;
import bookstoremanagementsystem.services.GenresManager;
import bookstoremanagementsystem.services.MenuManager;
import bookstoremanagementsystem.services.MenuManager.LogInForm;
import bookstoremanagementsystem.services.MenuManager.RegisterForm;
import bookstoremanagementsystem.services.OrderStatusManager;
import bookstoremanagementsystem.services.OrdersManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookStoreManagementSystem {

    public static void main(String[] args) {

        //Log in + Show menu for type account
        Scanner sc = new Scanner(System.in);
        int userChoice;
        try {
            IMenu menu = new MenuManager();
            IAccount accountManager = new AccountManager();
            menu.showWelcomeMenu();
            userChoice = sc.nextInt();
            if (userChoice == 1) {
                accountManager.LoadAccountProfile();
                LogInForm logInValue = menu.showLogInMenu();
                System.out.println("accountManager" + accountManager);
                int acc = accountManager.LogIn(logInValue.getEmail(), logInValue.getPassword());
                System.out.println("Login " + acc);

                //acc = 1 is admin log
                if (acc == 1) {
                    menu.showAdminMenu();
                    try {
                        userChoice = sc.nextInt();
                        switch (userChoice) {
                            //Account Management choice
                            case 1:
                                do {
                                    menu.showAccountMainMenu();
                                    accountManager.LoadAccountProfile();
                                    userChoice = sc.nextInt();
                                    switch (userChoice) {
                                        case 1:
                                            accountManager.showAccountList();
                                            break;
                                        case 2:
                                            List<Accounts> list = new ArrayList<>();
                                            list = accountManager.searchAccount(menu.showAccountSearchForm());
                                            accountManager.showAccountListByList(list);
                                            break;
                                        case 3:
                                            RegisterForm registerValue = menu.showRegisterMenu();
                                            accountManager.registerAccount(registerValue);
                                            accountManager.LoadAccountProfile();
                                            break;
                                        case 4:
                                            menu.showAccountUpdateForm();
                                            accountManager.LoadAccountProfile();
                                            break;
                                    }
                                } while (userChoice != 5);
                                break;
                            case 2:
                                break;
                            case 3:

                                break;
                            case 4:
                                IGenres genreManager = new GenresManager();
                                do {
                                    menu.showGenreMainMenu();
                                    genreManager.loadGenresFile();
                                    userChoice = sc.nextInt();
                                    switch (userChoice) {
                                        case 1:
                                            genreManager.showGenreList();
                                            break;
                                        case 2:
                                            genreManager.showGenreDetail();
                                            break;
                                        case 3:
                                            genreManager.createGenre();
                                            break;
                                        case 4:
                                            genreManager.updateGenre();
                                            break;
                                        case 5:
                                            genreManager.removeGenre();
                                            break;
                                    }
                                } while (userChoice != 6);
                                break;
                            case 5:
                                break;
                        }

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                } else if (acc == 0) {
                    menu.showUserMenu();
                } else {
                    System.out.printf("%10s\tEmail or password not true ", "");
                }
            } else {
                accountManager.LoadAccountProfile();
                RegisterForm registerValue = menu.showRegisterMenu();
                accountManager.registerAccount(registerValue);
            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }
}
