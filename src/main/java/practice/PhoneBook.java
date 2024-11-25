package practice;

import java.util.*;

public class PhoneBook {
    private Map<String, Set<String>> nameToPhones = new HashMap<>();
    private Map<String, String> phoneToName = new HashMap<>();

    private boolean isValidPhone(String phone) {
        return phone != null && !phone.isEmpty() && phone.matches("7\\d{10}");
    }

    private boolean isValidName(String name) {
        return name != null && !name.isEmpty() && name.matches("[a-zA-Zа-яА-Я\\s-]+");
    }

    public void addContact(String phone, String name) {
        if (!isValidPhone(phone) || !isValidName(name)) {
            return;
        }

        String oldName = phoneToName.get(phone);
        if (oldName != null) {
            nameToPhones.get(oldName).remove(phone);
            if (nameToPhones.get(oldName).isEmpty()) {
                nameToPhones.remove(oldName);
            }
        }

        nameToPhones.computeIfAbsent(name, k -> new TreeSet<>()).add(phone);
        phoneToName.put(phone, name);
    }

    public String getContactByPhone(String phone) {
        String name = phoneToName.get(phone);
        return name != null ? formatContact(name, nameToPhones.get(name)) : "";
    }

    public Set<String> getContactByName(String name) {
        Set<String> result = new TreeSet<>();
        Set<String> phones = nameToPhones.get(name);
        if (phones != null) {
            result.add(formatContact(name, phones));
        }
        return result;
    }

    public Set<String> getAllContacts() {
        Set<String> result = new TreeSet<>();
        for (Map.Entry<String, Set<String>> entry : nameToPhones.entrySet()) {
            result.add(formatContact(entry.getKey(), entry.getValue()));
        }
        return result;
    }

    private String formatContact(String name, Set<String> phones) {
        return name + " - " + String.join(", ", phones);
    }
}