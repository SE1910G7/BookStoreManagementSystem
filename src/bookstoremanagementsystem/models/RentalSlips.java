/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.models;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * @author nhulnt
 */
public class RentalSlips implements Serializable {

    String rentFormId;
    String accountId;
    String bookId;
    LocalDate startRentBookDate;
    LocalDate endRentBookDate;
    LocalDate createdAt;
    double totalPrice;
    String rentStatus;

    public RentalSlips(String rentFormId, String accountId, String bookId, LocalDate startRentBookDate,
            LocalDate endRentBookDate, LocalDate createdAt, double totalPrice, String rentStatus) {
        this.rentFormId = rentFormId;
        this.accountId = accountId;
        this.bookId = bookId;
        this.startRentBookDate = startRentBookDate;
        this.endRentBookDate = endRentBookDate;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.rentStatus = rentStatus;
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

    public String getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }
    
}
