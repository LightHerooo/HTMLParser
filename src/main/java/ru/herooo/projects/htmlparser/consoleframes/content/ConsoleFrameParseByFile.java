package ru.herooo.projects.htmlparser.consoleframes.content;

import ru.herooo.projects.htmlparser.consoleframes.ConsoleFrameHTMLParserAbstract;
import ru.herooo.projects.htmlparser.consoleframes.content.parse.ConsoleFrameParseHTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;

public class ConsoleFrameParseByFile extends ConsoleFrameHTMLParserAbstract {
    public ConsoleFrameParseByFile() {
        super("Парсинг через файл");
    }

    @Override
    public void printContent() {
        while (true) {
            clear();
            printHeader();

            String filePath = CONSOLE_SCANNER.waitString("Введите путь к файлу (0, чтобы отменить)");
            if (filePath.equals("0")) break;

            File file = new File(filePath);
            if (!file.exists()) {
                printWarning("Файл по пути \"%s\" не существует", filePath);
                continue;
            }

            Document document = null;
            try {
                document = Jsoup.parse(file);
            } catch (IOException e) {
                printError("Произошла ошибка парсинга", e);
                continue;
            }

            new ConsoleFrameParseHTML(document).printContent();
        }
    }
}
