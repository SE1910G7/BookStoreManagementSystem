/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoremanagementsystem.interfaces;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 *
 * @author DELL
 */
public interface CRUDPublisher {
    void addPublisher(String id, String name, StringBuilder outputBuffer);
    void viewPublishers(StringBuilder outputBuffer);
    void updatePublisher(String id, String newName, StringBuilder outputBuffer);
    void deletePublisher(String id, StringBuilder outputBuffer);
}
