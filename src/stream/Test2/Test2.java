package stream.Test2;

import java.util.ArrayList;
import java.util.List;

public class Test2 {
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

//        students.stream().map(e ->
//        {
//            e.setName(e.getName().toUpperCase());
//            return e;
//        })
//                .filter(e -> e.getSex() == 'f')
//                .sorted((x,y) -> x.getAge() - y.getAge())
//                .forEach(e -> System.out.println(e));

// Требуется отфильтровать студентов и оставить только тех, возраст которых больше 22 года
// и средняя оценка меньше 7.2
// Используем метод filter()

//        List<Student> students2 = new ArrayList<>();
//        students2 = students.stream().filter(element -> element.getAge() > 22 && element.getAvgGrade() < 7.2)
//                .collect(Collectors.toList());
//        System.out.println(students2);

// Стрим не обязательно создавать из чего-то (коллекции, массива и др.)
// Стрим также можно создать совершенно с нуля, например:
//        Stream<Student> myStream = Stream.of(st1, st2, st3, st4, st5);
//        myStream.filter(element -> element.getAge() > 22 && element.getAvgGrade() < 7.2)
//                .collect(Collectors.toList());

        Student first = students.stream().map(e ->
                {
                    e.setName(e.getName().toUpperCase());
                    return e;
                })
                .filter(e -> e.getSex() == 'f')
                .sorted((x,y) -> x.getAge() - y.getAge())
                .findFirst().get();
        System.out.println(first);

    }
}

