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
public class RentalSlipTransactions extends Transactions {

    String rentFormId;

    public RentalSlipTransactions(String rentFormId, String transactionid, String customerName, double price, LocalDate createDate) {
        super(transactionid, customerName, price, createDate);
        this.rentFormId = rentFormId;
    }

}
