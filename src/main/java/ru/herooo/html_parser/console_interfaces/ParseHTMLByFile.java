package ru.herooo.html_parser.console_interfaces;

import ru.herooo.html_parser.console_interfaces.layouts.Parse;
import ru.herooo.html_parser.console_interfaces.utils.ConsoleInterface;
import ru.herooo.html_parser.console_interfaces.utils.YesOrNoChoice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// C:\Users\Артём\Downloads\глаголы.html

public class ParseHTMLByFile extends ConsoleInterface {
    @Override
    public void print() {
        YesOrNoChoice choice = YesOrNoChoice.UNKNOWN;
        while(choice != YesOrNoChoice.NO) {
            consoleInterfaceLayouts.clearInterface();
            consoleInterfaceLayouts.printHR();
            consoleInterfaceLayouts.printHeader();
            consoleInterfaceLayouts.printHR();
            System.out.println("Запарсить через путь к файлу");
            consoleInterfaceLayouts.printHR();

            // Проверяем, впервые ли находимся в этом цикле?
            if (choice == YesOrNoChoice.UNKNOWN) {
                choice = consoleInterfaceLayouts.printYesOrNotChoice("Приступить к парсингу?");
                if (choice == YesOrNoChoice.UNKNOWN) {
                    consoleInterfaceLayouts.printError();
                    continue;
                } else if (choice == YesOrNoChoice.NO) {
                    break;
                }
            }

            // Даём ввести путь к файлу
            String filePath = consoleInterfaceLayouts.printStringChoice("Введите путь к файлу: ").trim();
            File htmlFile = new File(filePath);
            if (htmlFile.exists() && htmlFile.isFile() &&
                    customUtils.getExtension(htmlFile).equalsIgnoreCase(".html")) {
                try {
                    Document document = Jsoup.parse(htmlFile);
                    Parse parse = new Parse(document);
                    consoleInterfaceLayouts.printHR();
                    parse.print();
                    consoleInterfaceLayouts.printHR();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                String message = String.format("Файл [%s] не существует или не в формате .html",
                        filePath);
                consoleInterfaceLayouts.printError(message);
            }

            choice = consoleInterfaceLayouts.printYesOrNotChoice("Повторить операцию?");
            consoleInterfaceLayouts.printHR();
        }
    }
}
