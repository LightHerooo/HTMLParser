package ru.herooo.html_parser.console_interfaces.utils;

import ru.herooo.html_parser.utils.CustomUtils;

public abstract class ConsoleInterface {
    protected final ConsoleInterfaceLayouts consoleInterfaceLayouts;
    protected final CustomUtils customUtils;

    protected ConsoleInterface() {
        consoleInterfaceLayouts = new ConsoleInterfaceLayouts();
        customUtils = new CustomUtils();
    }

    public ConsoleInterfaceLayouts getConsoleInterfaceLayouts() {
        return consoleInterfaceLayouts;
    }

    public abstract void print();
}
