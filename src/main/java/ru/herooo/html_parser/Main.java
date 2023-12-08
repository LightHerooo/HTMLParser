package ru.herooo.html_parser;

import ru.herooo.html_parser.console_interfaces.utils.ConsoleInterface;
import ru.herooo.html_parser.console_interfaces.MainMenu;

public class Main {
    public static void main(String[] args) {
        ConsoleInterface consoleInterface = new MainMenu();
        consoleInterface.print();
    }
}
