package part2.part2_2;

/**
 * Задание 2.2 — Кредитная карта (реализация PaymentMethod)
 *
 * Подсказка: record автоматически генерирует конструктор,
 * геттеры cardNumber() и holder(), а также
 * equals(), hashCode() и toString().
 *
 * Для получения последних 4 цифр номера карты:
 * cardNumber.substring(cardNumber.length() - 4)
 */
public record CreditCard(String cardNumber, String holder) implements PaymentMethod {

    /**
     * "Оплата картой *XXXX: Z руб." (последние 4 цифры номера).
     *
     * Подсказка:
     * return "Оплата картой *" + cardNumber.substring(cardNumber.length() - 4) + ": " + amount + " руб.";
     */
    @Override
    public String process(double amount) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return "Оплата картой *" + cardNumber.substring(cardNumber.length() - 4) + ": " + amount + " руб."; // TODO: верните "Оплата картой *" + cardNumber.substring(cardNumber.length()-4) + ": " + amount + " руб."
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Комиссия = 2% от суммы.
     *
     * Подсказка: return amount * 0.02;
     */
    @Override
    public double fee(double amount) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return amount * 0.02; // TODO: верните amount * 0.02
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
