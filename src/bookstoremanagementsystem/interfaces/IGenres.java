/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.Genres;

/**
 *
 * @author mummykiara
 */
public interface IGenres {
    public void loadGenresFile();
    public void saveGenresFile();
    public void createGenre();
    public void showGenreList();
    public void updateGenre();
    public void removeGenre();
    public void showGenreDetail();
}
