package part6.part6_1;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Задание 6.1 (Часть B) — Мини-фреймворк валидации с аннотациями и Reflection
 *
 * Тема: Reflection API, собственные аннотации.
 *
 * Ключевая теория:
 *   - obj.getClass().getDeclaredFields() — получить все поля объекта.
 *   - field.setAccessible(true) — разрешить чтение private-полей.
 *   - field.isAnnotationPresent(NotEmpty.class) — проверить наличие аннотации.
 *   - field.getAnnotation(Range.class).min() — получить параметр аннотации.
 *
 * Ожидаемый вывод:
 *
 *     === Валидация корректной формы ===
 *     Все поля валидны!
 *
 *     === Валидация некорректной формы ===
 *       - Имя обязательно
 *       - Поле не может быть пустым
 *       - Возраст должен быть от 18 до 120
 */
public class ValidationFramework {

    // === Аннотация @NotEmpty ===

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface NotEmpty {
        String message() default "Поле не может быть пустым";
    }

    // === Аннотация @Range ===

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.FIELD)
    @interface Range {
        int min();
        int max();
        String message() default "Значение вне допустимого диапазона";
    }

    // === Класс с аннотированными полями ===

    static class RegistrationForm {
        @NotEmpty(message = "Имя обязательно")
        String name;

        @NotEmpty
        String email;

        @Range(min = 18, max = 120, message = "Возраст должен быть от 18 до 120")
        int age;

        RegistrationForm(String name, String email, int age) {
            this.name = name;
            this.email = email;
            this.age = age;
        }
    }

    // === Валидатор ===

    static class Validator {

        /**
         * Проверяет объект на соответствие аннотациям и возвращает список ошибок.
         *
         * Алгоритм:
         *   1. Создайте List<String> errors = new ArrayList<>()
         *   2. Получите все поля: obj.getClass().getDeclaredFields()
         *   3. Для каждого поля: field.setAccessible(true)
         *   4. Если поле аннотировано @NotEmpty:
         *      получите значение field.get(obj), приведите к String,
         *      проверьте == null || isEmpty() → добавьте сообщение в errors.
         *   5. Если поле аннотировано @Range:
         *      получите значение (int) field.get(obj),
         *      проверьте < min || > max → добавьте сообщение.
         *   6. Верните errors.
         */
        public static List<String> validate(Object obj) {

            List<String> errors = new ArrayList<>();
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    if (field.isAnnotationPresent(NotEmpty.class)) {
                        String s = (String) field.get(obj);
                        NotEmpty ann = field.getAnnotation(NotEmpty.class);
                        if (s == null || s.isEmpty()) {
                            errors.add(ann.message());
                        }
                    }
                    if (field.isAnnotationPresent(Range.class)) {
                        int v = field.getInt(obj);
                        Range ann = field.getAnnotation(Range.class);
                        if (v < ann.min() || v > ann.max()) {
                            errors.add(ann.message());
                        }
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            // ▼ ВАШ КОД ЗДЕСЬ ▼
            return errors; //???????????
            // ▲ КОНЕЦ ВАШЕГО КОДА ▲
        }
    }

    // === Метод main (дан — НЕ ИЗМЕНЯЙТЕ) ===

    public static void main(String[] args) {
        RegistrationForm valid = new RegistrationForm("Иван", "ivan@mail.ru", 25);
        RegistrationForm invalid = new RegistrationForm("", null, 15);

        System.out.println("=== Валидация корректной формы ===");
        List<String> errors1 = Validator.validate(valid);
        System.out.println(errors1.isEmpty() ? "Все поля валидны!" : errors1);

        System.out.println("\n=== Валидация некорректной формы ===");
        List<String> errors2 = Validator.validate(invalid);
        errors2.forEach(e -> System.out.println("  - " + e));
    }
}