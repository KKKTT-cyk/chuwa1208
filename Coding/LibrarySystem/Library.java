package Coding.LibrarySystem;

import java.util.List;

public class Library {
    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayLibrary() {
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + book.getIsbn());
        }
    }
}
