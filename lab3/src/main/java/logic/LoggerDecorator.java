package logic;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptor;
import qualifiers.DecoratorQualifier;

import java.util.Random;

@Priority(Interceptor.Priority.APPLICATION)
@Decorator
@Startup
public class LoggerDecorator implements LoggerInterface{

    private int checkSingleton;

    @DecoratorQualifier
    @Inject
    @Delegate
    LoggerInterface loggerService;

    @PostConstruct
    public void start(){
        logInfo("Logger started");
        Random random = new Random();
        checkSingleton = random.nextInt(100000);
    }

    @Override
    public void logInfo(String message) {
        loggerService.logInfo(String.format("%d : %s", checkSingleton, message));
    }

    @Override
    public void logWarning(String message) {
        loggerService.logWarning(message);
    }

    @Override
    public void logError(String message) {
        loggerService.logError(message);
    }
}
