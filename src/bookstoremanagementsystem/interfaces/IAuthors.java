/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.Authors;
import java.util.List;

public interface IAuthors {
   void addAuthor(String authorID, String fullName);
    void displayAuthors();
    void sortAuthors();
    void searchAuthor(String searchInput);
    void deleteAuthor(String searchInput);
    void saveAuthorsToFile();
    void loadAuthorsFromFile();
    Authors searchAuthorByID(String authorID);
    void updateAuthor(Authors author);
}
