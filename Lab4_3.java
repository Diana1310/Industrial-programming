// Ковальчук Диана Владимировна, 5 группа
//
// Для выполнения заданий использовать регулярные выражения.
// Каждое задание реализовать в отдельном методе.
// Строку получать из текстового файла input.txt.
// Результат работы методов записывать в выходной текстовый файл output.txt.
// 1. Из заданной строки исключить символы, расположенные внутри круглых скобок.
// Сами скобки тоже должны быть исключены.
// 2. Из заданной строки удалить из каждой группы идущих подряд цифр, в которой более двух цифр,
// все цифры, начиная с третьей.
// 3. Из заданной строки удалить из каждой группы идущих подряд цифр все незначащие нули.
//
// Прочитать содержимое файла input.txt.
// Применить регулярное выражение для исключения символов в скобках из текста и сохранить результат.
// Применить регулярное выражение для удаления цифр после второй цифры в числах и сохранить результат.
// Применить регулярное выражение для удаления ведущих нулей из чисел и сохранить результат.
// Записать все обработанные данные в файл output.txt.
//
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        try {
            String inputFileName = "input.txt";
            String outputFileName = "output.txt";

            String input = readFromFile(inputFileName);

            // Исключение символов в скобках из текста
            String result1 = excludeCharsInParentheses(input);

            // Удаление цифр после второй цифры в числах
            String result2 = removeDigitsAfterSecond(input);

            // Удаление ненужных нулей из чисел
            String result3 = removeLeadingZeros(input);

            writeToFile(outputFileName, result1 + "\n" + result2 + "\n" + result3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String readFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        reader.close();
        return stringBuilder.toString();
    }

    private static void writeToFile(String fileName, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(content);
        writer.close();
    }

    // Исключение символов в скобках из текста
    private static String excludeCharsInParentheses(String input) {
        return input.replaceAll("\\([^)]*\\)", "");
        // Регулярное выражение: "\\([^)]*\\)"
        // - "\\(" - ищет открывающую скобку
        // - "[^)]*" - ищет любой символ, кроме закрывающей скобки
        // - "\\)" - ищет закрывающую скобку
        // Текст в скобках (включая скобки) будет заменен на пустую строку
    }

    // Удаление цифр после второй цифры в числах
    private static String removeDigitsAfterSecond(String input) {
        Pattern pattern = Pattern.compile("(\\d{2})\\d+");
        Matcher matcher = pattern.matcher(input); // Создание Matcher для входной строки
        StringBuffer buffer = new StringBuffer(); // Создание StringBuffer для сохранения преобразованной строки

        while (matcher.find()) {
            // Находит следующее совпадение
            // Заменяет найденное совпадение на первые две цифры с помощью appendReplacement
            matcher.appendReplacement(buffer, matcher.group(1));
        }

        // Добавляет оставшуюся часть строки после последнего совпадения
        matcher.appendTail(buffer);

        return buffer.toString(); // Возвращает преобразованную строку
        // Регулярное выражение: "(\\d{2})\\d+"
        // - "(\\d{2})" - находит первые две цифры и сохраняет их как группу
        // - "\\d+" - находит последующие цифры после первых двух
        // Все цифры, начиная с третьей в числах, будут удалены
    }

    // Удаление  нулей из чисел
    private static String removeLeadingZeros(String input) {
        return input.replaceAll("\\b0+(?!\\b)", "");
        // Регулярное выражение: "\\b0+(?!\\b)"
        // - "\\b" - совпадение на границе слова
        // - "0+" - находит один или более нулей
        // - "(?!\\b)" - убеждается, что после нулей нет других границ слова
        // Ведущие нули в числах будут удалены
    }
}