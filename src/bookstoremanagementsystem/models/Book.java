/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public class Book implements Serializable {

    String bookId;
    String bookName;
    int publishedYear;
    String publisherId;
    String bookDescription;
    String language;
    double price;
    List<Genres> genres;

    public Book(String bookId, String bookName, int publishedYear, String publisherId, String bookDescription,
            String language, double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publishedYear = publishedYear;
        this.publisherId = publisherId;
        this.bookDescription = bookDescription;
        this.language = language;
        this.price = price;
        this.genres = new ArrayList<>();
    }
}
