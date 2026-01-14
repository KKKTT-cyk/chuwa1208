**Explain Join in RDBMS/SQL with sample queries.**

A JOIN is used to combine rows from two or more tables based on a related column between them.
It helps retrieve data from multiple tables in a single query.

Example:

Employees

| EmpID | Name  | DeptID |
| ----- | ----- | ------ |
| 1     | Alice | 101    |
| 2     | Bob   | 102    |
| 3     | Carol | 103    |


Departments

| DeptID | DeptName |
| ------ | -------- |
| 101    | HR       |
| 102    | IT       |
| 104    | Finance  |


1. INNER JOIN

Returns rows that have matching values in both tables.

Rows without matches are not included.

```
SELECT e.Name, d.DeptName
FROM Employees e
INNER JOIN Departments d
ON e.DeptID = d.DeptID;

```

2. LEFT JOIN (or LEFT OUTER JOIN)

Returns all rows from the left table, and matching rows from the right table.

If no match exists, right table columns are NULL.

```
SELECT e.Name, d.DeptName
FROM Employees e
LEFT JOIN Departments d
ON e.DeptID = d.DeptID;
```

3. RIGHT JOIN (or RIGHT OUTER JOIN)

Returns all rows from the right table, and matching rows from the left table.

If no match exists, left table columns are NULL.

```
SELECT e.Name, d.DeptName
FROM Employees e
RIGHT JOIN Departments d
ON e.DeptID = d.DeptID;
```

4. FULL OUTER JOIN

Returns all rows from both tables.

Rows without match in either table will have NULL for missing columns.

```
SELECT e.Name, d.DeptName
FROM Employees e
FULL OUTER JOIN Departments d
ON e.DeptID = d.DeptID;

```

5. CROSS JOIN
Returns Cartesian product of two tables (every row from left table combined with every row from right table).

```
SELECT e.Name, d.DeptName
FROM Employees e
CROSS JOIN Departments d;

```

6. SELF JOIN

Joining a table with itself using an alias. Useful for hierarchical data.

```
SELECT e1.Name AS Employee, e2.Name AS Manager
FROM Employees e1
LEFT JOIN Employees e2
ON e1.ManagerID = e2.EmpID;

```