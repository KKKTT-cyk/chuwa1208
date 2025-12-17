Why does the Book object's lifetime depend on the Library? How would you modify this to use Aggregation instead?
- In this design, the Library uses composition because it owns and manages its collection of Book objects.
  The lifetime of the Book objects depends on the Library, as they are created and stored as part of the library's internal collection.

- To use aggregation instead, the Book objects would be created outside the Library and passed in,
allowing them to exist independently of the Library's lifecycle.
