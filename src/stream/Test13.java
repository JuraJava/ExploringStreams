package stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Test13 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "a1b2c3", "hello123", "test", "45world6", "no-digits");

        String result = strings.stream()
                .filter(s -> s.matches(".*\\d.*"))              // 1. Фильтруем строки с цифрами
                .flatMap(s -> s.chars().mapToObj(c -> (char) c))      // 2. Преобразуем в символы
                .filter(Character::isDigit)                           // 3. Оставляем только цифры
                .map(Character::getNumericValue)                      // 4. Преобразуем в числа
                .distinct()                                           // 5. Убираем дубликаты
                .sorted(Comparator.reverseOrder())                    // 6. Сортируем по убыванию
                .map(String::valueOf)                                 // Преобразуем числа в строки
                .collect(Collectors.joining(", "));           // 7. Собираем в строку


//    String result = strings.stream()
//            .filter(s -> s.chars().anyMatch(Character::isDigit))
//            .flatMapToInt(String::chars)
//            .mapToObj(c -> (char) c)
//            .filter(Character::isDigit)
//            .map(Character::getNumericValue)
//            .distinct()
//            .sorted((a, b) -> b - a)
//            .map(String::valueOf)
//            .reduce((a, b) -> a + "," + b)
//            .orElse("Нет цифр");


        System.out.println(result); // Вывод: 6, 5, 4, 3, 2, 1

    }
}
