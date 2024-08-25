package ru.herooo.projects.htmlparser.consoleframes.content.html;

import ru.herooo.projects.htmlparser.consoleframes.ConsoleFrameHTMLParserAbstract;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ConsoleFrameParseElements extends ConsoleFrameHTMLParserAbstract {
    private Elements elements;

    public ConsoleFrameParseElements(Elements elements) {
        super("Парсинг элементов HTML-файла");
        this.elements = elements;
    }

    @Override
    public void printContent() {
        while (true) {
            clear();
            printHeader();

            System.out.printf("Найдено элементов: %d\n", elements.size());
            printDelimiter();
            for (int i = 0; i < elements.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, elements.get(i));
            }
            printDelimiter();

            System.out.println("Выберите, что нужно сохранить в файл:");
            Map<Integer, String> menuMap = new HashMap<>();
            menuMap.put(1, "Оставить всё как есть");
            menuMap.put(2, "Сохранить внутренние теги + текст");
            menuMap.put(3, "Сохранить внутренний текст");
            menuMap.put(4, "Сохранить значения атрибута найдённого тега");
            printMenu(menuMap, "Отмена", true, true);

            int choice = CONSOLE_SCANNER.waitInt();
            if (choice == 0) break;
            printDelimiter();

            try {
                File file = null;
                if (choice == 1) {
                    file = parse(elements);
                } else if (choice == 2) {
                    file = parseHTML(elements);
                } else if (choice == 3) {
                    file = parseText(elements);
                } else if (choice == 4) {
                    String attribute = CONSOLE_SCANNER.waitString("Введите атрибут (0, чтобы отменить)");
                    if (attribute.equals("0")) break;
                    printDelimiter();

                    file = parseAttribute(elements, attribute);
                }

                if (file != null) {
                    System.out.println("Парсинг успешно завершён!");
                    System.out.printf("Путь к файлу: %s\n", file.getPath());
                    printDelimiter();

                    CONSOLE_SCANNER.waitEnter();
                    break;
                }
            } catch (IOException e) {
                printError("Произошла ошибка парсинга", e);
                continue;
            }
        }
    }

    private File parse(Elements elements) throws IOException {
        File file = createNewParseResultFile();
        try (FileWriter fw = new FileWriter(file)) {
            for (Element element: elements) {
                fw.write(element + "\n");
            }
        }

        return file;
    }

    private File parseHTML(Elements elements) throws IOException {
        File file = createNewParseResultFile();
        try (FileWriter fw = new FileWriter(file)) {
            for (Element element: elements) {
                fw.write(element.html() + "\n");
            }
        }

        return file;
    }

    private File parseText(Elements elements) throws IOException {
        File file = createNewParseResultFile();
        try (FileWriter fw = new FileWriter(createNewParseResultFile())) {
            for (Element element: elements) {
                fw.write(element.text() + "\n");
            }
        }

        return file;
    }

    private File parseAttribute(Elements elements, String attribute) throws IOException {
        File file = createNewParseResultFile();
        try (FileWriter fw = new FileWriter(createNewParseResultFile())) {
            for (Element element: elements) {
                fw.write(element.attr(attribute) + "\n");
            }
        }

        return file;
    }

    private File createNewParseResultFile() throws IOException {
        File directory = new File("parse_results");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File parseResultFile = new File(String.format("%s/parse_result_%s.txt",
                directory.getPath(),
                new SimpleDateFormat("dd_MM_yyyy__hh_mm_ss").format(new Date())));
        parseResultFile.createNewFile();

        return parseResultFile;
    }
}