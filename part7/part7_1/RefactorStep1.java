package part7.part7_1;

import java.util.*;
import java.util.function.*;

/**
 * Задание 7.1, Этап 1 — Замена анонимных классов на лямбда-выражения
 *
 * Тема: лямбда-выражения (Java 8+).
 *
 * Ключевая теория:
 *   - Лямбда заменяет анонимный класс для функционального интерфейса
 *     (интерфейс с одним абстрактным методом).
 *   - Синтаксис: (параметры) -> выражение или (параметры) -> { блок }
 *   - Примеры:
 *       - Comparator<String> c = (a, b) -> Integer.compare(a.length(), b.length());
 *       - Consumer<String> print = s -> System.out.println(s);
 *       - Function<String, String> upper = s -> s.toUpperCase();
 *
 * Задача: замените каждый анонимный класс из RefactorOriginal.java
 * на лямбда-выражение. Результат должен быть идентичен оригиналу.
 */
public class RefactorStep1 {
    public static void main(String[] args) {
        List<String> cities = Arrays.asList("Москва", "Берлин", "Токио", "Нью-Йорк", "Париж");

        // 1. Сортировка по длине → замените анонимный класс на лямбду
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        cities.sort((a, b) -> Integer.compare(a.length(), b.length()));
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 2. Вывод каждого элемента
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        cities.forEach(city -> System.out.println(city));
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 3. Преобразование в верхний регистр
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Function<String, String> toUpper = s -> s.toUpperCase();
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 4. Проверка длины > 5
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Predicate<String> isLong = s -> s.length() > 5;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 5. Формирование строки с восклицательным знаком
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Function<String, String> exclaim = s -> s + "!";
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // 6. Создание нового списка
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        Supplier<List<String>> listFactory = () -> new ArrayList<>();
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // Использование (скопируйте из RefactorOriginal и адаптируйте)
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
