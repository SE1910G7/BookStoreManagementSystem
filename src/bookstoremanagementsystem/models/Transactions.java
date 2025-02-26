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
public class Transactions {

    String transactionid;
    String customerName;
    double price;
    LocalDate createDate;

    public Transactions(String transactionid, String customerName, double price, LocalDate createDate) {
        this.transactionid = transactionid;
        this.customerName = customerName;
        this.price = price;
        this.createDate = createDate;
    }
}
