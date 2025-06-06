**UUID vs Long ID with Identity Generation**
1. **UUID Approach**
`@Id
@GeneratedValue(strategy = GenerationType.UUID)
private UUID id;`

**Pros**
* Universally unique across all systems/databases
* Can be generated before inserting into database (good for distributed systems)
* No need for database sequence
* Harder to guess (security benefit)
* Works well with microservices architectures

**Cons**
* Larger storage size(16 bytes vs 8 bytes for Long)
* Slightly slower indexing/querying
* Not human-readable

2. **Long with IDENTITY Approach**
   `@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;`

**Pros**
* Smaller storage footprint
* Faster indexing
* Simple sequential numbers are easier to read/debug
* Traditional approach that works well with single databases

**Cons**
* Only unique within a single database
* You don't know the ID until after insert
* Can expose business information (number of records)

**When to use which:**
* Use UUID when you need distributed ID generation or are working with multiple databases
* Use Long IDENTITY for simple, single-database applications where performance is critical
* Use UUID when you want to obscure record counts or IDs from users

**Handling Time Variables**

**Understanding RelationShips in Spring Boot with JPA**
**RelationShip Types with JPA**
1. **One-To-Many RelationShip**
@OneToMany is on the "one side", @ManyToOne is on the many side
mappedBy indicates the field in Post entity that owns the relationship 
The foreign key (author_id) is stored in the Post table

2. **Many-To-Many Relationships**
- multiple instances of one entity can be associated by multiple instances of another entity.

`// In Post entity
@ManyToMany
@JoinTable(
name = "post_tags",
joinColumns = @JoinColumn(name = "post_id"),
inverseJoinColumns = @JoinColumn(name = "tag_id"))
private Set<Tag> tags = new HashSet<>();

// In Tag entity
@ManyToMany(mappedBy = "tags")
private Set<Post> posts = new HashSet<>();`

Requires a join table (post_tags) to store the relationships
One side must be the owner (Post in this case) with @JoinTable
The other side uses mappedBy
Using Set instead of List prevents duplicates

**Cascades**
Cascades define how changes in one entity affect the other

**Why author_id instead of user_id in Post entity**
This is primarily a semantic/naming convention choice. Here's why author_id is better:

- Domain Language Alignment: In a blog context, a User becomes an "Author" when they create posts. This better reflects the domain terminology.

- Clarity of Relationship: It's immediately clear that this ID refers to the author of the post, not just any user.

- Future Flexibility: If you later need to add other user relationships (like editors, reviewers), having specific names helps distinguish them.

- Database Readability: When examining the database schema, author_id is more descriptive than user_id in the posts table.

**About @RequiredArgsConstructor vs @Autowired**
   `@RequiredArgsConstructor`:
- Part of Lombok

- Generates a constructor with all final fields or fields marked with @NonNull

- More concise and reduces boilerplate

- Makes dependencies clearly immutable (when using final)

Example from the project:

`
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
private final CategoryService categoryService;
// Lombok generates constructor:
// public CategoryController(CategoryService categoryService) {...}
}`

`@Autowired`:
* Spring's native annotation

* Can be used on fields, setters, or constructors

* Field injection is generally discouraged (makes testing harder, hides dependencies)

* Constructor injection is preferred (explicit dependencies, immutable)

**Why prefer @RequiredArgsConstructor:**

1. Immutability: Forces you to use final fields 
2. Cleaner Code: Less boilerplate 
3. Testability: Easier to mock dependencies in tests 
4. Best Practice: Aligns with Spring's recommendation of constructor injection

**Interface + Implementation Pattern**
The separation into CategoryService interface and CategoryServiceImpl class is a best practice because:

* Abstraction: Interface defines the contract, implementation handles details

* Flexibility: Easy to swap implementations (e.g., for testing, different environments)

* Testability: Can mock the interface in tests

* Clean Architecture: Follows Dependency Inversion Principle

* Multiple Implementations: You might have different implementations (e.g., for caching)

**Your approach (single class) works but has limitations:**

* Harder to mock for testing

* Less flexible for future changes

* Violates "program to interfaces" principle

**About the N+1 Problem and LEFT JOIN FETCH**
**The N+1 Problem:**
When you fetch a list of categories (1 query), then for each category, Hibernate makes another query to fetch its posts (N queries). This is inefficient.

Solution with **LEFT JOIN FETCH**:
`
@Query("SELECT c FROM Category c LEFT JOIN FETCH c.posts")
List<Category> findAllWithPostCount();`

This:
* Fetches categories and their posts in a single query

* Uses Hibernate's fetch joining capability

* Avoids the performance penalty of N+1 queries

**How to Apply This Knowledge:**
* Always Be Aware of N+1: When you see entity relationships, consider the query implications.

* Use Fetch Joins: For operations where you know you'll need related entities.

* Profile Your Queries: Use spring.jpa.show-sql=true during development to see what queries are executed.

* Consider Lazy/Eager Loading:
- Lazy (default): Load relationships only when accessed
- Eager: Load relationships immediately
- Fetch joins override these settings for that specific query
* 
* DTO Projection: Another approach is to use DTO projections with JPQL to fetch only needed data.

**Calculating Post Count:**
The project uses a clever approach in the mapper:
`
@Named("calculatePostCount")
default long calculatePostCount(Set<Post> posts) {
if (posts == null) return 0;
return posts.stream()
.filter(post -> PostStatus.PUBLISHED.equals(post.getStatus()))
.count();
}`

This:
* Filters only published posts
* 
* Counts them
* 
* Is applied during the DTO mapping process

**Key Takeaways for Your Development:**
* Always consider the performance implications of relationships

* Use JPQL/HQL fetch joins when you need related entities

* Profile your queries during development

* Consider DTO projections for read operations

* Complex calculations can often be handled in mappers

Here's what you need to know about Dispatcher Servlet in Spring
It's the heart of Spring MVC framework, lemme explain to you why.

The title should be UserDetails and UserDetailsService because the two work together. Lemme show you how;

UserDetails Service is an interface for loading User Specific data from the database or an external service.
It has one method loadUserByUsername() which takes in the username and returns a fully-populated UserDetails object
The UserDetails object represents an authenticated user and contains details likeusername, password, authorities and additional attributes

InMemoryUserDetailsManager class- an implementation of UserDetailsService, that stores user details in memory,
only used in testing/development.
User class - 

Creating Authentication Endpoints
create the dtos; LoginRequest, LoginResponse make use of lombok

**UserDetails Vs UserDetailsService**

**Topics to revisit**
1. [x] Mapping the diff ways
2. [x] Stream API
3. [x] Error handling in SpringBoot
4. [x] Optional 
5. constants in place of enums
6. csrf and cors
7. Session management

Creating Auth Endpoints
1. Create response and request dtos.
2. Create endpoints in the controller 
3. a service interface containing the methods 
4. an implementation of the interface; implements the Authentication 

**Creating other Endpoints**
* Create the dtos, and get the variables included, integrate lombok where needed
* 