/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem;

//import bookstoremanagementsystem.models.CartItems;
//import bookstoremanagementsystem.services.CartItemsManager;
//import java.util.InputMismatchException;
//import bookstoremanagementsystem.interfaces.IAccount;
//import bookstoremanagementsystem.interfaces.IAuthors;
//import bookstoremanagementsystem.interfaces.IGenres;
//import bookstoremanagementsystem.interfaces.IMenu;
//import bookstoremanagementsystem.models.BookAuthors;
//import bookstoremanagementsystem.services.AccountManager;
//import bookstoremanagementsystem.services.AuthorManager;
//import bookstoremanagementsystem.services.BookAuthorsManager;
//import bookstoremanagementsystem.services.GenresManager;
//import bookstoremanagementsystem.services.MenuManager;
//import bookstoremanagementsystem.services.MenuManager.LogInForm;
//import java.util.Scanner;
//import bookstoremanagementsystem.interfaces.IBookGenres;
//import bookstoremanagementsystem.models.BookGenres;
//import bookstoremanagementsystem.services.AccountManager;
//import bookstoremanagementsystem.services.BookGenresManager;
//
public class BookStoreManagementSystem {

    public static void main(String[] args) {
//        CartItemsManager manager = new CartItemsManager();
//        try (Scanner scanner = new Scanner(System.in)) {
//            int choice;
//            
//            do {
//                System.out.println("\n=== CART ITEMS MANAGEMENT ===");
//                System.out.println("1. Add Cart Item");
//                System.out.println("2. Display Cart Items");
//                System.out.println("3. Update Cart Item");
//                System.out.println("4. Delete Cart Item");
//                System.out.println("5. Save Cart Items to File");
//                System.out.println("6. Load Cart Items from File");
//                System.out.println("7. Search Cart Items");
//                System.out.println("8. Exit");
//                System.out.print("Enter your choice: ");
//                
//                // Xử lý nhập số nguyên an toàn
//                while (true) {
//                    try {
//                        choice = scanner.nextInt();
//                        scanner.nextLine(); // Consume newline
//                        break;
//                    } catch (InputMismatchException e) {
//                        System.out.println("Invalid input! Please enter an integer.");
//                        scanner.next(); // Xóa dữ liệu đầu vào không hợp lệ
//                    }
//                }
//                
//                switch (choice) {
//                    case 1:
//                        System.out.print("Enter Cart Item ID: ");
//                        String id = scanner.nextLine();
//                        if (manager.isCartItemIdExists(id)) {
//                            System.out.println("Cart Item ID already exists! Please enter a unique ID.");
//                            break;
//                        }
//                        System.out.print("Enter Cart ID: ");
//                        String cartId = scanner.nextLine();
//                        System.out.print("Enter Book ID: ");
//                        String bookId = scanner.nextLine();
//                        
//                        int quantity = 0;
//                        double price = 0.0;
//                        
//                        // Kiểm tra nhập số nguyên an toàn
//                        while (true) {
//                            try {
//                                System.out.print("Enter Quantity: ");
//                                quantity = scanner.nextInt();
//                                scanner.nextLine(); // Consume newline
//                                if (quantity < 1) {
//                                    System.out.println("Quantity must be greater than 0. Try again!");
//                                    continue;
//                                }
//                                break;
//                            } catch (InputMismatchException e) {
//                                System.out.println("Invalid input! Please enter a valid integer for quantity.");
//                                scanner.next();
//                            }
//                        }
//                        
//                        // Kiểm tra nhập số thực an toàn
//                        while (true) {
//                            try {
//                                System.out.print("Enter Price: ");
//                                price = scanner.nextDouble();
//                                scanner.nextLine();
//                                if (price < 0) {
//                                    System.out.println("Price must be a positive number. Try again!");
//                                    continue;
//                                }
//                                break;
//                            } catch (InputMismatchException e) {
//                                System.out.println("Invalid input! Please enter a valid decimal number for price.");
//                                scanner.next();
//                            }
//                        }
//                        
//                        manager.addCartItem(new CartItems(id, cartId, bookId, quantity, price));
//                        break;
//                        
//                    case 2:
//                        manager.displayCartItems();
//                        break;
//                        
//                    case 3:
//                        System.out.print("Enter Cart Item ID to update: ");
//                        String updateID = scanner.nextLine();
//                        if (!manager.isCartItemIdExists(updateID)) {
//                            System.out.println("Cart Item ID not found!");
//                            break;
//                        }
//                        
//                        int newQuantity = 0;
//                        double newPrice = 0.0;
//                        
//                        while (true) {
//                            try {
//                                System.out.print("Enter new Quantity: ");
//                                newQuantity = scanner.nextInt();
//                                scanner.nextLine();
//                                if (newQuantity < 1) {
//                                    System.out.println("Quantity must be greater than 0. Try again!");
//                                    continue;
//                                }
//                                break;
//                            } catch (InputMismatchException e) {
//                                System.out.println("Invalid input! Please enter a valid integer for quantity.");
//                                scanner.next();
//                            }
//                        }
//                        
//                        while (true) {
//                            try {
//                                System.out.print("Enter new Price: ");
//                                newPrice = scanner.nextDouble();
//                                scanner.nextLine();
//                                if (newPrice < 0) {
//                                    System.out.println("Price must be a positive number. Try again!");
//                                    continue;
//                                }
//                                break;
//                            } catch (InputMismatchException e) {
//                                System.out.println("Invalid input! Please enter a valid decimal number for price.");
//                                scanner.next();
//                            }
//                        }
//                        
//                        manager.updateCartItem(updateID, newQuantity, newPrice);
//                        break;
//                        
//                    case 4:
//                        System.out.print("Enter Cart Item ID to delete: ");
//                        String deleteID = scanner.nextLine();
//                        manager.deleteCartItem(deleteID);
//                        break;
//                        
//                    case 5:
//                        manager.saveCartItemsToFile();
//                        break;
//                        
//                    case 6:
//                        manager.loadCartItemsFromFile();
//                        break;
//                        
//                    case 7:
//                        System.out.print("Enter keyword to search: ");
//                        String searchKeyword = scanner.nextLine();
//                        manager.searchCartItems(searchKeyword);
//                        break;
//                        
//                    case 8:
//                        System.out.println("Exiting...");
//                        break;
//                        
//                    default:
//                        System.out.println("Invalid choice! Please try again.");
//                }
//            } while (choice != 8);
//        }
//        //Diem Tran Code main
////        try (Scanner scanner = new Scanner(System.in)) {
////            IAuthors authorManager = new AuthorManager();
////
////            int choice;
////            String authorID, fullName, searchInput, newAuthorID, newFullName;
////            do {
////                System.out.println("**************************************");
////                System.out.println("!! AUTHOR MANAGEMENT !!");
////                System.out.println("1. Add Author");
////                System.out.println("2. Display Authors");
////                System.out.println("3. Sort by Author Name");
////                System.out.println("4. Search Author");
////                System.out.println("5. Delete Author");
////                System.out.println("6. Edit Author");
////                System.out.println("7. Exit");
////                System.out.print("Enter Option (1-7): ");
////                choice = scanner.nextInt();
////                scanner.nextLine();
////
////                switch (choice) {
////                    case 1:
////                        System.out.print("Enter Author ID: ");
////                        authorID = scanner.nextLine();
////                        System.out.print("Enter Author Full Name: ");
////                        fullName = scanner.nextLine();
////                        authorManager.addAuthor(authorID, fullName);
////                        break;
////                    case 2:
////                        authorManager.displayAuthors();
////                        break;
////                    case 3:
////                        authorManager.sortAuthors();
////                        break;
////                    case 4:
////                        System.out.print("Enter Author Name or ID to search: ");
////                        searchInput = scanner.nextLine();
////                        authorManager.searchAuthor(searchInput);
////                        break;
////                    case 5:
////                        System.out.print("Enter Author Name or ID to delete: ");
////                        searchInput = scanner.nextLine();
////                        authorManager.deleteAuthor(searchInput);
////                        break;
////                    case 6:
////                        System.out.print("Enter Author Name or ID to edit: ");
////                        searchInput = scanner.nextLine();
////                        System.out.print("Enter new Author ID: ");
////                        newAuthorID = scanner.nextLine();
////                        System.out.print("Enter new Author Full Name: ");
////                        newFullName = scanner.nextLine();
////                        authorManager.editAuthor(searchInput, newAuthorID, newFullName);
////                        break;
////                    case 7:
////                        System.out.println("Exiting...");
////                        authorManager.saveAuthorsToFile();
////                        break;
////                    default:
////                        System.out.println("Invalid option. Try again.");
////                }
////            } while (choice != 7);
////        }
//
//        //----------------------------------------------------------------------------
//        //Tai Code main
////        BookAuthorsManager service = new BookAuthorsManager();
////
////        // Adding authors
////        service.addAuthor(new BookAuthors("1", "101", "201"));
////        service.addAuthor(new BookAuthors("2", "102", "202"));
////
////        // Viewing authors
////        service.view();
////
////        // Updating an author
////        service.updateAuthor("1", new BookAuthors("1", "101", "203"));
////
////        // Viewing after update
////        service.view();
////
////        // Deleting an author
////        service.deleteAuthor("2");
////
////        // Viewing after delete
////        service.view();
//        //----------------------------------------------------------------------------
//        //----------------------------------------------------------------------------
//        //Nhu Code main
////        IAccount accountManager = new AccountManager();
////        accountManager.LoadAccountProfile();
////        int acc = accountManager.LogIn("abc@example.com", "abc123");
////        System.out.println("login " + acc);
//        //Log in + Show menu for type account
//        Scanner sc = new Scanner(System.in);
//        int userChoice;
//        try {
//            IMenu menu = new MenuManager();
//            IAccount accountManager = new AccountManager();
//
//            menu.showWelcomeMenu();
//            userChoice = sc.nextInt();
//            if (userChoice == 1) {
//                LogInForm logInValue = menu.showLogInMenu();
//                accountManager.LoadAccountProfile();
//                int acc = accountManager.LogIn(logInValue.getEmail(), logInValue.getPassword());
//
//                //acc = 1 is admin log
//                if (acc == 1) {
//                    menu.showAdminMenu();
//                    try {
//                        userChoice = sc.nextInt();
//                        if (userChoice == 4) {
//                            IGenres genreManager = new GenresManager();
//                            do {
//                                menu.showGenreMainMenu();
//                                genreManager.loadGenresFile();
//                                userChoice = sc.nextInt();
//                                switch (userChoice) {
//                                    case 1:
//                                        genreManager.showGenreList();
//                                        break;
//                                    case 2:
//                                        genreManager.showGenreDetail();
//                                        break;
//                                    case 3:
//                                        genreManager.createGenre();
//                                        break;
//                                    case 4:
//                                        genreManager.updateGenre();
//                                        break;
//                                    case 5:
//                                        genreManager.removeGenre();
//                                        break;
//                                }
//                            } while (userChoice != 6);
//
//                        }
//
//                    } catch (Exception e) {
//                        System.out.println(e);
//                    }
//
//                } else if (acc == 0) {
//                    menu.showUserMenu();
//                } else {
//                    System.out.printf("%10s\tEmail or password not true ", "");
//                }
//            } else {
//
//            }
//
//        } catch (Exception e) {
//            System.out.println("Exception: " + e);
//        }
//
//    
// ------------------------------------
// Ngoc Anh Code main
//try (Scanner scanner = new Scanner(System.in)) {
//            IBookGenres bookGenresManager = new BookGenresManager();
//
//            int choice;
//            String bookGenresId, bookId, genreId;
//            do {
//                System.out.println("**************************************");
//                System.out.println("!! BOOK GENRES MANAGEMENT !!");
//                System.out.println("1. Add Book Genre");
//                System.out.println("2. View Book Genres");
//                System.out.println("3. Update Book Genre");
//                System.out.println("4. Delete Book Genre");
//                System.out.println("5. Exit");
//                System.out.print("Enter Option (1-5): ");
//                choice = scanner.nextInt();
//                scanner.nextLine();
//
//                switch (choice) {
//                    case 1:
//                        System.out.print("Enter Book Genre ID: ");
//                        bookGenresId = scanner.nextLine();
//                        System.out.print("Enter Book ID: ");
//                        bookId = scanner.nextLine();
//                        System.out.print("Enter Genre ID: ");
//                        genreId = scanner.nextLine();
//                        bookGenresManager.addBookGenre(new BookGenres(bookGenresId, bookId, genreId));
//                        break;
//                    case 2:
//                        bookGenresManager.view();
//                        break;
//                    case 3:
//                        System.out.print("Enter Book Genre ID to update: ");
//                        bookGenresId = scanner.nextLine();
//                        System.out.print("Enter new Book ID: ");
//                        bookId = scanner.nextLine();
//                        System.out.print("Enter new Genre ID: ");
//                        genreId = scanner.nextLine();
//                        bookGenresManager.updateBookGenre(bookGenresId, new BookGenres(bookGenresId, bookId, genreId));
//                        break;
//                    case 4:
//                        System.out.print("Enter Book Genre ID to delete: ");
//                        bookGenresId = scanner.nextLine();
//                        bookGenresManager.deleteBookGenre(bookGenresId);
//                        break;
//                    case 5:
//                        System.out.println("Exiting...");
//                        break;
//                    default:
//                        System.out.println("Invalid option. Try again.");
//                }
//            } while (choice != 5);
//        }
//        
//        // ------------------------------------
//        // My Code main
//        Scanner scanner = new Scanner(System.in);
//        AccountManager accountManager = new AccountManager();
//
//        int choice;
//        do {
//            System.out.println("\n=== ACCOUNT MANAGEMENT ===");
//            System.out.println("1. Add Account");
//            System.out.println("2. Display Accounts");
//            System.out.println("3. Log In");
//            System.out.println("4. Delete Account");
//            System.out.println("5. Update Password");
//            System.out.println("6. Exit");
//            System.out.print("Enter your choice: ");
//
//            choice = scanner.nextInt();
//            scanner.nextLine();
//
//            switch (choice) {
//                case 1:
//                    System.out.print("Enter Account ID: ");
//                    String accountID = scanner.nextLine();
//                    System.out.print("Enter Full Name: ");
//                    String fullName = scanner.nextLine();
//                    System.out.print("Enter Email: ");
//                    String email = scanner.nextLine();
//                    System.out.print("Enter Password: ");
//                    String password = scanner.nextLine();
//                    System.out.print("Enter Phone Number: ");
//                    String phoneNumber = scanner.nextLine();
//                    System.out.print("Enter Address: ");
//                    String address = scanner.nextLine();
//                    System.out.print("Enter Role (Admin/User): ");
//                    String role = scanner.nextLine();
//
//                    accountManager.addAccount(accountID, fullName, email, password, phoneNumber, address, role);
//                    break;
//                case 2:
//                    accountManager.displayAccounts();
//                    break;
//
//                case 3:
//                    System.out.print("Enter Email: ");
//                    email = scanner.nextLine();
//                    System.out.print("Enter Password: ");
//                    password = scanner.nextLine();
//                    int result = accountManager.LogIn(email, password);
//                    if (result == 1) {
//                        System.out.println("Login successful! Welcome, Admin.");
//                    } else if (result == 0) {
//                        System.out.println("Login successful! Welcome, User.");
//                    } else {
//                        System.out.println("Incorrect email or password.");
//                    }
//                    break;
//                case 4:
//                    System.out.print("Enter Email to delete: ");
//                    email = scanner.nextLine();
//                    accountManager.deleteAccount(email);
//                    System.out.println("Account deleted successfully!");
//                    break;
//
//                case 5:
//                    System.out.print("Enter Email: ");
//                    email = scanner.nextLine();
//                    System.out.print("Enter New Password: ");
//                    String newPassword = scanner.nextLine();
//                    accountManager.updatePassword(email, newPassword);
//                    System.out.println("Password updated successfully!");
//                    break;
//
//                case 6:
//                    System.out.println("Exiting Account Management...");
//                    break;
//
//                default:
//                    System.out.println("Invalid choice! Please try again.");
//            }
//        } while (choice != 6);

    }
}
