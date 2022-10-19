package coding4world.model;

public class Employee {

    private String name;
    private JobTitle title;

    public Employee(String name, JobTitle title) {
        this.name = name;
        this.title = title;
    }

    public JobTitle getTitle() {
        return title;
    }

    public void setTitle(JobTitle title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
