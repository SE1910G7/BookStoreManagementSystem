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
public class Accounts {

    String accountID;
    String fullName;
    String email;
    String password;
    String phoneNumber;
    String address;
    String role;
    LocalDate createdAt;

    public Accounts(String accountID, String fullName, String email, String password, String phoneNumber,
            String address, String role, LocalDate createdAt) {
        this.accountID = accountID;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.createdAt = createdAt;
    }
}
