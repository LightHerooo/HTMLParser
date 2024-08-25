package ru.herooo.projects.htmlparser.consoleframes.content;

import ru.herooo.projects.htmlparser.consoleframes.ConsoleFrameHTMLParserAbstract;
import ru.herooo.libs.easyconsole.consoleframe.ConsoleFrameAbstract;

public class ConsoleFrameMain extends ConsoleFrameHTMLParserAbstract {
    public ConsoleFrameMain() {
        super("Главное меню");
    }

    @Override
    public void printContent() {
        while (true) {
            clear();
            printHeader();

            ConsoleFrameAbstract[] consoleFrames = new ConsoleFrameAbstract[] {
                    new ConsoleFrameParseByURL(),
                    new ConsoleFrameParseByFile()
            };
            printMenu(consoleFrames, "Выход", false, true);

            int choice = CONSOLE_SCANNER.waitChoice();
            if (choice == 0) break;

            choice--;
            if (choice >= 0 && choice < consoleFrames.length) {
                ConsoleFrameAbstract consoleFrame = consoleFrames[choice];
                if (consoleFrame != null) {
                    consoleFrame.printContent();
                }
            }
        }
    }
}
