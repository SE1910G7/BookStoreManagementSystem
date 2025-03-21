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
 * @author mummykiara
 */
public interface IMenu {

    public void showAdminMenu();

    public void showUserMenu();

    public void showWelcomeMenu();

    public LogInForm showLogInMenu();

    public void showGenreMainMenu();

    public RegisterForm showRegisterMenu();

    public void showAccountMainMenu();

    public String showAccountSearchForm();

    public void showAccountUpdateForm();
}
