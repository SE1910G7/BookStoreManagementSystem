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
 * @author mummykiara
 */
public class BookingTransactions extends Transactions implements Serializable {

    String orderId;

    public BookingTransactions(String orderId, String transactionid, String customerName, double price, LocalDate createDate) {
        super(transactionid, customerName, price, createDate);
        this.orderId = orderId;
    }

}
