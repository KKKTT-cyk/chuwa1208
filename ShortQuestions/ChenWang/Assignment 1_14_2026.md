# Assignment 1/14/2026

## Short Questions
### 1. Did you create the table POSTS in the database? if not, who did it for you? Can I change this behavior? (Hint: look at the application.properties file)  

I didn't create the table POSTS, it's spring projects who create that for me. 
It's line 11 "spring.jpa.hibernate.ddl-auto=update" in application.properties
Yes I can change it by providing different value in this line.

### 2. Is your id in the database same as what you set in your request? why does this happen? (Hint: search the annotations used in your code)

No, the id was set automatically by the server, and I cannot set id manually.
The reason is that  in Post.java class, in the model of Post, the member id has the annotation "@GeneratedValue(strategy = GenerationType.IDENTITY)"
