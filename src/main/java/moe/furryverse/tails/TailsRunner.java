/*
 * Copyright 2024 FurryVerse
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *           http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
