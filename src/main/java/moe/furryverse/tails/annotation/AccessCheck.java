package moe.furryverse.tails.annotation;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.exception.UnauthorizationException;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.security.Access;
import moe.furryverse.tails.service.AccessService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessCheck {
    Access[] access();

    boolean requiredLogin() default true;

    boolean requiredAllAccess() default true;

    @Aspect
    @Component
    @RequiredArgsConstructor
    @SuppressWarnings("all")
    public class AccessChecker {
        final AccessService accessService;
        static ObjectMapper mapper = new ObjectMapper();
        static JavaType listType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Access.class);

        @SneakyThrows
        @Around(value = "@annotation(moe.furryverse.tails.annotation.AccessCheck)")
        public Message<?> check(ProceedingJoinPoint point) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

            String authorization = request.getHeader(Resource.CustomHeader.AUTHORIZE_HEADER);
            if (authorization == null || !authorization.startsWith(Resource.CustomHeader.AUTHORIZE_HEADER_PREFIX)) {
                throw new UnauthorizationException(
                        Message.ExceptionMessage.NOT_FOUND_TOKEN_IN_REQUEST,
                        "token check service calling",
                        "GET",
                        null
                );
            }

            String token = authorization.substring(Resource.CustomHeader.AUTHORIZE_HEADER_PREFIX.length());

            // 获取注解所规定的权限
            MethodSignature signature = (MethodSignature) point.getSignature();
            Object target = point.getTarget();
            Method method = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
            AccessCheck annotation = method.getAnnotation(AccessCheck.class);

            List<Access> willBeCheck = List.of(annotation.access());

            // 检测权限
            if (!accessService.check(token, willBeCheck)) {
                throw new UnauthorizationException(
                        Message.ExceptionMessage.PERMISSION_DENIED,
                        "token check service calling",
                        "GET",
                        null
                );
            }

            // 校验完成 继续执行业务代码
            return (Message<?>) point.proceed();
        }

        private Class<?>[] getParameterTypes(ProceedingJoinPoint point) {
            Object[] args = point.getArgs();
            Class<?>[] parameterTypes = new Class<?>[args.length];
            for (int i = 0; i < args.length; i++) {
                parameterTypes[i] = args[i].getClass();
            }
            return parameterTypes;
        }
    }
}