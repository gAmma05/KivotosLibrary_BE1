/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import static java.lang.Character.isDigit;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import models.InterfBookList;
import models.Books;

/**
 *
 * @author NCPC
 */
public class BookList extends ArrayList<Books> implements InterfBookList, Serializable {

    @Override
    public void add() { //add Product
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert book's name : ");//book's name
        String name = sc.nextLine();
        for (char c : name.toCharArray()) {
            if (Character.isDigit(c)) {
                System.out.println("The input must be letters");
                return;
            }
        }

        boolean isDuplicate = false;
        boolean done = false;
        for (Books existingBook : this) {
            if (existingBook.getTitle().equalsIgnoreCase(name)) {//check if the input already existed in the list before
                System.out.println(name + " already exists");
                isDuplicate = true;//
                break;
            }
        }
        if (!isDuplicate) {//check if the input is new and not duplicated compared to the inside of the list
            System.out.println("Insert book's price (only accept higher than 10$ stock) : ");//book's price
            int price = sc.nextInt();
            if (price <= 10) {
                System.out.println("We only accept book's price costs higher than 10$");
                return;
            }

            System.out.println("Insert book's ID : ");
            int ID = sc.nextInt();

            sc.nextLine();
            if (ID <= 999999 && ID >= 100000) {
                System.out.println("Insert author's name : ");//author's name
                String authorName = sc.nextLine();
                for (char c : authorName.toCharArray()) {
                    if (Character.isDigit(c)) {
                        System.out.println("The input must be letters");
                        return;
                    }
                }
                System.out.println("Insert author's ID (4 digits) : ");//author's ID
                int authorID = sc.nextInt();
                if (authorID <= 9999 && authorID >= 1000) {
                    done = true;
                    long min;
                    min = 1000000000000L;
                    long max;
                    max = 9999999999999L;
                    long ISBN = ThreadLocalRandom.current().nextLong(min, max + 1);//randomize ISBN (13 digits)
                    Books book = new Books(ISBN, name, price, ID, authorName, authorID);
                    this.add(book);
                    this.overwriteToFile();
                    this.saveToFile();
                } else {
                    System.out.println("Author's ID must contain only 4 digits and must not start with 0");
                }
            } else {
                System.out.println("Your book's ID must contain only 6 digits and must not start with 0");
            }
        }
        //check if book was added successfully or undone
        if (done == true) {
            System.out.println("Added successfully");
        } else {
            System.out.println("Failed to add");
        }
    }

    @Override
    public void show() {
        Iterator<Books> it = this.iterator();
        if (!it.hasNext()) {
            System.out.println("The book list is empty");
        } else {
            for (Books book : this) {
                System.out.println(book.toString());
            }
        }
    }

    @Override
    public void delete() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert your book's ID to execute deletion : ");
        int delID = sc.nextInt();

