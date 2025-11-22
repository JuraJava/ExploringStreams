package stream;


import stream.test2.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test5 {
    public static void main(String[] args) {
//        int[] array = {3, 8, 1, 5, 9, 12, 4, 21, 81, 7, 18};
//        array = Arrays.stream(array).sorted().toArray();
//        System.out.println(Arrays.toString(array));
//
//        Student st1 = new Student("Ivan", 'm', 22, 3, 8.3);
//        Student st2 = new Student("Nikolay", 'm', 28, 2, 6.4);
//        Student st3 = new Student("Elena", 'f', 19, 1, 8.9);
//        Student st4 = new Student("Petr", 'm', 35, 4, 7);
//        Student st5 = new Student("Mariya", 'f', 23, 3, 7.4);
//        List<Student> students = new ArrayList<>();
//        students.add(st1);
//        students.add(st2);
//        students.add(st3);
//        students.add(st4);
//        students.add(st5);
//
//        students = students.stream().sorted((x,y) -> x.getName().compareTo(y.getName()))
//                .collect(Collectors.toList());
//        // в параметрах метода sorted() мы вставили Comparator
//        System.out.println(students);

// Отфильтруем стрим так чтобы остались только нечётные числа,
// затем подели на 3 только те числа, которые делятся на 3 без остатка,
// затем найдём сумму оставшихся чисел
        int[] array = {3, 8, 1, 5, 9, 12, 4, 21, 81, 7, 18};
        int result = Arrays.stream(array).filter(e -> e % 2 == 1)
                .map(e -> {
                    if (e % 3 == 0) {
                        e = e / 3;
                    }
                    return e;
                })
                .reduce((a, e) -> a + e).getAsInt();
        System.out.println(result);

// Имена студентов сделать заглавными буквами, потом отфильтровать
// по полу (выбрать только девушек), затем отсортировать по возрасту
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

        students.stream().map(e ->
                {
                    e.setName(e.getName().toUpperCase());
                    return e;
                })
                .filter(e -> e.getSex() == 'f')
                .sorted((x, y) -> x.getAge() - y.getAge())
                .forEach(e -> System.out.println(e));

        // Так работает метод chaining, т.е. цепочка методов:
        // Сначала из имеющейся последовательности элементов создаём стрим,
        // затем применяем к стриму intermediate (промежуточные) методы, после этого
        // к получившемуся стриму применяется terminal (конечный) метод,
        // в конечном результате может быть и не стрим
    }
}