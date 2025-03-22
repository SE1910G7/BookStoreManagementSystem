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
    String genres;

    public Book(String bookId, String bookName, int publishedYear, String publisherId, String bookDescription,
            String language,String genres, double price) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.publishedYear = publishedYear;
        this.publisherId = publisherId;
        this.bookDescription = bookDescription;
        this.language = language;
        this.price = price;
        this.genres = genres;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(String publisherId) {
        this.publisherId = publisherId;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }
    
}
