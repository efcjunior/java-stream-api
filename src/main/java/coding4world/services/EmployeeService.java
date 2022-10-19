package coding4world.services;

import coding4world.model.Employee;
import coding4world.model.JobTitle;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    private final List<Employee> employees;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    /*public Map<JobTitle, List<Employee>> groupByJobTitle() {
        Map<JobTitle, List<Employee>> resultMap = new HashMap<>();
        for (Employee employee : employees) {
            List<Employee> employeeSubList = resultMap.getOrDefault(employee.getTitle(), new ArrayList<Employee>());
            employeeSubList.add(employee);
            resultMap.put(employee.getTitle(), employeeSubList);
        }
        return resultMap;
    }*/

    public Map<JobTitle, List<Employee>> groupByJobTitle() {
        return employees.stream().collect(Collectors.groupingBy(Employee::getTitle));
    }

    /*public double calculateAverage() {
        double sum = 0;
        int count = 0;
        for (Employee employee:employees) {
            sum += employee.getSalary();
            count++;
        }
        return (double) sum / count;
    }*/

    public double calculateAverage() {
        return employees.stream().mapToDouble(Employee::getSalary).average().orElseThrow();
    }
}
