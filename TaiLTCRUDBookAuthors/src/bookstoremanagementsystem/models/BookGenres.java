/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

/**
 *
 * @author mummykiara
 */
public class BookGenres {

    String bookGenresId;
    String bookId;
    String genreId;

    public BookGenres(String bookGenresId, String bookId, String genreId) {
        this.bookGenresId = bookGenresId;
        this.bookId = bookId;
        this.genreId = genreId;
    }
}
