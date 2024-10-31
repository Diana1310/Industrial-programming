// Ковальчук Диана Владимировна, 5 группа
//
// Задача "Контакты"
//
// Класс Contact:
// Этот класс представляет объект, содержащий различную информацию о контакте,
// такую как имя, номера телефонов, email, веб-сайт и адрес.
// Класс реализует интерфейсы Comparable и Iterable,
// что позволяет сортировать и итерировать по полям контакта.
// Методы compareTo(), compareByEmail() и compareByMobile()
// предоставляют различные способы сортировки контактов.
//
// Класс Main:
// Запрашивается у пользователя количество контактов,
// затем последовательно запрашивает данные для каждого контакта и добавляет их в список contacts.
// Пользователь может выбрать способ сортировки контактов
// (по имени, email или мобильному номеру), после чего отсортированный список выводится на экран.
//
// Пример
// Введите количество контактов: 4
// Введите данные контакта (1):
// Имя (обязательно): Диана
// Мобильный номер (обязательно): +375333880001
// Рабочий номер:
// Домашний номер:
// Email: kov.diana.05@gmail.com
// Веб-сайт:
// Адрес:
//
// Введите данные контакта (2):
// Имя (обязательно): Игорь
// Мобильный номер (обязательно): +375293520703
// Рабочий номер:
// Домашний номер: +3754449912
// Email: igor1419sam@mail.ru
// Веб-сайт:
// Адрес:
//
// Введите данные контакта (3):
// Имя (обязательно): Артем
// Мобильный номер (обязательно): +375447458310
// Рабочий номер: +375295153305
// Домашний номер:
// Email:
// Веб-сайт:
// Адрес:
//
// Введите данные контакта (4):
// Имя (обязательно): Константин
// Мобильный номер (обязательно): +375448120306
// Рабочий номер:
// Домашний номер:
// Email: konstantin1515@gmail.com
// Веб-сайт:
// Адрес:
//
//Выберите способ сортировки:
// 1 - По имени
// 2 - По email
// 3 - По мобильному номеру
// 1
//
// Контакты, отсортированные по имени:
// Name: Артем, Mobile: +375447458310, Work: +375295153305, Home: , Email: , Website: , Address:
// Name: Диана, Mobile: +375333880001, Work: , Home: , Email: kov.diana.05@gmail.com, Website: , Address:
// Name: Игорь, Mobile: +375293520703, Work: , Home: +3754449912, Email: igor1419sam@mail.ru, Website: , Address:
// Name: Константин, Mobile: +375448120306, Work: , Home: , Email: konstantin1515@gmail.com, Website: , Address:
import java.util.*;

// Класс Contact представляет контакт с различными полями
class Contact implements Comparable<Contact>, Iterable<String> {
    private final String name;             // Обязательное поле: имя контакта
    private final String mobileNumber;     // Обязательное поле: мобильный номер
    private final String workNumber;       // Необязательное поле: рабочий номер
    private final String homeNumber;       // Необязательное поле: домашний номер
    private final String email;            // Необязательное поле: email
    private final String website;          // Необязательное поле: веб-сайт
    private final String address;          // Необязательное поле: адрес

