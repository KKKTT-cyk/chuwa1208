# Short Questsions

## What is JPA? What is Hibernate?

- JPA (Java Persistence API): a specification (interfaces + rules) for ORM in Java. It defines annotations (@Entity, @Id, etc.) and APIs (like EntityManager) but does not implement them.
- Hibernate: the most common implementation of JPA. It’s the actual ORM engine that does mapping, SQL generation, caching, dirty checking, lazy loading, etc.


## What is Hikari? Benefits of connection pool?

- Hikari: the default JDBC connection pool in Spring Boot
- Benefits:
  - reuses connection, avoid the cost of frequently creating DB connection
  - higher throughput, lower latency
  - controls concurrency, avoid DB overload
  - improves stability

## `@OneToMany`, `@ManyToOne`, `@ManyToMany` 

- One-to-Many: e.g. One `Post` -> Many `Comment`
```java
@OneToMany(mappedBy="post")
private List<Comment> comments;
```
- Many-to-One: e.g. Many `Comment` -> One `Post`
```java
@ManyToOne
@JoinColumn(name="post_id")
private Post post;
```
- Many-to-Many: e.g. Many `Post` <-> Many `Tag`
```java
@ManyToMany
@JoinTable(
  name="post_tags",
  joinColumns=@JoinColumn(name="post_id"),
  inverseJoinColumns=@JoinColumn(name="tag_id")
)
private Set<Tag> tags;
```

## Cascade

- `CascadeType.ALL` = `{PERSIST, MERGE, REMOVE, REFRESH, DETACH}`
  - PERSIST: saving parent also saves new children
  - MERGE: merging parent merges children changes
  - REMOVE: deleting parent deletes children
  - REFRESH: refreshing parent also refreshes children from DB
  - DETACH: detaching parent detaches children
- `orphanRemoval = true`: if a child is removed from the parent collection and not referenced elsewhere, Hibernate will delete that child row from DB


## FetchType

- `FetchType.LAZY`: does not load related data until you access it.
  - Pros: faster initial queries, avoid huge joins
  - Cons: can cause `LazyInitializationException` if accessed outside transaction/seesion
- `FetchType.EAGER`: loads relationship immediately
  - Pros: simpler for small fixed-size relations
  - Cons: performance risk, big payloads
- Default to **LAZY** for collections; Use **EAGER** only when always need it and it's small

## JPA naming convention

- Examples:
  - `findByTitle(String title)`
  - `findByTitleContainingIgnoreCase(String keyword)`
  - `existesByIdAndUserId(Long postId, Long userId)`
  - `countByPostId(Long postId)`
- Only implement manually when
  - method name becomes too complex
  - need joins / projections / custom conditions
  - need  performance tuning


## JPQL

JPQL (Java Persistence Query Language) is SQL-like but queries entities and fields, not tables/columns


## `@NamedQuery` and `@NamedQueries`

- Predefined queries attached to an Entity class
- Useful to centralize query definitions and reuse them
```java
@Entity
@NamedQueries({
  @NamedQuery(
    name="Post.findRecent",
    query="select p from Post p order by p.createdAt desc"
  )
})
public class Post { ... }
```

## What is `@Query`? Which interface to write SQL/JPQL

- `@Query` is written in Repository interface
- by default, it's JPQL
- `@Query(value=""SELECT ...", nativeQuery = true)`

## HQL and Criteria Queries

- HQL: Hibernate Query Language, similar to JPQL, Hibernate specific
- Criteria API: programmatic query building (type-safe-ish), good when filters are dynamic:
  - build predicates conditionally 
  - avoid string-concatenation JPQL


## EntityManager

JPA interface that manges:
- persistence context
- CRUD
- JPQL queries
- transactions


## SessionFactory and Session

Hibernate native APIs:

- SessionFactory: heavyweight, created once, used to open sessions 
- Session: similar to EntityManager (in fact Hibernate can adapt one to another). It represents a unit of work + persistence context.

In Spring Boot with JPA, you typically use EntityManager (not Session directly).


## Transaction and how to manage it
A transaction is a unit of work that is atomic (all succeed or rollback)

- Put `@Transactional` on service methods
- If method completes normally -> commit
- If runtime exception occurs -> rollback


## Hibernate Caching Mechanism (Detailed)

Hibernate provides multiple levels of caching to improve performance and reduce unnecessary database access.

### First-Level Cache (L1)
- **Scope**: Per `Session` / `EntityManager` (persistence context)
- **Status**: Always enabled (cannot be turned off)
- **Behavior**:
    - If the same entity is loaded multiple times within the same transaction/session, Hibernate returns it from the L1 cache.
    - No additional SQL query is executed for repeated access.
- **Purpose**:
    - Ensures entity identity (`==`) within a session
    - Supports dirty checking and transactional consistency

### Second-Level Cache (L2)
- **Scope**: Application-wide (shared across sessions)
- **Status**: Optional (must be explicitly enabled and configured)
- **Requirements**:
    - A cache provider (e.g., Ehcache, Infinispan, Hazelcast)
    - Entity-level cache annotations/configuration
- **Behavior**:
    - Stores entity state across transactions
    - Can significantly reduce database hits for frequently accessed data
- **Use cases**:
    - Read-mostly data
    - Reference or lookup tables

### Query Cache (Optional)
- **Purpose**:
    - Caches query result **IDs**, not full entity data
- **Dependency**:
    - Typically used together with Second-Level Cache
- **Behavior**:
    - Query is cached → result IDs are reused
    - Entities themselves are retrieved from L2 cache
- **Caution**:
    - Sensitive to data changes
    - Must be carefully invalidated to avoid stale results

---

## First-Level Cache vs Second-Level Cache

### First-Level Cache (L1)
- **Scope**: Single `Session` / transaction
- **Enabled**: Always enabled
- **Safety**: Safest and most consistent
- **Lifecycle**: Exists only during the session
- **Use case**:
    - Transactional consistency
    - Prevents duplicate SQL within the same transaction

### Second-Level Cache (L2)
- **Scope**: Application-wide
- **Enabled**: Must be configured explicitly
- **Cache Provider**: Required (Ehcache, Infinispan, etc.)
- **Use case**:
    - Read-mostly data
    - Reference tables
- **Concerns**:
    - Cache consistency
    - Invalidation strategy
    - Stale data risks if misconfigured

---

## How to Understand `@Transactional`

`@Transactional` defines a **transaction boundary** around a method.

### What `@Transactional` Ensures
- **Atomicity**:
    - All operations succeed → commit
    - Any failure → rollback
- **Consistency**:
    - Reads/writes follow the configured isolation level
- **Session Management**:
    - Hibernate `Session` remains open within the transaction
    - Enables LAZY loading without `LazyInitializationException`

### Default Behavior
- Rolls back on **unchecked (`RuntimeException`)**
- Does **not** roll back on checked exceptions by default (configurable)

### Common Pitfalls
- **Self-invocation problem**:
    - Calling `this.someTransactionalMethod()` within the same class
    - Bypasses Spring proxy → transaction NOT applied
- **Wrong layer usage**:
    - Avoid placing `@Transactional` on Controllers
    - Best practice: put it on **Service layer**
- **Overusing transactions**:
    - Large transactions can hurt performance and lock resources

### Best Practices
- Keep transactions **short and focused**
- Place `@Transactional` at the **service boundary**
- Clearly define rollback rules when needed
