/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringEggGroup.SpringEggArtifact.Controladores;

import SpringEggGroup.SpringEggArtifact.Entidades.Autor;
import SpringEggGroup.SpringEggArtifact.Entidades.Editorial;
import SpringEggGroup.SpringEggArtifact.Servicios.MiExcepcion;
import SpringEggGroup.SpringEggArtifact.Servicios.ServicioAutor;
import SpringEggGroup.SpringEggArtifact.Servicios.ServicioEditorial;
import SpringEggGroup.SpringEggArtifact.Servicios.ServicioLibro;
import java.util.List;
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
@RequestMapping("/libros")
public class ControladorLibro {

    @Autowired
    ServicioLibro libroserv;

    @Autowired
    ServicioAutor autorserv;

    @Autowired
    ServicioEditorial editorialserv;

    @GetMapping("")
    public String listarLibros(ModelMap model) {
        model.addAttribute("libros", libroserv.listaLibros());
        return "lista-libros";
    }

    @GetMapping("/formulario")
    public String formularioLibro(ModelMap model) {
        model.addAttribute("editoriales", editorialserv.listaEditoriales());
        model.addAttribute("autores", autorserv.listaAutores());
        return "formulario-libros";
    }

    @PostMapping("/formulario")
    public String guardarLibro(ModelMap model, @RequestParam Long ISBN, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer EjemplaresT, @RequestParam Integer EjemplaresP, @RequestParam String Autor, @RequestParam String Editorial) {
        try {
            libroserv.registrarLibro(ISBN, titulo, anio, EjemplaresT, EjemplaresP, Autor, Editorial);
            model.addAttribute("exito", "Libro registrado");
            model.addAttribute("editoriales", editorialserv.listaEditoriales());
            model.addAttribute("autores", autorserv.listaAutores());
            return "formulario-libros";
        } catch (MiExcepcion ex) {
            model.addAttribute("error1", ex.getMessage());
            model.addAttribute("editoriales", editorialserv.listaEditoriales());
            model.addAttribute("autores", autorserv.listaAutores());
            return "formulario-libros";
        } catch (Exception e) {
            model.addAttribute("error2", "Hubo un error en el registro");
            model.addAttribute("editoriales", editorialserv.listaEditoriales());
            model.addAttribute("autores", autorserv.listaAutores());
            return "formulario-libros";
        }
    }

    @GetMapping("/modificar/{id}")
    public String modificarLibro(ModelMap model, @PathVariable String id) {
        model.addAttribute("libro", libroserv.buscarLibroPorId(id));
        List<Editorial> ListaEditoriales = editorialserv.listaEditoriales();
        List<Autor> ListaAutores = autorserv.listaAutores();
        ListaAutores.remove(libroserv.buscarLibroPorId(id).getAutor());
        ListaEditoriales.remove(libroserv.buscarLibroPorId(id).getEditorial());
        model.addAttribute("editoriales", ListaEditoriales);
        model.addAttribute("autores", ListaAutores);
        return "modificar-libro";
    }

    @PostMapping("/modificar/{id}")
    public String guardarLibroModificado(@PathVariable String id, ModelMap model, @RequestParam Long ISBN, @RequestParam String titulo, @RequestParam Integer anio, @RequestParam Integer EjemplaresT, @RequestParam Integer EjemplaresP, @RequestParam String Autor, @RequestParam String Editorial) {
        try {
            libroserv.modificarLibro(id, ISBN, titulo, anio, EjemplaresT, EjemplaresP, Autor, Editorial);
            model.addAttribute("exito", "Libro modificado");
            model.addAttribute("libro", libroserv.buscarLibroPorId(id));
            List<Editorial> ListaEditoriales = editorialserv.listaEditoriales();
            List<Autor> ListaAutores = autorserv.listaAutores();
            ListaAutores.remove(libroserv.buscarLibroPorId(id).getAutor());
            ListaEditoriales.remove(libroserv.buscarLibroPorId(id).getEditorial());
            model.addAttribute("editoriales", ListaEditoriales);
            model.addAttribute("autores", ListaAutores);
            return "modificar-libro";
        } catch (MiExcepcion ex) {
            model.addAttribute("error1", ex.getMessage());
            model.addAttribute("libro", libroserv.buscarLibroPorId(id));
            List<Editorial> ListaEditoriales = editorialserv.listaEditoriales();
            List<Autor> ListaAutores = autorserv.listaAutores();
            ListaAutores.remove(libroserv.buscarLibroPorId(id).getAutor());
            ListaEditoriales.remove(libroserv.buscarLibroPorId(id).getEditorial());
            model.addAttribute("editoriales", ListaEditoriales);
            model.addAttribute("autores", ListaAutores);
            return "modificar-libro";
        } catch (Exception e) {
            model.addAttribute("error2", "Hubo un error en la modificación del libro");
            model.addAttribute("libro", libroserv.buscarLibroPorId(id));
            List<Editorial> ListaEditoriales = editorialserv.listaEditoriales();
            List<Autor> ListaAutores = autorserv.listaAutores();
            ListaAutores.remove(libroserv.buscarLibroPorId(id).getAutor());
            ListaEditoriales.remove(libroserv.buscarLibroPorId(id).getEditorial());
            model.addAttribute("editoriales", ListaEditoriales);
            model.addAttribute("autores", ListaAutores);
            return "modificar-libro";
        }
    }

    @GetMapping("/borrar/{id}")
    public String borrarLibro(ModelMap model, @PathVariable String id) {
        try {
            libroserv.borrarLibro(id);
            return "redirect:/libros";
        } catch (Exception e) {
            return "redirect:/libros";
        }
    }

}
