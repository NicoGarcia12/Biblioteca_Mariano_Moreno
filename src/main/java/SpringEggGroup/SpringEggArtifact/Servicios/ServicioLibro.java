/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringEggGroup.SpringEggArtifact.Servicios;

import SpringEggGroup.SpringEggArtifact.Entidades.Libro;
import SpringEggGroup.SpringEggArtifact.Repositorios.RepositorioLibro;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author USUARIO
 */
@Service
public class ServicioLibro {

    @Autowired
    private RepositorioLibro libroRepo;

    @Autowired
    private ServicioAutor sa;

    @Autowired
    private ServicioEditorial se;

    @Transactional
    public void registrarLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, String autor, String editorial) throws MiExcepcion {
        try {
            validarDatos(1,isbn, titulo, anio, ejemplares, ejemplaresPrestados);
            Libro libroNuevo = new Libro();
            libroNuevo.setIsbn(isbn);
            libroNuevo.setTitulo(titulo);
            libroNuevo.setAnio(anio);
            libroNuevo.setEjemplares(ejemplares);
            libroNuevo.setEjemplaresPrestados(ejemplaresPrestados);
            libroNuevo.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
            libroNuevo.setAutor(sa.buscarAutorPorID(autor));
            libroNuevo.setEditorial(se.buscarEditorialPorID(editorial));
            libroRepo.save(libroNuevo);
        } catch (MiExcepcion e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Transactional
    public void borrarLibro(String id) {
        libroRepo.deleteById(id);
    }

    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, String autor, String editorial) throws MiExcepcion {
        try {
            validarDatos(2,isbn, titulo, anio, ejemplares, ejemplaresPrestados);
            Libro libroModificado = buscarLibroPorId(id);
            libroModificado.setIsbn(isbn);
            libroModificado.setTitulo(titulo);
            libroModificado.setAnio(anio);
            libroModificado.setEjemplares(ejemplares);
            libroModificado.setEjemplaresPrestados(ejemplaresPrestados);
            libroModificado.setEjemplaresRestantes(ejemplares - ejemplaresPrestados);
            libroModificado.setAutor(sa.buscarAutorPorID(autor));
            libroModificado.setEditorial(se.buscarEditorialPorID(editorial));
            libroRepo.save(libroModificado);
        } catch (MiExcepcion e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Transactional
    public List<Libro> listaLibros(){
        return libroRepo.findAll();
    }
    
    @Transactional
    public Libro buscarLibroPorId(String id){
        return libroRepo.getOne(id);
    }

    public void validarDatos(Integer accion, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados) throws MiExcepcion {
        try {
            List <Libro> libroValidado = libroRepo.buscarLibroPorISBN(isbn);
            if (isbn.toString().length()<13 || isbn.toString().length()>13) {
                throw new MiExcepcion("El ISBN tiene que tener 13 dígitos");
            }
            if (libroValidado.isEmpty()==false && accion==1) {
                throw new MiExcepcion("El ISBN ya está registrado");
            }
            if (titulo == null || titulo.trim().isEmpty()) {
                throw new MiExcepcion("El título no puede estar vacío");
            }
            int anioActual = LocalDate.now().getYear();
            if (anio > anioActual) {
                throw new MiExcepcion("El año no puede ser un año que aún no ha pasado");
            }
            if (ejemplares <= 0) {
                throw new MiExcepcion("Los ejemplares no pueden ser iguales a 0");
            }
            if (ejemplaresPrestados < 0 || ejemplaresPrestados > ejemplares) {
                throw new MiExcepcion("Los ejemplares prestados no pueden ser más que los ejemplares totales");
            }
        } catch (MiExcepcion e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
