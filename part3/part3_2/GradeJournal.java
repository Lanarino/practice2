package part3.part3_2;

/**
 * Задание 3.2 — Зубчатый массив: журнал оценок
 *
 * Тема: зубчатые массивы (jagged arrays).
 *
 * Ключевая теория:
 *   - Зубчатый массив — двумерный массив, в котором каждая строка
 *     может иметь разную длину.
 *   - grades[i].length — количество оценок i-го студента.
 *
 * Ожидаемый вывод:
 *
 * === Журнал оценок ===
 * Алиса   | Оценок: 5 | Средний: 4.40 | Мин: 3 | Макс: 5
 * Борис   | Оценок: 3 | Средний: 3.33 | Мин: 3 | Макс: 4
 * Вера    | Оценок: 6 | Средний: 4.83 | Мин: 4 | Макс: 5
 * Глеб    | Оценок: 4 | Средний: 4.00 | Мин: 3 | Макс: 5
 *
 * Лучший студент: Вера (средний балл: 4.83)
 */
public class GradeJournal {

    // === Данные (уже заполнены) ===

    static String[] names = {"Алиса", "Борис", "Вера", "Глеб"};

    static int[][] grades = {
        {5, 4, 5, 5, 3},       // Алиса
        {3, 3, 4},             // Борис
        {5, 5, 5, 5, 5, 4},   // Вера
        {4, 3, 4, 5}           // Глеб
    };

    /**
     * Вычисляет средний балл.
     *
     * Подсказка: просуммируйте все элементы, разделите на grades.length.
     * Не забудьте привести к double перед делением.
     */
    public static double average(int[] grades) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        int sum = 0; // TODO: просуммируйте все элементы, разделите на (double) grades.length
        for (int grade : grades) {
            sum += grade;
        }
        return (double) sum / grades.length;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Находит максимальную оценку.
     *
     * Подсказка: начните с grades[0], пройдите циклом
     * и обновляйте максимум.
     */
    public static int max(int[] grades) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        int max = grades[0]; // TODO: начните с grades[0], пройдите циклом, обновляйте максимум
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] > max) {
                max = grades[i];
            }
        }
        return max;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Находит минимальную оценку.
     *
     * Подсказка: начните с grades[0], пройдите циклом
     * и обновляйте минимум (if (grades[i] < min) min = grades[i];).
     */
    public static int min(int[] grades) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        int min = grades[0];// TODO: начните с grades[0], пройдите циклом, обновляйте минимум
        for (int i = 1; i < grades.length; i++) {
            if (grades[i] < min) {
                min = grades[i];
            }
        }
        return min;
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    // === Метод main ===

    public static void main(String[] args) {
        System.out.println("=== Журнал оценок ===");

        // ▼ ВАШ КОД ЗДЕСЬ ▼
        for (int i = 0; i < names.length; i++) {
            System.out.printf("%-8s| Оценок: %d | Средний: %.2f | Мин: %d | Макс: %d%n",
                    names[i], grades[i].length, average(grades[i]), min(grades[i]), max(grades[i]));
        }
        String bestName = names[0];
        double bestAvg = average(grades[0]);
        for (int i = 1; i < names.length; i++) {
            double avg = average(grades[i]);
            if (avg > bestAvg) {
                bestAvg = avg;
                bestName = names[i];
            }
        }
        System.out.printf("%nЛучший студент: %s (средний балл: %.2f)%n", bestName, bestAvg);
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
