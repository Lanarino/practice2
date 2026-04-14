package part5.part5_2;

/**
 * Задание 5.2 — Record с бизнес-логикой и Enum с абстрактным методом
 *
 * Тема: record с методами, enum с индивидуальной реализацией.
 *
 * Ключевая теория:
 *   - Record может содержать произвольные методы (не только геттеры).
 *   - Enum может объявить абстрактный метод — каждая константа обязана его реализовать.
 *   - Формулы конверсии температуры:
 *     C→F: C × 9/5 + 32;
 *     C→K: C + 273.15;
 *     F→C: (F − 32) × 5/9;
 *     K→C: K − 273.15.
 *   - Абсолютный ноль = 0 K = −273.15 °C = −459.67 °F.
 *
 * Как запустить: нажмите ▶ рядом с main.
 */
public class RecordEnumDemo {

    // === Record Temperature ===

    /**
     * Температура с единицей измерения.
     *
     * Задача:
     *   1. В компактном конструкторе проверьте, что значение не ниже абсолютного нуля.
     *   2. Реализуйте convertTo() для конверсии между единицами.
     *   3. Переопределите toString(): "36.6 °C", "97.88 °F", "309.75 K".
     */
    record Temperature(double value, Unit unit) {

        enum Unit { CELSIUS, FAHRENHEIT, KELVIN }

        Temperature {
            // TODO: переведите value в kelvin и проверьте >= 0
            // Алгоритм:
            //   1. Вычислите double kelvin = switch (unit) {
            //        case CELSIUS    -> value + 273.15;
            //        case FAHRENHEIT -> (value - 32) * 5.0/9.0 + 273.15;
            //        case KELVIN     -> value;
            //      };
            //   2. if (kelvin < 0) throw new IllegalArgumentException("Ниже абсолютного нуля");
            // ▼ ВАШ КОД ЗДЕСЬ ▼
            double kelvin = switch (unit) {
                case CELSIUS -> value + 273.15;
                case FAHRENHEIT -> (value - 32) * 5.0 / 9.0 + 273.15;
                case KELVIN -> value;
            };
            if (kelvin < 0) {
                throw new IllegalArgumentException("Ниже абсолютного нуля");
            }
            // ▲ КОНЕЦ ВАШЕГО КОДА ▲
        }

        /**
         * Конвертирует температуру в другую единицу.
         *
         * Алгоритм:
         *   1. Переведите текущее значение в Цельсии (промежуточный шаг).
         *   2. Из Цельсия переведите в целевую единицу.
         */
        public Temperature convertTo(Unit targetUnit) {
            // ▼ ВАШ КОД ЗДЕСЬ ▼
            double celsius = switch (unit) {
                case CELSIUS -> value;
                case FAHRENHEIT -> (value - 32) * 5.0 / 9.0;
                case KELVIN -> value - 273.15;
            };
            double result = switch (targetUnit) {
                case CELSIUS -> celsius;
                case FAHRENHEIT -> celsius * 9.0 / 5.0 + 32;
                case KELVIN -> celsius + 273.15;
            };
            return new Temperature(result, targetUnit);
            // ▲ КОНЕЦ ВАШЕГО КОДА ▲
        }

        /**
         * Формат: "36.60 °C" или "97.88 °F" или "309.75 K".
         *
         * Подсказка: switch по unit: CELSIUS → "°C", FAHRENHEIT → "°F", KELVIN → "K".
         */
        @Override
        public String toString() {
            // ▼ ВАШ КОД ЗДЕСЬ ▼
            String suffix = switch (unit) {
                case CELSIUS -> "°C";
                case FAHRENHEIT -> "°F";
                case KELVIN -> "K";
            };
            return String.format("%.2f %s", value, suffix);
            // ▲ КОНЕЦ ВАШЕГО КОДА ▲
        }
    }

    // === Enum MathOperation ===

    /**
     * Математическая операция с абстрактным методом.
     *
     * Синтаксис enum с абстрактным методом:
     *
     *     enum MathOperation {
     *         ADD {
     *             public double apply(double a, double b) { return a + b; }
     *         },
     *         SUBTRACT {
     *             public double apply(double a, double b) { return a - b; }
     *         };
     *         public abstract double apply(double a, double b);
     *     }
     */
    enum MathOperation {
        ADD {
            @Override
            public double apply(double a, double b) {
                return a+b; // TODO: верните a + b
            }
        },
        SUBTRACT {
            @Override
            public double apply(double a, double b) {
                return a-b; // TODO: верните a - b
            }
        },
        MULTIPLY {
            @Override
            public double apply(double a, double b) {
                return a*b; // TODO: верните a * b
            }
        },
        DIVIDE {
            @Override
            public double apply(double a, double b) {
                // TODO: проверьте b != 0, иначе throw new ArithmeticException("Деление на ноль")
                if (b == 0) { // TODO: верните a / b (с проверкой на ноль)
                    throw new ArithmeticException("Деление на ноль");
                }
                return a / b;
            }
        };

        public abstract double apply(double a, double b);
    }

    // === Метод main (дан — НЕ ИЗМЕНЯЙТЕ) ===

    public static void main(String[] args) {
        Temperature body = new Temperature(36.6, Temperature.Unit.CELSIUS);
        System.out.println(body);
        System.out.println(body.convertTo(Temperature.Unit.FAHRENHEIT));
        System.out.println(body.convertTo(Temperature.Unit.KELVIN));

        System.out.println();

        double a = 10, b = 3;
        for (MathOperation op : MathOperation.values()) {
            System.out.printf("%s(%g, %g) = %g%n", op.name(), a, b, op.apply(a, b));
        }
    }
}
