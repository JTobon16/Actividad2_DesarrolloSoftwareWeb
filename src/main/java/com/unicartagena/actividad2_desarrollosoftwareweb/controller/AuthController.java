package com.unicartagena.actividad2_desarrollosoftwareweb.controller;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.User;
import com.unicartagena.actividad2_desarrollosoftwareweb.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // GET /auth/login — Muestra el formulario de login
    @GetMapping("/login")
    public String login(HttpSession session) {
        // Si ya hay sesion activa va directo al home
        if (session.getAttribute("userSession") != null) {
            return "redirect:/";
        }
        return "auth/login";
    }

    // POST /auth/login — Procesa el login
    @PostMapping("/login")
    public String processLogin(@RequestParam String email,
                               @RequestParam String password,
                               HttpSession session,
                               Model model) {

        // Busca el usuario por email
        Optional<User> userOpt = userService.findByEmail(email);

        // Si no existe el usuario
        if (userOpt.isEmpty()) {
            model.addAttribute("error", "Credenciales incorrectas");
            return "auth/login";
        }

        User user = userOpt.get();

        // Verifica la contraseña
        if (!user.getPassword().equals(password)) {
            model.addAttribute("error", "Credenciales incorrectas");
            return "auth/login";
        }

        // Guarda el usuario en la sesion
        session.setAttribute("userSession", user.getName());
        session.setAttribute("userRole", user.getRole());
        session.setAttribute("userId", user.getId());

        return "redirect:/";
    }

    // POST /auth/logout — Cierra la sesion
    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes flash) {
        // Destruye la sesion
        session.invalidate();
        flash.addFlashAttribute("success", "Sesión cerrada correctamente");
        return "redirect:/auth/login";
    }
}