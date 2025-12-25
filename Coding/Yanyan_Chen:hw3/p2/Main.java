public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(new Book("Thirteen Days in September", "0884"));
        library.addBook(new Book("Wolf in White Van", "5991"));
        library.addBook(new Book("Vindication of the Rights of Woman", "3610"));

        library.displayLibrary();
    }
}
