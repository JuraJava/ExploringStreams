package stream;

import stream.test2.Student;

import java.util.ArrayList;
import java.util.List;

public class Test9 {
    public static void main(String[] args) {
        Student st1 = new Student("Ivan", 'm', 22, 3, 8.3);
        Student st2 = new Student("Nikolay", 'm', 28, 2, 6.4);
        Student st3 = new Student("Elena", 'f', 19, 1, 8.9);
        Student st4 = new Student("Petr", 'm', 35, 4, 7);
        Student st5 = new Student("Mariya", 'f', 23, 3, 7.4);
        List<Student> students = new ArrayList<>();
        students.add(st1);
        students.add(st2);
        students.add(st3);
        students.add(st4);
        students.add(st5);

//        Student min = students.stream().min((x, y) -> x.getAge() - y.getAge()).get();
//        System.out.println(min);
//
//        Student max = students.stream().max((x, y) -> x.getAge() - y.getAge()).get();
//        System.out.println(max);

//        students.stream().filter(e -> e.getAge() > 20).forEach(System.out::println);
//        System.out.println("________________________________________________________");
//        students.stream().filter(e -> e.getAge() > 20).limit(2).forEach(System.out::println);
//// Метод limit(2) оставляет в результате столько элементов стрима, сколько указано в его параметре, т.е. 2
//        System.out.println("________________________________________________________");
//        students.stream().filter(e -> e.getAge() > 20).skip(3).forEach(System.out::println);
//// Метод skip(3) пропускает в результате столько элементов стрима, сколько указано в его параметре, т.е. 3

//        List<Integer> courses = students.stream()
//                .mapToInt(e1 -> e1.getCourse())
//                .boxed()
//                .collect(Collectors.toList());
//
//        System.out.println(courses);

// Метод boxed() преобразует значение int в Integer

        int sum = students.stream().mapToInt(e1 -> e1.getCourse()).sum();
        System.out.println(sum);

        double average = students.stream().mapToInt(e1 -> e1.getCourse()).average().getAsDouble();
        System.out.println(average);

        int min = students.stream().mapToInt(e1 -> e1.getCourse()).min().getAsInt();
        System.out.println(min);

        int max = students.stream().mapToInt(e1 -> e1.getCourse()).max().getAsInt();
        System.out.println(max);
        // Метод mapToInt() (mapToDouble и др.) преобразует стрим в стрим интов (или стрим даблов)
        // А далее уже можно пользоваться методами инт-стримов

    }
}
