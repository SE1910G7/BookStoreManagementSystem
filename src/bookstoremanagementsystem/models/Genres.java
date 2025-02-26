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
public class Genres {

    String genreId;
    String genreName;
    String description;

    public Genres(String genreId, String genreName, String description) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.description = description;
    }

}
