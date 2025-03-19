/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

/**
 *
 * @author PC
 */

import bookstoremanagementsystem.models.RentalSlips;

public interface IRentalSlips {
    void addRentalSlip(RentalSlips rentalSlip);
    void displayRentalSlips();
    void sortRentalSlips();
    void searchRentalSlip(String searchInput);
    void deleteRentalSlip(String searchInput);
    void editRentalSlip(String searchInput, RentalSlips newRentalSlip);
    void saveRentalSlipsToFile();
    void loadRentalSlipsFromFile();
}
