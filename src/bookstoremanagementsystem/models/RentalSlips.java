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

    public String getRentFormId() {
        return rentFormId;
    }

    public void setRentFormId(String rentFormId) {
        this.rentFormId = rentFormId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public LocalDate getStartRentBookDate() {
        return startRentBookDate;
    }

    public void setStartRentBookDate(LocalDate startRentBookDate) {
        this.startRentBookDate = startRentBookDate;
    }

    public LocalDate getEndRentBookDate() {
        return endRentBookDate;
    }

    public void setEndRentBookDate(LocalDate endRentBookDate) {
        this.endRentBookDate = endRentBookDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    
}
