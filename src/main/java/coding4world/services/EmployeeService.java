package coding4world.services;

import coding4world.model.Employee;
import coding4world.model.JobTitle;

import java.util.*;

public class EmployeeService {

    private final List<Employee> employees;

    public EmployeeService(List<Employee> employees) {
        this.employees = employees;
    }

    public Map<JobTitle, List<Employee>> groupByJobTitle() {
        Map<JobTitle, List<Employee>> resultMap = new HashMap<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            List<Employee> employeeSubList = resultMap.getOrDefault(employee.getTitle(), new ArrayList<Employee>());
            employeeSubList.add(employee);
            resultMap.put(employee.getTitle(), employeeSubList);
        }
        return resultMap;
    }
}
