package src.P2_composition;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library(){
        this.books = new ArrayList<>();
    }

    // Aggregation?
    public void addBook(Book book) {
        this.books.add(book);
    }

    public void displayLibrary() {
        System.out.println("Library Books:");
        for (Book book: this.books) {
            System.out.println("Book title: " + book.getTitle() +
                    ", ISBN: " + book.getIsbn() +
                    ", author: " + book.getAuthor().getName());
        }
    }
}
