## @Entity
@Entity is used on a class to indicate that the class is a JPA entity. It represents a table in the database, and its fields are mapped to columns in that table.

## @Controller
@Controller is used on a class to mark it as a Spring MVC controller. It handles HTTP requests and typically returns a view such as an HTML page.

## @RestController
@RestController is a combination of @Controller and @ResponseBody. It is used to create RESTful web services where methods return JSON or XML data directly in the HTTP response body.

## @Repository
@Repository is used on a class to indicate that it is a data access component. It handles database operations and enables automatic exception translation by Spring.

## @Configuration
@Configuration is used on a class to indicate that it contains Spring configuration. It is commonly used to define beans using @Bean methods.

## @Id
@Id is used on a field in an entity class to mark it as the primary key of the database table.

## @Column
@Column is used on a field in an entity class to map it to a specific column in the database table and to define column-related properties.

## @RequestBody
@RequestBody is used on a method parameter in a controller to bind the HTTP request body to a Java object, commonly used with POST or PUT requests.

## @RequestMapping
@RequestMapping is used to map HTTP requests to handler methods or controller classes. It can specify the URL path and HTTP method.

## @GetMapping
@GetMapping is a specialized version of @RequestMapping used to handle HTTP GET requests, typically for retrieving data.

## @PostMapping
@PostMapping is a specialized version of @RequestMapping used to handle HTTP POST requests, typically for creating or submitting data.

## @Autowired
@Autowired is used to automatically inject dependencies from the Spring container into a class, field, constructor, or method.

## @Service
@Service is used on a class to indicate that it contains business logic and acts as a service layer component in the application.
