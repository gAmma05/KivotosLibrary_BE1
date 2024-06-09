/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

//import controllers.ProductList;
/**
 *
 * @author NCPC
 */
public interface IBookList {

    void add();

    void saveToFile();

    void overwriteToFile();

    void show();

    void delete();

    void update();

    void searchByTitle();

    void searchByAuthor();

}
