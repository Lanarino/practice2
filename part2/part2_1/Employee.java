package part2.part2_1;

/**
 * Задание 2.1 — Иерархия сотрудников (абстрактный класс)
 *
 * Тема: абстрактные классы и полиморфизм.
 *
 * Ключевая теория:
 *   - Абстрактный класс — класс с модификатором abstract. Нельзя создать
 *     его экземпляр напрямую (new Employee() — ошибка компиляции).
 *   - Абстрактный метод — метод без тела. Каждый подкласс обязан его реализовать.
 *   - Обычные (не абстрактные) методы абстрактного класса наследуются как есть.
 *   - Паттерн «шаблонный метод»: totalCompensation() вызывает
 *     calculateBonus(), но реализация бонуса зависит от подкласса.
 *
 * Примечание: запускать нужно EmployeeBonus.java, а не этот файл.
 */
public abstract class Employee {

    /** Имя сотрудника. protected — доступно в подклассах. */
    protected String name;

    /** Базовый оклад. protected — доступно в подклассах. */
    protected double baseSalary;

    /**
     * Конструктор абстрактного класса.
     *
     * Подсказка: сохраните параметры в поля:
     * this.name = name; this.baseSalary = baseSalary;
     *
     * Важно: конструктор абстрактного класса вызывается из подклассов
     * через super(name, baseSalary).
     */
    public Employee(String name, double baseSalary) {
        // TODO: сохраните параметры в поля:
        //   this.name = name;
        //   this.baseSalary = baseSalary;
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        this.name = name;
        this.baseSalary = baseSalary;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public String getName() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return name; // TODO: верните name
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    public double getBaseSalary() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return baseSalary; // TODO: верните baseSalary
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Вычисляет бонус сотрудника. Абстрактный метод — каждый подкласс
     * определяет свою формулу.
     *
     * @return сумма бонуса
     */
    public abstract double calculateBonus();

    /**
     * Полная компенсация = оклад + бонус.
     *
     * Подсказка: return baseSalary + calculateBonus();
     *
     * Этот метод работает одинаково для всех подклассов благодаря полиморфизму:
     * calculateBonus() вызовет версию конкретного подкласса.
     *
     * @return базовый оклад + бонус
     */
    public double totalCompensation() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return baseSalary + calculateBonus(); // TODO: верните baseSalary + calculateBonus()
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Формат: "Ольга | Оклад: 120000.0 | Бонус: 43000.0 | Итого: 163000.0"
     *
     * Подсказка:
     * return name + " | Оклад: " + baseSalary + " | Бонус: " + calculateBonus() + " | Итого: " + totalCompensation();
     */
    @Override
    public String toString() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return name + " | Оклад: " + baseSalary + " | Бонус: " + calculateBonus() + " | Итого: " + totalCompensation(); // TODO: верните name + " | Оклад: " + baseSalary + " | Бонус: " + calculateBonus() + " | Итого: " + totalCompensation()
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
