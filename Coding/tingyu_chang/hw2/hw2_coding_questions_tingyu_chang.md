### P1\.Encapsulation Practice
* This following code demonstrates Encapsulation by keeping fields ```private``` and providing ```public``` methods to access and modify them safely.

```
public class BankAccount {
    // 1. Private fields for Encapsulation
    private String accountNumber;
    private double balance;

    // 2. Parameterized Constructor
    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        // Validate initial balance
        if (initialBalance >= 0) {
            this.balance = initialBalance;
        } else {
            System.out.println("Error: Initial balance cannot be negative. Setting to 0.");
            this.balance = 0;
        }
    }

    // 3. Getter and Setter methods with Validation
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        // Validation: balance cannot be negative
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Error: Balance cannot be negative.");
        }
    }

    // 4. Methods for Deposit and Withdraw
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited: $" + amount);
        } else {
            System.out.println("Error: Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrew: $" + amount);
        } else {
            System.out.println("Error: Invalid withdrawal amount or insufficient funds.");
        }
    }
}

```
---

### P2\.Inheritance 
* This following code demonstrates Inheritance using the ```extends``` keyword and Polymorphism (Runtime) by overriding the ```displayInfo()``` method.

```
// 1. Base Class
class Vehicle {
    protected String brand; // 'protected' allows subclasses to access this field directly
    protected double speed;

    public Vehicle(String brand, double speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public void displayInfo() {
        System.out.println("Brand: " + brand);
        System.out.println("Speed: " + speed + " mph");
    }
}

// 2. Derived Class: Car
class Car extends Vehicle {
    private int numDoors;

    public Car(String brand, double speed, int numDoors) {
        super(brand, speed); // Call the constructor of the parent (Vehicle)
        this.numDoors = numDoors;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Reuse the logic from the parent class
        System.out.println("Number of Doors: " + numDoors);
    }
}

// 3. Derived Class: Motorcycle
class Motorcycle extends Vehicle {
    private boolean hasSidecar;

    public Motorcycle(String brand, double speed, boolean hasSidecar) {
        super(brand, speed); // Call the constructor of the parent (Vehicle)
        this.hasSidecar = hasSidecar;
    }

    @Override
    public void displayInfo() {
        super.displayInfo(); // Reuse the logic from the parent class
        System.out.println("Has Sidecar: " + (hasSidecar ? "Yes" : "No"));
    }
}

// 4. Main Class to Test P2
public class Main {
    public static void main(String[] args) {
        // Polymorphism: Reference is Vehicle, Object is Car
        Vehicle myCar = new Car("Toyota", 120, 4);
        myCar.displayInfo();

        System.out.println("----------------");

        // Polymorphism: Reference is Vehicle, Object is Motorcycle
        Vehicle myMoto = new Motorcycle("Harley", 100, false);
        myMoto.displayInfo();
    }
}

```
---

### P3\.Method Overloading
* This following code demonstrates Method Overloading (Static Polymorphism) by creating the ```Calculator``` class with three variations of the ```add()``` method.
```
class Calculator {
  // 1. Overload: Two integers
  public int add(int a, int b) {
  return a + b;
  }

  // 2. Overload: Three integers
  public int add(int a, int b, int c) {
  return a + b + c;
  }

  // 3. Overload: Two doubles
  public double add(double a, double b) {
  return a + b;
  }
  }

public class MainP3 {
public static void main(String[] args) {
Calculator calc = new Calculator();

        // Demonstrate calling each method
        System.out.println("Sum of 2 ints (10 + 5): " + calc.add(10, 5));
        System.out.println("Sum of 3 ints (10 + 5 + 20): " + calc.add(10, 5, 20));
        System.out.println("Sum of 2 doubles (2.5 + 3.5): " + calc.add(2.5, 3.5));
    }
}
```
---

### P4\.Method Overriding and Polymorphism
* This code demonstrates Method Overriding (Dynamic Polymorphism). It defines an ```abstract``` base class ```Shape``` and implements the ```getArea()``` logic specifically for ```Rectangle``` and ```Circle```.
```
// 1. Abstract Base Class
abstract class Shape {
    // Abstract method: Children must implement this
    public abstract double getArea();
}

// 2. Concrete Class: Rectangle
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double getArea() {
        return width * height;
    }
}

// 3. Concrete Class: Circle
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
}

// 4. Main Class
public class MainP4 {
    public static void main(String[] args) {
        // Create an array of Shape objects containing both Rectangles and Circles
        Shape[] shapes = new Shape[3];
        shapes[0] = new Rectangle(4, 5); // Area should be 20
        shapes[1] = new Circle(3);       // Area should be approx 28.27
        shapes[2] = new Rectangle(10, 2); // Area should be 20

        // Iterate and print area for each
        System.out.println("--- Calculating Areas ---");
        for (Shape s : shapes) {
            // Polymorphism: The JVM decides at runtime which getArea() to call
            System.out.printf("Area: %.2f\n", s.getArea());
        }
    }
}

```

---

### P5\.Constructor Chaining
* This code demonstrates Constructor Chaining using the ```this()``` keyword to call one constructor from another within the same class. This avoids code duplication by centralizing the initialization logic.
```
class Employee {

    private String name;
    private int id;
    private double salary;

    // 1. Default constructor (sets default values)
    public Employee() {
        // Calls the 3-parameter constructor with default values
        // Use this() to call one constructor from another
        this("Unknown", 0, 0.0);
        System.out.println("Default Constructor called");
    }
    // 2. Constructor with name and id parameters (sets salary to 0)
    public Employee(String name, int id) {
        // Calls the 3-parameter constructor
        // Use this() to call one constructor from another
        this(name, id, 0.0);
        System.out.println("2-Argument Constructor called");
    }

    // 3. Constructor with all three parameters (the main initial logic)
    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
        System.out.println("3-Argument Constructor called");
    }
    
    // Display method to verify values
    public void display() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: $" + salary);
    }
    
   // Demonstrate creating objects using all three constructors
    public class MainP5 {
        public static void main(String[] args) {
            System.out.println("--- Creating Employee 1 (Default) ---");
            Employee e1 = new Employee();
            e1.display();

            System.out.println("\n--- Creating Employee 2 (Name, ID) ---");
            Employee e2 = new Employee("Alice", 101);
            e2.display();

            System.out.println("\n--- Creating Employee 3 (All Details) ---");
            Employee e3 = new Employee("Bob", 102, 75000.00);
            e3.display();
        }
    }
    
}

```

---