
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BookGenreManager {
    static Scanner scanner = new Scanner(System.in);
    static List<BookGenre> bookGenres = new ArrayList<>();

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("**************************************");
            System.out.println("!! BOOK GENRE MANAGER !!");
            System.out.println("1. Add Book to Genre");
            System.out.println("2. Display Books by Genre");
            System.out.println("3. Sort by Book Name");
            System.out.println("4. Search Book by Name");
            System.out.println("5. Exit");
            System.out.println("Enter Option 1-5: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBookToGenre();
                    break;
                case 2:
                    displayBooksByGenre();
                    break;
                case 3:
                    sortBooksByName();
                    break;
                case 4:
                    searchBookByName();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (choice != 5);
    }

    static void addBookToGenre() {
        System.out.print("Enter BookGenres ID: ");
        String bookGenresId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        System.out.print("Enter Genre ID: ");
        String genreId = scanner.nextLine();
        System.out.print("Enter Book Name: ");
        String bookName = scanner.nextLine();
        System.out.print("Enter Genre Name: ");
        String genreName = scanner.nextLine();
        
        bookGenres.add(new BookGenre(bookGenresId, bookId, genreId, bookName, genreName));
        System.out.println("Book added successfully!");
    }

    static void displayBooksByGenre() {
        if (bookGenres.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        System.out.println("**************************************");
        System.out.println("!! BOOKS BY GENRE !!");
        System.out.printf("%-15s %-20s %-20s\n", "Book ID", "Book Name", "Genre Name");
        System.out.println("-------------------------------------------------------");
        for (BookGenre bg : bookGenres) {
            System.out.printf("%-15s %-20s %-20s\n", bg.bookId, bg.bookName, bg.genreName);
        }
    }

    static void sortBooksByName() {
        bookGenres.sort(Comparator.comparing(bg -> bg.bookName));
        System.out.println("Books sorted by name!");
        displayBooksByGenre();
    }

    static void searchBookByName() {
        System.out.print("Enter Book Name to search: ");
        String searchName = scanner.nextLine();
        boolean found = false;
        for (BookGenre bg : bookGenres) {
            if (bg.bookName.equalsIgnoreCase(searchName)) {
                System.out.printf("Found - Book ID: %s, Book Name: %s, Genre: %s\n", bg.bookId, bg.bookName, bg.genreName);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Book not found.");
        }
    }
}
