/**
 * Дан список студентов с их оценками по разным предметам.
 * Нужно выполнить несколько операций анализа.
 */

package stream.Test10;
import java.util.*;
import java.util.stream.Collectors;

public class StudentAnalysis {
    public static void main(String[] args) {
        List<Student2> students = Arrays.asList(
                new Student2("Иван", "Группа А", Map.of("Математика", 4, "Физика", 5, "Химия", 3)),
                new Student2("Мария", "Группа Б", Map.of("Математика", 5, "Физика", 5, "Химия", 4)),
                new Student2("Пётр", "Группа А", Map.of("Математика", 3, "Физика", 4, "Химия", 5)),
                new Student2("Анна", "Группа Б", Map.of("Математика", 4, "Физика", 3, "Химия", 4)),
                new Student2("Сергей", "Группа А", Map.of("Математика", 5, "Физика", 5, "Химия", 5))

        );

        // Промежуточные операции: filter, map, flatMap, sorted, distinct, limit
        // Терминальные операции: collect, forEach, count, average, max, min, reduce

        // 1. Найти студентов с средним баллом выше 4.0
        System.out.println("Студенты с средним баллом > 4.0:");
        students.stream()
                .filter(student -> student.getAverageGrade() > 4) // промежуточная
                .map(Student2::getName) // промежуточная
                .forEach(System.out::println); // терминальная

        // 2. Сгруппировать студентов по группам
        System.out.println("\nСтуденты по группам:");
        Map<String, List<Student2>> studentsByGroup = students.stream()
                .collect(Collectors.groupingBy(Student2::getGroup)); // терминальная

        studentsByGroup.forEach((group, studentList) -> {
            System.out.println(group + ": " +
                    studentList.stream()
                            .map(Student2::getName)
                            .collect(Collectors.joining(", ")));

        });

        // 3. Найти средний балл по каждой группе
        System.out.println("\nСредний балл по группам:");
        Map<String, Double> averageByGroup = students.stream()
                .collect(Collectors.groupingBy(
                        Student2::getGroup,
                        Collectors.averagingDouble(Student2::getAverageGrade)
                ));

        averageByGroup.forEach((group, avg) ->
                System.out.printf("%s: %.2f\n", group, avg));

        // 4. Получить все уникальные предметы
        System.out.println("\nВсе предметы:");
        Set<String> allSubjects = students.stream()
                .flatMap(student2 -> student2.getGrades().keySet().stream()) // промежуточная
                .distinct() // промежуточная
                .sorted()// промежуточная
                .collect(Collectors.toSet()); // терминальная

        allSubjects.forEach(System.out::println);

        // 5. Найти топ-3 студента по среднему баллу
        System.out.println("\nТоп-3 студента:");
        students.stream()
                .sorted((s1, s2) -> Double.compare(s2.getAverageGrade(), s1.getAverageGrade())) // промежуточная
                .limit(3) // промежуточная
                . forEach(student2 -> System.out.printf("%s: %.2f\n",
                        student2.getName(), student2.getAverageGrade())); // промежуточная

        // 6. Подсчитать общее количество оценок "5"
        long excellentCount = students.stream()
                .flatMap(student2 -> student2.getGrades().values().stream()) // промежуточная
                .filter(grade -> grade == 5) // промежуточная
                .count(); // терминальная

        System.out.println("\nКоличество отличных оценок: " + excellentCount);

        // 7. Найти максимальный средний балл
        OptionalDouble maxAverage = students.stream()
                .mapToDouble(Student2::getAverageGrade) // промежуточная
                .max(); // терминальная

        maxAverage.ifPresent(max ->
                System.out.println("Максимальный средний балл: " + max));


    }
}