    // Конструктор для инициализации объекта Contact
    public Contact(String name, String mobileNumber, String workNumber, String homeNumber,
                   String email, String website, String address) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.workNumber = workNumber;
        this.homeNumber = homeNumber;
        this.email = email;
        this.website = website;
        this.address = address;
    }

    // Переопределение метода toString для удобного отображения информации о контакте
    @Override
    public String toString() {
        return String.format("Name: %s, Mobile: %s, Work: %s, Home: %s, Email: %s, Website: %s, Address: %s",
                name, mobileNumber, workNumber, homeNumber, email, website, address);
    }

    // Переопределение метода compareTo для сортировки по имени
    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    // Метод для получения компаратора для сортировки по email
    public static Comparator<Contact> compareByEmail() {
        return Comparator.comparing(contact -> contact.email);
    }

    // Метод для получения компаратора для сортировки по мобильному номеру
    public static Comparator<Contact> compareByMobile() {
        return Comparator.comparing(contact -> contact.mobileNumber);
    }

    // Переопределение метода iterator для итерации по полям контакта
    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index = 0;            // Индекс текущего элемента
            private final String[] fields = {name, mobileNumber, workNumber, homeNumber, email, website, address};

            @Override
            public boolean hasNext() {
                return index < fields.length; // Проверка наличия следующего элемента
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException(); // Исключение, если элементов больше нет
                }
                return fields[index++];                 // Возврат текущего элемента и переход к следующему
            }
        };
    }
}


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Contact> contacts = new ArrayList<>();     // Список для хранения контактов

        // Запрос количества контактов у пользователя
        int numberOfContacts;
        while (true) {
            try {
                System.out.println("Введите количество контактов:");
                numberOfContacts = Integer.parseInt(scanner.nextLine()); // Чтение количества контактов
                if (numberOfContacts <= 0) {
                    throw new NumberFormatException();                   // Исключение, если количество меньше или равно нулю
                }
                break; // Выход, если ввод корректен
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное положительное число.");
            }
        }

        // Цикл для ввода данных каждого контакта
        for (int i = 0; i < numberOfContacts; i++) {
            System.out.println("Введите данные контакта (" + (i + 1) + "):");

            // Ввод имени
            String name;
            while (true) {
                System.out.print("Имя (обязательно): ");
                name = scanner.nextLine().trim(); // Чтение имени и удаление пробелов
                if (!name.isEmpty()) {
                    break; // Имя не должно быть пустым
                }
                System.out.println("Имя не может быть пустым. Пожалуйста, введите имя.");
            }

            // Ввод мобильного номера
            String mobileNumber;
            while (true) {
                System.out.print("Мобильный номер (обязательно): ");
                mobileNumber = scanner.nextLine().trim(); // Чтение мобильного номера
                if (!mobileNumber.isEmpty()) {
                    break; // Мобильный номер не должен быть пустым
                }
                System.out.println("Мобильный номер не может быть пустым. Пожалуйста, введите номер.");
            }

            // Ввод остальных полей, которые являются необязательными
            System.out.print("Рабочий номер: ");
            String workNumber = scanner.nextLine().trim();
            System.out.print("Домашний номер: ");
            String homeNumber = scanner.nextLine().trim();
            System.out.print("Email: ");
            String email = scanner.nextLine().trim();
            System.out.print("Веб-сайт: ");
            String website = scanner.nextLine().trim();
            System.out.print("Адрес: ");
            String address = scanner.nextLine().trim();

            // Добавление нового контакта в список
            contacts.add(new Contact(name, mobileNumber, workNumber, homeNumber, email, website, address));
        }

        // Запрос способа сортировки
        System.out.println("\nВыберите способ сортировки:");
        System.out.println("1 - По имени");
        System.out.println("2 - По email");
        System.out.println("3 - По мобильному номеру");
        int choice = Integer.parseInt(scanner.nextLine()); // Чтение выбора

        // Сортировка списка контактов в зависимости от выбора
        switch (choice) {
            case 1:
                contacts.sort(Comparator.naturalOrder()); // Сортировка по имени
                System.out.println("\nКонтакты, отсортированные по имени:");
                break;
            case 2:
                contacts.sort(Contact.compareByEmail()); // Сортировка по email
                System.out.println("\nКонтакты, отсортированные по email:");
                break;
            case 3:
                contacts.sort(Contact.compareByMobile()); // Сортировка по мобильному номеру
                System.out.println("\nКонтакты, отсортированные по мобильному номеру:");
                break;
            default:
                System.out.println("Неверный выбор. Сортировка не выполнена."); // Обработка неверного выбора
                return;
        }

        // Вывод отсортированного списка контактов
        for (Contact contact : contacts) {
            System.out.println(contact); // Печать информации о каждом контакте
        }

        scanner.close();
    }
}