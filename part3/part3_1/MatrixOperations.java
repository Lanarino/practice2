package part3.part3_1;

/**
 * Задание 3.1 — Операции с матрицами
 *
 * Тема: двумерные массивы (int[][]).
 *
 * Ключевая теория:
 *   - Двумерный массив — массив массивов. matrix[i][j] =
 *     элемент строки i, столбца j.
 *   - matrix.length — число строк, matrix[0].length — число столбцов.
 *   - Транспонирование: строки ↔ столбцы. result[j][i] = matrix[i][j].
 *   - Умножение матриц A(m×n) × B(n×p) = C(m×p):
 *     C[i][j] = Σ(A[i][k] × B[k][j]) для k от 0 до n-1.
 *   - Умножение возможно только если число столбцов A = числу строк B.
 *
 * Как запустить: нажмите ▶ рядом с main.
 *
 * Ожидаемый вывод:
 *
 * Матрица A (2x3):
 *    1   2   3
 *    4   5   6
 *
 * Транспонированная A (3x2):
 *    1   4
 *    2   5
 *    3   6
 *
 * Матрица B (3x2):
 *    7   8
 *    9  10
 *   11  12
 *
 * A * B (2x2):
 *   58  64
 *  139 154
 *
 * Сумма диагонали A*B: 212
 */
public class MatrixOperations {

    /**
     * Выводит матрицу в отформатированном виде (каждое число шириной 4 символа).
     *
     * Подсказка: два вложенных цикла. Для форматирования:
     * System.out.printf("%4d", matrix[i][j]);
     * После каждой строки: System.out.println();
     */
    public static void print(int[][] matrix) {
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        for (int[] row : matrix) {
            for (int v : row) {
                System.out.printf("%4d", v);
            }
            System.out.println();
        }
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
    }

    /**
     * Возвращает транспонированную матрицу (строки ↔ столбцы).
     *
     * Алгоритм:
     *   1. Определите размеры: rows = matrix.length, cols = matrix[0].length.
     *   2. Создайте новый массив: int[][] result = new int[cols][rows];
     *   3. Заполните: result[j][i] = matrix[i][j];
     */
    public static int[][] transpose(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[][] result = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = matrix[i][j];
            }
        }
        return result;

    }

    /**
     * Умножает две матрицы. Если размеры несовместимы — выводит ошибку и возвращает null.
     *
     * Алгоритм:
     *   1. Проверьте: a[0].length == b.length (столбцы A = строки B).
     *      Если нет — выведите ошибку и верните null.
     *   2. Создайте результат: int[a.length][b[0].length].
     *   3. Тройной цикл (i, j, k): result[i][j] += a[i][k] * b[k][j];
     */
    public static int[][] multiply(int[][] a, int[][] b) {
        if (a[0].length != b.length) {
            System.out.println("Ошибка: несовместимые размеры матриц");
            return null;
        }
        int m = a.length;
        int n = b[0].length;
        int p = b.length;
        int[][] result = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < p; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return result;
    }

    /**
     * Возвращает сумму элементов главной диагонали.
     *
     * Подсказка: один цикл: sum += matrix[i][i];
     */
    public static int diagonalSum(int[][] matrix) {
        int sum = 0;
        // ▼ ВАШ КОД ЗДЕСЬ ▼
        int n = Math.min(matrix.length, matrix[0].length);
        for (int i = 0; i < n; i++) {
        sum += matrix[i][i];
        }
        // ▲ КОНЕЦ ВАШЕГО КОДА ▲
        return sum;
    }

    // === Метод main (дан — НЕ ИЗМЕНЯЙТЕ) ===

    public static void main(String[] args) {
        int[][] a = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] b = {
            {7,  8},
            {9,  10},
            {11, 12}
        };

        System.out.println("Матрица A (2x3):");
        print(a);

        System.out.println("\nТранспонированная A (3x2):");
        print(transpose(a));

        System.out.println("\nМатрица B (3x2):");
        print(b);

        int[][] c = multiply(a, b);
        System.out.println("\nA * B (2x2):");
        print(c);

        System.out.println("\nСумма диагонали A*B: " + diagonalSum(c));
    }
}
