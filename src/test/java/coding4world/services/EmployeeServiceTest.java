package coding4world.services;

import coding4world.model.Employee;
import coding4world.model.JobTitle;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static com.google.common.collect.Lists.newArrayList;
import static coding4world.model.JobTitle.*;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeServiceTest {

    private Employee employee1 = new Employee(randomAlphabetic(8), DATA_ANALYST);
    private Employee employee2 = new Employee(randomAlphabetic(8), DATA_ANALYST);
    private Employee employee3 = new Employee(randomAlphabetic(8), JAVA_ENGINEER);
    private Employee employee4 = new Employee(randomAlphabetic(8), JAVA_ENGINEER);
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
        assertThat(groupByJobTitle.get(DATA_ANALYST)).isEqualTo(newArrayList(employee1, employee2));
        assertThat(groupByJobTitle.get(JAVA_ENGINEER)).isEqualTo(newArrayList(employee3, employee4));
    }
}
