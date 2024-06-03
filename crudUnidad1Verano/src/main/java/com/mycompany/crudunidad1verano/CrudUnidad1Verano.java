/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.crudunidad1verano;

import negocio.AlumnoNegocio;
import negocio.IAlumnoNegocio;
import persistencia.AlumnoDAO;
import persistencia.ConexionBD;
import persistencia.IAlumnoDAO;
import persistencia.IConexionBD;
import presentacion.frmCRUD;

/**
 *
 * @author Arturo ITSON
 */
public class CrudUnidad1Verano {

    public static void main(String[] args) {
        
//            AlumnosTemporal at = new AlumnosTemporal();
//        
//           // at.insertar();
//            at.leer();
        // CAPA persistencia
        IConexionBD ConexionBD = new ConexionBD();
        IAlumnoDAO alumnoDAO = new AlumnoDAO(ConexionBD);
        
        // CAPA negocio
        IAlumnoNegocio alumnoNegocio = new AlumnoNegocio(alumnoDAO);



        frmCRUD iniciar = new frmCRUD(alumnoNegocio);
        iniciar.setVisible(true);
        
        
        
    }
    
   
    
}
