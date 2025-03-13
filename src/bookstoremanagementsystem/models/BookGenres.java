/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

public class BookGenres {
    private String bookGenresId;
    private String bookId;
    private String genreId;

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
}

