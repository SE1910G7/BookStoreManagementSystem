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
public class Authors {

    private String authorID;
    private String fullName;

    public Authors(String authorID, String fullName) {
        this.authorID = authorID;
        this.fullName = fullName;
    }

    public String getAuthorID() {
        return authorID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setAuthorID(String authorID) {
        this.authorID = authorID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
