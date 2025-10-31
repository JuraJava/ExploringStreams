package stream;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Test4 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(8);
        list.add(2);
        list.add(4);
        list.add(3);

        int result = list.stream().reduce((accumulator, element) ->
                accumulator * element).get();
        System.out.println(result);

        int result2 = list.stream().reduce(1, (accumulator, element) ->
                accumulator * element);
        System.out.println(result2);
//  Здесь в параметрах 1 означает первичное значение нашего аккумулятора
// В таком случае т.к. значение null мы уже не получим, то метод get()
// использовать не нужно

        // Метод reduce() принимает стрим, а возвращает один элемент (например, число)
        // Метод reduce() возвращает Optional. Чтобы из него получить значение
        // необходимо использовать метод get()
        // Объект типа Optional оборачивает собой объект Integer, который мы
        // получаем в результате, т.е. Optional является контейнером для нашего объекта
        // Integer, получившегося в результате. Optional может содержать null или not null значения
        // поэтому обернув Integer в Optional мы можем дополнительно объект Integer в коде
        // не проверять, а воспользоваться уже готовым методом isPresent() (присутствует ли требуемый элемент
        // и если присутсвует, то сделай с ним то что я укажу далее после этого метода), который
        // имеется у объекта Optional
        // Это бывает нужно, когда, например, мы делаем фильтрацию и ни один элемент стрима
        // эту фильтрацию не прошёл
        // Пример ниже

//        List<Integer> list100 = new ArrayList<>();
//        Optional<Integer> optional = list100.stream().reduce((accumulator, element) ->
//                accumulator * element);
//        if (optional.isPresent()) {
//            System.out.println(optional.get());
//        } else {
//            System.out.println("Not present");
//        }

    }
}
