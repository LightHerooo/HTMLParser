package ru.herooo.html_parser.console_interfaces;

import ru.herooo.html_parser.console_interfaces.layouts.Parse;
import ru.herooo.html_parser.console_interfaces.utils.ConsoleInterface;
import ru.herooo.html_parser.console_interfaces.utils.YesOrNoChoice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ParseHTMLBySite extends ConsoleInterface {
    @Override
    public void print() {
        YesOrNoChoice choice = YesOrNoChoice.UNKNOWN;
        while(choice != YesOrNoChoice.NO) {
            consoleInterfaceLayouts.clearInterface();
            consoleInterfaceLayouts.printHR();
            consoleInterfaceLayouts.printHeader();
            consoleInterfaceLayouts.printHR();
            System.out.println("Запарсить через ссылку на страницу сайта");
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

            // Даём ввести ссылку на страницу сайта
            String url = consoleInterfaceLayouts.printStringChoice("Введите ссылку на страницу сайта: ");
            url = url.trim();
            try {
                Document document = Jsoup.connect(url)
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
                Parse parse = new Parse(document);
                consoleInterfaceLayouts.printHR();
                parse.print();
                consoleInterfaceLayouts.printHR();
            } catch (IOException e) {
                String message = String.format("Не удалось подключиться к серверу :( [%s]", url);
                consoleInterfaceLayouts.printError(message);
            } catch (IllegalArgumentException ex) {
                String message = String.format("Неправильный адрес :( [%s]", url);
                consoleInterfaceLayouts.printError(message);
            }

            choice = consoleInterfaceLayouts.printYesOrNotChoice("Повторить операцию?");
            if (choice == YesOrNoChoice.UNKNOWN) {
                consoleInterfaceLayouts.printError();
            }
        }
    }
}
