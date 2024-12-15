package logic;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.util.logging.Logger;

@ApplicationScoped
@Named
public class LoggerService {
    private final Logger logger = Logger.getLogger(LoggerService.class.getName());

    public void logInfo(String message) {logger.info(message);}
    public void logWarning(String message) {
        logger.warning(message);
    }
    public void logError(String message) {
        logger.severe(message);
    }
}
