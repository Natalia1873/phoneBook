package practice;
import java.util.Scanner;
import java.util.Set;

public class Main {
    private static PhoneBook phoneBook = new PhoneBook();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            processInput(input);
        }
    }

    private static void processInput(String input) {
        if (input.equalsIgnoreCase("LIST")) {
            displayAllContacts();
            return;
        }

        if (input.matches("7\\d{10}") || input.matches("[a-zA-Zа-яА-Я\\s-]+")) {
            processContactInput(input);
        } else {
            System.out.println("Неверный формат ввода");
        }
    }

    private static void displayAllContacts() {
        Set<String> contacts = phoneBook.getAllContacts();
        if (contacts.isEmpty()) {
            System.out.println("Телефонная книга пуста");
        } else {
            contacts.forEach(System.out::println);
        }
    }

    private static void processContactInput(String input) {
        if (!findExistingContact(input)) {
            addNewContact(input);
        }
    }

    private static boolean findExistingContact(String input) {
        String contactByPhone = phoneBook.getContactByPhone(input);
        if (!contactByPhone.isEmpty()) {
            System.out.println(contactByPhone);
            return true;
        }

        Set<String> contactsByName = phoneBook.getContactByName(input);
        if (!contactsByName.isEmpty()) {
            contactsByName.forEach(System.out::println);
            return true;
        }
        return false;
    }

    private  static void addNewContact(String input) {
        if (input.matches("7\\d{10}")) {
            addContactByPhone(input);
        } else {
            addContactByName(input);
        }
        System.out.println("Контакт сохранен!");
    }
    
    private static void addContactByPhone(String phone) {
        System.out.println("Такого номера нет в телефонной книге.");
        System.out.println("Введите имя абонента для номера \"" + phone + "\":");
        String name = scanner.nextLine().trim();
        phoneBook.addContact(phone, name);
    }

    private static void addContactByName(String name) {
        System.out.println("Такого имени в телефонной книге нет.");
        System.out.println("Введите номер телефона для абонента \"" + name + "\":");
        String phone = scanner.nextLine().trim();
        phoneBook.addContact(phone, name);
    }

}

