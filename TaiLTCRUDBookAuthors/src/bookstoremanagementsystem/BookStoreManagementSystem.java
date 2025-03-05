/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem;
import bookstoremanagementsystem.models.BookAuthors;
import services.BookAuthorsManager;
/**
 *
 * @author mummykiara
 */
public class BookStoreManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BookAuthorsManager service = new BookAuthorsManager();
            
        
        // Adding authors
        service.addAuthor(new BookAuthors("1", "101", "201"));
        service.addAuthor(new BookAuthors("2", "102", "202"));
        
        // Viewing authors
        service.view();
        
        // Updating an author
        service.updateAuthor("1", new BookAuthors("1", "101", "203"));
        
        // Viewing after update
        service.view();
        
        // Deleting an author
        service.deleteAuthor("2");
        
        // Viewing after delete
        service.view();
    }
}
