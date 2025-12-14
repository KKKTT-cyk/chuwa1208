public class App {
    public static void main(String[] args) throws Exception {
        Calculator calculator = new Calculator();

        int result1 = calculator.add(3, 4);
        System.out.println(result1);

        int result2 = calculator.add(3, 4, 5);
        System.out.println(result2);

        double result3 = calculator.add(3.2, 3.5);
        System.out.println(result3);
    }
}
