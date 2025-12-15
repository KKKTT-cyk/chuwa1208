package p3;

public class Calculator {
    //  multiple overloaded add() methods:
    //  add(int a, int b)
    public int add(int a, int b) {
        return a + b;
    }

    //  add(int a, int b, int c)
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    //  add(double a, double b)
    public double add(double a, double b) {
        return a + b;
    }

    // Demonstrate calling each method in a main method.
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // add(int, int)
        System.out.println(calc.add(5, 6));
        // add(int, int, int)
        System.out.println(calc.add(1, 2, 3));
        // add(double, double)
        System.out.println(calc.add(4.8, 6.9));
    }
}
