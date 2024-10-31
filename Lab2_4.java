// Ковальчук Диана Владимировна, 5 группа
// 33. Переставляя строки и столбцы матрицы, добиться, чтобы наибольший элемент (один из них) оказался в верхнем левом углу.  
// Вывести на экран полученную матрицу.
// 
// Алгоритм
// Поиск максимального элемента:
// maxValue = минимальное целое число (Integer.MIN_VALUE), maxRow и maxCol = 0.
// Если matrix[i][j] > maxValue:
// Обновить maxValue = matrix[i][j]. Обновить maxRow = i. Обновить maxCol = j.
// Перемещение максимального элемента:
// Если maxRow не равен 0:
// Поменять строки: заменить первую строку матрицы на строку с максимальным элементом.
// Если maxCol не равен 0:
// Для каждой строки i:
// Поменять элементы: заменить элемент в первом столбце на элемент в столбце maxCol.
//
// Пример
// Введите количество строк матрицы: 
// 3
// Введите количество столбцов матрицы: 
// 3
// Введите элементы матрицы:
// 1 2 3
// 4 5 6
// 7 8 9
// Матрица с максимальным элементом в верхнем левом углу:
// 9 8 7 
// 6 5 4 
// 3 2 1 

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        // Создание матрицы
        int[][] matrix = new int[rows][cols];
        System.out.println("Введите элементы матрицы:");

        // Ввод элементов матрицы
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        // Поиск максимального элемента и его индексов
        int maxValue = Integer.MIN_VALUE;
        int maxRow = 0, maxCol = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] > maxValue) {
                    maxValue = matrix[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        // Перемещение максимального элемента в (0, 0)
        // Меняем строки
        if (maxRow != 0) {
            int[] tempRow = matrix[0];
            matrix[0] = matrix[maxRow];
            matrix[maxRow] = tempRow;
        }

        // Меняем столбцы
        if (maxCol != 0) {
            for (int i = 0; i < rows; i++) {
                int temp = matrix[i][0];
                matrix[i][0] = matrix[i][maxCol];
                matrix[i][maxCol] = temp;
            }
        }

        // Вывод полученной матрицы
        System.out.println("Матрица с максимальным элементом в верхнем левом углу:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}