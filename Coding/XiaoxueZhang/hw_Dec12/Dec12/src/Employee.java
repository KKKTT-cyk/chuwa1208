public class Employee {
    private String name;
    private String id;
    private double salary;

    public Employee(){
        this("Unknown","Unknown",0);
    }

    public Employee(String name,String id,double salary){
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
    public Employee(String name,String id){
        this(name,id,0);
    }

    public String getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }
}
