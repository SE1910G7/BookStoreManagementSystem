/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.Accounts;
import bookstoremanagementsystem.services.MenuManager;
import java.util.List;

/**
 *
 * @author mummykiara
 */
public interface IAccount {

    public void LoadAccountProfile();

    public int LogIn(String email, String password);

    public void registerAccount(MenuManager.RegisterForm registerForm);

    public void saveToFile();

    public void showAccountList();

    public List<Accounts> searchAccount(String searchDetail);

    public Accounts searchAccountByEmail(String email);

    public void showAccountListByList(List<Accounts> list);
    public void UpdateAccount(Accounts model);
}
