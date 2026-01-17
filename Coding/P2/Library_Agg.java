package P2;

import java.util.List;

public class Library_Agg {
    private List<Book> books;

    public Library_Agg(List<Book> books){
        this.books = books;
    }
    public void addBook(Book book){
        books.add(book);
    }
    public void displayLibrary(){
        for (Book b : books){
            System.out.println(b);
        }
    }
}
