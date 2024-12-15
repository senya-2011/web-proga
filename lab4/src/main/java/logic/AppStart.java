package logic;


import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Startup
@Singleton
public class AppStart {

    @Inject
    LoggerService loggerService;

    @PostConstruct
    public void init() {
        loggerService.logInfo("Start");
    }
}
