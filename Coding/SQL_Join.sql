/* ============================================================
   Demonstration of JOIN in RDBMS / SQL
   Database: PostgreSQL
   ============================================================ */

/* ------------------------------------------------------------
   Step 1: Clean up
   ------------------------------------------------------------ */
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS department;

/* ------------------------------------------------------------
   Step 2: Create tables
   department: parent table
   student: child table
   ------------------------------------------------------------ */
CREATE TABLE department (
                            dept_id INT PRIMARY KEY,
                            dept_name VARCHAR(50) NOT NULL
);

CREATE TABLE student (
                         student_id INT PRIMARY KEY,
                         student_name VARCHAR(50) NOT NULL,
                         dept_id INT
);

/* ------------------------------------------------------------
   Step 3: Insert sample data
   Note: student Charlie has no department (dept_id is NULL)
   ------------------------------------------------------------ */
INSERT INTO department VALUES
                           (101, 'Computer Science'),
                           (102, 'Electrical Engineering'),
                           (103, 'Mathematics');

INSERT INTO student VALUES
                        (1, 'Alice', 101),
                        (2, 'Bob', 102),
                        (3, 'Charlie', NULL);

/* ============================================================
   JOIN Examples
   ============================================================ */

/* ------------------------------------------------------------
   INNER JOIN
   Returns only rows with matching values in both tables
   Students without a department are excluded.
   ------------------------------------------------------------ */
SELECT s.student_name, d.dept_name
FROM student s
         INNER JOIN department d
                    ON s.dept_id = d.dept_id;

/* ------------------------------------------------------------
   LEFT JOIN (LEFT OUTER JOIN)
   Returns all rows from the left table (student).
   If no matching department exists, dept_name is NULL.
   ------------------------------------------------------------ */
SELECT s.student_name, d.dept_name
FROM student s
         LEFT JOIN department d
                   ON s.dept_id = d.dept_id;

/* ------------------------------------------------------------
   RIGHT JOIN (RIGHT OUTER JOIN)
   Returns all rows from the right table (department).
   Departments without students are included.
   ------------------------------------------------------------ */
SELECT s.student_name, d.dept_name
FROM student s
         RIGHT JOIN department d
                    ON s.dept_id = d.dept_id;

/* ------------------------------------------------------------
   FULL OUTER JOIN
   Returns all rows from both tables.
   Unmatched rows on either side are filled with NULL.
   ------------------------------------------------------------ */
SELECT s.student_name, d.dept_name
FROM student s
         FULL OUTER JOIN department d
                         ON s.dept_id = d.dept_id;

/* ------------------------------------------------------------
   CROSS JOIN
   Returns the Cartesian product of both tables.
   Number of rows = students × departments.
   Usually not used in real applications.
   ------------------------------------------------------------ */
SELECT s.student_name, d.dept_name
FROM student s
         CROSS JOIN department d;

/* ============================================================
   Summary:
   - JOIN is used to combine rows from multiple tables
     based on a related column.
   - INNER JOIN: only matching rows
   - LEFT JOIN: all rows from left table
   - RIGHT JOIN: all rows from right table
   - FULL JOIN: all rows from both tables
   - CROSS JOIN: Cartesian product
   ============================================================ */
