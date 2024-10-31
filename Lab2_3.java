// Ковальчук Диана Владимировна, 5 группа
// 19. Пусть m(А, i) означает номер столбца матрицы A, в котором находится последний в строке минимум i-й строки. 
// Проверить,  что для заданной матрицы А выполняются неравенства m(A,1) <= m(A,2) <= ... <= m(A,m).
// 
// Алгоритм
// Поиск последних минимумов:
// Для каждой строки от 0 до rows - 1 инициализировать переменную min как максимальное целое число.
// Для каждого столбца от 0 до cols - 1, если matrix[i][j] < min, обновить min.
// Установить m[i] в j + 1 (номер столбца, начиная с 1).
// Иначе, если matrix[i][j] == min, установить m[i] в j + 1 (обновляем на последний найденный минимум).
// (Я СЧИТАЮ, что отсчет столбцов начинается с 1, то есть если столбцов 3, то считаю не 0, 1, 2, а 1, 2, 3)
//
// Пример
// Введите количество строк матрицы: 
// 3
// Введите количество столбцов матрицы: 
// 3
// Введите элементы матрицы:
// 1 2 3 4 5 6 7 8 9
// Ваша матрица:
// 1 2 3 
// 4 5 6 
// 7 8 9 
// Значения m(A, i) для каждой строки:
// m(A,1) = 1
// m(A,2) = 1
// m(A,3) = 1
// Неравенства m(A,1) <= m(A,2) <= ... <= m(A,n) выполняются.

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();

        // Создание матрицы как ArrayList<ArrayList<Integer>>
        ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
        System.out.println("Сгенерированные элементы матрицы:");
        for (int i = 0; i < rows; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                int value = random.nextInt(10); // Генерация случайного числа от 0 до 9
                row.add(value);
                System.out.print(value + " ");
            }
            matrix.add(row);
            System.out.println();
        }

        // Массив для хранения номеров последних минимумов
        int[] m = new int[rows];

        // Поиск номера последнего минимума в каждой строке
        for (int i = 0; i < rows; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < cols; j++) {
                if (matrix.get(i).get(j) < min) {
                    min = matrix.get(i).get(j);
                    m[i] = j + 1; // Нумерация столбцов начинается с 1
                } else if (matrix.get(i).get(j) == min) {
                    m[i] = j + 1; // Обновляем на последний найденный минимум
                }
            }
        }

        // Вывод значений m(A, i)
        System.out.println("Значения m(A, i) для каждой строки:");
        for (int i = 0; i < rows; i++) {
            System.out.println("m(A," + (i + 1) + ") = " + m[i]);
        }

        // Проверка неравенств
        boolean isNonDecreasing = true;
        for (int i = 1; i < rows; i++) {
            if (m[i] < m[i - 1]) {
                isNonDecreasing = false;
                break;
            }
        }

        // Вывод результата
        if (isNonDecreasing) {
            System.out.println("Неравенства m(A,1) <= m(A,2) <= ... <= m(A,n) выполняются.");
        } else {
            System.out.println("Неравенства m(A,1) <= m(A,2) <= ... <= m(A,n) не выполняются.");
        }

        scanner.close();
    }
}