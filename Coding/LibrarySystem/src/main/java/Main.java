//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book b1 = new Book("Clean Code", "111-AAA");
        Book b2 = new Book("Effective Java", "222-BBB");
        Book b3 = new Book("Design Patterns", "333-CCC");

        library.addBook(b1);
        library.addBook(b2);
        library.addBook(b3);

        library.displayLibrary();
    }

    /**
     * In composition, library owns the collection of books.
     * When library is destroyed, books are destroyed as well.
     * To change to aggregation, pass books to Library constructor.
     *
     *     public Library(List<Book> books) {
     *         this.books = books;
     *     }
     * Then in main:
     *
     *         List<Book> sharedBooks = new ArrayList<>();
     *
     *         sharedBooks.add(new Book("Clean Code", "111-AAA"));
     *         sharedBooks.add(new Book("Effective Java", "222-BBB"));
     *         sharedBooks.add(new Book("Design Patterns", "333-CCC"));
     *
     *         Library library = new Library(sharedBooks); <- add books to constructor
     *
     */
}