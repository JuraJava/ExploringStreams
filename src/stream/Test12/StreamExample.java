/**
 * Дан список сотрудников с полями: имя, отдел, зарплата. Необходимо:
 * 1.	Отфильтровать сотрудников с зарплатой выше 50,000
 * 2.	Сгруппировать их по отделам
 * 3.	Для каждого отдела посчитать среднюю зарплату
 * 4.	Вывести результат в отсортированном виде по названию отдела
 */

package stream.Test12;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        // Создаем список сотрудников
        List<Employee> employees = Arrays.asList(
                new Employee("Иван", "IT", 75000),
                new Employee("Мария", "HR", 45000),
                new Employee("Петр", "IT", 80000),
                new Employee("Анна", "Finance", 60000),
                new Employee("Сергей", "HR", 55000),
                new Employee("Ольга", "Finance", 48000),
                new Employee("Дмитрий", "IT", 90000)
        );

        // Решение с использованием Stream API
        Map<String, Double> result = employees.stream()
                // Шаг 1: Фильтрация сотрудников с зарплатой выше 50,000
                .filter(emp -> emp.getSalary() > 50000)

                // Шаг 2: Группировка по отделам
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,

                        // Шаг 3: Вычисление средней зарплаты для каждой группы
                        Collectors.averagingDouble(Employee::getSalary)
                ))

                // Шаг 4: Сортировка по названию отдела
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new
                ));

        // Вывод результата
        System.out.println("Средняя зарплата по отделам (сотрудники с зарплатой > 50,000):");
        result.forEach((dept, avgSalary) ->
                System.out.printf("%s: %.2f%n", dept, avgSalary)
        );
    }
}

