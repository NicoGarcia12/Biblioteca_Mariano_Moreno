/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringEggGroup.SpringEggArtifact.Servicios;

import SpringEggGroup.SpringEggArtifact.Entidades.Autor;
import SpringEggGroup.SpringEggArtifact.Repositorios.RepositorioAutor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USUARIO
 */
@Service
public class ServicioAutor {

    @Autowired
    RepositorioAutor AutorRepo;

    public Autor buscarAutorPorID(String id) {
        return AutorRepo.getById(id);
    }

    public void registrarAutor(String apellido, String nombre) throws MiExcepcion {
        try {
            validarDatos(apellido, nombre);
            Autor autorNuevo = new Autor();
            autorNuevo.setNombre(nombre);
            autorNuevo.setApellido(apellido);
            AutorRepo.save(autorNuevo);
        } catch (MiExcepcion e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void borrarAutor(String id) {
        AutorRepo.deleteById(id);
    }

    @Transactional
    public void modificarAutor(String apellido, String nombre) throws MiExcepcion {
        try {
            validarDatos(apellido, nombre);
            Autor autorNuevo = new Autor();
            autorNuevo.setApellido(apellido);
            autorNuevo.setNombre(nombre);
            AutorRepo.save(autorNuevo);
        } catch (MiExcepcion e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public List<Autor> listaAutores() {
        return AutorRepo.listaAutores();
    }

    public void validarDatos(String apellido, String nombre) throws MiExcepcion {
        try {
            if (apellido == null || apellido.trim().isEmpty()) {
                throw new MiExcepcion("El apellido no puede estar vacío");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new MiExcepcion("El nombre no puede estar vacío");
            }
        } catch (MiExcepcion e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
