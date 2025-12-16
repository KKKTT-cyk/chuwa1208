package src.P2_composition;

public class Main {
    public static void main(String[] args) {
        Library lb = new Library();

        lb.addBook(new Book("Artificial Intelligence", "001", "Alpha"));
        lb.addBook(new Book("Machine Learning", "002", "Bravo"));
        lb.addBook(new Book("Reinforcement Learning", "003", "Charlie"));

        lb.displayLibrary();
    }
}
