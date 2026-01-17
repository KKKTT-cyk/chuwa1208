package P2;
/*
Create a Book class with:
    private String title
    private String isbn
    Constructor and getter methods
 */
public class Book {
    private String title;
    private String isbn;

    public Book(String title, String isbn){
        this.title = title;
        this.isbn = isbn;
    }
    public String getTitle(){
        return title;
    }
    public String getIsbn(){
        return isbn;
    }
}
