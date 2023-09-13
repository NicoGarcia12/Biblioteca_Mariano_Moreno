/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringEggGroup.SpringEggArtifact.Controladores;

import SpringEggGroup.SpringEggArtifact.Servicios.MiExcepcion;
import SpringEggGroup.SpringEggArtifact.Servicios.ServicioEditorial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author USUARIO
 */
@Controller
@RequestMapping("/editoriales")
public class ControladorEditorial {

    @Autowired
    ServicioEditorial editorialServ;

    @GetMapping("")
    public String listarEditoriales(ModelMap model) {
        model.addAttribute("editoriales", editorialServ.listaEditoriales());
        return "lista-editoriales";
    }

    @GetMapping("/formulario")
    public String formularioEditorial(ModelMap model) {
        return "formulario-editoriales";
    }

    @PostMapping("/formulario")
    public String guardarEditorial(ModelMap model, @RequestParam String nombre) {
        try {
            editorialServ.registrarEditorial(nombre);
            model.addAttribute("exito", "Editorial registrada");
            return "formulario-editoriales";
        } catch (MiExcepcion ex) {
            model.addAttribute("error1", ex.getMessage());
            return "formulario-editoriales";
        } catch (Exception e) {
            model.addAttribute("error2", "Hubo un error en el registro");
            return "formulario-editoriales";
        }
    }

    @GetMapping("/borrar/{id}")
    public String borrarEditorial(ModelMap model, @PathVariable String id) {
        try {
            editorialServ.borrarEditorial(id);
            return "redirect:/editoriales";
        } catch (Exception e) {
            model.addAttribute("editoriales", editorialServ.listaEditoriales());
            model.addAttribute("borrado", "SI");
            return "lista-editoriales";
        }

    }
}
