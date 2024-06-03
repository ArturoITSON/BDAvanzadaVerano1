/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crudunidad1verano;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Arturo ITSON
 */
public class AlumnosTemporal {
    
    
    //Constantes para la conexion de la base de datos
    final String SERVER ="localhost"; // Servidor de la base de datos
    final String BASE_DATOS = "veranobd"; // Nombre de la base de datos
    final String CADENA_CONEXION = "jdbc:mysql://" + SERVER + "/" + BASE_DATOS; // URL de conexion a la base de datos
    final String USUARIO = "root"; // Usuario de la base de datos
    final String CONTRASEÑA = "1234"; // Contraseña del usuario de la base de datos


public void insertar(){

    try{
        
        // Establecer la conexion a la base de datos
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
    
        // Sentencia SQL para insertar un nuevo alumno en la tabla 'alumnos'
        String sentenciaSql = "INSERT INTO alumnos (nombres, apellidoPaterno, apellidoMaterno) VALUES (?, ?, ?);";
    
        // Prepara la sentencia SQL permitiendo obtenr las claves generadas automaticamente
        PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);
        
        // Establecer los valores para los parametros ed lse sentencia SQL
        preparedStatement.setString(1, "Sergio Arturo");
        preparedStatement.setString(2, "Garcia");
        preparedStatement.setString(3, "Ramirez");
        
        
        // Ejecutar la sentencia SQL de insercion
        preparedStatement.executeUpdate();
        
        
        // Obtener los claves generadas automaticamente (por ejemplo, el ID del nuevo registro)
        ResultSet resultado = preparedStatement.getGeneratedKeys();
        while (resultado.next()){
            // Imprimir el ID generado para el nuevo registro
            System.out.println(resultado.getInt(1));
        } 
        
    }

    catch (SQLException ex){
        // Capturar y manejar cualquier excepcion SQL que ocurra
        System.out.println("Ocurrio un error " + ex.getMessage());
    }
  
}



    public void leer(){
    
         try{
        
        // Establecer la conexion a la base de datos
        Connection conexion = DriverManager.getConnection(CADENA_CONEXION, USUARIO, CONTRASEÑA);
        
        
        // Sentencia SQL para seleccionar un alumno por su id
        String sentenciaSql = "SELECT * FROM alumnos WHERE idAlumno = ?;";
        
        // Preparar le sentencia SQL
        PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql);
        
        // Establecer los valores para los parametros de la sentencia sql
        preparedStatement.setInt(1, 1);
        
        //Ejecutar la sentencia SQL y obtener el resultado
        ResultSet resultado = preparedStatement.executeQuery();
        
        
        // Procesar el resultado
        while (resultado.next()){
            int idAlumno = resultado.getInt("idAlumno");
            String nombres = resultado.getString("nombres");
            String apellidoPaterno = resultado.getString("apellidoPaterno");
            String apellidoMaterno = resultado.getString("apellidoMaterno");
            boolean eliminado = resultado.getBoolean("eliminado");
            boolean activo = resultado.getBoolean("activo");
            
            
            System.out.println("ID del alumno: " + idAlumno);
            System.out.println("Nombres: " + nombres);
            System.out.println("apellidoPaterno: " + apellidoPaterno);
            System.out.println("apellidoMaterno: " + apellidoMaterno);
            System.out.println("Eliminado: " + eliminado);
            System.out.println("activo: " + activo);
        }

    }

         catch(SQLException ex){
             //Capturar y manejar cualquier excepcion SQL que ocurra
             System.out.println("Ocurrio un error " + ex.getMessage());
         }

    }




}
