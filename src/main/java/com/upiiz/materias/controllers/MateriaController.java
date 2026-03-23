package com.upiiz.materias.controllers;
import com.upiiz.materias.models.Materia;
import com.upiiz.materias.services.MateriaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/materias")
public class MateriaController {
    private final MateriaService materiaService;

    public MateriaController(MateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materias", materiaService.obtenerTodas());
        return "listado";
    }

    @GetMapping("/nueva")
    public String formularioCrear(Model model) {
        model.addAttribute("materia", new Materia());
        return "formulario-crear";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Materia materia) {
        materiaService.guardar(materia);
        return "redirect:/materias";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("materia", materiaService.obtenerPorId(id));
        return "formulario-actualizar";
    }

    @GetMapping("/eliminar/{id}")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        model.addAttribute("materia", materiaService.obtenerPorId(id));
        return "formulario-eliminar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        materiaService.eliminar(id);
        return "redirect:/materias";
    }
}