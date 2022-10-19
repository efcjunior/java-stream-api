package coding4world.services;

import coding4world.model.Employee;
import coding4world.model.JobTitle;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Maps.newHashMap;
import static coding4world.model.JobTitle.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeServiceTest {

    private final Employee employee1 = new Employee(DATA_ANALYST, 1000.00);
    private final Employee employee2 = new Employee(DATA_ANALYST, 3000.00);
    private final Employee employee3 = new Employee(JAVA_ENGINEER, 4000.00);
    private final Employee employee4 = new Employee(JAVA_ENGINEER, 2000.00);
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        List<Employee> employees = newArrayList(employee1, employee2, employee3, employee4);
        employeeService = new EmployeeService(employees);
    }

    @Test
    public void groupByJobTitle() {
        //when
        Map<JobTitle, List<Employee>> groupByJobTitle = employeeService.groupByJobTitle();

        //then
        HashMap<JobTitle,List<Employee>> assertMap = newHashMap();
        assertMap.put(DATA_ANALYST, newArrayList(employee1, employee2));
        assertMap.put(JAVA_ENGINEER, newArrayList(employee3, employee4));

        assertMap.forEach((jobTitle, employees) -> assertThat(groupByJobTitle.get(jobTitle)).isEqualTo(employees));
    }

    @Test
    public void calculateAverage() {
        //when
       double calculateAverage = employeeService.calculateAverage();

        //then
        assertThat(calculateAverage).isEqualTo(2500);
    }
}
