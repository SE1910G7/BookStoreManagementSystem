/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.services;

import bookstoremanagementsystem.interfaces.IBookAuthors;
import bookstoremanagementsystem.models.BookAuthors;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public class BookAuthorsManager implements IBookAuthors {

    private final List<BookAuthors> authors = new ArrayList<>();

    @Override
    public void view() {
        if (authors.isEmpty()) {
            System.out.println("No authors available.");
        } else {
            authors.forEach((author) -> {
                System.out.println("Book Author ID: " + author.getBookAuthorId() + ", Book ID: " + author.getBookID() + ", Author ID: " + author.getAuthorID());
            });
        }
    }

    @Override
    public void addAuthor(BookAuthors author) {
        authors.add(author);
        System.out.println("Author added: " + author.getAuthorID());
    }

    @Override
    public void updateAuthor(String bookAuthorId, BookAuthors newAuthor) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getBookAuthorId().equals(bookAuthorId)) {
                authors.set(i, newAuthor);
                System.out.println("Author updated: " + bookAuthorId);
                return;
            }
        }
        System.out.println("Author not found with ID: " + bookAuthorId);
    }

    @Override
    public void deleteAuthor(String bookAuthorId) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getBookAuthorId().equals(bookAuthorId)) {
                System.out.println("Author removed: " + authors.remove(i).getAuthorID());
                return;
            }
        }
        System.out.println("Author not found with ID: " + bookAuthorId);
    }

}
