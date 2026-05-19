package com.unicartagena.actividad2_desarrollosoftwareweb.controller;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.User;
import com.unicartagena.actividad2_desarrollosoftwareweb.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UserController {

    // Inyeccion del servicio
    private final UserService userService;

    // Constructor — Spring inyecta el servicio automaticamente
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /users — Lista todos los usuarios
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/list";
    }

    // GET /users/create — Muestra formulario de creacion
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "users/create";
    }

    // POST /users/create — Procesa el formulario de creacion
    @PostMapping("/create")
    public String store(@ModelAttribute User user, RedirectAttributes flash) {
        // Genera un UUID como ID unico
        user.setId(UUID.randomUUID().toString());
        // Establece fechas
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        // Guarda en la BD
        userService.save(user);
        flash.addFlashAttribute("success", "Usuario creado correctamente");
        return "redirect:/users";
    }

    // GET /users/{id} — Muestra detalle de un usuario
    @GetMapping("/{id}")
    public String show(@PathVariable String id, Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return "redirect:/users";
        }
        model.addAttribute("user", user.get());
        return "users/show";
    }

    // GET /users/{id}/edit — Muestra formulario de edicion
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return "redirect:/users";
        }
        model.addAttribute("user", user.get());
        return "users/edit";
    }

    // POST /users/{id}/edit — Procesa el formulario de edicion
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute User user,
                         RedirectAttributes flash) {
        if (!userService.existsById(id)) {
            return "redirect:/users";
        }
        // Mantiene el mismo ID
        user.setId(id);
        // Actualiza fecha de modificacion
        user.setUpdatedAt(LocalDateTime.now());
        userService.save(user);
        flash.addFlashAttribute("success", "Usuario actualizado correctamente");
        return "redirect:/users";
    }

    // POST /users/{id}/delete — Elimina un usuario
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes flash) {
        if (!userService.existsById(id)) {
            flash.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/users";
        }
        userService.deleteById(id);
        flash.addFlashAttribute("success", "Usuario eliminado correctamente");
        return "redirect:/users";
    }
}