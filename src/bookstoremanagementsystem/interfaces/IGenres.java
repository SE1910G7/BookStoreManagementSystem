/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

/**
 *
 * @author Admin Lenovo
 */
public interface IGenres {
    void addGenre(String genreID, String genreName, String description);
    void displayGenres();
    void sortGenres();
    void searchGenre(String searchInput);
    void deleteGenre(String searchInput);
    void saveGenresToFile();
    void loadGenresFromFile();
    void editGenre(String searchInput, String newGenreID, String newGenreName, String newDescription);
}
