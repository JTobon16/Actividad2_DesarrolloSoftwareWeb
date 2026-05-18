package com.unicartagena.actividad2_desarrollosoftwareweb.controller;

import com.unicartagena.actividad2_desarrollosoftwareweb.model.EntradaCine;
import com.unicartagena.actividad2_desarrollosoftwareweb.service.EntradaCineService;
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
@RequestMapping("/entradas-cine")
public class EntradaCineController {

    // Inyeccion del servicio
    private final EntradaCineService entradaCineService;

    // Constructor — Spring inyecta el servicio automaticamente
    public EntradaCineController(EntradaCineService entradaCineService) {
        this.entradaCineService = entradaCineService;
    }

    // GET /entradas-cine — Lista todas las entradas
    @GetMapping
    public String list(Model model) {
        // Envia la lista de entradas a la vista
        model.addAttribute("entradas", entradaCineService.findAll());
        return "entradas-cine/list"; // apunta a templates/entradas-cine/list.html
    }

    // GET /entradas-cine/create — Muestra formulario de creacion
    @GetMapping("/create")
    public String create(Model model) {
        // Envia una entrada vacia para el formulario
        model.addAttribute("entrada", new EntradaCine());
        return "entradas-cine/create"; // apunta a templates/entradas-cine/create.html
    }

    // POST /entradas-cine/create — Procesa el formulario de creacion
    @PostMapping("/create")
    public String store(@ModelAttribute EntradaCine entradaCine,
                        RedirectAttributes flash) {
        // Genera un UUID como ID unico
        entradaCine.setId(UUID.randomUUID().toString());
        // Establece fechas de creacion y actualizacion
        entradaCine.setCreatedAt(LocalDateTime.now());
        entradaCine.setUpdatedAt(LocalDateTime.now());
        // Guarda la entrada en la BD
        entradaCineService.save(entradaCine);
        flash.addFlashAttribute("success", "Entrada creada correctamente");
        return "redirect:/entradas-cine"; // redirige a la lista
    }

    // GET /entradas-cine/{id} — Muestra detalle de una entrada
    @GetMapping("/{id}")
    public String show(@PathVariable String id, Model model) {
        // Busca la entrada por ID
        Optional<EntradaCine> entrada = entradaCineService.findById(id);
        if (entrada.isEmpty()) {
            return "redirect:/entradas-cine";
        }
        // Envia la entrada a la vista
        model.addAttribute("entrada", entrada.get());
        return "entradas-cine/show"; // apunta a templates/entradas-cine/show.html
    }

    // GET /entradas-cine/{id}/edit — Muestra formulario de edicion
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable String id, Model model) {
        // Busca la entrada por ID
        Optional<EntradaCine> entrada = entradaCineService.findById(id);
        if (entrada.isEmpty()) {
            return "redirect:/entradas-cine";
        }
        // Envia la entrada a la vista
        model.addAttribute("entrada", entrada.get());
        return "entradas-cine/edit"; // apunta a templates/entradas-cine/edit.html
    }

    // POST /entradas-cine/{id}/edit — Procesa el formulario de edicion
    @PostMapping("/{id}/edit")
    public String update(@PathVariable String id,
                         @ModelAttribute EntradaCine entradaCine,
                         RedirectAttributes flash) {
        // Verifica que la entrada exista
        if (!entradaCineService.existsById(id)) {
            return "redirect:/entradas-cine";
        }
        // Mantiene el mismo ID
        entradaCine.setId(id);
        // Actualiza la fecha de modificacion
        entradaCine.setUpdatedAt(LocalDateTime.now());
        // Guarda los cambios en la BD
        entradaCineService.save(entradaCine);
        flash.addFlashAttribute("success", "Entrada actualizada correctamente");
        return "redirect:/entradas-cine";
    }

    // POST /entradas-cine/{id}/delete — Elimina una entrada
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable String id, RedirectAttributes flash) {
        // Verifica que la entrada exista
        if (!entradaCineService.existsById(id)) {
            flash.addFlashAttribute("error", "Entrada no encontrada");
            return "redirect:/entradas-cine";
        }
        // Elimina la entrada de la BD
        entradaCineService.deleteById(id);
        flash.addFlashAttribute("success", "Entrada eliminada correctamente");
        return "redirect:/entradas-cine";
    }
}