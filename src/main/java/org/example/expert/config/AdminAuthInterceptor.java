package org.example.expert.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.example.expert.domain.user.enums.UserRole;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalDateTime;

@Slf4j
@Component
public class AdminAuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserRole userRole = UserRole.of((String) request.getAttribute("userRole"));
        if (!userRole.equals(UserRole.ADMIN)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자가 아닙니다.");
            return false;
        }

        log.info("[ADMIN 인증] time:{}, method:{}, url:{}",
                LocalDateTime.now(),
                request.getMethod(),
                request.getRequestURI()
        );

        return true;
    }
}
