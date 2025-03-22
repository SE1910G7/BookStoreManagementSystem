/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.RentalSlips;

/**
 *
 * @author mummykiara
 */
public interface IRentalSlips {

    void loadRentalSlips();

    void saveToFile();

    void createRentalSlip(RentalSlips slip);

    void updateRentalStatus(String rentFormId, String newStatus);

    void deleteRentalSlip(String rentFormId);

    void showRentalSlips();

    RentalSlips getRentalSlipById(String rentFormId);

    void showRentalSlipDetails(RentalSlips slip);
}
