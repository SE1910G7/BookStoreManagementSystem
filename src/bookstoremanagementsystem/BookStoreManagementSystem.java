/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem;

import bookstoremanagementsystem.interfaces.IAccount;
import bookstoremanagementsystem.interfaces.IAuthors;
import bookstoremanagementsystem.interfaces.IGenres;
import bookstoremanagementsystem.interfaces.IMenu;
import bookstoremanagementsystem.models.BookAuthors;
import bookstoremanagementsystem.services.AccountManager;
import bookstoremanagementsystem.services.AuthorManager;
import bookstoremanagementsystem.services.BookAuthorsManager;
import bookstoremanagementsystem.services.GenresManager;
import bookstoremanagementsystem.services.MenuManager;
import bookstoremanagementsystem.services.MenuManager.LogInForm;
import java.util.Scanner;

/**
 *
 * @author mummykiara
 */
public class BookStoreManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Diem Tran Code main
//        try (Scanner scanner = new Scanner(System.in)) {
//            IAuthors authorManager = new AuthorManager();
//
//            int choice;
//            String authorID, fullName, searchInput, newAuthorID, newFullName;
//            do {
//                System.out.println("**************************************");
//                System.out.println("!! AUTHOR MANAGEMENT !!");
//                System.out.println("1. Add Author");
//                System.out.println("2. Display Authors");
//                System.out.println("3. Sort by Author Name");
//                System.out.println("4. Search Author");
//                System.out.println("5. Delete Author");
//                System.out.println("6. Edit Author");
//                System.out.println("7. Exit");
//                System.out.print("Enter Option (1-7): ");
//                choice = scanner.nextInt();
//                scanner.nextLine();
//
//                switch (choice) {
//                    case 1:
//                        System.out.print("Enter Author ID: ");
//                        authorID = scanner.nextLine();
//                        System.out.print("Enter Author Full Name: ");
//                        fullName = scanner.nextLine();
//                        authorManager.addAuthor(authorID, fullName);
//                        break;
//                    case 2:
//                        authorManager.displayAuthors();
//                        break;
//                    case 3:
//                        authorManager.sortAuthors();
//                        break;
//                    case 4:
//                        System.out.print("Enter Author Name or ID to search: ");
//                        searchInput = scanner.nextLine();
//                        authorManager.searchAuthor(searchInput);
//                        break;
//                    case 5:
//                        System.out.print("Enter Author Name or ID to delete: ");
//                        searchInput = scanner.nextLine();
//                        authorManager.deleteAuthor(searchInput);
//                        break;
//                    case 6:
//                        System.out.print("Enter Author Name or ID to edit: ");
//                        searchInput = scanner.nextLine();
//                        System.out.print("Enter new Author ID: ");
//                        newAuthorID = scanner.nextLine();
//                        System.out.print("Enter new Author Full Name: ");
//                        newFullName = scanner.nextLine();
//                        authorManager.editAuthor(searchInput, newAuthorID, newFullName);
//                        break;
//                    case 7:
//                        System.out.println("Exiting...");
//                        authorManager.saveAuthorsToFile();
//                        break;
//                    default:
//                        System.out.println("Invalid option. Try again.");
//                }
//            } while (choice != 7);
//        }

        //----------------------------------------------------------------------------
        //Tai Code main
//        BookAuthorsManager service = new BookAuthorsManager();
//
//        // Adding authors
//        service.addAuthor(new BookAuthors("1", "101", "201"));
//        service.addAuthor(new BookAuthors("2", "102", "202"));
//
//        // Viewing authors
//        service.view();
//
//        // Updating an author
//        service.updateAuthor("1", new BookAuthors("1", "101", "203"));
//
//        // Viewing after update
//        service.view();
//
//        // Deleting an author
//        service.deleteAuthor("2");
//
//        // Viewing after delete
//        service.view();
        //----------------------------------------------------------------------------
        //----------------------------------------------------------------------------
        //Nhu Code main
//        IAccount accountManager = new AccountManager();
//        accountManager.LoadAccountProfile();
//        int acc = accountManager.LogIn("abc@example.com", "abc123");
//        System.out.println("login " + acc);
        //Log in + Show menu for type account
        Scanner sc = new Scanner(System.in);
        int userChoice;
        try {
            IMenu menu = new MenuManager();
            IAccount accountManager = new AccountManager();

            menu.showWelcomeMenu();
            userChoice = sc.nextInt();
            if (userChoice == 1) {
                LogInForm logInValue = menu.showLogInMenu();
                accountManager.LoadAccountProfile();
                int acc = accountManager.LogIn(logInValue.getEmail(), logInValue.getPassword());

                //acc = 1 is admin log
                if (acc == 1) {
                    menu.showAdminMenu();
                    try {
                        userChoice = sc.nextInt();
                        if (userChoice == 4) {
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

            }

        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }

    }

}
