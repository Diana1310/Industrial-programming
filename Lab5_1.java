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
import java.io.*;
import java.util.*;

class Contact implements Comparable<Contact>, Iterable<String> {
    private final String name;
    private final String mobileNumber;
    private final String workNumber;
    private final String homeNumber;
    private final String email;
    private final String website;
    private final String address;

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

    @Override
    public String toString() {
        return String.format("Name: %s, Mobile: %s, Work: %s, Home: %s, Email: %s, Website: %s, Address: %s",
                name, mobileNumber, workNumber, homeNumber, email, website, address);
    }

    @Override
    public int compareTo(Contact other) {
        return this.name.compareTo(other.name);
    }

    public static Comparator<Contact> compareByEmail() {
        return Comparator.comparing(contact -> contact.email);
    }

    public static Comparator<Contact> compareByMobile() {
        return Comparator.comparing(contact -> contact.mobileNumber);
    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final String[] fields = {name, mobileNumber, workNumber, homeNumber, email, website, address};

            @Override
            public boolean hasNext() {
                return index < fields.length;
            }

            @Override
            public String next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return fields[index++];
            }
        };
    }
}

public class Main {
    public static void main(String[] args) {
        List<Contact> contacts = new ArrayList<>();

        // Чтение данных из файла
        try (BufferedReader reader = new BufferedReader(new FileReader("contacts.txt"))) {
            int numberOfContacts = Integer.parseInt(reader.readLine().trim());

            for (int i = 0; i < numberOfContacts; i++) {
                String line = reader.readLine();
                if (line != null) {
                    String[] data = line.split(",");
                    if (data.length < 2) {
                        System.out.println("Недостаточно данных для контакта.");
                        continue;
                    }
                    String name = data[0].trim();
                    String mobileNumber = data[1].trim();
                    String workNumber = data.length > 2 ? data[2].trim() : "";
                    String homeNumber = data.length > 3 ? data[3].trim() : "";
                    String email = data.length > 4 ? data[4].trim() : "";
                    String website = data.length > 5 ? data[5].trim() : "";
                    String address = data.length > 6 ? data[6].trim() : "";

                    contacts.add(new Contact(name, mobileNumber, workNumber, homeNumber, email, website, address));
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
            return;
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата числа: " + e.getMessage());
            return;
        }

        // Запрос способа сортировки
        System.out.println("\nВыберите способ сортировки:");
        System.out.println("1 - По имени");
        System.out.println("2 - По email");
        System.out.println("3 - По мобильному номеру");

        Scanner scanner = new Scanner(System.in);
        int choice = Integer.parseInt(scanner.nextLine());

        // Сортировка и вывод в файл
        List<Contact> sortedContacts = new ArrayList<>(contacts);
        String outputFileName = "sorted_contacts.txt";

        switch (choice) {
            case 1:
                sortedContacts.sort(Comparator.naturalOrder());
                outputFileName = "sorted_by_name.txt";
                break;
            case 2:
                sortedContacts.sort(Contact.compareByEmail());
                outputFileName = "sorted_by_email.txt";
                break;
            case 3:
                sortedContacts.sort(Contact.compareByMobile());
                outputFileName = "sorted_by_mobile.txt";
                break;
            default:
                System.out.println("Неверный выбор. Сортировка не выполнена.");
                return;
        }

        // Запись отсортированных контактов в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFileName))) {
            for (Contact contact : sortedContacts) {
                writer.write(contact.toString());
                writer.newLine();
            }
            System.out.println("Контакты успешно записаны в файл: " + outputFileName);
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл: " + e.getMessage());
        }

        scanner.close();
    }
}