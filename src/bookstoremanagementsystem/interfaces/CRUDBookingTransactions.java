/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

/**
 *
 * @author DELL
 */
public interface CRUDBookingTransactions {
    void addBooking(String id, String details, StringBuilder outputBuffer);
    void viewBookings(StringBuilder outputBuffer);
    void deleteBooking(String id, StringBuilder outputBuffer);
}
