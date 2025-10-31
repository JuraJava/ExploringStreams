package stream.Test10;

import java.util.Map;

public class Student2 {
    String name;
    String group;
    Map<String, Integer> grades;

    public Student2(String name, String group, Map<String, Integer> grades) {
        this.name = name;
        this.group = group;
        this.grades = grades;
    }

    public double getAverageGrade() {
        return grades.values().stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    public Map<String, Integer> getGrades() {
        return grades;
    }
}
