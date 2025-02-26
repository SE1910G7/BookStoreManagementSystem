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
public class Publishers {

    String publisherId;
    String publisherName;
    boolean isActive;

    public Publishers(String publisherId, String publisherName, boolean isActive) {
        this.publisherId = publisherId;
        this.publisherName = publisherName;
        this.isActive = isActive;
    }
}
