package P2;

import java.util.ArrayList;
import java.util.List;

/*
Create a Library class with:
    private List<Book> books
    Initialize books list in constructor (demonstrate Composition)
    void addBook(Book book)
    void displayLibrary() - display all books
 */
public class Library {
    private List<Book> books;
    public Library(){
        this.books = new ArrayList<>();
    }
    void addBook(Book book){
        books.add(book);
    }
    void displayLibrary(){
        for (Book b : books){
            System.out.println(b);
        }
    }
}
