package p2;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;
    // Initialize books list in constructor (demonstrate Composition)
    public Library() {
        books = new ArrayList<>();
    }

    // modifying to Aggregation
    /*
    public Library(List<Book> books) {
        this.books = books;
    }
    * */

    // void addBook(Book book)
    public void addBook(Book book) {
        books.add(book);
    }
    // void displayLibrary() - display all books
    public void displayLibrary() {
        System.out.println("=====Library Collection=====\n");
        for (Book book : books) {
            System.out.println(
                    "Title: " + book.getBookTitle() +
                            "\nISBN: " + book.getBookIsbn()
                            +"\n"
            );
        }
        System.out.println("============================");
    }
}
