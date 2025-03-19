/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.BookGenres;
/**
 *
 * @author PC
 */

public interface IBookGenres {
    void view();
    void addBookGenre(BookGenres bookGenre);
    void updateBookGenre(String bookGenresId, BookGenres newBookGenre);
    void deleteBookGenre(String bookGenresId);
}