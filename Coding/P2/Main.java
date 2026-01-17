package P2;

import java.util.ArrayList;
import java.util.List;

/*
In Main, create a Library object, add 3 books, and display them
 */
public class Main {
    public static void main(String[] args) {
        // Create a library list
        System.out.println("Test Library");
        Book s1 = new Book("Hello", "123-456");
        Book s2 = new Book("World", "234-567");
        Book s3 = new Book("Check", "345-678");

//        Library l1 = new Library();
//        l1.addBook(s1);
//        l1.addBook(s2);
//        l1.addBook(s3);
//
//        l1.displayLibrary();
//
        // Aggregation method
        List<Book> sharebook = new ArrayList<>();
        sharebook.add(s1);
        sharebook.add(s2);
        sharebook.add(s3);

        Library_Agg l2 = new Library_Agg(sharebook);
        l2.displayLibrary();

        // use Author class
        Author author = new Author("Chen");
        System.out.println(author.getName());


    }
}
