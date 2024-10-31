// Ковальчук Диана Владимировна, 5 группа
// Функция представлена в виде своего ряда Тейлора. 
// Вычислить приближённое значение суммы этого бесконечного ряда. 
// Вычисления заканчивать, когда очередное слагаемое окажется по модулю меньше заданного числа e. 
// Вид этого числа определяется  следующим условием: e = 10^(-k), где k – натуральное число. 
// Сравнить полученный результат со значением, вычисленным через стандартные функции.
// Значения x и k ввести с клавиатуры. 
// Вывод результата осуществить с тремя знаками после десятичной точки.
//
// 1/((1+x)^(1/2))=1-1*x/2+(1*3*x^2)/(2*4)-(1*3*5*x^3)/(2*4*6)...   x в (-1,+1)

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

public class Main {
    // Функция для вычисления x в степени k
    public static double expon(double x, int k) {
        // Если степень равна 0, результат всегда 1
        if (k == 0) return 1;

        double res = 1;
        if (k > 0) {
            // Для положительных k умножаем x k раз
            for (int i = 0; i < k; i++) {
                res *= x;
            }
        } else {
            // Для отрицательных k делим 1 на x k раз
            for (int i = 0; i < -k; i++) {
                res /= x; 
            }
        }
        return res; 
    }

    // Функция для вычисления функции через ряд
    public static double myFun(double x, double e) {
        double y = 1; 
        double m = 1; 
        double k = 1; 

        
        while (Math.abs(m) > e) {
            m = (-m * k * x) / (k + 1); 
            y += m; 
            k += 2; 
        }
        return y; 
    }

    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        try {
            
            System.out.println("Введите x в диапозоне (-1,+1): ");
            String count = br.readLine();
            double ourNumber = Double.parseDouble(count); // Преобразуем строку в число

            
            System.out.println("Введите натуральное число k: ");
            String count2 = br.readLine();
            int ourDegree = Integer.parseInt(count2); // Преобразуем строку в целое число
            ourDegree = -ourDegree; // Делаем степень отрицательной

            // Вычисляем точность e
            double e = expon(10, ourDegree);
            System.out.println("Результат через стандартные функции: ");
            double res = 1 / Math.sqrt(ourNumber + 1);

            
            NumberFormat formatter = NumberFormat.getNumberInstance();
            formatter.setMaximumFractionDigits(3); // выводим 3 знака после запятой 
            System.out.println(formatter.format(res)); 

            
            System.out.println("Результат через мои функции: ");
            double myRes = myFun(ourNumber, e);
            System.out.println(formatter.format(myRes)); 

        } catch (NumberFormatException e) {
            System.out.println("Не число");
        } catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
        }
    }
}