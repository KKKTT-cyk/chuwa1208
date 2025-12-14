class Calculator {
    public int add(int a, int b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }


    public double add(double a, double b) {
        return a + b;
    }

    public static void main(String[] strings) {
        Calculator c = new Calculator();
        System.out.println("add(a,b), a = 1， b = 2, res = " + c.add(1, 2));
        System.out.println("add(a,b,c), a = 1， b = 2, c = 3, res = " + c.add(1, 2, 3));
        System.out.println("add(a,b), a = 1.0， b = 2.0, res = " + c.add(1.0, 2.0));
    }
}