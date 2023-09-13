/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SpringEggGroup.SpringEggArtifact.Servicios;

/**
 *
 * @author USUARIO
 */
public class MiExcepcion extends Exception {

    /**
     * Creates a new instance of <code>MiException</code> without detail
     * message.
     */
    public MiExcepcion() {
    }

    /**
     * Constructs an instance of <code>MiException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public MiExcepcion(String msg) {
        super(msg);
    }
}
