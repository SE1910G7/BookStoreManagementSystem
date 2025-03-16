package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IBookGenres;
import bookstoremanagementsystem.models.BookGenres;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author
 */
public class BookGenresManager implements IBookGenres {

    private final List<BookGenres> bookGenresList = new ArrayList<>();
    private final String filePath = "book_genres.txt";

    public BookGenresManager() {
        loadBookGenresFromFile();
    }

    @Override
    public void view() {
        if (bookGenresList.isEmpty()) {
            System.out.println("No book genres available.");
        } else {
            bookGenresList.forEach((bookGenre) -> {
                System.out.println("Book Genre ID: " + bookGenre.getBookGenresId() + 
                                   ", Book ID: " + bookGenre.getBookId() + 
                                   ", Genre ID: " + bookGenre.getGenreId());
            });
        }
    }

    @Override
    public void addBookGenre(BookGenres bookGenre) {
        bookGenresList.add(bookGenre);
        System.out.println("Book Genre added: " + bookGenre.getBookGenresId());
        saveBookGenresToFile(); 
    }

    @Override
    public void updateBookGenre(String bookGenresId, BookGenres newBookGenre) {
        for (int i = 0; i < bookGenresList.size(); i++) {
            if (bookGenresList.get(i).getBookGenresId().equals(bookGenresId)) {
                bookGenresList.set(i, newBookGenre);
                System.out.println("Book Genre updated: " + bookGenresId);
                saveBookGenresToFile();
                return;
            }
        }
        System.out.println("Book Genre not found with ID: " + bookGenresId);
    }

    @Override
    public void deleteBookGenre(String bookGenresId) {
        for (int i = 0; i < bookGenresList.size(); i++) {
            if (bookGenresList.get(i).getBookGenresId().equals(bookGenresId)) {
                System.out.println("Book Genre removed: " + bookGenresList.remove(i).getBookGenresId());
                saveBookGenresToFile(); // Save after deleting
                return;
            }
        }
        System.out.println("Book Genre not found with ID: " + bookGenresId);
    }


    private void saveBookGenresToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (BookGenres bookGenre : bookGenresList) {
                writer.write(bookGenre.getBookGenresId() + "," + 
                            bookGenre.getBookId() + "," + 
                            bookGenre.getGenreId());
                writer.newLine();
            }
            System.out.println("Book Genres saved to file!");
        } catch (IOException e) {
            System.err.println("Error saving book genres to file: " + e.getMessage());
        }
    }

    private void loadBookGenresFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found: " + filePath + ". Will create new on save.");
            return;
        }
        if (file.length() == 0) {
            System.out.println("File " + filePath + " is empty. No book genres loaded.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    bookGenresList.add(new BookGenres(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                } else {
                    System.err.println("Invalid line in file " + filePath + ": " + line);
                }
            }
            System.out.println("Book Genres loaded from file!");
        } catch (IOException e) {
            System.err.println("Error loading book genres from file: " + e.getMessage());
        }
    }
}