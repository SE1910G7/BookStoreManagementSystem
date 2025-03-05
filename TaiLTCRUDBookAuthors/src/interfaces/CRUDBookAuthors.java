/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import bookstoremanagementsystem.models.BookAuthors;
import java.util.List;
/**
 *
 * @author DELL
 */
public interface CRUDBookAuthors {
    public void view();
           void addAuthor(BookAuthors author);
           void updateAuthor(String bookAuthorId, BookAuthors newAuthor);
           void deleteAuthor(String bookAuthorId);
    
}
