package p2;

public class Book {
    private String title;
    private String isbn;
    // Constructor and getter methods
    public Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return title;
    }

    public String getBookIsbn() {
        return isbn;
    }
}
