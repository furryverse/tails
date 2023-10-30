package moe.furryverse.tails.annotation;

import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.security.Access;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WebSocketCheck {
    Access[] access();

    @Aspect
    @Component
    @RequiredArgsConstructor
    @SuppressWarnings("all")
    public class WebSocketChecker {
    }
}
