import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (book != null) {
            books.add(book);
        }
    }

    public void displayLibrary() {
        if (!books.isEmpty()) {
            for (Book b: books) {
                System.out.println("Title: " + b.getTitle() + " ISBN: " + b.getIsbn());
            }
        }
    }
}
