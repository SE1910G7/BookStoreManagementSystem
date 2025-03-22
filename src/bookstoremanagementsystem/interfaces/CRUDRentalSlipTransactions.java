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
public interface CRUDRentalSlipTransactions {

    void addRentalSlip(String id, String details, StringBuilder outputBuffer);

    void viewRentalSlips(StringBuilder outputBuffer);

    void deleteRentalSlip(String id, StringBuilder outputBuffer);
}
