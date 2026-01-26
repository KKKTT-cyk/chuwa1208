public class Main {
    public static void main(String[] args) {
        Library lib = new Library();

        lib.addBook(new Book("foo", "000001"));
        lib.addBook(new Book("bar", "000002"));

        lib.displayLibrary();
    }
}
