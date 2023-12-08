package ru.herooo.html_parser.console_interfaces.layouts;

import ru.herooo.html_parser.console_interfaces.utils.ConsoleInterface;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Parse extends ConsoleInterface {
    private Document document;

    public Parse(Document document) {
        this.document = document;
    }

    @Override
    public void print() {
        while (true) {
            System.out.println("Парсинг HTML-файла:");
            System.out.println("- Любой тег без угловых скобок (например: div)");
            System.out.println("- Любой класс (например: .my-class)");
            System.out.println("- Тег с несколькими классами (например: .my-first-class.my-second-class.my-third-class)");
            consoleInterfaceLayouts.printHR();

            // Даём ввести тег или класс
            String htmlTag = consoleInterfaceLayouts.printStringChoice("Введите тег или класс, который следует найти: ");
            consoleInterfaceLayouts.printHR();
            if (htmlTag.isEmpty() || htmlTag.isBlank()) {
                consoleInterfaceLayouts.printError("Тег или класс не может быть пустым.");
                continue;
            }

            // Получаем только выбранные теги или классы
            Elements elements = document.select(htmlTag);
            if (elements.size() > 0) {
                System.out.printf("Найдено элементов: %d\n", elements.size());
                consoleInterfaceLayouts.printHR();
                final List<String> results = new ArrayList<>();

                int choice = -1;
                while (choice < 0) {
                    System.out.println("Что нужно сохранить?:");
                    String[] menu = {
                            "Полученный HTML",
                            "Внутренний текст",
                            "Значение атрибута",
                            "Отмена"
                    };
                    consoleInterfaceLayouts.printList(true, menu);
                    consoleInterfaceLayouts.printHR();

                    // Даём выбрать, что распарсить из полученных тегов
                    choice = consoleInterfaceLayouts.printIntChoice();
                    consoleInterfaceLayouts.printHR();

                    switch (choice) {
                        case 1 -> {
                            elements.forEach(el -> results.add(el.html()));
                            generateFile(results, ".html");
                        }
                        case 2 -> {
                            elements.forEach(el -> results.add(el.text()));
                            generateFile(results, ".txt");
                        }
                        case 3 -> {
                            String parseAttributeChoice = consoleInterfaceLayouts.printStringChoice("Введите атрибут: ");
                            if (parseAttributeChoice != null && !parseAttributeChoice.isEmpty()) {
                                elements.forEach(el -> results.add(el.attr(parseAttributeChoice)));
                                generateFile(results, ".txt");
                            }
                        }
                        case 0 -> { }
                        default -> {
                            consoleInterfaceLayouts.printError("Введите номер из списка.");
                        }
                    }
                }
            } else {
                System.out.println("Элементы не найдены :(");
            }
            break;
        }
    }

    private void generateFile(List<String> elements, String fileExtension) {
        // Даём задать название новому файлу
        String newFileName = consoleInterfaceLayouts.printStringChoice("Введите название нового файла " +
                "(если хотите случайное название, пропустите данный шаг): ");
        if (newFileName == null || newFileName.isEmpty() || newFileName.isBlank()) {
            // Генерируем название, если пользователь пропустил шаг
            newFileName = customUtils.getStringWithRandomSymbols(20);
        }
        newFileName = String.format("%s%s", newFileName, fileExtension);

        // Создаём папку, где будет генерироваться результат
        String folderPath = String.format("%s\\%s", System.getProperty("user.dir"), "results");
        File resultsFolder = new File(folderPath);
        if (!resultsFolder.exists()) resultsFolder.mkdirs();

        // Создаём файл с результатом
        String newFilePath = String.format("%s\\%s", folderPath, newFileName);
        try (FileWriter fw = new FileWriter(newFilePath)) {
            System.out.println("Генерация файла...");
            for (String s : elements) {
                if (s != null && !s.isEmpty() && !s.isBlank()) {
                    fw.write(s + "\n");
                }
            }
            System.out.println();
            System.out.println("Файл успешно сгенерирован!");
            System.out.printf("Путь: %s\n", newFilePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
