/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringEggGroup.SpringEggArtifact.Repositorios;

import SpringEggGroup.SpringEggArtifact.Entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USUARIO
 */
@Repository
public interface RepositorioLibro extends JpaRepository<Libro, String> {

    @Query("SELECT l FROM Libro l WHERE l.titulo = :titulo")
    public List<Libro> buscarLibroPorTitulo(@Param("titulo") String titulo);

    @Query("SELECT l FROM Libro l WHERE l.isbn = :isbn")
    public List<Libro> buscarLibroPorISBN(@Param("isbn") Long isbn);

}
