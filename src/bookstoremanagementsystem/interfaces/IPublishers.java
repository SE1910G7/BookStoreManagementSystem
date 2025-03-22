/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import bookstoremanagementsystem.models.Publishers;
import java.util.List;

/**
 *
 * @author nhulnt
 */
public interface IPublishers {
    void loadPublishers();
    void addPublisher(Publishers publisher);
    void updatePublisher(Publishers publisher);
    void deletePublisher(String publisherId);
    void showPublishers();
    List<Publishers> searchPublisher(String searchDetail);
    Publishers searchPublisherById(String publisherId);
    void saveToFile();
    void showPublishersByList(List<Publishers> list);
}
