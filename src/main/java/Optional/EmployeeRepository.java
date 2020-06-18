package Optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class EmployeeRepository {

    private static Map<String, Employee> employees = new HashMap<>(2);




    static {
        employees.put("Pawel", new Employee("Pawel", 32));
        employees.put("Dawid", new Employee("Dawid", 29));
    }


    private EmployeeRepository() {
    }

    public static Optional<Employee> find(String name) {

         return Optional.ofNullable(employees.get(name));
    }
}
