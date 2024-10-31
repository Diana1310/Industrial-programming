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
//
// ПримерВведите x в диапозоне (-1,+1): 
// 0.9
// Введите натуральное число k: 
// 5
// Результат через стандартные функции: 
// 0.725
// Мой результат: 
// 0.725
//
import java.io.BufferedReader;
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.math.BigDecimal; 
import java.text.NumberFormat; 



public class Main {
    public static void main(String[] args) {
        InputStreamReader isr = new InputStreamReader(System.in); 
        BufferedReader br = new BufferedReader(isr); 
        try {
            
            System.out.println("Введите x в диапозоне (-1,+1): ");
            String count = br.readLine(); 
            BigDecimal ourNumber = new BigDecimal(count); // Преобразуем строку в BigDecimal

            System.out.println("Введите натуральное число k: ");
            String count2 = br.readLine(); 
            BigDecimal ourDegree = new BigDecimal(count2); // Преобразуем строку в BigDecimal

            // Инициализируем константы
            BigDecimal one = new BigDecimal(1); // Единица
            BigDecimal ten = new BigDecimal(10.0); // Десятка

            // Делаем степень отрицательной
            ourDegree = ourDegree.negate();
            BigDecimal newDegree = new BigDecimal(ourDegree.doubleValue()); // Преобразуем степень в BigDecimal

            BigDecimal e = new BigDecimal(1.0);
            for (int i = 0; i <= newDegree.doubleValue(); i++) {
                e = e.divide(ten); // Делим e на 10 для достижения нужной точности
            }

            // Вычисляем результат через стандартную функцию
            System.out.println("Результат через стандартные функции: ");
            double result = 1 / Math.sqrt(ourNumber.add(BigDecimal.ONE).doubleValue()); 
            NumberFormat formatter = NumberFormat.getNumberInstance(); 
            formatter.setMaximumFractionDigits(3); // Кол-во знаков после запятой
            System.out.println(formatter.format(result)); 

            // Вычисляем свой результат 
            System.out.println("Мой результат: ");
            BigDecimal newOurNumber = ourNumber.add(BigDecimal.ONE); 
            BigDecimal res = new BigDecimal(1 / Math.sqrt(newOurNumber.doubleValue())); 
            System.out.println(formatter.format(res)); 
        } catch (NumberFormatException e) {
            System.out.println("Не число");
        } catch (IOException e) {
            System.out.println("Ошибка чтения с клавиатуры");
        }
    }
}