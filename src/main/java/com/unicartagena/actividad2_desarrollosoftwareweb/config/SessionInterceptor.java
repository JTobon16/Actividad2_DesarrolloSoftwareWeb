package com.unicartagena.actividad2_desarrollosoftwareweb.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// Intercepta todas las peticiones y verifica si hay sesion activa
@Component
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

        // Rutas publicas que no requieren sesion
        boolean isPublic = uri.startsWith("/auth") ||
                uri.startsWith("/css") ||
                uri.startsWith("/js") ||
                uri.startsWith("/images") ||
                uri.equals("/users/create") ||
                uri.equals("/") ;

        // Si es ruta publica deja pasar
        if (isPublic) {
            return true;
        }

        // Si hay sesion activa deja pasar
        if (session != null && session.getAttribute("userSession") != null) {
            return true;
        }

        // Si no hay sesion redirige al login
        response.sendRedirect("/auth/login");
        return false;
    }
}