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
import bookstoremanagementsystem.interfaces.IOrders;
import bookstoremanagementsystem.interfaces.IPublishers;
import bookstoremanagementsystem.interfaces.IRentalSlips;
import bookstoremanagementsystem.models.Accounts;
import bookstoremanagementsystem.models.Book;
import bookstoremanagementsystem.models.BookAuthors;
import bookstoremanagementsystem.models.Genres;
import bookstoremanagementsystem.models.Orders;
import bookstoremanagementsystem.models.Publishers;
import bookstoremanagementsystem.models.RentalSlips;
import bookstoremanagementsystem.services.AccountManager;
import bookstoremanagementsystem.services.AuthorManager;
import bookstoremanagementsystem.services.BookAuthorsManager;
import bookstoremanagementsystem.services.BookManager;
import bookstoremanagementsystem.services.GenresManager;
import bookstoremanagementsystem.services.MenuManager;
import bookstoremanagementsystem.services.MenuManager.LogInForm;
import bookstoremanagementsystem.services.MenuManager.RegisterForm;
import bookstoremanagementsystem.services.OrdersManager;
import bookstoremanagementsystem.services.PublisherManager;
import bookstoremanagementsystem.services.RentalSlipsManager;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeParseException;
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
        IOrders orderManager = new OrdersManager();
        IRentalSlips rentalSlipsManager = new RentalSlipsManager();
        String emailLogin = "";

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
                emailLogin = logInValue.getEmail();
                int acc = accountManager.LogIn(logInValue.getEmail(), logInValue.getPassword());

                //Admin menu
                if (acc == 1) {
                    boolean isAdminRunning = true;
                    while (isAdminRunning) {
                        menu.showAdminMenu();
                        try {
                            userChoice = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                            sc.nextLine();
                            continue;
                        }

                        switch (userChoice) {
                            // Account manager
                            case 1:
                                boolean isAccountMenuRunning = true;
                                while (isAccountMenuRunning) {
                                    menu.showAccountMainMenu();
                                    accountManager.LoadAccountProfile();
                                    try {
                                        userChoice = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                        sc.nextLine();
                                        continue;
                                    }

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
                                    try {
                                        userChoice = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                        sc.nextLine();
                                        continue;
                                    }

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
                            case 6:
                                boolean isRentalMenuRunning = true;
                                while (isRentalMenuRunning) {
                                    menu.showRentalSlipsMainMenu();
                                    rentalSlipsManager.loadRentalSlips();

                                    try {
                                        userChoice = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                        sc.nextLine();
                                        continue;
                                    }

                                    switch (userChoice) {
                                        case 1:
                                            rentalSlipsManager.showRentalSlips();
                                            break;
                                        case 2:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Rental Slip ID: ", "");
                                            String rentalSlipId = sc.nextLine();
                                            RentalSlips rentalSlipDetail = rentalSlipsManager.getRentalSlipById(rentalSlipId);
                                            if (rentalSlipDetail != null) {
                                                rentalSlipsManager.showRentalSlipDetails(rentalSlipDetail);
                                            } else {
                                                System.out.printf("%10sRental Slip not found!\n", "");
                                            }
                                            break;
                                        case 3:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Rental Slip ID: ", "");
                                            String newRentalSlipId = sc.nextLine();

                                            System.out.printf("%10sEnter Customer Name: ", "");
                                            String customerAccountId = sc.nextLine();

                                            List<Book> availableBooksForRental = bookManager.getBooksList();
                                            if (availableBooksForRental.isEmpty()) {
                                                System.out.println("No books available for rental.");
                                                break;
                                            }

                                            System.out.println("\nAvailable Books:");
                                            for (Book book : availableBooksForRental) {
                                                System.out.printf("Book ID: %s | Name: %s\n", book.getBookId(), book.getBookName());
                                            }

                                            System.out.printf("%10sEnter the number of books to rent: ", "");
                                            int rentalBookCount;
                                            try {
                                                rentalBookCount = sc.nextInt();
                                                sc.nextLine();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input. Please enter a number.");
                                                sc.nextLine();
                                                break;
                                            }

                                            List<String> booksRentedList = new ArrayList<>();
                                            for (int i = 0; i < rentalBookCount; i++) {
                                                System.out.printf("%10sEnter Book ID #%d: ", "", (i + 1));
                                                String bookId = sc.nextLine();

                                                boolean bookExists = availableBooksForRental.stream()
                                                        .anyMatch(book -> book.getBookId().equals(bookId));
                                                if (!bookExists) {
                                                    System.out.println("Invalid Book ID. Try again.");
                                                    i--;
                                                    continue;
                                                }
                                                booksRentedList.add(bookId);
                                            }
                                            String booksRented = String.join(", ", booksRentedList);

                                            LocalDate startRentBookDate = LocalDate.now();
                                            LocalDate createdAt = LocalDate.now();

                                            System.out.printf("%10sEnter Rental End Date (yyyy-MM-dd): ", "");
                                            LocalDate endRentBookDate;
                                            try {
                                                endRentBookDate = LocalDate.parse(sc.nextLine());
                                                if (endRentBookDate.isBefore(startRentBookDate)) {
                                                    System.out.println("End date cannot be before start date!");
                                                    break;
                                                }
                                            } catch (DateTimeParseException e) {
                                                System.out.println("Please enter in yyyy-MM-dd.");
                                                break;
                                            }

                                            System.out.printf("%10sEnter Total Price: ", "");
                                            double totalPrice;
                                            try {
                                                totalPrice = sc.nextDouble();
                                                sc.nextLine();
                                                if (totalPrice < 0) {
                                                    System.out.println("Total price cannot be negative. Try again.");
                                                    break;
                                                }
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input. Please enter a valid number.");
                                                sc.nextLine();
                                                break;
                                            }

                                            RentalSlips newRentalSlip = new RentalSlips(
                                                    newRentalSlipId, customerAccountId, booksRented,
                                                    startRentBookDate, endRentBookDate, createdAt,
                                                    totalPrice, "Active"
                                            );

                                            rentalSlipsManager.createRentalSlip(newRentalSlip);
                                            System.out.println("Rental slip added successfully!");
                                            break;
                                        case 4:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Rental Slip ID to delete: ", "");
                                            String deleteRentalSlipId = sc.nextLine();
                                            rentalSlipsManager.deleteRentalSlip(deleteRentalSlipId);
                                            break;
                                        case 5:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Rental Slip ID to update status: ", "");
                                            String updateRentalSlipId = sc.nextLine();
                                            RentalSlips existingRentalSlip = rentalSlipsManager.getRentalSlipById(updateRentalSlipId);
                                            if (existingRentalSlip == null) {
                                                System.out.println("Rental Slip not found!");
                                                continue;
                                            }
                                            System.out.printf("%10sEnter New Status: ", "");
                                            String newStatus = sc.nextLine();
                                            rentalSlipsManager.updateRentalStatus(updateRentalSlipId, newStatus);
                                            break;
                                        case 6:
                                            isRentalMenuRunning = false;
                                            break;
                                        default:
                                            System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                    }
                                }
                                break;

                            case 7:
                                boolean isOrderMenuRunning = true;
                                while (isOrderMenuRunning) {
                                    menu.showOrdersMainMenu();
                                    orderManager.loadOrders();
                                    try {
                                        userChoice = sc.nextInt();
                                    } catch (InputMismatchException e) {
                                        System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                                        sc.nextLine();
                                        continue;
                                    }

                                    switch (userChoice) {
                                        case 1:
                                            orderManager.showOrderList();
                                            break;
                                        case 2:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Order ID: ", "");
                                            String orderId = sc.nextLine();
                                            Orders orderDetail = orderManager.getOrderById(orderId);
                                            if (orderDetail != null) {
                                                orderManager.showOrderDetails(orderDetail);
                                            } else {
                                                System.out.printf("%10sOrder not found!\n", "");
                                            }
                                            break;
                                        case 3:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Order ID: ", "");
                                            String newOrderId = sc.nextLine();
                                            System.out.printf("%10sEnter Customer Name: ", "");
                                            String customerId = sc.nextLine();

                                            List<Book> availableBooks = bookManager.getBooksList();
                                            if (availableBooks.isEmpty()) {
                                                System.out.println("No books available.");
                                                break;
                                            }

                                            System.out.println("\nAvailable Books:");
                                            for (Book book : availableBooks) {
                                                System.out.printf("Book ID: %s | Name: %s\n", book.getBookId(), book.getBookName());
                                            }

                                            System.out.printf("%10sEnter the number of books to order: ", "");
                                            int bookCount;
                                            try {
                                                bookCount = sc.nextInt();
                                                sc.nextLine();
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input. Please enter a number.");
                                                sc.nextLine();
                                                break;
                                            }

                                            List<String> booksOrderedList = new ArrayList<>();
                                            for (int i = 0; i < bookCount; i++) {
                                                System.out.printf("%10sEnter Book ID #%d: ", "", (i + 1));
                                                String bookId = sc.nextLine();

                                                boolean bookExists = availableBooks.stream().anyMatch(book -> book.getBookId().equals(bookId));
                                                if (!bookExists) {
                                                    System.out.println("Invalid Book ID. Try again.");
                                                    i--;
                                                    continue;
                                                }
                                                booksOrderedList.add(bookId);
                                            }
                                            String booksOrdered = String.join(", ", booksOrderedList);

                                            Orders newOrder = new Orders(newOrderId, customerId, LocalDate.now(), booksOrdered, "Create");
                                            orderManager.createOrder(newOrder);
                                            break;
                                        case 4:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Order ID to delete: ", "");
                                            String deleteOrderId = sc.nextLine();
                                            orderManager.deleteOrder(deleteOrderId);
                                            break;
                                        case 5:
                                            sc.nextLine();
                                            System.out.printf("%10sEnter Order ID to update status: ", "");
                                            String updateOrderId = sc.nextLine();
                                            Orders existingOrder = orderManager.getOrderById(updateOrderId);
                                            if (existingOrder == null) {
                                                System.out.println("Order not found!");
                                                continue;
                                            }
                                            System.out.printf("%10sEnter New Status: ", "");
                                            String newStatus = sc.nextLine();
                                            orderManager.updateOrderStatus(updateOrderId, newStatus);
                                            break;
                                        case 6:
                                            isOrderMenuRunning = false;
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
                        try {
                            userChoice = sc.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.printf("%10s\tInvalid choice. Please try again.\n", "");
                            sc.nextLine();
                            continue;
                        }

                        switch (userChoice) {
                            case 1:
                                Accounts user = accountManager.searchAccountByEmail(emailLogin);
                                accountManager.showAccountDetails(user);
                                break;
                            case 2:
                                menu.showAccountUpdateForm();
                                accountManager.LoadAccountProfile();
                                break;
                            case 3:
                                bookManager.showBookList();
                                break;
                            case 4:
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
