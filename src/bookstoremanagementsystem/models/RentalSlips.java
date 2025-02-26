/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.time.LocalDate;

/**
 *
 * @author mummykiara
 */
public class RentalSlips {

    String rentFormId;
    String accountId;
    String bookId;
    LocalDate startRentBookDate;
    LocalDate endRentBookDate;
    LocalDate createdAt;
    double totalPrice;

    public RentalSlips(String rentFormId, String accountId, String bookId, LocalDate startRentBookDate,
            LocalDate endRentBookDate, LocalDate createdAt, double totalPrice) {
        this.rentFormId = rentFormId;
        this.accountId = accountId;
        this.bookId = bookId;
        this.startRentBookDate = startRentBookDate;
        this.endRentBookDate = endRentBookDate;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
    }
}
