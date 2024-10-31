// Ковальчук Диана Владимировна, 1 курс, 5 группа
// 
// Задача В5.0
// Условие:
// Задан текстовый файлInput.txt, состоящий из слов. 
// Разделителями между словами является некоторое множество знаков препинания. 
// Найти в каждой строке  слова– числа (это слова, состоящие только из цифр), 
// заменить их числом, равным сумме цифр этого слова–числа.  
// Результат записать в новый файл Output.txt. 
// Упорядочить слова в полученных строках по возрастанию количества цифр в словах, результат сортировки записать в файл Out_sort.txt. 
//
// Решение:
// Открываем файл на чтение Input.txt. Преобразовываем строку в массив символов. 
// Делим массив на лексемы и проверяем, состоят ли они их цифр.
// Если да - находим сумму цифр и выводим в файл Output.txt, иначе выводим лексему. Закрываем файлы.
// После открываем файл на чтение Output.txt. Преобразовываем строку в массив символов.
// Делим массив на лексемы и записываем их в новый массив из массива символов.
// Сортируем это массив из слов по возрастанию количества цифр в словах. Выводим слова из массива в файл Out_sort.txt.
import java.io.*;
import java.util.*;

public class Main {
    // Функция проверки, является ли слово числом
    public static boolean isNumber(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    // Функция нахождения суммы цифр в слове-числе
    public static int sumDigits(String str) {
        return str.chars().map(c -> c - '0').sum();
    }

    // Функция нахождения количества цифр в слове
    public static int countDigits(String str) {
        return (int) str.chars().filter(Character::isDigit).count();
    }

    public static void main(String[] args) {
        // Чтение из файла input.txt и запись в output.txt
        try (BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
             BufferedWriter fout = new BufferedWriter(new FileWriter("output.txt"))) {

            String line;
            // Читаем строки из входного файла
            while ((line = fin.readLine()) != null) {
                StringBuilder result = new StringBuilder();
                String[] words = line.split("[,.:!?\\s]+");
                List<String> currentWords = new ArrayList<>();

                for (String word : words) {
                    // Проверяем, является ли слово числом
                    if (isNumber(word)) {
                        // Если да, считаем сумму его цифр
                        int sum = sumDigits(word);
                        result.append(sum).append(" "); // Добавляем сумму в результат
                        currentWords.add(String.valueOf(sum)); // Сохраняем сумму в список
                    } else {
                        result.append(word).append(" "); 
                        currentWords.add(word); 
                    }
                }

                // Записываем полученную строку в output.txt
                fout.write(result.toString().trim());
                fout.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом input.txt: " + e.getMessage());
        }

        // Сортировка строк из output.txt и запись в out_sort.txt
        try (BufferedReader fin = new BufferedReader(new FileReader("output.txt"));
             BufferedWriter fout = new BufferedWriter(new FileWriter("out_sort.txt"))) {

            List<String> lines = new ArrayList<>();
            String line;
            // Читаем строки из output.txt
            while ((line = fin.readLine()) != null) {
                lines.add(line); // Сохраняем строки в списке
            }

            // Сортировка слов в строках
            for (int i = 0; i < lines.size(); i++) {
                String[] words = lines.get(i).split(" ");
                List<String> wordsList = new ArrayList<>(Arrays.asList(words));
                
                // Сортировка по количеству цифр в словах
                wordsList.sort(Comparator.comparingInt(Main::countDigits));

                StringBuilder sortedLine = new StringBuilder();
                for (String word : wordsList) {
                    sortedLine.append(word).append(" "); // Добавляем отсортированные слова
                }

                for (String word : words) {
                    if (isNumber(word)) {
                        sortedLine.append(sumDigits(word)).append(" "); // Добавляем сумму цифр
                    }
                }

                // Запись отсортированной строки в out_sort.txt
                fout.write(sortedLine.toString().trim());
                fout.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при работе с файлом output.txt: " + e.getMessage());
        }
    }
}