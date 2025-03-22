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
import bookstoremanagementsystem.interfaces.IBooks;
import bookstoremanagementsystem.interfaces.IGenres;
import bookstoremanagementsystem.interfaces.IMenu;
import bookstoremanagementsystem.interfaces.IPublishers;
import bookstoremanagementsystem.models.Accounts;
import bookstoremanagementsystem.models.Book;
import bookstoremanagementsystem.models.BookAuthors;
import bookstoremanagementsystem.models.Genres;
import bookstoremanagementsystem.models.OrderStatus;
import bookstoremanagementsystem.models.Orders;
import bookstoremanagementsystem.models.Publishers;
import bookstoremanagementsystem.services.AccountManager;
import bookstoremanagementsystem.services.AuthorManager;
import bookstoremanagementsystem.services.BookAuthorsManager;
import bookstoremanagementsystem.services.BookManager;
import bookstoremanagementsystem.services.GenresManager;
import bookstoremanagementsystem.services.MenuManager;
import bookstoremanagementsystem.services.MenuManager.LogInForm;
import bookstoremanagementsystem.services.MenuManager.RegisterForm;
import bookstoremanagementsystem.services.OrderStatusManager;
import bookstoremanagementsystem.services.OrdersManager;
import bookstoremanagementsystem.services.PublisherManager;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookStoreManagementSystem {

    public static void main(String[] args) {

        //Log in + Show menu for type account
        Scanner sc = new Scanner(System.in);
        int userChoice;

        IMenu menu = new MenuManager();
        IAccount accountManager = new AccountManager();
        IAuthors authorManager = new AuthorManager();
        IBooks bookManager = new BookManager();
        IGenres genreManager = new GenresManager();
        IPublishers publisherManager = new PublisherManager();

        // loop main menu
        while (true) {
            menu.showWelcomeMenu();

            try {
                userChoice = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                sc.nextLine();
                continue;
            }

            if (userChoice == 1) {
                accountManager.LoadAccountProfile();
                LogInForm logInValue = menu.showLogInMenu();
                int acc = accountManager.LogIn(logInValue.getEmail(), logInValue.getPassword());

                //Admin menu
                if (acc == 1) {
                    boolean isAdminRunning = true;
                    while (isAdminRunning) {
                        menu.showAdminMenu();
                        userChoice = sc.nextInt();

                        switch (userChoice) {
                            // Account manager
                            case 1:
                                boolean isAccountMenuRunning = true;
                                while (isAccountMenuRunning) {
                                    menu.showAccountMainMenu();
                                    accountManager.LoadAccountProfile();
                                    userChoice = sc.nextInt();

                                    switch (userChoice) {
                                        case 1:
                                            accountManager.showAccountList();
                                            break;
                                        case 2:
                                            List<Accounts> list = accountManager.searchAccount(menu.showAccountSearchForm());
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
                                        case 5:
                                            isAccountMenuRunning = false;
                                            break;
                                        default:
                                            System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                    }
                                }
                                break;
                            case 2:
                                boolean isBookMenuRunning = true;
                                while (isBookMenuRunning) {
                                    menu.showBooksMainMenu();
                                    bookManager.loadBooks();
                                    try {
                                        userChoice = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                        sc.nextLine();
                                        continue;
                                    }

                                    switch (userChoice) {
                                        case 1:
                                            bookManager.showBookList();
                                            break;
                                        case 2:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Book ID: ", "");
                                            String bookId = sc.nextLine();
                                            Book book = bookManager.searchBookById(bookId);
                                            if (book != null) {
                                                bookManager.showBookDetails(book);
                                            } else {
                                                System.out.printf("%10sBook not found!\n", "");
                                            }
                                            break;
                                        case 3:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter book name or keyword: ", "");
                                            String keyword = sc.nextLine();
                                            List<Book> results = bookManager.searchBooks(keyword);
                                            bookManager.showBookSearchResults(results);
                                            break;
                                        case 4:
                                            boolean exitCreateBook = false;
                                            int currentYear = Year.now().getValue();
                                            bookManager.loadBooks();
                                            sc.nextLine();

                                            System.out.printf("%10sAvailable Publishers:\n", "");
                                            List<Publishers> publishers = publisherManager.GetPublisherList();
                                            for (Publishers pub : publishers) {
                                                System.out.printf("%10s-Id: %s\t|Name: %s\n", "", pub.getPublisherId(), pub.getPublisherName());
                                            }

                                            System.out.printf("%10sAvailable Genres:\n", "");
                                            List<Genres> genres = genreManager.GetGenreList();
                                            for (Genres gen : genres) {
                                                System.out.printf("%10s-Id: %s\t|Name: %s\n", "", gen.getGenreId(), gen.getGenreName());
                                            }

                                            System.out.printf("%10sEnter Book ID: ", "");
                                            String newId = sc.nextLine();

                                            System.out.printf("%10sEnter Book Name: ", "");
                                            String newName = sc.nextLine();
                                            
                                           int year = currentYear;
                                            System.out.printf("%10sEnter Published Year: ", "");
                                            while (true) {
                                                while (!sc.hasNextInt()) {
                                                    System.out.printf("%10sInvalid input! Enter a valid year: ", "");
                                                    sc.next();
                                                }

                                                year = sc.nextInt();
                                                sc.nextLine();

                                                if (year <= currentYear) {
                                                    break;
                                                } else {
                                                    System.out.printf("%10sThe Published's Year cannot be in the future (%d or earlier): ", "", currentYear);
                                                }
                                            }

                                            String publisherId = null;
                                            boolean isValidPublisher = false;
                                            while (!isValidPublisher) {
                                                System.out.printf("%10sEnter Publisher ID: ", "");
                                                publisherId = sc.nextLine();

                                                for (Publishers pub : publishers) {
                                                    if (pub.getPublisherId().equals(publisherId)) {
                                                        isValidPublisher = true;
                                                        break;
                                                    }
                                                }
                                                if (isValidPublisher) {
                                                    break;
                                                }
                                                System.out.printf("%10sInvalid Publisher ID! Try again or type 'exit' to cancel: ", "");
                                                String exitOption = sc.nextLine();
                                                if (exitOption.equalsIgnoreCase("exit")) {
                                                    exitCreateBook = true;
                                                    break;
                                                }
                                            }

                                            if (exitCreateBook) {
                                                break;
                                            }

                                            System.out.printf("%10sEnter Description: ", "");
                                            String description = sc.nextLine();

                                            System.out.printf("%10sEnter Language: ", "");
                                            String language = sc.nextLine();

                                            String genre = null;
                                            boolean isValidGenre = false;

                                            while (!isValidGenre) {
                                                System.out.printf("%10sEnter Genre ID: ", "");
                                                genre = sc.nextLine();
                                                for (Genres gen : genres) {
                                                    if (gen.getGenreId().equals(genre)) {
                                                        isValidGenre = true;
                                                        break;
                                                    }
                                                }
                                                if (isValidGenre) {
                                                    break;
                                                }
                                                System.out.printf("%10sInvalid Genre ID! Try again or type 'exit' to cancel: ", "");
                                                String exitOption = sc.nextLine();
                                                if (exitOption.equalsIgnoreCase("exit")) {
                                                    exitCreateBook = true;
                                                    break;
                                                }
                                            }
                                            if (exitCreateBook) {
                                                break;
                                            }

                                            System.out.printf("%10sEnter Price: ", "");
                                            while (!sc.hasNextDouble()) {
                                                System.out.printf("%10sInvalid input! Enter a valid price: ", "");
                                                sc.next();
                                            }
                                            double price = sc.nextDouble();
                                            sc.nextLine();

                                            Book newBook = new Book(newId, newName, year, publisherId, description, language, genre, price);
                                            bookManager.addBook(newBook);
                                            break;
                                        case 5:
                                            sc.nextLine();
                                            bookManager.loadBooks();
                                            System.out.printf("%10sEnter Book ID to update: ", "");
                                            String updateId = sc.nextLine();
                                            Book existingBook = bookManager.searchBookById(updateId);
                                            if (existingBook != null) {
                                                System.out.printf("%10sEnter New Book Name (press Enter to skip): ", "");
                                                String updatedName = sc.nextLine();
                                                if (!updatedName.isEmpty()) {
                                                    existingBook.setBookName(updatedName);
                                                }

                                                System.out.printf("%10sEnter New Price (press Enter to skip): ", "");
                                                String priceInput = sc.nextLine();
                                                if (!priceInput.isEmpty()) {
                                                    try {
                                                        double updatedPrice = Double.parseDouble(priceInput);
                                                        existingBook.setPrice(updatedPrice);
                                                    } catch (NumberFormatException e) {
                                                        System.out.printf("%10sInvalid price! Keeping the old value.\n", "");
                                                    }
                                                }
                                                bookManager.updateBook(existingBook);
                                            } else {
                                                System.out.printf("%10sBook not found!\n", "");
                                            }
                                            break;
                                        case 6:
                                            sc.nextLine();
                                            bookManager.loadBooks();
                                            System.out.printf("%10sEnter Book ID to delete: ", "");
                                            String deleteId = sc.nextLine();
                                            bookManager.deleteBook(deleteId);
                                            break;
                                        case 7:
                                            isBookMenuRunning = false;
                                            break;
                                        default:
                                            System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                    }
                                }
                                break;

                            case 3:
                                boolean isAuthorMenuRunning = true;
                                while (isAuthorMenuRunning) {
                                    menu.showAuthorsMainMenu();
                                    authorManager.loadAuthorsFromFile();

                                    try {
                                        userChoice = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                        sc.nextLine();
                                        continue;
                                    }

                                    switch (userChoice) {
                                        case 1:
                                            authorManager.displayAuthors();
                                            break;
                                        case 2:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Author ID:", "");
                                            String authorId = sc.nextLine();
                                            authorManager.searchAuthor(authorId);
                                            break;
                                        case 3:
                                            sc.nextLine();
                                            System.out.printf("%10s\tEnter Author ID: ", "");
                                            String authorID = sc.nextLine();
                                            System.out.printf("%10s\tEnter Full Name: ", "");
                                            String fullName = sc.nextLine();
                                            authorManager.addAuthor(authorID, fullName);
                                            break;
                                        case 4:
                                            menu.showAuthorUpdateForm();
                                            authorManager.loadAuthorsFromFile();
                                            break;
                                        case 5:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Author ID to delete:", "");
                                            String deleteId = sc.nextLine();
                                            authorManager.deleteAuthor(deleteId);
                                            break;
                                        case 6:
                                            isAuthorMenuRunning = false;
                                            break;
                                        default:
                                            System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                    }
                                }
                                break;

                            case 4:
                                boolean isGenreMenuRunning = true;
                                while (isGenreMenuRunning) {
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
                                        case 6:
                                            isGenreMenuRunning = false;
                                            break;
                                        default:
                                            System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                    }
                                }
                                break;
                            case 5:
                                boolean isPublisherMenuRunning = true;
                                while (isPublisherMenuRunning) {
                                    menu.showPublishersMainMenu();
                                    publisherManager.loadPublishers();

                                    try {
                                        userChoice = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                        sc.nextLine();
                                        continue;
                                    }

                                    switch (userChoice) {
                                        case 1:
                                            publisherManager.showPublishers();
                                            break;
                                        case 2:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter keyword: ", "");
                                            String searchDetail = sc.nextLine();
                                            List<Publishers> results = publisherManager.searchPublisher(searchDetail);
                                            publisherManager.showPublishersByList(results);
                                            break;
                                        case 3:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Publisher ID: ", "");
                                            String id = sc.nextLine();
                                            System.out.printf("%10sEnter Name: ", "");
                                            String name = sc.nextLine();

                                            Publishers publisher = new Publishers(id, name);
                                            publisherManager.addPublisher(publisher);
                                            break;
                                        case 4:
                                            sc.nextLine();
                                            System.out.print("Enter Publisher ID to update: ");
                                            String pubId = sc.nextLine();
                                            Publishers existingPublisher = publisherManager.searchPublisherById(pubId);
                                            if (existingPublisher == null) {
                                                System.out.println("Publisher not found!");
                                                continue;
                                            }
                                            System.out.print("Enter New Name: ");
                                            String pubName = sc.nextLine();

                                            existingPublisher.setPublisherName(pubName);
                                            publisherManager.updatePublisher(existingPublisher);
                                            publisherManager.loadPublishers();
                                            break;
                                        case 5:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Publisher ID to delete:", "");
                                            String deleteId = sc.nextLine();
                                            publisherManager.deletePublisher(deleteId);
                                            break;
                                        case 6:
                                            isPublisherMenuRunning = false;
                                            break;
                                        default:
                                            System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                    }
                                }
                                break;

                            case 8:
                                isAdminRunning = false;
                                break;

                            default:
                                System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                        }
                    }
                } else if (acc == 0) { //User menu
                    boolean isUserRunning = true;
                    while (isUserRunning) {
                        menu.showUserMenu();
                        userChoice = sc.nextInt();

                        switch (userChoice) {
                            case 6:
                                isUserRunning = false;
                                break;
                            default:
                                System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                        }
                    }
                } else {
                    System.out.printf("%10s\tEmail or password incorrect.\n", "");
                }

            } else if (userChoice == 2) {
                accountManager.LoadAccountProfile();
                RegisterForm registerValue = menu.showRegisterMenu();
                accountManager.registerAccount(registerValue);
            } else if (userChoice == 3) {
                System.out.printf("%10s\tShutting down the system. Goodbye!\n", "");
                System.exit(0);
            } else {
                System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
            }
        }

    }
}
