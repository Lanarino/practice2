package part2.part2_1;

/**
 * Задание 2.1 — Стажёр (подкласс Employee)
 *
 * Формула бонуса: фиксированные 10000 рублей.
 *
 * Подсказка для конструктора: вызовите super(name, baseSalary).
 * Дополнительных полей нет.
 */
public class Intern extends Employee {

    public Intern(String name, double baseSalary) {
        super(name, baseSalary);
    }

    /**
     * Бонус стажёра = фиксированные 10000 рублей.
     */
    @Override
    public double calculateBonus() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return 10000; // TODO: верните 10000
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
