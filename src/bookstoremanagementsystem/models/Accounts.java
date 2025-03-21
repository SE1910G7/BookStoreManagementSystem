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
public class Accounts implements Serializable{

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

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Accounts{" + "accountID=" + accountID + ", fullName=" + fullName + ", email=" + email + ", password=" + password + ", phoneNumber=" + phoneNumber + ", address=" + address + ", role=" + role + ", createdAt=" + createdAt + '}';
    }
    
}
