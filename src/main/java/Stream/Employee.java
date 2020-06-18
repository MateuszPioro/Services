package Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;


public class Employee {
    private int id;
    private double salary;
    private String division;
    private DayJob dayJob;

    public Employee(int id, double salary, String division, DayJob dayJob) {
        this.id = id;
        this.salary = salary;
        this.division = division;
        this.dayJob = dayJob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public DayJob getDayJob() {
        return dayJob;
    }

    public void setDayJob(DayJob dayJob) {
        this.dayJob = dayJob;
    }

    public enum DayJob {
        FULL_TIME("Full-time job"),
        PART_TIME("Part-time job");

        String dayJobDescription;

        DayJob(String dayJobDescription) {
            this.dayJobDescription = dayJobDescription;
        }
    }

    @Override
    public String toString() {
        final StringBuilder stringBuilder = new StringBuilder("Employee {");
        stringBuilder.append(" \nid: ").append(this.id);
        stringBuilder.append(", \nsalary: ").append(this.salary);
        stringBuilder.append(", \ndivision: ").append(this.division);
        stringBuilder.append(", \ndayJob: ").append(this.dayJob);
        stringBuilder.append("}");

        return stringBuilder.toString();
    }

    public static void main(String[] args) {

        List<Employee> employees =
                Arrays.asList(
                        new Employee(1, 2000d, "Risk Department", Employee.DayJob.FULL_TIME),
                        new Employee(2,2500d, "Scoring Department", Employee.DayJob.FULL_TIME),
                        new Employee(3,2600d, "Scoring Department", Employee.DayJob.FULL_TIME),
                        new Employee(4,2700d, "Credit Department", Employee.DayJob.FULL_TIME),
                        new Employee(5,2700d, "Credit Department", Employee.DayJob.PART_TIME)
                );

        employees.stream()
                .filter(employee -> employee.getDayJob().equals(DayJob.FULL_TIME))
                .reduce((employee1, employee2) ->employee1.getSalary()>employee2.getSalary() ? employee1 :employee2 )
                .ifPresent(System.out::println);


  Double salariesSumForFullTimeEmployees =
          employees.stream()
          .filter(e->e.getDayJob().equals(DayJob.FULL_TIME))
          .map(Employee::getSalary)
          .reduce(0.0,Double::sum);

//        System.out.println(salariesSumForFullTimeEmployees);

        List<Integer> array = Arrays.asList(-2,0,4,6,8);

        int sum =array.stream()
                .reduce(0, Integer::sum);

//        System.out.println(sum);

        int product = IntStream.range(2,4)
                .reduce((e1,e2)->e1*e2)
                .orElse(-1);

        System.out.println(product);

        List<String> lettrs = Arrays.asList("a","b","c","d","e");

        String result = lettrs.stream()
                .reduce("",String::concat);
        System.out.println(result);



//        List<Employee> employeeList =
//                employees.stream()
//                        .filter(g->g.salary>2000)
//                .collect(Collectors.toList());
//
//        System.out.println(employeeList);
//
//        List<List<Integer>> slicedIntegers = Arrays.asList(
//                Arrays.asList(2,4,6),
//                Arrays.asList(8,10,12),
//                Arrays.asList(14,16,18)
//        );
//        System.out.println(slicedIntegers);
//
//        List<Integer> simpleIntegerList =
//                slicedIntegers.stream()
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//
//        System.out.println(simpleIntegerList);


    }
}