package pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogsPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(LogsPage.class);

    public LogsPage() {
        logger.info("Navigate to LogsPage");
    }

}