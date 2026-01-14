package Coding;

import java.util.Arrays;

import Coding.LibrarySystem.Book;
import Coding.LibrarySystem.Library;
import Coding.PaymentSystem.CreditCardPayment;
import Coding.PaymentSystem.PayPalPayment;
import Coding.PaymentSystem.PaymentMethod;
import Coding.Singleton.ConfigManager;

public class Main {
    public static void main(String[] args) {
        PaymentMethod paymentMethod = new CreditCardPayment();
        paymentMethod.processPayment(100);
        paymentMethod.printReceipt();

        paymentMethod = new PayPalPayment();
        paymentMethod.processPayment(250.5);
        paymentMethod.printReceipt();

        Book book1 = new Book("The Great Gatsby", "1234567890");
        Book book2 = new Book("1984", "1234567890");
        Book book3 = new Book("To Kill a Mockingbird", "1234567890");
        Library library = new Library(Arrays.asList(book1, book2, book3));
        library.displayLibrary();

        ConfigManager configManager = ConfigManager.getInstance();
        configManager.setConfig("database.url", "jdbc:mysql://localhost:3306/library");
        configManager.setConfig("database.username", "root");
        configManager.setConfig("database.password", "password");
        configManager.displayConfigs();
    }
}
