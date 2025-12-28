public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book("Clean Code", "978-0132350884", new Author("Robert C. Martin")));
        library.addBook(new Book("Effective Java", "978-0134685991", new Author("Joshua Bloch")));
        library.addBook(new Book("Design Patterns", "978-0201633610", new Author("GoF")));

        library.displayLibrary();
    }
}
