//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //p1
        BankAccount acc = new BankAccount("123",22);
        double bal = acc.getBalance();
        System.out.println(bal);

        //p2
        Car car = new Car();
        car.setSpeed(123);
        car.displayInfo();

        //p3
        int sum = Calculator.add(2,3);
        System.out.println(sum);
        int sum2 = Calculator.add(2,3,5);
        System.out.println(sum2);
        double sum3 = Calculator.add(1.2,2.2);
        System.out.println(sum3);

        //p4
        Shape[] shapes = new Shape[2];
        shapes[0] = new Rectangle(23,3);
        shapes[1] = new Circle(3);
        System.out.println("Area of rectangle:"+shapes[0].getArea());
        System.out.println("Area of circle:"+shapes[1].getArea());

        //p5
        Employee em1 = new Employee();
        System.out.println("name of em1: "+ em1.getName());
        Employee em2 = new Employee("Jack","123",3344);
        System.out.println("name of em2: "+ em2.getName());
        Employee em3 = new Employee("Alice","456");
        System.out.println("name of em3: "+ em3.getName());
    }
}