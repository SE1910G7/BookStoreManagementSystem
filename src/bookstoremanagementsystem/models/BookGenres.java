/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class BookGenres implements Serializable {

    String bookGenresId;
    String bookId;
    String genreId;

    public BookGenres(String bookGenresId, String bookId, String genreId) {
        this.bookGenresId = bookGenresId;
        this.bookId = bookId;
        this.genreId = genreId;
    }

    public String getBookGenresId() {
        return bookGenresId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setBookGenresId(String bookGenresId) {
        this.bookGenresId = bookGenresId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }
    
    
}
