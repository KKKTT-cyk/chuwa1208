public class Calculator() {
    
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();

        int add2 = calc.add(1, 2);
        int add3 = calc.add(1, 2, 3);
        int addDouble = calc.add(1.1, 2.2);
        
        System.out.println("add2: " + add2 + ", add3: " + add3 + ", add double: " + addDouble);
    }
}
