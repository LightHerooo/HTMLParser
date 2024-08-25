package htmlparser.consoleframes.content;

import htmlparser.consoleframes.ConsoleFrameHTMLParserAbstract;
import htmlparser.consoleframes.content.html.ConsoleFrameParseHTML;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ConsoleFrameParseByURL extends ConsoleFrameHTMLParserAbstract {
    public ConsoleFrameParseByURL() {
        super("Парсинг через ссылку на сайт");
    }

    @Override
    public void printContent() {
        while (true) {
            clear();
            printHeader();

            String url = CONSOLE_SCANNER.waitString("Введите адрес сайта (0, чтобы отменить)");
            if (url.equals("0")) break;

            Document document = null;
            try {
                document = Jsoup.connect(url)
                        .userAgent("Chrome/4.0.249.0 Safari/532.5")
                        .referrer("http://www.google.com")
                        .get();
            } catch (IOException | IllegalArgumentException e) {
                printError("Произошла ошибка подключения", e);
            }

            new ConsoleFrameParseHTML(document).printContent();
        }
    }
}
