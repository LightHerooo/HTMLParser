package ru.herooo.html_parser.console_interfaces.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class ConsoleInterfaceLayouts {
    private Scanner scanner;

    public ConsoleInterfaceLayouts() {
        scanner = new Scanner(System.in, "ibm866");
    }

    public void printHR() {
        System.out.println("-------------------");
    }
    public void printHeader() {
        System.out.println("HTMLParser (v. 1.0)");
        System.out.println("Возможность парсинга HTML-файлов напрямую через сайт или файл");
    }

    public void clearInterface() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }

    public void printList(boolean isTheLastElementWithZero, List<String> elements) {
        printList(isTheLastElementWithZero, elements.toArray(new String[0]));
    }

    public void printList(boolean isTheLastElementWithZero, String ...elements) {
        if (elements.length > 0) {
            String elementLayout = "%d. %s\n";
            IntStream.range(0, elements.length - 1).forEach(i ->
                    System.out.printf(elementLayout, i + 1, elements[i]));

            if (isTheLastElementWithZero) {
                System.out.printf(elementLayout, 0, elements[elements.length - 1]);
            } else {
                System.out.printf(elementLayout, elements.length, elements[elements.length - 1]);
            }
        }
    }

    public int printIntChoice() {
        return printIntChoice("Введите целое число: ");
    }

    public int printIntChoice(String message) {
        System.out.print(message);
        int choice = -1;
        if (scanner.hasNextInt()) {
            choice = scanner.nextInt();
        }

        scanner.nextLine();
        return choice;
    }

    public char printCharChoice() {
        return printCharChoice("Введите символ: ");
    }

    public char printCharChoice(String message) {
        System.out.print(message);
        char choice = '?';
        if (scanner.hasNextLine()) {
            String str = scanner.next();
            if (str != null && !str.isEmpty()) {
                choice = str.charAt(0);
            }
        }

        return choice;
    }

    public String printStringChoice() {
        return printStringChoice("Введите строку: ");
    }

    public String printStringChoice(String message) {
        System.out.print(message);
        String choice = null;
        if (scanner.hasNextLine()) {
            choice = scanner.next();
        }

        return choice;
    }

    public YesOrNoChoice printYesOrNotChoice() {
        return printYesOrNotChoice("Введите операцию");
    }

    public YesOrNoChoice printYesOrNotChoice(String message) {
        final char YES_CHARACTER = 'y';
        final char NO_CHARACTER = 'n';

        String newMessage = String.format("%s (%c/%c): ", message, YES_CHARACTER, NO_CHARACTER) ;
        char choice = Character.toLowerCase(printCharChoice(newMessage));

        YesOrNoChoice yonc;
        switch (choice) {
            case YES_CHARACTER -> yonc = YesOrNoChoice.YES;
            case NO_CHARACTER -> yonc = YesOrNoChoice.NO;
            default -> yonc = YesOrNoChoice.UNKNOWN;
        }

        return yonc;
    }

    public void printError() {
        printError("Произошла ошибка.");
    }

    public void printError(String message) {
        try {
            System.out.println(message);
            printHR();
            System.in.read();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
