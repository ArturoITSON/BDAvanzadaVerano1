/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

/**
 *
 * @author Arturo ITSON
 */

import java.sql.Connection;
import java.sql.SQLException;


public interface IConexionBD {
    
    public Connection crearConexion() throws SQLException;
}
