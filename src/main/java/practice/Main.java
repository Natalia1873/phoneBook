package practice;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите номер, имя или команду:");
            String input = scanner.nextLine().trim();

            // Skip empty input
            if (input.isEmpty()) {
                continue;
            }

            if (input.equalsIgnoreCase("LIST")) {
                Set<String> contacts = phoneBook.getAllContacts();
                if (contacts.isEmpty()) {
                    System.out.println("Телефонная книга пуста");

                } else {
                    contacts.forEach(System.out::println);
                }
                continue;
            }
            // Check that the input matches the phone number format (starts with 7 + 10 digits)
            // OR the name format (letters/spaces/hyphens)
            if (input.matches("7\\d{10}") || input.matches("[a-zA-Zа-яА-Я\\s-]+")) {
               String contactByPhone = phoneBook.getContactByPhone(input);
               if (!contactByPhone.isEmpty()) {
                   System.out.println(contactByPhone);
                   continue;
               }

               Set<String> contactsByName = phoneBook.getContactByName(input);
               if (!contactsByName.isEmpty()) {
                   contactsByName.forEach(System.out::println);
                   continue;
               }

               // Add a new contact
               if (input.matches("7\\d{10}")) {
                   System.out.println("Такого номера нет в телефонной книге.");
                   System.out.println("Введите имя абонента для номера \"" + input + "\":");
                   String name = scanner.nextLine().trim();
                   phoneBook.addContact(input, name);
               } else {
                   System.out.println("Такого имени в телефонной книге нет.");
                   System.out.println("Введите номер телефона для абонента \"" + input + "\":");
                   String phone = scanner.nextLine().trim();
                   phoneBook.addContact(phone, input);
               }
               System.out.println("Контакт сохранен!");
            } else {
               System.out.println("Неверный формат ввода");
               continue;
            }
        }
    }
}
