package part2.part2_2;

/**
 * Задание 2.2 — Обработчик платежей с паттерн-матчингом
 *
 * Тема: switch с паттерн-матчингом (Java 21+).
 *
 * Ключевая теория:
 *   - В Java 21 switch может проверять тип объекта: case CreditCard cc -> ...
 *   - При работе с sealed-интерфейсом компилятор проверяет,
 *     что switch покрывает ВСЕ допустимые типы — default не нужен.
 *
 * Пример синтаксиса:
 *
 *   switch (pm) {
 *       case CreditCard cc   -> System.out.println("Карта: " + cc.holder());
 *       case BankTransfer bt -> System.out.println("Банк: " + bt.bankName());
 *       case CryptoWallet cw -> System.out.println("Крипто: " + cw.currency());
 *   }
 */
public class PaymentProcessor {

    /**
     * Выводит подробное описание способа оплаты с помощью switch.
     *
     * Алгоритм: используйте switch с паттерн-матчингом.
     * Для каждого типа выведите специфическую информацию
     * (держатель карты, название банка, адрес кошелька и т.д.).
     *
     * @param pm способ оплаты
     */
    public static void describe(PaymentMethod pm) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        switch (pm) {
            case CreditCard cc -> System.out.println("Карта: " + cc.holder());
            case BankTransfer bt -> System.out.println("Банк: " + bt.bankName());
            case CryptoWallet cw -> System.out.println("Кошелёк: " + cw.address());
        }
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
