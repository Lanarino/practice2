package part2.part2_3;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Задание 2.3 — Интерфейс Loggable с default, static и private методами
 *
 * Тема: эволюция интерфейсов в Java (8, 9+).
 *
 * Ключевая теория:
 *   - abstract метод — без тела, обязателен к реализации.
 *   - default метод — с реализацией по умолчанию, можно переопределить.
 *   - static метод — принадлежит интерфейсу, вызывается через Loggable.getLogLevel().
 *   - private метод (Java 9+) — вспомогательный, используется внутри default-методов.
 */
public interface Loggable {

    /**
     * Возвращает имя компонента. Каждый класс, реализующий Loggable,
     * должен реализовать этот метод.
     */
    String getComponentName();

    /**
     * Логирует сообщение.
     *
     * Формат: [14:30:15] [DatabaseService] Подключение установлено
     *
     * Подсказка:
     * System.out.println("[" + formatTimestamp() + "] [" + getComponentName() + "] " + message);
     */
    default void log(String message) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        System.out.println("[" + formatTimestamp() + "] [" + getComponentName() + "] " + message);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Логирует сообщение об ошибке (с префиксом "ОШИБКА: ").
     *
     * Подсказка: вызовите log("ОШИБКА: " + message);
     */
    default void logError(String message) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
      log("ОШИБКА: " + message);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Форматирует текущее время в формате "HH:mm:ss".
     *
     * Подсказка:
     * return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
     */
    private String formatTimestamp() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")); // TODO: верните LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Возвращает уровень логирования. Статический метод интерфейса —
     * вызывается через Loggable.getLogLevel().
     *
     * Подсказка: return "INFO";
     */
    static String getLogLevel() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return "INFO"; // TODO: верните "INFO"
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
