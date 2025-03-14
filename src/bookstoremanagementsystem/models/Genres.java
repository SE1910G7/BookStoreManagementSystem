/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.io.Serializable;

/**
 *
 * @author kiara
 */
public class Genres implements Serializable {

    String genreId;
    String genreName;
    String description;

    public Genres(String genreId, String genreName, String description) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.description = description;
    }

    public String getGenreId() {
        return genreId;
    }

    public void setGenreId(String genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String shortDescription = description;
        if (description.length() > 25) { 
            shortDescription = description.substring(0, 25) + "...";
        }
        return String.format("| %-8s | %-15s | %-25s |", genreId, genreName, shortDescription);
    }
}
