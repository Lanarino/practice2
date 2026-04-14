package part6.part6_1;

import java.lang.annotation.*;
import java.lang.reflect.Method;

/**
 * Задание 6.1 (Часть A) — Исправьте мета-аннотации
 *
 * Тема: собственные аннотации и мета-аннотации.
 *
 * Ключевая теория:
 *   - Аннотация объявляется через @interface.
 *   - @Retention — когда аннотация доступна:
 *     SOURCE (только код), CLASS (байткод), RUNTIME (через Reflection).
 *   - @Target — к чему применяется:
 *     METHOD, FIELD, TYPE, PARAMETER.
 *   - Элемент аннотации: тип имя() default значение;
 *
 * Задача: исправьте значения @Retention и @Target,
 * чтобы аннотация была доступна через Reflection и применялась к методам.
 *
 * Подсказка: запустите программу сначала как есть — аннотации НЕ будут найдены.
 * Затем исправьте две строки и запустите снова.
 */

// TODO: замените SOURCE на правильную политику, чтобы аннотация была доступна через Reflection
// Подсказка: SOURCE → стирается при компиляции, CLASS → только в байткоде, RUNTIME → доступна через Reflection
@Retention(RetentionPolicy.RUNTIME)
// Вопрос: почему здесь указано METHOD? Что будет, если заменить на TYPE или FIELD?
@Target(ElementType.METHOD)
@interface TestInfo {
    String author();
    String date();
    String description() default "";
    int priority() default 5;
}

/**
 * Демонстрационный класс с аннотированными методами.
 * После заполнения пропусков программа выведет информацию об аннотациях.
 */
class Calculator {

    @TestInfo(author = "Иван", date = "2024-01-15", description = "Тест сложения", priority = 1)
    public void testAdd() {
        System.out.println("  2 + 3 = " + (2 + 3));
    }

    @TestInfo(author = "Мария", date = "2024-01-16", priority = 3)
    public void testMultiply() {
        System.out.println("  4 * 5 = " + (4 * 5));
    }

    /**
     * Точка входа. Использует Reflection для чтения аннотаций.
     */
    public static void main(String[] args) throws Exception {
        Calculator calc = new Calculator();

        System.out.println("=== Анализ аннотаций @TestInfo ===\n");

        for (Method method : Calculator.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(TestInfo.class)) {
                TestInfo info = method.getAnnotation(TestInfo.class);
                System.out.println("Метод: " + method.getName());
                System.out.println("  Автор:    " + info.author());
                System.out.println("  Дата:     " + info.date());
                System.out.println("  Описание: " + info.description());
                System.out.println("  Приоритет: " + info.priority());
                System.out.print("  Результат: ");
                method.invoke(calc);
                System.out.println();
            }
        }
    }
}
