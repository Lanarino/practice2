package part2.part2_1;

/**
 * Задание 2.1 — Разработчик (подкласс Employee)
 *
 * Формула бонуса: baseSalary * 0.12
 *
 * Подсказка для конструктора: вызовите super(name, baseSalary),
 * затем сохраните language.
 */
public class Developer extends Employee {

    /** Основной язык программирования. */
    private String language;

    public Developer(String name, double baseSalary, String language) {
        super(name, baseSalary);
        // TODO: сохраните language в поле
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        this.language = language;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Бонус разработчика = baseSalary * 0.12.
     *
     * Пример: оклад 95000 → бонус = 11400.
     */
    @Override
    public double calculateBonus() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return getBaseSalary() * 0.12; // TODO: верните baseSalary * 0.12
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
