package stream.test3;

import java.util.Arrays;

public class Test3 {
    public static void main(String[] args) {
        int[] array = {5, 9, 3, 8, 1};
// Arrays.stream(array).forEach(e1 -> {e1*=2;System.out.println(e1);});
// Arrays.stream(array).forEach(e1->System.out.println(e1));
        Arrays.stream(array).forEach(System.out::println);
// Выражение, которое в параметрах метода e1->System.out.println(e1)
// равно выражению System.out::println
// Здесь мы даём ссылку на наш метод println
// т.е. до двух двоеточий "::" указывается класс
// а после двух двоеточий "::" указывается метод
// Ниже идёт пример уже с другимметодом myMethod()
        Arrays.stream(array).forEach(Utils::myMethod);
//Arrays.stream(array).forEach(e1 -> Utils.myMethod(e1));
// Это означает, что необходимо программе каждый элемент потока поместить
// в метод myMethod() в качестве параметра, который находится в классе Utils
// Но такое ввозможно если тип элементов стрима такой же как и тип параметра метода
    }
}
