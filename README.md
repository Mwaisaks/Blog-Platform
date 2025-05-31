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