package moe.furryverse.tails.annotation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import moe.furryverse.tails.content.Resource;
import moe.furryverse.tails.exception.UnauthorizationException;
import moe.furryverse.tails.message.Message;
import moe.furryverse.tails.service.AccessService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Set;

@Qualifier
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface GetAccount {
    @SuppressWarnings("all")
    @RequiredArgsConstructor
    public class GetAccountInterceptor implements HandlerInterceptor {
        final AccessService accessService;

        @Override
        public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) {
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

            // 检查令牌
            if (!accessService.check(token, Set.of())) {
                throw new UnauthorizationException(
                        Message.ExceptionMessage.PERMISSION_DENIED,
                        "token check service calling",
                        "GET",
                        null
                );
            }

            // 将 Account ID 存储到请求属性中，以便后续的参数注入
            request.setAttribute("accountId", accessService.getAccountId(token));

            return true;
        }
    }
}
