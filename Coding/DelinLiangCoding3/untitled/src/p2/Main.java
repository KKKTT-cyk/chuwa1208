package p2;

public class Main {
    public static void main(String[] args) {

        Library library = new Library();

        Book b1 = new Book("Game Engine Architecture", "9781138035454");
        Book b2 = new Book("Cracking the Coding Interview", "9781466208681");
        Book b3 = new Book("System Design Interview", "9798645383572");

        // in case of Aggregation, we can new the list of books here
        // and then create the Library based on it, like this:
        /*
        List<Book> books = new ArrayList<>();
        books.add(b1);
        books.add(b2);
        books.add(b3);

        Library library = new Library(books);
        * */

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        library.displayLibrary();
    }

    /* Think: Why does the Book object's lifetime depend on the Library?
    * Answer: Because new books are only created in the Library object,
    * that means the lifetime of books cannot outlive the lifetime of
    * the Library object
    *
    * Think: How would you modify this to use Aggregation instead?
    * Answer: Instead of creating a new list of Books in Library constructor,
    * we modify it to own a list of books created somewhere else
    * (see the Library.java file for more detailed example showcase)
     * */
}
