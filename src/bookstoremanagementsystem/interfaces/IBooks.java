/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.Book;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public interface IBooks {

    void loadBooks();

    void saveToFile();

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(String bookId);

    Book searchBookById(String bookId);

    List<Book> searchBooks(String keyword);

    void showBookList();
    
    void showBookDetails(Book book);
    
    void showBookSearchResults(List<Book> list);
}
