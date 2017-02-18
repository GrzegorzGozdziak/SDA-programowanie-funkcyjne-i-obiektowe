package sda;

import sda.workers.AbstractEmployee;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by RENT on 2017-02-18.
 */
public class Application {
    public static void main(String[] args) {
        List<AbstractEmployee> employeeList = new ArrayList<>();

        employeeList.add(new AbstractEmployee("Szymon", "Nowak", 2000, "JAVA"));
        employeeList.add(new AbstractEmployee("Jan", "Nowak", 5000, "HR"));
        employeeList.add(new AbstractEmployee("Wojtek", "Nowak", 3000, "JAVA"));
        employeeList.add(new AbstractEmployee("Anna", "Nowacka", 1000, "PR"));
        employeeList.add(new AbstractEmployee("Ewa", "Nowak", 5000, "JAVA"));

        System.out.println();
        System.out.println("wyswietl tylko tych z dzialu JAVA");
        employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .forEach(e -> System.out.println(e));

        System.out.println();
        System.out.println("szukamy dziewczynek - imie konczy sie na a");


        employeeList.stream()
                .filter(e->e.getFirstName().endsWith("a"))
                .forEach(e -> System.out.println(e));

        System.out.println();
        System.out.println("szukamy zarabiajacych pow. 3000");

        employeeList.stream()
                .filter(e -> e.getSalary()>3000)
                .forEach(e -> System.out.println(e));

        System.out.println();
        System.out.println("szukamy zarabiajacych pow. 3000 i pracujacych w dziale java");

        employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .filter(e -> e.getSalary()>=3000)
                .forEach(e -> System.out.println(e));

        System.out.println();
        System.out.println("szukamy pracujacych w dziale JAVA i wrzuca wyniki do osobnej listy");

        List<AbstractEmployee> javaEmployee = employeeList.stream()
                .filter(e -> e.getDepartment().equals("JAVA"))
                .collect(Collectors.toList());
        System.out.println(javaEmployee);

        System.out.println();
        System.out.println("szukamy tylko takich pracownikow ktorych nazwisko rozpoczyna sie od Now");

        employeeList.stream()
                .filter(e -> e.getLastName().startsWith("Now"))
                .forEach(e -> System.out.println(e));

        System.out.println("");

        Map<String, AbstractEmployee> map = employeeList.stream()
                .collect(Collectors.toMap((e -> e.getFirstName()), e -> e));
        map.forEach((k,v) -> System.out.println(k + ": " + v));

        System.out.println();
        System.out.println("zwracamy liste pasujaca do imie +' '+ nazwisko");

        employeeList.stream()
                .filter(e -> ((e.getFirstName()+" "+e.getLastName()).equals("Szymon Nowak")))
                .forEach(e -> System.out.println(e));

        System.out.println();
        System.out.println("posortuj po salary:");
        employeeList.sort((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1);
        employeeList.forEach(e -> System.out.println(e.getFirstName() + ": " + e.getSalary()));

        System.out.println();

        employeeList.sort((e1, e2) -> e1.getSalary() < e2.getSalary() ? 1 : -1);
        System.out.println(employeeList.get(0));

        AbstractEmployee richestEmployee = employeeList.stream()
                .max((e1, e2) -> e1.getSalary() < e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(richestEmployee);

        AbstractEmployee poorestEmployee = employeeList.stream()
                .max((e1, e2) -> e1.getSalary() > e2.getSalary() ? 1 : -1)
                .get();
        System.out.println(poorestEmployee);

        System.out.println();

        Map<String, List<AbstractEmployee>> map1 = listToMap(employeeList);
        List<AbstractEmployee> tmpList = new ArrayList<>();
        map1.entrySet().stream()
                .forEach(e -> {
                    List<AbstractEmployee> value = e.getValue();
                    value.stream()
                            .filter(e1 -> e1.getSalary() > 3000)
                            .forEach(e1 -> tmpList.add(e1));
                });



    }

    public static Map<String, List<AbstractEmployee>> listToMap(List<AbstractEmployee> employees) {
        Map<String, List<AbstractEmployee>> map = new HashMap<>();
        for (AbstractEmployee employee : employees) {
            if(map.containsKey(employee.getDepartment())) {
                List<AbstractEmployee> listEmployees = map.get(employee.getDepartment());
                listEmployees.add(employee);
            } else {
                ArrayList<AbstractEmployee> listOfEmployees = new ArrayList<>();
                listOfEmployees.add(employee);
                map.put(employee.getDepartment(), listOfEmployees);
            }
        }
        return map;
    }
}
