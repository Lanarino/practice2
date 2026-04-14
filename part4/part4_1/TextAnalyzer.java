package part4.part4_1;

/**
 * Задание 4.1 — Анализатор текста
 *
 * Тема: работа со строками (String, StringBuilder).
 *
 * Ключевая теория:
 *   - String — неизменяемый (immutable). Каждая операция создаёт новый объект.
 *   - split(" ") — разбивает строку на массив слов по пробелу.
 *   - toLowerCase() — преобразует строку в нижний регистр
 *     (полезно для регистронезависимого поиска).
 *   - StringBuilder — изменяемый буфер, эффективнее для конкатенации в цикле.
 *   - Палиндром — строка, которая читается одинаково слева направо и справа налево.
 *
 * Ожидаемый вывод:
 *
 * Текст: Java Programming is Fun and Java is Powerful
 * Слов: 8
 * Самое длинное слово: Programming
 * Слова наоборот: Powerful is Java and Fun is Programming Java
 * 'Java' встречается: 2 раз(а)
 * 'is' встречается: 2 раз(а)
 * Палиндром: false
 *
 * Текст: А роза упала на лапу Азора
 * Палиндром: true
 */
public class TextAnalyzer {

    /** Исходный текст для анализа. */
    private final String text;

    /**
     * Присвойте параметр полю text (поле final — иначе класс не скомпилируется).
     */
    public TextAnalyzer(String text) {
        this.text = text;
    }

    /**
     * Возвращает количество слов (разделённых пробелами).
     *
     * Подсказка: return text.split(" ").length;
     */
    public int wordCount() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return text.split(" ").length; // TODO: верните text.split(" ").length
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Возвращает самое длинное слово.
     *
     * Подсказка: разбейте на слова через split(" "),
     * пройдите циклом, сравнивая длины.
     */
    public String longestWord() {

        String[] words = text.split(" ");
        String best = words[0];
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        for (String word : words) {
            if (word.length() > best.length()) {
                best = word;
            }
        }
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
        return best;

    }

    /**
     * Возвращает текст с обратным порядком слов (не букв!).
     * Пример: "Привет мир" → "мир Привет"
     *
     * Подсказка: split(" "), создайте StringBuilder,
     * добавляйте слова от последнего к первому.
     */
    public String reverseWords() {
        String[] words = text.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            if (i < words.length - 1) {
                sb.append(' ');
            }
            sb.append(words[i]);
        }
        return sb.toString();

    }

    /**
     * Считает, сколько раз подстрока встречается в тексте (без учёта регистра).
     *
     * Алгоритм:
     *   1. Приведите оба текста к нижнему регистру: toLowerCase().
     *   2. В цикле ищите: index = lower.indexOf(targetLower, fromIndex).
     *   3. Пока index != -1: увеличьте счётчик, сдвиньте fromIndex = index + 1.
     */
    public int countOccurrences(String target) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        String lower = text.toLowerCase();
        String t = target.toLowerCase();
        int count = 0;
        int from = 0;
        int idx;
        while ((idx = lower.indexOf(t, from)) != -1) {
            count++;
            from = idx + 1;
        }
        return count; // ???
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Проверяет, является ли текст палиндромом.
     *
     * Алгоритм:
     *   1. Удалите всё кроме букв: replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "")
     *   2. Приведите к нижнему регистру.
     *   3. Сравните с реверсом: new StringBuilder(clean).reverse().toString()
     */
    public boolean isPalindrome() {

        String clean = text.replaceAll("[^a-zA-Zа-яА-ЯёЁ]", "").toLowerCase();
        return clean.contentEquals(new StringBuilder(clean).reverse());

    }

    @Override
    public String toString() {
        return text;
    }

    // === Метод main (дан — НЕ ИЗМЕНЯЙТЕ) ===

    public static void main(String[] args) {
        TextAnalyzer ta = new TextAnalyzer("Java Programming is Fun and Java is Powerful");

        System.out.println("Текст: " + ta);
        System.out.println("Слов: " + ta.wordCount());
        System.out.println("Самое длинное слово: " + ta.longestWord());
        System.out.println("Слова наоборот: " + ta.reverseWords());
        System.out.println("'Java' встречается: " + ta.countOccurrences("java") + " раз(а)");
        System.out.println("'is' встречается: " + ta.countOccurrences("is") + " раз(а)");
        System.out.println("Палиндром: " + ta.isPalindrome());

        System.out.println();

        TextAnalyzer palindrome = new TextAnalyzer("А роза упала на лапу Азора");
        System.out.println("Текст: " + palindrome);
        System.out.println("Палиндром: " + palindrome.isPalindrome());
    }
}
