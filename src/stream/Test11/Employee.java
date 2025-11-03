package stream.Test11;

public class Employee {
    private String name;
    private String department;
    private double salary;
    private int performanceRating;

    public Employee(String name, String department, double salary, int performanceRating) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.performanceRating = performanceRating;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public int getPerformanceRating() {
        return performanceRating;
    }

}
