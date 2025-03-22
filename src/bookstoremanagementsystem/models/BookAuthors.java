/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.io.Serializable;

/**
 *
 * @author mummykiara
 */
public class BookAuthors implements Serializable {

    String bookAuthorId;
    String bookID;
    String authorID;

    public BookAuthors(String bookAuthorId, String bookID, String authorID) {
        this.bookAuthorId = bookAuthorId;
        this.bookID = bookID;
        this.authorID = authorID;
    }

    public String getBookAuthorId() {
        return bookAuthorId;
    }

    public String getBookID() {
        return bookID;
    }

    public String getAuthorID() {
        return authorID;
    }
}
