package p2;
import java.util.ArrayList;
import java.util.List;

/*
- Create a `Library` class with:
  - `private List<Book> books`
  - Initialize books list in constructor (demonstrate Composition)
  - `void addBook(Book book)`
  - `void displayLibrary()` - display all books
 */

public class Library {
    // Composition: Library "owns" the list of books
    private List<Book> books;

    public Library() {
        // Initializing the list inside the constructor creates the strong Composition relationship
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void displayLibrary() {
        System.out.println("--- Current Library Inventory ---");
        for (Book b : books) {
            System.out.println(b);
        }
    }
}
