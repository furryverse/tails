package moe.furryverse.tails;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TailsRunner implements ApplicationRunner {
    @Override
    @SuppressWarnings("all")
    public void run(ApplicationArguments args) {
        log.info(
                """
                                                
                        ___________      .__.__                 \s
                        \\__    ___/____  |__|  |   ______      \s
                          |    |  \\__  \\ |  |  |  /  ___/     \s
                          |    |   / __ \\|  |  |__\\___ \\     \s
                          |____|  (____  /__|____/____  >       \s
                                      \\/             \\/      \s
                                               
                        Furryverse Open Source.
                        EMail: opensource@furryverse.moe
                                        
                        """
        );
    }
}
