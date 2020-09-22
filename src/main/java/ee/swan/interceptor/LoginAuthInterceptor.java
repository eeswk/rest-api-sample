package ee.swan.interceptor;

import ee.swan.config.annotation.UserAuth;
import ee.swan.exception.AuthFailureException;
import ee.swan.web.dto.UserDto;
import java.lang.annotation.Annotation;
import java.util.Optional;
import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("login auth check..");
        UserAuth annotation = getAnnotation((HandlerMethod) handler, UserAuth.class);

        if (!ObjectUtils.isEmpty(annotation)) {
            String auth =  request.getHeader("x-auth-code");
            if (auth == null) {
                log.info("auth fail");
                throw new AuthFailureException("유효한 사용자가 아닙니다.");
            }

            request.setAttribute("userName", "test");

        }
        return true;
    }

    private <T extends Annotation> T getAnnotation(HandlerMethod handlerMethod, Class<T> annotationType) {
        return Optional.ofNullable(handlerMethod.getMethodAnnotation(annotationType))
                .orElse(handlerMethod.getBeanType().getAnnotation(annotationType));
    }
}
