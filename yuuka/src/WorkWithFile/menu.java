/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WorkWithFile;

import controllers.BookList;
import java.util.Scanner;
import models.InterfBookList;

/**
 *
 * @author NCPC
 */
public class menu {

    public static void menu() {
        System.out.println("--------------------***--------------------");
        System.out.println("Welcome to Kivotos Bookstore");
        System.out.println("1. Show the book list");
        System.out.println("2. Add a new book");
        System.out.println("3. Update the information of specific book");
        System.out.println("4. Delete a book");
        System.out.println("5. Search book by title");
        System.out.println("6. Search book by author's name");
        System.out.println("0. Quit");
    }

    public static void main(String[] args) {
        InterfBookList list = new BookList();
        Scanner sc = new Scanner(System.in);

        int option = 0;
        do {
            menu();
            System.out.print("Choose an option : ");
            option = sc.nextInt();
            switch (option) {
                case 1:
                    list.show();
                    break;

                case 2:
                    list.add();
                    break;

                case 3:
                    list.update();
                    break;

                case 4:
                    list.delete();
                    break;

                case 5:
                    list.searchByTitle();
                    break;

                case 6:
                    list.searchByAuthor();
                    break;

                case 0:
                    break;

                default:
                    System.out.println("Error input. Try again!");
                    break;
            }
        } while (option != 0);
        //list.add();

        //list.saveToFile();
        //list.readFile();
    }
}
