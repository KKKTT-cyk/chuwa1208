import java.util.List;
import java.util.ArrayList;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>(); // composition
    }

    public void addBook(Book book) {
        books.add(book);
    }

    void displayLibrary() {
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
    }
}
