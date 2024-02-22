package moe.furryverse.tails.annotation;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.exception.UnauthorizationException;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.security.Permission;
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
import java.util.Set;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionCheck {
    String[] access();

    boolean requiredLogin() default true;

    boolean requiredAllAccess() default true;

    @Aspect
    @Component
    @RequiredArgsConstructor
    @SuppressWarnings("all")
    public class AccessChecker {
        static ObjectMapper mapper = new ObjectMapper();
        static JavaType listType = mapper.getTypeFactory().constructParametricType(ArrayList.class, Permission.class);
        final AccessService accessService;

        @SneakyThrows
        @Around(value = "@annotation(moe.furryverse.tails.annotation.PermissionCheck)")
        public Object check(ProceedingJoinPoint point) {
            // 获取注解所规定的权限
            MethodSignature signature = (MethodSignature) point.getSignature();
            Object target = point.getTarget();
            Method method = target.getClass().getMethod(signature.getName(), signature.getParameterTypes());
            PermissionCheck annotation = method.getAnnotation(PermissionCheck.class);

            // 如果没有要求登录的话
            if (!annotation.requiredLogin()) {
                // 仍然需要检查是否存在 Token 并提取
                HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
                String authorization = request.getHeader(Resource.CustomHeader.AUTHORIZE_HEADER);
                if (authorization != null && authorization.startsWith(Resource.CustomHeader.AUTHORIZE_HEADER_PREFIX)) {
                    String token = authorization.substring(Resource.CustomHeader.AUTHORIZE_HEADER_PREFIX.length());

                    // 设置 Account ID 到请求中
                    request.setAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER, accessService.getAccountId(token));
                }

                return point.proceed();
            }

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
            Set<String> willBeCheck = Set.of(annotation.access());

            // 检测权限
            if (!annotation.requiredAllAccess()) {
                if (!accessService.check(token, willBeCheck)) {
                    throw new UnauthorizationException(
                            Message.ExceptionMessage.PERMISSION_DENIED,
                            "token check service calling",
                            "GET",
                            null
                    );
                }
            } else {
                for (String permission : willBeCheck) {
                    if (!accessService.check(token, permission)) {
                        throw new UnauthorizationException(
                                Message.ExceptionMessage.PERMISSION_DENIED,
                                "token check service calling",
                                "GET",
                                null
                        );
                    }
                }
            }

            // 设置 Account ID 到请求中
            request.setAttribute(Resource.CustomHeader.ACCOUNT_ID_HEADER, accessService.getAccountId(token));

            // 校验完成 继续执行业务代码
            return point.proceed();
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