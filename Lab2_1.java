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
// Введите элементы матрицы:
// 3 2 1 3 2 1 3 2 1
// Введенная матрица:
// 3 2 1 
// 3 2 1 
// 3 2 1 
// Введите значение n: 
// 2
// Столбец с минимальным произведением элементов: 3
// Минимальное произведение: 1
//
//
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Вводим размеры матрицы
        System.out.print("Введите количество строк матрицы: ");
        int rows = scanner.nextInt();
        System.out.print("Введите количество столбцов матрицы: ");
        int cols = scanner.nextInt();
        
        int[][] matrix = new int[rows][cols]; 
        
        // Вводим элементы матрицы
        System.out.println("Введите элементы матрицы:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        
        // Отображение матрицы
        System.out.println("Введенная матрица:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        
        
        // Вво значения n
        System.out.print("Введите значение n: ");
        int n = scanner.nextInt();
        
        
        int minProduct = Integer.MAX_VALUE;   // Переменная для минимального произведения
        int minColumnIndex = -1;              // Переменная для индекса столбца
        
        // Обход столбцов матрицы
        for (int j = 0; j < cols; j++) {
            int product = 1;                  // Начальное произведение
            boolean validColumn = true;
            
            for (int i = 0; i < rows; i++) {
                // Проверка условия по модулю
                if (Math.abs(matrix[i][j]) > n) {    // Если больше по модулю, выходим из цикла
                    validColumn = false;
                    break;
                }
                product *= matrix[i][j];            
            }
            
            // Если столбец верен, проверяем произведение
            if (validColumn && product < minProduct) { // Выбираем из тех, что удовлетворяют условию по модулю
                minProduct = product;
                minColumnIndex = j+1 ; // Номер нашего столбца (+1 потому что изначально начинали счет с 0, а не с 1)
            }
        }
        
        // Вывод результата
        if (minColumnIndex != -1) {
            System.out.println("Столбец с минимальным произведением элементов: " + minColumnIndex);
            System.out.println("Минимальное произведение: " + minProduct);
        } else {
            System.out.println("Нет столбцов, удовлетворяющих условию.");
        }
        
        scanner.close();
    }
}