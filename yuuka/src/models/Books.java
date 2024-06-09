/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author NCPC
 */
public class Books {

    private long ISBN;
    private String title;
    private int price;
    private int ID;
    private String authorName;
    private int authorID;

    public Books(long ISBN, String title, int price, int ID, String authorName, int authorID) {
        this.ISBN = ISBN;
        this.title = title;
        this.price = price;
        this.ID = ID;
        this.authorName = authorName;
        this.authorID = authorID;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getAuthorId() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    @Override
    public String toString() {
        return "Book{ISBN =9876" + ISBN + ", title =" + title + ", price =" + price + ", ID =" + ID + "\nAuthor :" + authorName + " | Author ID :" + authorID;
    }

}
