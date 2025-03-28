/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.services.MenuManager.LogInForm;
import bookstoremanagementsystem.services.MenuManager.RegisterForm;

/**
 *
 * @author nhulnt
 */
public interface IMenu {

    //account type menu
    public void showAdminMenu();

    public void showUserMenu();

    public RegisterForm showRegisterMenu();

    public LogInForm showLogInMenu();

    //welcome menu
    public void showWelcomeMenu();

    //genre manager menu
    public void showGenreMainMenu();

    //account manager menu
    public void showAccountMainMenu();

    public String showAccountSearchForm();

    public void showAccountUpdateForm();
    public void showUserUpdateForm(String email);

    //author manager menu
    public void showAuthorsMainMenu();

    public void showAuthorUpdateForm();

    //publisher manager menu
    public void showPublishersMainMenu();

    //book manager menu
    public void showBooksMainMenu();

    //Orders manager menu
    void showOrdersMainMenu();
    
     //Orders manager menu
    void showRentalSlipsMainMenu();

}
