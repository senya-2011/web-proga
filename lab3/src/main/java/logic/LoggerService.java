package logic;
import jakarta.ejb.Singleton;
import qualifiers.DecoratorQualifier;
import java.util.logging.Logger;

@DecoratorQualifier
@Singleton
public class LoggerService implements LoggerInterface{

    private final Logger logger = Logger.getLogger(LoggerService.class.getName());

    @Override
    public void logInfo(String message) {logger.info(message);}
    @Override
    public void logWarning(String message) {
        logger.warning(message);
    }
    @Override
    public void logError(String message) {
        logger.severe(message);
    }
}
