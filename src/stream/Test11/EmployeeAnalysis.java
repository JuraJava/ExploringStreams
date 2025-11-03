/**
 * Дан список сотрудников с информацией о имени, отделе, зарплате и рейтинге производительности.
 * Нужно выполнить комплексный анализ данных.
 */
package stream.Test11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeAnalysis {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
                new Employee("Иван", "IT", 75000, 4),
                new Employee("Мария", "HR", 65000, 5),
                new Employee("Петр", "IT", 80000, 3),
                new Employee("Анна", "Finance", 70000, 4),
                new Employee("Сергей", "IT", 90000, 5),
                new Employee("Ольга", "HR", 60000, 4),
                new Employee("Алексей", "Finance", 85000, 2)
        );

        // Комплексный анализ с использованием нескольких терминальных операций
        performComprehensiveAnalysis(employees);
    }

    public static void performComprehensiveAnalysis(List<Employee> employees) {
        System.out.println("=== КОМПЛЕКСНЫЙ АНАЛИЗ СОТРУДНИКОВ ===\n");

        // 1. Группировка по отделам с агрегацией данных
        Map<String, DoubleSummaryStatistics> deptStats = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summarizingDouble(Employee::getSalary)
                ));

        System.out.println("1. Статистика зарплат по отделам:");
        deptStats.forEach((dept, stats) ->
                System.out.printf("%s: средняя=%.2f, макс=%.2f, мин=%.2f, количество=%d%n",
                        dept, stats.getAverage(), stats.getMax(), stats.getMin(), stats.getCount())
        );

        // 2. Поиск сотрудников с лучшими показателями
        System.out.println("\n2. Топ-3 сотрудника по зарплате:");
        employees.stream()
                .sorted((e1, e2) -> Double.compare(e2.getSalary(), e1.getSalary()))
                .limit(3)
                .forEach(e -> System.out.printf("%s (%s): %.2f%n",
                        e.getName(), e.getDepartment(), e.getSalary()));

        // 3. Анализ производительности
        System.out.println("\n3. Распределение по рейтингу производительности:");
        Map<Integer, Long> ratingDistribution = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getPerformanceRating,
                        Collectors.counting()
                ));

        ratingDistribution.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry ->
                        System.out.printf("Рейтинг %d: %d сотрудников%n",
                                entry.getKey(), entry.getValue())
                );

        // 4. Поиск кандидатов на премию (высокий рейтинг + зарплата выше средней)
        double avgSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);

        System.out.printf("\n4. Кандидаты на премию (рейтинг >=4, зарплата > средней %.2f):%n", avgSalary);
        List<String> bonusCandidates = employees.stream()
                .filter(e -> e.getPerformanceRating() >= 4)
                .filter(e -> e.getSalary() > avgSalary)
                .map(Employee::getName)
                .collect(Collectors.toList());

        if (bonusCandidates.isEmpty()) {
            System.out.println("Кандидаты не найдены");
        } else {
            bonusCandidates.forEach(name -> System.out.println("• " + name));
        }

        // 5. Проверка наличия сотрудников с низким рейтингом
        boolean hasLowPerformers = employees.stream()
                .anyMatch(e -> e.getPerformanceRating() <= 2);

        System.out.println("\n5. Есть ли сотрудники с низким рейтингом (<=2): " +
                (hasLowPerformers ? "ДА" : "НЕТ"));

        // 6. Самый высокооплачиваемый сотрудник
        Optional<Employee> highestPaid = employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));

        highestPaid.ifPresent(emp ->
                System.out.printf("\n6. Самый высокооплачиваемый сотрудник: %s (%.2f)%n",
                        emp.getName(), emp.getSalary())
        );

        // 7. Общая сумма зарплатного фонда
        double totalSalary = employees.stream()
                .mapToDouble(Employee::getSalary)
                .sum();

        System.out.printf("\n7. Общий зарплатный фонд: %.2f%n", totalSalary);
    }
}
