**Explain Referential Integrity in RDBMS/SQL with sample queries.**
Referential Integrity is a set of rules that ensures the relationships between tables remain consistent. It prevents "orphaned records"—data that points to something that no longer exists.

It is primarily enforced through Foreign Keys. Referential integrity dictates that for any foreign key value in one table, there must be a corresponding primary key value in the referenced table.

The Three Rules of Enforcement

- No Ghost Entries: You cannot add a record to the child table with a foreign key that doesn't exist in the parent table.

- No Disappearing Parents: You cannot delete a record from the parent table if a child record is still pointing to it.

- No ID Changes: You cannot change a primary key value in the parent table if it has dependent child records.




SQL Implementation Example
Imagine we have a Department table and an Employee table. Every employee must belong to a valid department.

1. Creating the Parent Table

```
CREATE TABLE Departments (
    DeptID INT PRIMARY KEY,
    DeptName VARCHAR(50) NOT NULL
);
```
2. Creating the Child Table with a Foreign Key

```
CREATE TABLE Employees (
    EmpID INT PRIMARY KEY,
    EmpName VARCHAR(50),
    DeptID INT,
    -- This constraint enforces Referential Integrity
    FOREIGN KEY (DeptID) REFERENCES Departments(DeptID)
);
```

3. Testing the Integrity

The Success Case: Adding a department first, then an employee.


```
INSERT INTO Departments (DeptID, DeptName) VALUES (1, 'Engineering');
INSERT INTO Employees (EmpID, EmpName, DeptID) VALUES (101, 'Alice', 1); 
-- Success: DeptID 1 exists.
The Failure Case (Violation): Trying to add an employee to a department that doesn't exist.
```


```
INSERT INTO Employees (EmpID, EmpName, DeptID) VALUES (102, 'Bob', 99);
```

-- Error: Foreign key constraint fails because DeptID 99 does not exist.


**Handling Deletions: Referential Actions

When you try to delete a row in the parent table that has "children," you can define how the database should react using these common options:

| Action               | Result                                                       |
|----------------------|--------------------------------------------------------------|
| ON DELETE RESTRICT    | (Default) Prevents the deletion of the parent row.          |
| ON DELETE CASCADE     | Deletes the parent row **and** all associated child rows automatically. |
| ON DELETE SET NULL    | Deletes the parent row and sets the child’s foreign key to NULL. |
