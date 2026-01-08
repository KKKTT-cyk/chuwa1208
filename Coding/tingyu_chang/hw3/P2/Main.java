/*
- In `Main`, create a Library object, add 3 books, and display them
- Think: Why does the Book object's lifetime depend on the Library? How would you modify this to use Aggregation instead?
 */
package p2;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Library Composition Test ---");

        // 1. Create Library
        Library myLibrary = new Library();

        // 2. Create Books (Using the constructor from Book.java)
        Book b1 = new Book("Effective Java", "978-0134685991");
        Book b2 = new Book("Clean Code", "978-0132350884");
        Book b3 = new Book("Head First Design Patterns", "978-0596007126");

        // 3. Add Books to Library
        myLibrary.addBook(b1);
        myLibrary.addBook(b2);
        myLibrary.addBook(b3);

        // 4. Display
        myLibrary.displayLibrary();

        // The Author class exists in the folder as per requirements,
        // but is not linked to Book in this specific version.
        Author auth = new Author("Generic Author");
        System.out.println("\n(Author created separately: " + auth.getName() + ")");
    }
}
