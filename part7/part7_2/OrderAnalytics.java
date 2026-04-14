package part7.part7_2;

import java.util.*;
import java.util.stream.*;

/**
 * Задание 7.2 — Конвейер обработки данных (Stream API)
 *
 * Тема: Stream API, Collectors, ссылки на методы.
 *
 * Ключевая теория:
 *   - Stream — конвейер операций над данными: источник → промежуточные → терминальная.
 *   - Промежуточные операции: filter(), map(), sorted(), distinct().
 *   - Терминальные операции: collect(), forEach(), reduce(), count().
 *   - Коллекторы: toList(), toSet(), groupingBy(),
 *     partitioningBy(), averagingDouble(), counting().
 *   - Используйте ссылки на методы вместо лямбд, где возможно:
 *     Order::total, Order::customer, System.out::println.
 *
 * Как запустить: нажмите ▶ рядом с main.
 */
public class OrderAnalytics {

    /** Запись заказа. Метод total() вычисляет итоговую стоимость. */
    record Order(String customer, String product, double price, int quantity, String category) {
        double total() { return price * quantity; }
    }

    public static void main(String[] args) {

        List<Order> orders = List.of(
            new Order("Алиса",  "Ноутбук",     45000, 1, "Электроника"),
            new Order("Борис",  "Наушники",     3500, 2, "Электроника"),
            new Order("Алиса",  "Книга Java",    900, 3, "Книги"),
            new Order("Вера",   "Монитор",     25000, 1, "Электроника"),
            new Order("Глеб",   "Мышь",          800, 5, "Электроника"),
            new Order("Вера",   "Клавиатура",   2500, 1, "Электроника"),
            new Order("Борис",  "Книга Python", 1200, 2, "Книги"),
            new Order("Алиса",  "Кофе",          350, 10, "Продукты"),
            new Order("Глеб",   "Стул",         8000, 2, "Мебель"),
            new Order("Вера",   "Стол",        15000, 1, "Мебель"),
            new Order("Борис",  "Рюкзак",       3000, 1, "Аксессуары")
        );

        // --- Задание 1: Заказы дороже 5000 руб. (по total()) ---
        System.out.println("=== Заказы дороже 5000 руб. ===");
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        orders.stream().filter(o -> o.total() > 5000).forEach(System.out::println);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // --- Задание 2: Уникальные клиенты, отсортированные по алфавиту ---
        System.out.println("\n=== Уникальные клиенты ===");
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        var clients = orders.stream().map(Order::customer).distinct().sorted().collect(Collectors.toList());
        System.out.println(clients);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // --- Задание 3: Общая выручка ---
        System.out.println("\n=== Общая выручка ===");
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        double total = orders.stream().mapToDouble(Order::total).sum();
        System.out.printf("%.2f руб.%n", total);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // --- Задание 4: Самый дорогой заказ ---
        System.out.println("\n=== Самый дорогой заказ ===");
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        orders.stream().max(Comparator.comparingDouble(Order::total)).ifPresent(System.out::println);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // --- Задание 5: Количество заказов в каждой категории ---
        System.out.println("\n=== Заказы по категориям ===");
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        var byCategory = orders.stream().collect(Collectors.groupingBy(Order::category, Collectors.counting()));
        byCategory.forEach((cat, count) -> System.out.println(cat + ": " + count));
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // --- Задание 6: Средняя стоимость заказа по каждому клиенту ---
        System.out.println("\n=== Средняя стоимость по клиентам ===");
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        var avgByClient = orders.stream().collect(Collectors.groupingBy(Order::customer, Collectors.averagingDouble(Order::total)));
        avgByClient.forEach((client, avg) -> System.out.printf("%s: %.2f руб.%n", client, avg));
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // --- Задание 7: Дорогие (total > 3000) и дешёвые заказы ---
        System.out.println("\n=== Дорогие vs дешёвые ===");
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        var partitioned = orders.stream().collect(Collectors.partitioningBy(o -> o.total() > 3000));
        System.out.println("Дорогие: " + partitioned.get(true).size());
        System.out.println("Дешёвые: " + partitioned.get(false).size());
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲

        // --- Задание 8: Товары дороже 1000, без дубликатов, в верхнем регистре ---
        System.out.println("\n=== Товары дороже 1000 (uppercase) ===");
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        var products = orders.stream().filter(o -> o.price() > 1000).map(Order::product).distinct().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(products);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
