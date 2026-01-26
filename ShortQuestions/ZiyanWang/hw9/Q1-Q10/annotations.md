## 1. Core Spring Annotations

- `@Component` – Marks a class as a Spring-managed component
- `@Service` – Marks a service-layer component
- `@Repository` – Marks a data access layer component
- `@Controller` – Marks a Spring MVC controller
- `@RestController` – Controller that returns REST responses
- `@Configuration` – Defines Spring configuration class
- `@Bean` – Defines a Spring bean method

---

## 2. Dependency Injection

- `@Autowired` – Automatically injects dependencies
- `@Qualifier` – Specifies which bean to inject
- `@Primary` – Marks a default bean
- `@Value` – Injects values from properties

---

## 3. Spring Boot

- `@SpringBootApplication` – Main entry for Spring Boot application
- `@EnableAutoConfiguration` – Enables auto-configuration
- `@ComponentScan` – Scans Spring components

---

## 4. Spring MVC / REST

- `@RequestMapping` – Maps HTTP requests
- `@GetMapping` – Handles GET requests
- `@PostMapping` – Handles POST requests
- `@PutMapping` – Handles PUT requests
- `@DeleteMapping` – Handles DELETE requests
- `@PatchMapping` – Handles PATCH requests

- `@PathVariable` – Binds URI path variable
- `@RequestParam` – Binds request parameter
- `@RequestBody` – Maps request body to object
- `@ResponseBody` – Returns response body
- `@ResponseStatus` – Sets HTTP response status

---

## 5. Exception Handling

- `@ExceptionHandler` – Handles controller exceptions
- `@ControllerAdvice` – Global exception handler
- `@RestControllerAdvice` – REST global exception handler

---

## 6. JPA / Hibernate (ORM)

### Entity Mapping
- `@Entity` – Marks a JPA entity
- `@Table` – Maps entity to table
- `@Id` – Primary key
- `@GeneratedValue` – Auto-generates ID
- `@Column` – Maps column

### Relationships / Foreign Keys
- `@OneToOne` – One-to-one relationship
- `@OneToMany` – One-to-many relationship
- `@ManyToOne` – Many-to-one relationship
- `@ManyToMany` – Many-to-many relationship
- `@JoinColumn` – Defines foreign key column
- `@JoinColumns` – Composite foreign key
- `@JoinTable` – Join table for many-to-many
- `@ForeignKey` – Custom foreign key constraint

### Transactions
- `@Transactional` – Manages database transactions

---

## 7. Validation

- `@NotNull` – Must not be null
- `@NotEmpty` – Must not be empty
- `@NotBlank` – Must not be blank
- `@Size` – Size constraint
- `@Min` – Minimum value
- `@Max` – Maximum value
- `@Email` – Valid email
- `@Pattern` – Regex validation
- `@Valid` – Triggers validation

---

## 8. Security

- `@EnableWebSecurity` – Enables Spring Security
- `@EnableMethodSecurity` – Enables method-level security
- `@PreAuthorize` – Authorization before method execution
- `@PostAuthorize` – Authorization after method execution

---

## 9. Testing

- `@Test` – Marks a test method
- `@BeforeEach` – Runs before each test
- `@AfterEach` – Runs after each test
- `@SpringBootTest` – Loads full Spring context
- `@WebMvcTest` – Tests controller layer
- `@MockBean` – Mocks Spring bean
- `@DataJpaTest` – Tests JPA repositories

---

## 10. Java / Misc

- `@Override` – Overrides superclass method
- `@Deprecated` – Marks deprecated code
- `@SuppressWarnings` – Suppresses compiler warnings
