public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        Book book1 = new Book("Book1", "000-001");
        Book book2 = new Book("Book2", "000-002");
        Book book3 = new Book("Book3", "000-003");

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.displayLibrary();
    }
}
