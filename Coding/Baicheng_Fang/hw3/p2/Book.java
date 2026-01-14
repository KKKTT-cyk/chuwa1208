public class Book {
    private String title;
    private String isbn;

    Book(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
    }

    String getTitle() {
        return title;
    }

    String getIsbn() {
        return isbn;
    }
}
