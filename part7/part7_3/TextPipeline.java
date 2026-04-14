package part7.part7_3;

import java.util.*;
import java.util.function.Function;

/**
 * Задание 7.3 — Композиция функций и локальный класс
 *
 * Тема: Function<T, R>, композиция через andThen(), локальные классы.
 *
 * Ключевая теория:
 *   - Function<T, R> — функциональный интерфейс: принимает T, возвращает R.
 *   - f.andThen(g) = сначала f, потом g (f → g).
 *   - f.compose(g) = сначала g, потом f (g → f).
 *   - Локальный класс — класс, объявленный внутри метода. Виден только в этом методе.
 *
 * Как запустить: нажмите ▶ рядом с main.
 */
public class TextPipeline {

    public static void main(String[] args) {

        // === Часть A: Композиция функций ===
        // ▼ ВАШ КОД ЗДЕСЬ (Часть A) ▼

        // TODO: создайте 4 функции Function<String, String>:
        Function<String, String> trim = String::trim;
        Function<String, String> lower = String::toLowerCase;
        Function<String, String> removeExtraSpaces = s -> s.replaceAll("\\s+", " ");
        Function<String, String> capitalize = s -> s.isEmpty() ? s : Character.toUpperCase(s.charAt(0)) + s.substring(1);

        // TODO: скомпонуйте в одну функцию:
        var normalize = trim.andThen(lower).andThen(removeExtraSpaces).andThen(capitalize);

        // TODO: примените к нескольким строкам:
        String[] testStrings = {
            "  пРИВЕТ    МИР  ",
            "   jAVA   пРОГРАММИРОВАНИЕ   ",
            "ТЕСТ"
        };
        for (String s : testStrings) System.out.println("\"" + s + "\" → \"" + normalize.apply(s) + "\"");

        // ▲ КОНЕЦ ВАШЕГО КОДА (Часть A) ▲

        // === Часть B: Локальный класс ===
        // ▼ ВАШ КОД ЗДЕСЬ (Часть B) ▼

        // TODO: объявите локальный класс WordCounter прямо здесь, внутри main:
        class WordCounter {
            private final String text;
            WordCounter(String text) { this.text = text; }

            Map<String, Integer> count() {
                Map<String, Integer> map = new HashMap<>();
                for (String word : text.split(" ")) {
                    if (!word.isEmpty()) {
                        map.merge(word.toLowerCase(), 1, Integer::sum);
                    }
                }
                return map;
            }

            String mostFrequent() {
                return count().entrySet()
                        .stream()
                        .max(Map.Entry.comparingByValue())
                        .map(Map.Entry::getKey)
                        .orElse(null);
            }
        }

        // TODO: используйте WordCounter для анализа нормализованной строки:
        var wc = new WordCounter(normalize.apply("  java java PYTHON  java python  "));
        System.out.println("Частоты: " + wc.count());
        System.out.println("Самое частое: " + wc.mostFrequent());
        // ▲ КОНЕЦ ВАШЕГО КОДА (Часть B) ▲
    }
}
