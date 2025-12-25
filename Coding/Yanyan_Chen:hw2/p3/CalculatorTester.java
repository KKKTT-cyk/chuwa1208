public class CalculatorTester {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        System.out.println(calculator.add(39, 21));        // int, int
        System.out.println(calculator.add(10, 20, 30));     // int, int, int
        System.out.println(calculator.add(21.5, 38.5));    // double, double
    }
}
