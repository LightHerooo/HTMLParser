package ru.herooo.html_parser.console_interfaces;

import ru.herooo.html_parser.console_interfaces.utils.ConsoleInterface;

public class MainMenu extends ConsoleInterface {
    @Override
    public void print() {

        int choice = -1;
        while(choice != 0) {
            consoleInterfaceLayouts.clearInterface();
            consoleInterfaceLayouts.printHR();
            consoleInterfaceLayouts.printHeader();
            consoleInterfaceLayouts.printHR();

            String[] menu = {
                    "Запарсить через ссылку на сайт",
                    "Запарсить через путь к файлу",
                    "Выход"
            };
            consoleInterfaceLayouts.printList(true, menu);
            consoleInterfaceLayouts.printHR();

            choice = consoleInterfaceLayouts.printIntChoice();
            consoleInterfaceLayouts.printHR();

            switch (choice) {
                case 1: new ParseHTMLBySite().print(); break;
                case 2: new ParseHTMLByFile().print(); break;
                case 0: break;
                default: consoleInterfaceLayouts.printError("Введите номер из списка.");
            }

            consoleInterfaceLayouts.clearInterface();
        }
    }
}
