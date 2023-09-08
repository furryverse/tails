package moe.furryverse.server.common.annotation;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import moe.furryverse.server.common.content.Resource;
import moe.furryverse.server.common.exception.UnauthorizationException;
import moe.furryverse.server.common.message.Message;
import moe.furryverse.server.common.security.Access;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessCheck {
    Access[] access();

    @Aspect
    @Component
    @SuppressWarnings("all")
    public class AccessChecker {
        static ObjectMapper mapper = new ObjectMapper();
        static JavaType listType = mapper.getTypeFactory().constructParametricType(ArrayList.class, String.class);

        @SneakyThrows
        @SuppressWarnings("DuplicatedCode")
        @Around(
                value = "@annotation(moe.furryverse.server.common.annotation.AccessCheck) && args(request)",
                argNames = "point,request"
        )
        public Message<?> check(ProceedingJoinPoint point, HttpServletRequest request) {
            String id = request.getHeader(Resource.CustomHeader.ACCOUNT_ID_HEADER);
            String access = request.getHeader(Resource.CustomHeader.ACCOUNT_ACCESS_HEADER);

            if (id == null) {
                throw new UnauthorizationException(Message.ExceptionMessage.NOT_FOUND_ACCOUNT_ID_IN_REQUEST, "token check service calling", "GET", null);
            }

            if (access == null) {
                throw new UnauthorizationException(Message.ExceptionMessage.NOT_FOUND_ACCOUNT_ACCESS_IN_REQUEST, "token check service calling", "GET", null);
            }

            String authorization = request.getHeader(Resource.CustomHeader.AUTHORIZE_HEADER);
            if (authorization == null) {
                throw new UnauthorizationException(Message.ExceptionMessage.NOT_FOUND_TOKEN_IN_REQUEST, "token check service calling", "GET", null);
            }

            String token = authorization.substring(authorization.indexOf(Resource.CustomHeader.AUTHORIZE_HEADER_PREFIX) + Resource.ExtendInfo.AUTHORIZE_BANNER_LENGTH);
            if (token.isBlank() || token.isEmpty()) {
                throw new UnauthorizationException(Message.ExceptionMessage.NOT_FOUND_TOKEN_IN_REQUEST, "token check service calling", "GET", null);
            }

            // 获取注解所规定的权限
            AccessCheck annotation = point
                    .getTarget()
                    .getClass()
                    .getMethod(point.getSignature().getName(), getParameterTypes(point))
                    .getAnnotation(AccessCheck.class);

            Access[] willBeCheck = annotation.access();

            // 检查权限
            List<Access> willBeMatch = mapper.readValue(access, listType);

            if (!willBeMatch.containsAll(willBeMatch)) {
                throw new UnauthorizationException(Message.ExceptionMessage.PERMISSION_DENIED, "token check service calling", "GET", null);
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