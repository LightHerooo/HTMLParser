package ru.herooo.projects.htmlparser.consoleframes.content.html;

import ru.herooo.projects.htmlparser.consoleframes.ConsoleFrameHTMLParserAbstract;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class ConsoleFrameParseHTML extends ConsoleFrameHTMLParserAbstract {
    private Document document;
    public ConsoleFrameParseHTML(Document document) {
        super("Парсинг HTML-файла");
        this.document = document;
    }

    @Override
    public void printContent() {
        while (true) {
            clear();
            printHeader();

            System.out.println("Для успешного парсинга HTML-файла необходимо придерживаться следующих условий:");
            System.out.println("1. Если вам нужно найти конкретный тег, вводите его без угловых скобок (div)");
            System.out.println("2. Если вам нужно найти тег с конкретным классом, вводите его с точкой в начале (.some_class)");
            System.out.println("3. Если вам нужно найти тег с конкретными классами, вводите их одной строкой с точками, как разделители (.some_class.some_class2)");
            printDelimiter();

            String str = CONSOLE_SCANNER.waitString("Введите тег, класс или группу классов (0, чтобы отменить)");
            if (str.equals("0")) break;

            Elements elements = document.select(str);
            if (elements.size() > 0) {
                new ConsoleFrameParseElements(elements).printContent();
            } else {
                printWarning("Элементы не найдены (%s)", str);
                continue;
            }
        }
    }
}
