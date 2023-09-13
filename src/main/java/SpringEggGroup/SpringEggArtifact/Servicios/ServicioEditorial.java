/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringEggGroup.SpringEggArtifact.Servicios;

import SpringEggGroup.SpringEggArtifact.Entidades.Editorial;
import SpringEggGroup.SpringEggArtifact.Repositorios.RepositorioEditorial;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USUARIO
 */
@Service
public class ServicioEditorial {

    @Autowired
    RepositorioEditorial EditorialRepo;

    public Editorial buscarEditorialPorID(String id) {
        return EditorialRepo.getById(id);
    }

    public void registrarEditorial(String nombre) throws MiExcepcion {
        try {
            validarDatos(nombre);
            Editorial editorialNueva = new Editorial();
            editorialNueva.setNombre(nombre);
            EditorialRepo.save(editorialNueva);
        } catch (MiExcepcion e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void borrarEditorial(String id) {
        EditorialRepo.deleteById(id);
    }

    @Transactional
    public void modificarEditorial(String nombre) throws MiExcepcion {
        try {
            validarDatos(nombre);
            Editorial editorialNueva = new Editorial();
            editorialNueva.setNombre(nombre);
            EditorialRepo.save(editorialNueva);
        } catch (MiExcepcion e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public List<Editorial> listaEditoriales() {
        return EditorialRepo.listaEditoriales();
    }

    public void validarDatos(String nombre) throws MiExcepcion {
        try {
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
