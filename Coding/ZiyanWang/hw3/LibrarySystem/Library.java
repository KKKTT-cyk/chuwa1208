import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> books;

    // Initialize books list in constructor (composition flavor)
    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayLibrary() {
        System.out.println("Library Books:");
        for (Book b : books) {
            System.out.println("- " + b.getTitle()
                    + " | ISBN: " + b.getIsbn()
                    + " | Author: " + b.getAuthor().getName());
        }
    }
}
