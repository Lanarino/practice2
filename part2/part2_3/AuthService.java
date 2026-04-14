package part2.part2_3;

/**
 * Задание 2.3 — Сервис аутентификации (реализация Loggable)
 */
public class AuthService implements Loggable {

    /**
     * Подсказка: return "AuthService";
     */
    @Override
    public String getComponentName() {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        return "AuthService"; // TODO: верните "AuthService"
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Логирует попытку входа.
     *
     * Алгоритм:
     *   1. Если success: log("Вход пользователя: " + username + " — успешно");
     *   2. Если не success: logError("Вход пользователя: " + username + " — отказано");
     */
    public void login(String username, boolean success) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        if (success) {
            log("Вход пользователя: " + username + " — успешно");
        } else {
            logError("Вход пользователя: " + username + " — отказано");
        }
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }
}