        boolean isRemoved = false;
        Iterator<Books> it = this.iterator();
        while (it.hasNext()) {
            Books book = it.next();
            if (book.getID() == delID) { // Check for book ID match
                int isValidated;
                System.out.print("Do you want to delete this book? (1 = YES, 2 = NO) : ");
                isValidated = sc.nextInt();
                if (isValidated == 1) {
                    isRemoved = true;
                    it.remove(); // Remove the element using iterator's remove method
                    this.overwriteToFile();
                    this.saveToFile();
                    break; // Exit loop after successful deletion
                } else if (isValidated == 2) {
                    System.out.println("Failed to delete (Reason : User refused to execute)");
                    return;
                }
            }
        }
        //check if book was deleted successfully or undone
        if (isRemoved == true) {
            System.out.println("Deleted successfully.");
            this.saveToFile();
        } else {
            System.out.println("Book with ID " + delID + " not found.");
        }
    }

    @Override
    public void update() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert your book's ID to update information of it : ");
        int updID = sc.nextInt();
        Iterator<Books> it = this.iterator();
        boolean isUpdated = false;
        boolean isFound = false;
        while (it.hasNext()) {
            Books book = it.next();
            if (book.getID() == updID) {
                isFound = true;
                sc.nextLine();

                System.out.println("Insert new book's name : ");//author's name
                String newTitle = sc.nextLine();
                for (char c : newTitle.toCharArray()) {
                    if (Character.isDigit(c)) {
                        System.out.println("The input must be letters");
                        return;
                    }
                }
                boolean isDuplicate = false;
                for (Books existingBook : this) {
                    if (existingBook.getTitle().equalsIgnoreCase(newTitle)) {//check if the current input for the book equals to other books existed in list or the old name of current book
                        System.out.println(newTitle + " already exists");
                        isDuplicate = true;
                        break;
                    }
                }
                if (!isDuplicate) {
                    System.out.println("Insert new book's price (only accept higher than 10$ stock) : ");//book's price
                    int newPrice = sc.nextInt();
                    if (newPrice <= 10) {//check if the price is higher than the given condition
                        System.out.println("Your new book should cost more than 10$!");
                        return;// return to the menu
                    }

                    System.out.println("Do you want to change the author of this book? (1 = YES, 2 = NO) : ");
                    int check = sc.nextInt();
                    if (check == 1) {
                        sc.nextLine();

                        System.out.println("Insert new author's name for this book : ");
                        String newAuthorName = sc.nextLine();
                        for (char c : newAuthorName.toCharArray()) {
                            if (Character.isDigit(c)) {
                                System.out.println("The input must be letters");
                                return;
                            }
                        }

                        System.out.println("Insert new author's ID for this book : ");
                        int newAuthorID = sc.nextInt();

                        int isValidated;
                        System.out.print("Do you want to change the information of this book (1 = YES, 2 = NO) : ");
                        isValidated = sc.nextInt();
                        if (isValidated == 1) {
                            if (newAuthorID < 1000 || newAuthorID > 9999) {
                                System.out.println("Author's ID must be limited between 4 digits and must not start with 0\nFailed to update");
                                return;
                            } else if (book.getID() == newAuthorID) {
                                System.out.println("This author ID already existed\nFailed to update");
                                return;
                            } else {
                                isUpdated = true;
                                long ISBN = book.getISBN();
                                int ID = book.getID();
                                Books bok = new Books(ISBN, newTitle, newPrice, ID, newAuthorName, newAuthorID);
                                this.add(bok);// add to arraylist
                                this.remove(book);// remove the old one
                                this.overwriteToFile();
                                this.saveToFile();
                            }
                        } else if (isValidated == 2) {
                            System.out.println("Failed to delete (Reason : User refused to execute)");
                            return;//return to the menu
                        }
                    } else if (check == 2) {
                        isUpdated = true;
                        String noCAuthorName = book.getAuthorName();
                        int noCAuthorID = book.getAuthorId();
                        long ISBN = book.getISBN();
                        int ID = book.getID();

                        Books bok = new Books(ISBN, newTitle, newPrice, ID, noCAuthorName, noCAuthorID);
                        this.add(bok);
                        this.remove(book);
                        this.overwriteToFile();
                        this.saveToFile();
                    } else if (check != 1 && check != 2) {
                        System.out.println("Error input.");
                        return;
                    }
                }
            }
        }

        //check if ID was successfully updated or undone
        if (isFound == false) {
            System.out.println(updID + "is not found");
        }
        if (isUpdated == true) {
            System.out.println("Updated successfully");
        } else {
            System.out.println("Failed to update (Reason : Error syntax / input)");
        }
    }

    @Override
    public void searchByTitle() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert book's title to search : ");
        String findTitle = sc.nextLine();
        boolean isFound = false;

        for (Books existingBook : this) {
            if (existingBook.getTitle().equalsIgnoreCase(findTitle)) {
                System.out.println(existingBook.toString());
                isFound = true;
            }
        }
        if (isFound == false) {
            System.out.println("Book with title " + findTitle + " is not found!");
        }
    }

    @Override
    public void searchByAuthor() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insert author's name to search for books : ");
        String findAuthor = sc.nextLine();
        boolean isFound = false;

        for (Books existingBook : this) {
            if (existingBook.getAuthorName().equalsIgnoreCase(findAuthor)) {
                System.out.println(existingBook.toString());
                isFound = true;
            }
        }
        if (isFound == false) {
            System.out.println("Books composed by author's name " + findAuthor + " is not found!");
        }
    }

    @Override
    public void saveToFile() { //save Product to File
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\data\\product.dat", true))) {
            for (Books book : this) {
                bw.write(book.toString() + "\n");
                //bw.write(book.toAString()+ "\n");
            }
            bw.flush();
            bw.close();
            //System.out.println("Success");
        } catch (IOException e) {
            //System.out.println("Failed!");
        }
    }

    @Override
    public void overwriteToFile() { //clear all data
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src\\data\\product.dat"))) {
            bw.write("");
            //bw.write(book.toAString()+ "\n");
            bw.flush();
            bw.close();
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Failed!");
        }
    }
}
