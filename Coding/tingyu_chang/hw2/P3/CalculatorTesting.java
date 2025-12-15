public class CalculatorTesting {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        // Demonstrate calling each method in a main method
        System.out.println("Sum of 2 ints (12 + 6): " + calc.add(12, 6));
        System.out.println("Sum of 3 ints (12 + 6 + 18): " + calc.add(12, 6, 18));
        System.out.println("Sum of 2 doubles (2.5 + 3.5): " + calc.add(2.5, 3.5));
    }
}