## Annotations Learned from Spring Data (Class & Homework)

### Core Spring / Spring Boot
- `@SpringBootApplication` – Entry point of Spring Boot application
- `@Component` – Marks a Spring-managed component
- `@Service` – Service-layer component (business logic)
- `@Repository` – DAO layer, enables exception translation
- `@Autowired` – Dependency Injection

### Web / REST
- `@RestController` – RESTful controller
- `@RequestMapping` – Base URL mapping
- `@GetMapping` – Handle HTTP GET requests
- `@PostMapping` – Handle HTTP POST requests
- `@PutMapping` – Handle HTTP PUT requests
- `@DeleteMapping` – Handle HTTP DELETE requests
- `@PathVariable` – Bind URI path variable
- `@RequestParam` – Bind query parameter
- `@RequestBody` – Bind request body to object

### JPA / Hibernate
- `@Entity` – Marks a JPA entity
- `@Table` – Specifies database table name
- `@Id` – Primary key
- `@GeneratedValue` – Auto-generate primary key
- `@Column` – Column mapping
- `@OneToMany` – One-to-many relationship
- `@ManyToOne` – Many-to-one relationship
- `@ManyToMany` – Many-to-many relationship
- `@JoinColumn` – Foreign key column
- `@JoinTable` – Join table for many-to-many
- `@MappedSuperclass` – Base entity class
- `@Enumerated` – Map enum to column
- `@Transactional` – Transaction management
- `@Cacheable` – Enable second-level cache
- `@NamedQuery` – Static JPQL query
- `@NamedQueries` – Multiple named queries
- `@Query` – Custom JPQL or SQL query
- `@Modifying` – Update/Delete query
- `@Temporal` – Date/time mapping
- `@CreationTimestamp` – Auto set create time
- `@UpdateTimestamp` – Auto set update time

### Repository / Spring Data JPA
- `JpaRepository` – CRUD + paging + sorting
- `PagingAndSortingRepository` – Pagination & sorting


