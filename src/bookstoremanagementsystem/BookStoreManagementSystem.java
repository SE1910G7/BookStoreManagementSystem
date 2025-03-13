/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookstoremanagementsystem;


import bookstoremanagementsystem.models.BookGenres; // Correct package for BookGenres
import interfaces.IBookGenres; // Sửa lại cho đúng package

import services.BookGenresManager;                  // Correct package for BookGenresManager



import java.util.Scanner;

public class BookStoreManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookGenresManager service = new BookGenresManager(); // Khởi tạo đúng cách

        int choice;
        String bookGenresId, bookId, genreId, searchInput;

        do {
            System.out.println("**************************************");
            System.out.println("!! BOOK-GENRE MANAGEMENT !!");
            System.out.println("1. Add Book-Genre");
            System.out.println("2. Display Book-Genres");
            System.out.println("3. Search Book-Genre");
            System.out.println("4. Delete Book-Genre");
            System.out.println("5. Exit");
            System.out.print("Enter Option (1-5): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Book-Genre ID: ");
                    bookGenresId = scanner.nextLine();
                    System.out.print("Enter Book ID: ");
                    bookId = scanner.nextLine();
                    System.out.print("Enter Genre ID: ");
                    genreId = scanner.nextLine();
                    service.addBookGenre(new BookGenres(bookGenresId, bookId, genreId));
                    break;
                case 2:
                    service.view();
                    break;
                case 3:
                    System.out.print("Enter Book-Genre ID to search: ");
                    searchInput = scanner.nextLine();
                    service.view();  // Chưa có search nên tạm hiển thị tất cả
                    break;
                case 4:
                    System.out.print("Enter Book-Genre ID to delete: ");
                    searchInput = scanner.nextLine();
                    service.deleteBookGenre(searchInput);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);

        scanner.close();
    }
}
