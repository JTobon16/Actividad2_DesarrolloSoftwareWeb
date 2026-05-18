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

// Le dice a Spring que esta clase es un controlador web
@Controller
// Prefijo base para todas las rutas de este controlador
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
        // Envia la lista de usuarios a la vista
        model.addAttribute("users", userService.findAll());
        return "users/list"; // apunta a templates/users/list.html
    }

    // GET /users/create — Muestra formulario de creacion
    @GetMapping("/create")
    public String create(Model model) {
        // Envia un usuario vacio para el formulario
        model.addAttribute("user", new User());
        return "users/create"; // apunta a templates/users/create.html
    }

    // POST /users/create — Procesa el formulario de creacion
    @PostMapping("/create")
    public String store(@ModelAttribute User user, RedirectAttributes flash) {
        // Genera un UUID como ID unico
        user.setId(UUID.randomUUID().toString());
        // Encripta la contraseña antes de guardar
        user.setPassword(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
                .class.cast(new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder())
                .encode(user.getPassword()));
        // Establece fechas de creacion y actualizacion
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());
        // Guarda el usuario en la BD
        userService.save(user);
        // Mensaje de exito para la vista
        flash.addFlashAttribute("success", "Usuario creado correctamente");
        return "redirect:/users"; // redirige a la lista
    }

    // GET /users/{id} — Muestra detalle de un usuario
    @GetMapping("/{id}")
    public String show(@PathVariable String id, Model model) {
        // Busca el usuario por ID
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return "redirect:/users"; // si no existe redirige a la lista
        }
        // Envia el usuario a la vista
        model.addAttribute("user", user.get());
        return "users/show"; // apunta a templates/users/show.html
    }

    // GET /users/{id}/edit — Muestra formulario de edicion
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        // Busca el usuario por ID
        Optional<User> user = userService.findById(id);
        if (user.isEmpty()) {
            return "redirect:/users";
        }
        // Envia el usuario a la vista
        model.addAttribute("user", user.get());
        return "users/edit"; // apunta a templates/users/edit.html
    }

    // POST /users/{id}/edit — Procesa el formulario de edicion
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id, @ModelAttribute User user,
                         RedirectAttributes flash) {
        // Verifica que el usuario exista
        if (!userService.existsById(id)) {
            return "redirect:/users";
        }
        // Mantiene el mismo ID
        user.setId(id);
        // Actualiza la fecha de modificacion
        user.setUpdatedAt(LocalDateTime.now());
        // Guarda los cambios en la BD
        userService.save(user);
        flash.addFlashAttribute("success", "Usuario actualizado correctamente");
        return "redirect:/users";
    }

    // POST /users/{id}/delete — Elimina un usuario
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes flash) {
        // Verifica que el usuario exista
        if (!userService.existsById(id)) {
            flash.addFlashAttribute("error", "Usuario no encontrado");
            return "redirect:/users";
        }
        // Elimina el usuario de la BD
        userService.deleteById(id);
        flash.addFlashAttribute("success", "Usuario eliminado correctamente");
        return "redirect:/users";
    }
}