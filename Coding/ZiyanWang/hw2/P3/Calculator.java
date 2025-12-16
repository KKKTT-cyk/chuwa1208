public class Calculator {
    // add two int
    public int add(int a, int b) {
        return a + b;
    }

    // add three int
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // add two double
    public double add(double a, double b) {
        return a + b;
    }

    // main
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        System.out.println(calc.add(1, 2));        // calls add(int, int)
        System.out.println(calc.add(1, 2, 3));     // calls add(int, int, int)
        System.out.println(calc.add(1.5, 2.5));    // calls add(double, double)
    }
}