package ru.herooo.projects.htmlparser.consoleframes.content;

import ru.herooo.projects.htmlparser.consoleframes.ConsoleFrameHTMLParserAbstract;
import ru.herooo.projects.htmlparser.consoleframes.content.parse.ConsoleFrameParseHTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ConsoleFrameParseByURL extends ConsoleFrameHTMLParserAbstract {
    public ConsoleFrameParseByURL() {
        super("Парсинг через URL");
    }

    @Override
    public void printContent() {
        while (true) {
            clear();
            printHeader();

            String url = CONSOLE_SCANNER.waitString("Введите URL сайта (0, чтобы отменить)");
            if (url.equals("0")) break;

            Document document = null;
            try {
                document = Jsoup.connect(url)
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
            } catch (IOException | IllegalArgumentException e) {
                printError("Произошла ошибка подключения", e);
                continue;
            }

            new ConsoleFrameParseHTML(document).printContent();
        }
    }
}
