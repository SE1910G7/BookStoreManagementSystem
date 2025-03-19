package services;

import interfaces.IBookGenres; 

import bookstoremanagementsystem.models.BookGenres;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookGenresManager implements IBookGenres {
    private final List<BookGenres> bookGenresList = new ArrayList<>();

    public void view() {
        if (bookGenresList.isEmpty()) {
            System.out.println("No book-genre relationships available.");
        } else {
            for (BookGenres bg : bookGenresList) {
                System.out.println("BookGenres ID: " + bg.getBookGenresId() +
                        ", Book ID: " + bg.getBookId() +
                        ", Genre ID: " + bg.getGenreId());
            }
        }
    }

    public void addBookGenres(BookGenres bookGenre) {
        bookGenresList.add(bookGenre);
        System.out.println("Book-Genre relationship added: " + bookGenre.getBookGenresId());
    }

    public void updateBookGenres(String bookGenresId, BookGenres newBookGenre) {
        for (int i = 0; i < bookGenresList.size(); i++) {
            if (bookGenresList.get(i).getBookGenresId().equals(bookGenresId)) {
                bookGenresList.set(i, newBookGenre);
                System.out.println("Book-Genre relationship updated: " + bookGenresId);
                return;
            }
        }
        System.out.println("Book-Genre relationship not found with ID: " + bookGenresId);
    }

    public void deleteBookGenres(String bookGenresId) {
        for (int i = 0; i < bookGenresList.size(); i++) {
            if (bookGenresList.get(i).getBookGenresId().equals(bookGenresId)) {
                System.out.println("Book-Genre relationship removed: " + bookGenresList.remove(i).getBookGenresId());
                return;
            }
        }
        System.out.println("Book-Genre relationship not found with ID: " + bookGenresId);
    }

    public void saveToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (BookGenres bg : bookGenresList) {
                writer.write(bg.getBookGenresId() + "," + bg.getBookId() + "," + bg.getGenreId());
                writer.newLine();
            }
            System.out.println("Data saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void addBookGenre(BookGenres bookGenre) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void updateBookGenre(String bookGenresId, BookGenres newBookGenre) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void deleteBookGenre(String bookGenresId) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}
