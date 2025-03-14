/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.services.MenuManager.LogInForm;

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
}
