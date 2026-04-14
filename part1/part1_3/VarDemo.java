package part1.part1_3;

import java.util.ArrayList;
import java.util.Arrays;

import part1.part1_1.BankAccount;

/**
 * Задание 1.3 — Ключевое слово var
 *
 * Тема: вывод типа локальных переменных (Java 10+).
 *
 * Ключевая теория:
 *   - var — НЕ динамическая типизация. Компилятор определяет тип из правой части
 *     и фиксирует его навсегда.
 *   - var работает ТОЛЬКО для локальных переменных с инициализатором.
 *   - Нельзя использовать: для полей класса, параметров методов, без init, с null.
 *   - Проверка типа: ((Object) variable).getClass().getSimpleName()
 *
 * Как запустить: нажмите ▶ рядом с main.
 *
 * Ожидаемый вывод (примерный):
 *   42 -> Integer
 *   Java -> String
 *   [один, два] -> ArrayList
 *   [1, 2, 3] -> int[]
 *   BankAccount -> BankAccount
 */
public class VarDemo {

    // var field = 10;
    // ↑ Не компилируется: var нельзя использовать для полей класса.

    public static void main(String[] args) {

        // === Рабочие примеры var ===

        // TODO: Пример 1 — целое число
        //   var number = 42;
         //  System.out.println(number + " -> " + ((Object) number).getClass().getSimpleName());

        // TODO: Пример 2 — строка
          // var text = "Java";
          // System.out.println(text + " -> " + text.getClass().getSimpleName());

        // TODO: Пример 3 — ArrayList
        //   var list = new ArrayList<>(Arrays.asList("один", "два"));
        //   System.out.println(list + " -> " + list.getClass().getSimpleName());

        // TODO: Пример 4 — массив
        // var array = new int[]{1, 2, 3};
        //   System.out.println(Arrays.toString(array) + " -> " + array.getClass().getSimpleName());

        // TODO: Пример 5 — ваш собственный объект (например, BankAccount)
        //   var account = new BankAccount("Тест", 0);
        //   System.out.println(account.getClass().getSimpleName() + " -> " + account.getClass().getSimpleName());

        // TODO: раскомментируйте примеры выше по одному и запустите программу.

        // === Случаи, где var НЕ компилируется ===

        // 1. Без инициализации:  var x;            // нельзя определить тип
        // 2. Как параметр:       void test(var p)   // параметры требуют явный тип
        // 3. Как поле класса:    (показано выше)
        // 4. С null:             var nothing = null; // null не имеет типа
    }
}
