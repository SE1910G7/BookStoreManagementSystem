/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.Accounts;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public interface IAccount {
    public void LoadAccountProfile();
    public int LogIn(String email, String password);
}
