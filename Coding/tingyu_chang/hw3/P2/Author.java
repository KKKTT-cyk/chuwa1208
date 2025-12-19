package p2;

/*
- Create an `Author` class with:
  - `String name` - private field
  - `Author(String name)` - constructor
  - `String getName()` - accessor
 */
public class Author {
    private String name;

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}