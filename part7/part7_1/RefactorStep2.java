package part7.part7_1;

import java.util.*;
import java.util.function.*;

/**
 * Задание 7.1, Этап 2 — Замена лямбд на ссылки на методы
 *
 * Тема: ссылки на методы (method references, Java 8+).
 *
 * Ключевая теория — 4 вида ссылок на методы:
 *   - Класс::статическийМетод — например, Integer::compare
 *   - Класс::экземплярныйМетод — например, String::toUpperCase
 *     (вызывается на первом параметре лямбды)
 *   - объект::метод — например, System.out::println
 *   - Класс::new — ссылка на конструктор, например ArrayList::new
 *
 * Важно: НЕ ВСЕ лямбды можно заменить на ссылки. Нельзя, если:
 *   - Лямбда выполняет операцию, а не просто вызывает метод: s -> s + "!"
 *   - Лямбда вызывает метод с дополнительными аргументами: s -> s.length() > 5
 *
 * Задача: замените лямбды на ссылки на методы ТАМ, ГДЕ ЭТО ВОЗМОЖНО.
 * Для невозможных случаев оставьте лямбду и напишите комментарий «почему нельзя».
 */
public class RefactorStep2 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине
        //   Можно ли заменить (a, b) -> Integer.compare(a.length(), b.length()) на ссылку?
        //   Подсказка: нет, потому что вызывается a.length() — это не прямой вызов одного метода.
        //   Используйте Comparator.comparingInt(String::length)
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        cities.sort(Comparator.comparingInt(String::length));
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 2. Вывод каждого элемента
        //   city -> System.out.println(city) → System.out::println
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        cities.forEach(System.out::println);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 3. Преобразование в верхний регистр
        //   s -> s.toUpperCase() → String::toUpperCase
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Function<String, String> toUpper = String::toUpperCase;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 4. Проверка длины > 5
        //   Можно ли заменить s -> s.length() > 5 на ссылку?
        //   Ответ: нет — это не вызов одного метода, а выражение с операцией сравнения.
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Predicate<String> isLong = s -> s.length() > 5;  // оставляем лямбду
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 5. Формирование строки с восклицательным знаком
        //   Можно ли заменить s -> s + "!" на ссылку?
        //   Ответ: нет — это конкатенация, а не вызов метода.
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Function<String, String> exclaim = s -> s + "!";  // оставляем лямбду
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 6. Создание нового списка
        //   () -> new ArrayList<>() → ArrayList::new
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Supplier<List<String>> listFactory = ArrayList::new;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // Использование (скопируйте из RefactorStep1 и адаптируйте)
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        List<String> result = listFactory.get();
        for (String city : cities) {
            if (isLong.test(city)) {
                result.add(toUpper.apply(city));
            }
        }
        System.out.println("Длинные города: " + result);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
