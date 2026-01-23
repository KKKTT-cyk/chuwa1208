@Autowired Injects required dependencies into a Spring-managed component (constructor injection is preferred).

@Entity Indicates that the class is a JPA entity mapped to a database table.

@Service Marks a service-layer component that contains business logic.

@Id Defines the primary key of the entity.

@GeneratedValue Specifies how the primary key value is automatically generated.

@Column Customizes the mapping between a class field and a database column (name, nullable, length, etc.).

@Repository Marks the data access layer and enables persistence-related exception translation.

@PathVariable Binds a URI template variable to a method parameter.

@RequestBody Maps the HTTP request body to a Java object (DTO or entity).

@RestController Marks the class as a RESTful web controller where methods return data objects (e.g. JSON) instead of views.

@RequestMapping("/path") Defines the base URL path (and optional HTTP methods) for the controller or handler methods.
