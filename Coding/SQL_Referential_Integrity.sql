
CREATE TABLE department (
                            dept_id INT PRIMARY KEY,
                            dept_name VARCHAR(50) NOT NULL
);

/* ------------------------------------------------------------
   Create Child Table
   The student table is the CHILD table.
   dept_id is a FOREIGN KEY referencing department(dept_id).
   This enforces referential integrity.
   ------------------------------------------------------------ */
CREATE TABLE student (
                         student_id INT PRIMARY KEY,
                         student_name VARCHAR(50) NOT NULL,
                         dept_id INT,
                         FOREIGN KEY (dept_id) REFERENCES department(dept_id)
);

/* ------------------------------------------------------------
   Insert data into parent table (VALID)
   Parent records must exist before child records.
   ------------------------------------------------------------ */
INSERT INTO department VALUES (101, 'Computer Science');
INSERT INTO department VALUES (102, 'Electrical Engineering');

/* ------------------------------------------------------------
   Insert valid data into child table
   dept_id exists in department table → succeeds
   ------------------------------------------------------------ */
INSERT INTO student VALUES (1, 'Alice', 101);
INSERT INTO student VALUES (2, 'Bob', 102);

/* ------------------------------------------------------------
   Insert invalid data into child table (WILL FAIL)
   dept_id = 999 does NOT exist in department table
   This violates referential integrity.
   Uncomment to test the error.
   ------------------------------------------------------------ */
-- INSERT INTO student VALUES (3, 'Charlie', 999);

/* ------------------------------------------------------------
   Demonstrate referential integrity on DELETE
   The following DELETE will FAIL because student records
   still reference department 101.
   ------------------------------------------------------------ */
-- DELETE FROM department WHERE dept_id = 101;

/* ------------------------------------------------------------
   Create tables with ON DELETE CASCADE
   This allows deleting a parent row and automatically
   deleting related child rows.
   ------------------------------------------------------------ */
DROP TABLE IF EXISTS student;
DROP TABLE IF EXISTS department;

CREATE TABLE department (
                            dept_id INT PRIMARY KEY,
                            dept_name VARCHAR(50) NOT NULL
);

CREATE TABLE student (
                         student_id INT PRIMARY KEY,
                         student_name VARCHAR(50) NOT NULL,
                         dept_id INT,
                         FOREIGN KEY (dept_id)
                             REFERENCES department(dept_id)
                             ON DELETE CASCADE
);

/* ------------------------------------------------------------
   Reinsert data
   ------------------------------------------------------------ */
INSERT INTO department VALUES (101, 'Computer Science');
INSERT INTO student VALUES (1, 'Alice', 101);
INSERT INTO student VALUES (2, 'Bob', 101);

/* ------------------------------------------------------------
   Delete parent record
   This will automatically delete related student records
   due to ON DELETE CASCADE.
   ------------------------------------------------------------ */
DELETE FROM department WHERE dept_id = 101;

/* ------------------------------------------------------------
   Verify results
   Both department and related student records are removed.
   ------------------------------------------------------------ */
SELECT * FROM department;
SELECT * FROM student;

/* ============================================================
   Summary:
   - Referential Integrity ensures foreign key values always
     reference valid parent table rows.
   - Enforced using FOREIGN KEY constraints.
   - Prevents orphan records and maintains data consistency.
   ============================================================ */
