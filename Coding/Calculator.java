public class Calculator {
    public int add(int a, int b){
        return a + b;
    }
    // Overload add method
    public int add (int a, int b, int c){
        return a + b + c;
    }
    // method add two number
    public  double add (double a, double b){
        return a + b;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        // call first add method
        System.out.println(calculator.add(1, 2));
        // call second add method
        System.out.println(calculator.add(1, 2, 3));
        // call third add method
        System.out.println(calculator.add(1.0, 2.0));
    }
}
