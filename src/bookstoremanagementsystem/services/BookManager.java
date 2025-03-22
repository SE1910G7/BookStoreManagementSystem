/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IBooks;
import bookstoremanagementsystem.interfaces.IGenres;
import bookstoremanagementsystem.models.Book;
import bookstoremanagementsystem.models.Genres;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public class BookManager implements IBooks {

    private List<Book> bookList = new ArrayList<>();
    private final String bookFile = "books.txt";

    @Override
    public void loadBooks() {
        bookList.clear();
        try (FileInputStream fi = new FileInputStream(bookFile);
                ObjectInputStream oi = new ObjectInputStream(fi)) {
            Book book;
            while ((book = (Book) oi.readObject()) != null) {
                bookList.add(book);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No book file found. Creating a new one.");
        } catch (IOException | ClassNotFoundException e) {
//            System.out.println("Error loading books: " + e.getMessage());
        }
    }

    @Override
    public void saveToFile() {
        try (FileOutputStream fo = new FileOutputStream(bookFile);
                ObjectOutputStream oo = new ObjectOutputStream(fo)) {
            for (Book book : bookList) {
                oo.writeObject(book);
            }
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }

    @Override
    public void addBook(Book book) {
        bookList.add(book);
        saveToFile();
        System.out.printf("%10sBook added successfully! ", "");
    }

    @Override
    public void updateBook(Book book) {
        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId().equals(book.getBookId())) {
                bookList.set(i, book);
                saveToFile();
                System.out.printf("%10sBook updated successfully! ", "");
                return;
            }
        }
    }

    @Override
    public void deleteBook(String bookId) {

        for (int i = 0; i < bookList.size(); i++) {
            if (bookList.get(i).getBookId().equals(bookId)) {
                bookList.remove(i);
                saveToFile();
                System.out.printf("%10sBook deleted successfully!\n", "");
                return;
            }
        }
        System.out.printf("%10sBook not found!\n", "");

    }

    @Override
    public Book searchBookById(String bookId) {
        for (Book book : bookList) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    @Override
    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();
        for (Book book : bookList) {
            if (book.getBookName().toLowerCase().contains(keyword.toLowerCase())
                    || book.getBookDescription().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public void showBookList() {
        loadBooks();
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*--------------------!! BOOK LIST !!----------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-6s | %-25s | %-10s | %-8s |\n", "", "ID", "Name", "Year", "Price");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (bookList.isEmpty()) {
            System.out.printf("%10sNo books found!\n", "");
            return;
        }

        for (Book book : bookList) {
            String bookName = book.getBookName().length() > 20 ? book.getBookName().substring(0, 17) + "..." : book.getBookName();
            String publisher = book.getPublisherId().length() > 25 ? book.getPublisherId().substring(0, 22) + "..." : book.getPublisherId();
            String language = book.getLanguage().length() > 18 ? book.getLanguage().substring(0, 15) + "..." : book.getLanguage();
            String description = book.getBookDescription().length() > 30 ? book.getBookDescription().substring(0, 27) + "..." : book.getBookDescription();

            System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", book.getBookId(), bookName, publisher);
            System.out.printf("%10s|        | Language: %-10s | Price: %-8.2fVND %6s|\n", "", language, book.getPrice(), "");
            System.out.printf("%10s|        | Description: %-30s %5s |\n", "", description, "");
            System.out.printf("%10s-------------------------------------------------------------\n", "");
            System.out.printf("%10s-------------------------------------------------------------\n", "");
        }
    }

    @Override
    public void showBookDetails(Book book) {
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*-------------------!! BOOK DETAILS !!-------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");

        System.out.printf("%10s| %-15s: %-40s |\n", "", "ID", book.getBookId());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Name", book.getBookName());
        System.out.printf("%10s| %-15s: %-40d |\n", "", "Year", book.getPublishedYear());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Publisher", book.getPublisherId());
        System.out.printf("%10s| %-15s: %-40s |\n", "", "Language", book.getLanguage());
        System.out.printf("%10s| %-15s: $%-39.2f |\n", "", "Price", book.getPrice());

        System.out.printf("%10s-------------------------------------------------------------\n", "");
        System.out.printf("%10s| %-15s %42s|\n", "", "Description", "");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        String[] descriptionLines = book.getBookDescription().split("(?<=\\G.{50})");
        for (String line : descriptionLines) {
            System.out.printf("%10s| %-50s |\n", "", line.trim());
        }

        IGenres genreManager = new GenresManager();
        genreManager.loadGenresFile();
        Genres bookGenre = genreManager.searchGenreById(book.getGenres());

        System.out.printf("%10s-------------------------------------------------------------\n", "");
        System.out.printf("%10s| %-15s %42s|\n", "", "Genres", "");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (bookGenre == null) {
            System.out.printf("%10s| %-50s |\n", "", "No genres available.");
        } else {
            System.out.printf("%10s| %-50s |\n", "", bookGenre.getGenreName());
        }

        System.out.printf("%10s*************************************************************\n", "");
    }

    @Override
    public void showBookSearchResults(List<Book> list) {
        System.out.printf("\n\n%10s*************************************************************\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*-------------------!! BOOK SEARCH RESULTS !!------------------*\n", "");
        System.out.printf("%10s*%59s*\n", "", "");
        System.out.printf("%10s*************************************************************\n", "");
        System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", "ID", "Name", "Publisher");
        System.out.printf("%10s-------------------------------------------------------------\n", "");

        if (list.isEmpty()) {
            System.out.printf("%10s| %-59s |\n", "", "No matching books found.");
            System.out.printf("%10s-------------------------------------------------------------\n", "");
            System.out.printf("%10s*************************************************************\n", "");
            return;
        }

        for (Book book : list) {
            String bookName = book.getBookName().length() > 20 ? book.getBookName().substring(0, 17) + "..." : book.getBookName();
            String publisher = book.getPublisherId().length() > 25 ? book.getPublisherId().substring(0, 22) + "..." : book.getPublisherId();
            String language = book.getLanguage().length() > 18 ? book.getLanguage().substring(0, 15) + "..." : book.getLanguage();
            String description = book.getBookDescription().length() > 30 ? book.getBookDescription().substring(0, 27) + "..." : book.getBookDescription();

            System.out.printf("%10s| %-6s | %-20s | %-25s |\n", "", book.getBookId(), bookName, publisher);
            System.out.printf("%10s|        | Language: %-10s | Price: %-8.2fVND %6s|\n", "", language, book.getPrice(), "");
            System.out.printf("%10s|        | Description: %-30s %5s |\n", "", description, "");
            System.out.printf("%10s-------------------------------------------------------------\n", "");
        }

        System.out.printf("%10s*************************************************************\n", "");
    }

}
