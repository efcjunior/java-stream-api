package coding4world.model;

public class Employee {

    private final JobTitle title;
    private final Double salary;

    public Employee(JobTitle title, Double salary) {
        this.title = title;
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public JobTitle getTitle() {
        return title;
    }
}
