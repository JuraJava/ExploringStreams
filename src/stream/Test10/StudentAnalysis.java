/**
 * Дан список студентов с их оценками по разным предметам.
 * Нужно выполнить несколько операций анализа.
 */

package stream.Test10;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StudentAnalysis {
    public static void main(String[] args) {
        List<Student2> students = Arrays.asList(
                new Student2("Иван", "Группа А", Map.of("Математика", 4, "Физика", 5, "Химия", 3)),
                new Student2("Мария", "Группа Б", Map.of("Математика", 5, "Физика", 5, "Химия", 4)),
                new Student2("Пётр", "Группа А", Map.of("Математика", 3, "Физика", 4, "Химия", 5)),
                new Student2("Анна", "Группа Б", Map.of("Математика", 4, "Физика", 3, "Химия", 4)),
                new Student2("Сергей", "Группа А", Map.of("Математика", 5, "Физика", 5, "Химия", 5))

        );
        System.out.println("Студенты с средним баллом > 4.0:");
        students.stream()
                .filter(student -> student.getAverageGrade() > 4);

    }
}
