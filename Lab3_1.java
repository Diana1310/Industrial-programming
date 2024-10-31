// Ковальчук Диана Владимировна, 1 курс, 5 группа
// 
// Задача А5.0
// Условие:
// Строка состоит из слов. Слова в исходной строке разделяются некоторым множеством разделителей. 
// Среди слов, состоящих только из цифр, найти слово, содержащее максимальное число нулей. 
// Если таких слов больше одного, найти предпоследнее из них. 
// Слова в исходной строке разделяются некоторым множеством разделителей. 
// Слова в новой строке должны разделяться ровно одним пробелом. 
//
// Решение:
// Вводим строку. Посимвольно проверяем, если находим пробел - 
// за ним удаляем все остальные пробелы до следующего слова.
// Выделяем каждое слово, проверяем, состоит ли оно только из цифр.
// Если состоит, то считаем количество нулей.
// Проверяем, является ли найденное слово с болишым либо равным количеством нулей, чем предыдущее.
// Запоминаем текущее и предыдущее количество нулей, запоминаем текущее и предыдущее слова-числа.
// Выводим слово с максимальным количеством нулей, если их несколько, то предыдущее.
//
// Пример
// Введите строку: 
// 0048180   ,..,.,,0572700,,., 4720 02850  ..,  yjjme9603000
// Преобразованная строка: 0048180 0572700 4720 02850 yjjme9603000
// Слово с максимальным количеством нулей: 0048180

import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите строку: ");
        String s = scanner.nextLine();
        String s1 = s;
        String a = "", ma = "", mp = "";
        int pr = 0;
        int maxx = 0;

        // Удаление лишних разделителей и преобразование строки
        StringBuilder cleanedString = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c) || Character.isLetter(c) || Character.isWhitespace(c)) {
                cleanedString.append(c);
            } else {
                cleanedString.append(' '); // Заменяем другие знаки на пробел
            }
        }
        String cleanInput = cleanedString.toString().replaceAll("\\s+", " ").trim();
        
        System.out.println("Преобразованная строка: " + cleanInput);

        // Используем StringTokenizer для разбивки строки на слова
        StringTokenizer tokenizer = new StringTokenizer(cleanInput);
        
        while (tokenizer.hasMoreTokens()) {
            String word = tokenizer.nextToken();
            if (!word.isEmpty()) {
                int k = 0; // Количество цифр
                int n = word.length();
                for (char c : word.toCharArray()) {
                    if (Character.isDigit(c)) k++;
                }
                if (k == n) {
                    int r = 0; // Количество нулей
                    for (char c : word.toCharArray()) {
                        if (c == '0') r++;
                    }
                    if (maxx < r) {
                        pr = maxx;
                        mp = ma;
                        maxx = r;
                        ma = word;
                    }
                }
            }
        }

        if (pr == maxx) {
            System.out.println("Предпоследнее слово с максимальным количеством нулей: " + mp);
        } else {
            System.out.println("Слово(слова) с максимальным количеством нулей: " + ma);
        }
        scanner.close();
    }
}