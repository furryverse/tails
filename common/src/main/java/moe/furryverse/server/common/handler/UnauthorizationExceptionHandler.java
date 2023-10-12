package moe.furryverse.server.common.handler;

import lombok.extern.slf4j.Slf4j;
import moe.furryverse.server.common.exception.UnauthorizationException;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.utils.Time;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
@SuppressWarnings("SpellCheckingInspection")
public class UnauthorizationExceptionHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnauthorizationException.class)
    public Message<?> emailVerifyExceptionHandler(UnauthorizationException e) {
        log.info(
                String.format("unauthorization request %s %s", e.getMethod(), e.getPath())
        );

        return new Message<>(403, Time.getMilliUnixTime(), e.getMessage(), null);
    }
}
