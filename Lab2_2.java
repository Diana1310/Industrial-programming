// Ковальчук Диана Владимировна, 5 группа
// 5. Среди столбцов заданной  матрицы, 
// содержащих только такие элементы, которые по модулю не больше n, 
// найти столбец с минимальным произведением элементов.
// 
// Алгоритм
// Проходим по каждому столбцу матрицы.
// Для каждого столбца необходимо:
// 1. Проверить, все ли элементы в этом столбце по модулю меньше или равны n.
// (Если столбец не удовлетворяет этому условию, он игнорируется.
// Если удовлетворяет, вычисляется произведение всех его элементов.)
// Нахождим минимальное произведение
// Сравниваем произведение текущего столбца с минимальным найденным ранее.
// Если текущее произведение меньше, обновляем минимальное значение и запоминаем индекс столбца.
// (Я СЧИТАЮ, что отсчет столбцов начинается с 1, то есть если столбцов 3, то считаю не 0, 1, 2, а 1, 2, 3)
//
// Пример
// Введите количество строк матрицы: 
// 3
// Введите количество столбцов матрицы: 
// 3
// Сгенерированная матрица:
// [-7, -2, -8]
// [-8, 10, 2]
// [7, -3, 4]
// Введите значение n: 
// 10
// Столбец с минимальным произведением элементов: 3
// Минимальное произведение: -64

import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


class Matrix {
    private Vector<Vector<Integer>> data; 
    private int rows;                      // Количество строк
    private int cols;                      // Количество столбцов

    // Конструктор для инициализации матрицы
    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.data = new Vector<>(rows);
        generateRandomData(); 
    }

    // Метод для генерации случайных данных для матрицы
    private void generateRandomData() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            Vector<Integer> row = new Vector<>(cols);
            for (int j = 0; j < cols; j++) {
                row.add(random.nextInt(21) - 10); // Случайные числа от -10 до 10
            }
            data.add(row); // Добавление строки в матрицу
        }
    }

    // Метод для отображения матрицы
    public void display() {
        System.out.println("Сгенерированная матрица:");
        for (Vector<Integer> row : data) {
            System.out.println(row); 
        }
    }

    // Метод для получения элемента матрицы по индексу
    public int getElement(int row, int col) {
        return data.get(row).get(col); // Возврат элемента по строке и столбцу
    }

    // Метод для получения количества строк
    public int getRows() {
        return rows;
    }

    // Метод для получения количества столбцов
    public int getCols() {
        return cols;
    }

    // Метод для нахождения столбца с минимальным произведением
    public void findMinProductColumn(int n) {
        int minProduct = Integer.MAX_VALUE;  // Переменная для минимального произведения
        int minColumnIndex = -1;             // Переменная для индекса столбца

        // Обход столбцов матрицы
        for (int j = 0; j < cols; j++) {
            int product = 1;                 // Начальное произведение
            boolean validColumn = true;      // Флаг для проверки валидности столбца

            // Обход строк текущего столбца
            for (int i = 0; i < rows; i++) {
                // Проверка условия по модулю
                if (Math.abs(getElement(i, j)) > n) {
                    validColumn = false; 
                    break; // Выход из цикла, если условие не выполнено
                }
                product *= getElement(i, j); // Умножение элемента на произведение
            }

            // Если столбец верен, проверяем на минимальное произведение
            if (validColumn && product < minProduct) {
                minProduct = product; 
                minColumnIndex = j + 1; // Номер столбца (+1 для удобства)
            }
        }

        // Вывод результата
        if (minColumnIndex != -1) {
            System.out.println("Столбец с минимальным произведением элементов: " + minColumnIndex);
            System.out.println("Минимальное произведение: " + minProduct);
        } else {
            System.out.println("Нет столбцов, удовлетворяющих условию.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();
        
        Matrix matrix = new Matrix(rows, cols);

        matrix.display();

        System.out.print("Введите значение n: ");
        int n = scanner.nextInt();

        matrix.findMinProductColumn(n);

        scanner.close(); 
    }
}