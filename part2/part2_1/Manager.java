package part2.part2_1;

/**
 * Задание 2.1 — Менеджер (подкласс Employee)
 *
 * Формула бонуса: baseSalary * 0.15 + teamSize * 5000
 *
 * Подсказка для конструктора: вызовите super(name, baseSalary),
 * затем сохраните teamSize в поле.
 */
public class Manager extends Employee {

    /** Количество человек в команде менеджера. */
    private int teamSize;

    public Manager(String name, double baseSalary, int teamSize) {
        super(name, baseSalary);
        // TODO: сохраните teamSize в поле
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        this.teamSize = teamSize;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Бонус менеджера = baseSalary * 0.15 + teamSize * 5000.
     *
     * Пример: оклад 120000, команда 5 → бонус = 18000 + 25000 = 43000.
     */
    @Override
    public double calculateBonus() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return getBaseSalary() * 0.15 + teamSize * 5000; // TODO: верните baseSalary * 0.15 + teamSize * 5000
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
