# Annotations learned 

## Spring Boot / Spring Core
- @SpringBootApplication
- @ComponentScan
- @Configuration
- @Bean
- @Component
- @Service
- @Repository
- @Autowired 
- @Qualifier
- @Value
- @Primary

## Spring Web (MVC / REST)
- @RestController
- @Controller
- @RequestMapping
- @GetMapping
- @PostMapping
- @PutMapping
- @DeleteMapping
- @PatchMapping
- @RequestParam
- @PathVariable
- @RequestBody
- @ResponseStatus
- @ExceptionHandler
- @ControllerAdvice / @RestControllerAdvice

## Validation (often used with DTOs)
- @Valid
- @NotNull
- @NotBlank
- @NotEmpty
- @Size
- @Min / @Max
- @Email
- @Pattern
- @Past / @Future

## JPA / Hibernate (ORM)
- @Entity
- @Table
- @Id
- @GeneratedValue
- @Column
- @Transient
- @Enumerated
- @Lob
- @CreationTimestamp (Hibernate)
- @UpdateTimestamp (Hibernate)

### Relationships
- @OneToMany
- @ManyToOne
- @ManyToMany
- @OneToOne
- @JoinColumn
- @JoinTable
- @MappedBy

### Fetch / Cascade
- fetch = FetchType.LAZY
- fetch = FetchType.EAGER
- cascade = CascadeType.ALL
- CascadeType.PERSIST
- CascadeType.MERGE
- CascadeType.REMOVE
- CascadeType.REFRESH
- CascadeType.DETACH
- orphanRemoval = true

## Transaction
- @Transactional

## JPQL / Query
- @Query
- @NamedQuery
- @NamedQueries
- @Param (for @Query parameters)
