/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringEggGroup.SpringEggArtifact.Controladores;

import SpringEggGroup.SpringEggArtifact.Servicios.MiExcepcion;
import SpringEggGroup.SpringEggArtifact.Servicios.ServicioAutor;
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
@RequestMapping("/autores")
public class ControladorAutor {

    @Autowired
    ServicioAutor autorServ;

    @GetMapping("")
    public String listarAutores(ModelMap model) {
        model.addAttribute("autores", autorServ.listaAutores());
        return "lista-autores";
    }

    @GetMapping("/formulario")
    public String formularioAutor(ModelMap model) {
        return "formulario-autores";
    }

    @PostMapping("/formulario")
    public String guardarAutor(ModelMap model, @RequestParam String apellido, @RequestParam String nombre) {
        try {
            autorServ.registrarAutor(apellido, nombre);
            model.addAttribute("exito", "Autor registrado");
            return "formulario-autores";
        } catch (MiExcepcion ex) {
            model.addAttribute("error1", ex.getMessage());
            return "formulario-autores";
        } catch (Exception e) {
            model.addAttribute("error2", "Hubo un error en el registro");
            return "formulario-autores";
        }
    }

    @GetMapping("/borrar/{id}")
    public String borrarAutor(ModelMap model, @PathVariable String id) {
        try {
            autorServ.borrarAutor(id);
            return "redirect:/autores";
        } catch (Exception e) {
            model.addAttribute("autores", autorServ.listaAutores());
            model.addAttribute("borrado", "SI");
            return "lista-autores";
        }
    }
}
