package src.P2_composition;

public class Book {
    private String title;
    private String isbn;
    private Author author;
    public Book(String title, String isbn, String authorName) {
        this.title = title;
        this.isbn = isbn;
        this.author = new Author(authorName);
    }

    public String getTitle(){
        return this.title;
    }

    public String getIsbn(){
        return this.isbn;
    }

    public Author getAuthor(){
        return this.author;
    }

}
