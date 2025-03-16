package bookstoremanagementsystem;

import bookstoremanagementsystem.interfaces.IBookGenres;
import bookstoremanagementsystem.models.BookGenres;
import bookstoremanagementsystem.services.BookGenresManager;
import java.util.Scanner;

/**
 *
 * @author [Your Name]
 */
public class BookStoreManagementSystem {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            IBookGenres bookGenresManager = new BookGenresManager();

            int choice;
            String bookGenresId, bookId, genreId;
            do {
                System.out.println("**************************************");
                System.out.println("!! BOOK GENRES MANAGEMENT !!");
                System.out.println("1. Add Book Genre");
                System.out.println("2. View Book Genres");
                System.out.println("3. Update Book Genre");
                System.out.println("4. Delete Book Genre");
                System.out.println("5. Exit");
                System.out.print("Enter Option (1-5): ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Book Genre ID: ");
                        bookGenresId = scanner.nextLine();
                        System.out.print("Enter Book ID: ");
                        bookId = scanner.nextLine();
                        System.out.print("Enter Genre ID: ");
                        genreId = scanner.nextLine();
                        bookGenresManager.addBookGenre(new BookGenres(bookGenresId, bookId, genreId));
                        break;
                    case 2:
                        bookGenresManager.view();
                        break;
                    case 3:
                        System.out.print("Enter Book Genre ID to update: ");
                        bookGenresId = scanner.nextLine();
                        System.out.print("Enter new Book ID: ");
                        bookId = scanner.nextLine();
                        System.out.print("Enter new Genre ID: ");
                        genreId = scanner.nextLine();
                        bookGenresManager.updateBookGenre(bookGenresId, new BookGenres(bookGenresId, bookId, genreId));
                        break;
                    case 4:
                        System.out.print("Enter Book Genre ID to delete: ");
                        bookGenresId = scanner.nextLine();
                        bookGenresManager.deleteBookGenre(bookGenresId);
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } while (choice != 5);
        }
    }
}