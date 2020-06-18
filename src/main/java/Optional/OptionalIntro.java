package Optional;

import org.w3c.dom.ls.LSOutput;

import java.util.Optional;

public class OptionalIntro {
    public static void main(String[] args) {

        Optional<Employee> employee  =  EmployeeRepository.find("Pawel");

//        employee.ifPresent(employee1 -> System.out.println(employee1.getName()));

//        employee
//                .filter(emp->emp.getAge()<30)
//                .map(Employee::getName)
//                .map(String::toUpperCase)
//                .ifPresent(System.out::println);
    //Sytuacja z nieistnięcym Jackiem!!!



        employee
                .or(()->Optional.of(new Employee("Jacek",35)))
                .filter(emp->emp.getAge()<30)
                .map(Employee::getName)
                .map(String::toUpperCase)
                .ifPresentOrElse(
                        System.out::println,
                        ()->System.out.println("Brak pracownika spełniajacego pdane kryteria")
                );

    }
}
