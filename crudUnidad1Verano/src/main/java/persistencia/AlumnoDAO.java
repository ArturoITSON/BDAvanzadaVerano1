/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import dtos.AlumnoEntidadDTO;
import entidad.AlumnoEntidad;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public class AlumnoDAO implements IAlumnoDAO{
 
    
    private IConexionBD conexionBD;
    
    
    public AlumnoDAO(IConexionBD conexionBD){
        this.conexionBD = conexionBD;
    }

    
    
    @Override
    public List<AlumnoEntidad> buscarAlumnosTabla() throws PersistenciaException {
        
        try{
        
            List<AlumnoEntidad> alumnosLista = null;
        
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "SELECT idAlumno, nombres, apellidoPaterno, apellidoMaterno, eliminado, activo FROM alumnos";
            Statement comandoSQL = conexion.createStatement();
            ResultSet resultado = comandoSQL.executeQuery(codigoSQL);
            
            while (resultado.next()){
                if(alumnosLista == null){
                    alumnosLista = new ArrayList<>();
                }
                AlumnoEntidad alumno = this.convertirEntidad(resultado);
                alumnosLista.add(alumno);
            }
        
            conexion.close();
            
            return alumnosLista;
        }
        
        catch (SQLException ex){
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new PersistenciaException("ocurrio un error al leer la base de datos, intentelo de nuevo y si el error persiste");
        }   
        
    }
    
    
    private AlumnoEntidad convertirEntidad(ResultSet resultado) throws SQLException{
        int id = resultado.getInt("idAlumno");
        String nombre = resultado.getString("nombres");
        String paterno = resultado.getString("apellidoPaterno");
        String materno = resultado.getString("apellidoMaterno");
        boolean eliminado = resultado.getBoolean("eliminado");
        boolean activo = resultado.getBoolean("activo");
        
        return new AlumnoEntidad(id, nombre, paterno, materno, eliminado, activo);
    }

    @Override
    public AlumnoEntidad insertar(AlumnoEntidadDTO alumnoEntidadDTO) throws PersistenciaException {
        
        //
        // Mandarle una entidad no una dto
        //
        AlumnoEntidad alumnoEntidad = new AlumnoEntidad();
        alumnoEntidad.setApellidoMaterno(alumnoEntidadDTO.getApellidoMaterno());
        alumnoEntidad.setApellidoPaterno(alumnoEntidadDTO.getApellidoPaterno());
        alumnoEntidad.setNombres(alumnoEntidadDTO.getNombres());
        alumnoEntidad.setActivo(alumnoEntidadDTO.isActivo());
        
        
        try{
            Connection conexion = this.conexionBD.crearConexion();
            String codigoSQL = "INSERT INTO alumnos (nombres, apellidoPaterno, apellidoMaterno, activo) VALUES (?, ?, ?, ?);";
            
            // Prepara la sentencia SQL permitiendo obtenr las claves generadas automaticamente
            PreparedStatement preparedStatement = conexion.prepareStatement(codigoSQL, Statement.RETURN_GENERATED_KEYS);
            
            // Establecer los valores para los parametros ed lse sentencia SQL
            preparedStatement.setString(1, alumnoEntidad.getNombres());
            preparedStatement.setString(2, alumnoEntidad.getApellidoPaterno());
            preparedStatement.setString(3, alumnoEntidad.getApellidoMaterno());
            preparedStatement.setBoolean(4, alumnoEntidad.isActivo());
            
            // Ejecutar la sentencia SQL de insercion
            preparedStatement.executeUpdate();
            
            
            
            // Obtener los claves generadas automaticamente (por ejemplo, el ID del nuevo registro)
            ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()){
            // Imprimir el ID generado para el nuevo registro
            System.out.println(resultado.getInt(1));
        } 
            
            return alumnoEntidad;
        }
        
        catch(SQLException ex){
            // hacer uso de Logger
            System.out.println(ex.getMessage());
            throw new PersistenciaException("ocurrio un error al leer la base de datos, intentelo de nuevo y si el error persiste");   
        }
        
        
    }

    @Override
    public AlumnoEntidad obtenerPorId(int id) throws PersistenciaException {
        
        AlumnoEntidad alum = new AlumnoEntidad();
         try{
        
        // Establecer la conexion a la base de datos
        Connection conexion = this.conexionBD.crearConexion();
        
        
        // Sentencia SQL para seleccionar un alumno por su id
        String sentenciaSql = "SELECT * FROM alumnos WHERE idAlumno = (?) ";
        
        PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
        
        comandoSQL.setInt(1, id);
        
        ResultSet resultado = comandoSQL.executeQuery();
        
        resultado.next();
        
        AlumnoEntidad alumnoConsultado = new AlumnoEntidad(
            resultado.getInt(1),
            resultado.getString(2),
            resultado.getString(3),
            resultado.getString(4)   );
            
             System.out.println("21");
            return alumnoConsultado;
            
        }

         catch(SQLException ex){
             //Capturar y manejar cualquier excepcion SQL que ocurra
             System.out.println("Ocurrio un error " + ex.getMessage());
         }

         
        return alum;
    }

    @Override
    public AlumnoEntidad editar(AlumnoEntidad alumno) throws PersistenciaException {

        AlumnoEntidad alum = new AlumnoEntidad();

        alum.setActivo(alumno.isActivo());
        alum.setApellidoMaterno(alumno.getApellidoMaterno());
        alum.setApellidoPaterno(alumno.getApellidoPaterno());
        alum.setIdAlumno(alumno.getIdAlumno());
        alum.setNombres(alumno.getNombres());
        
        try{
//        

        Connection conexion = this.conexionBD.crearConexion();

        
        String sentenciaSql = "UPDATE alumnos set nombres=?, apellidoPaterno=?, apellidoMaterno=?, activo=? WHERE idAlumno =? ";

        PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
            
        PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);
    
        // Establecer los valores para los parametros ed lse sentencia SQL
        preparedStatement.setString(1, alum.getNombres());
        preparedStatement.setString(2, alum.getApellidoPaterno());
        preparedStatement.setString(3, alum.getApellidoMaterno());
        preparedStatement.setBoolean(4, alum.isActivo());
        preparedStatement.setInt(5, alum.getIdAlumno());
            
        // Ejecutar la sentencia SQL de insercion
        preparedStatement.executeUpdate();
        
        // Obtener los claves generadas automaticamente (por ejemplo, el ID del nuevo registro)
        ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()){
            // Imprimir el ID generado para el nuevo registro
            System.out.println(resultado.getInt(1));
        }
               
            
        return alum;    
 
//        // Establecer la conexion a la base de datos
//        Connection conexion = this.conexionBD.crearConexion();
//        
//        
//        // Sentencia SQL para seleccionar un alumno por su id
//        String sentenciaSql = "SELECT * FROM alumnos WHERE idAlumno = (?) ";
//        
//        PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
//        
//        comandoSQL.setInt(1, id);
//        
//        ResultSet resultado = comandoSQL.executeQuery();
//        
//        resultado.next();
//        
//        AlumnoEntidad alumnoConsultado = new AlumnoEntidad(
//            resultado.getInt(1),
//            resultado.getString(2),
//            resultado.getString(3),
//            resultado.getString(4)   );
//            
//             System.out.println("21");
//            return alumnoConsultado;
//            
        }
//
         catch(SQLException ex){
//             //Capturar y manejar cualquier excepcion SQL que ocurra
             System.out.println("Ocurrio un error " + ex.getMessage());
         }
//
//         
//        return alum;       
//        
        return null;
    }

    @Override
    public AlumnoEntidad eliminar(AlumnoEntidad alumno) throws PersistenciaException {
        AlumnoEntidad alum = new AlumnoEntidad();

        alum.setIdAlumno(alumno.getIdAlumno());

        
        try{
//        

        Connection conexion = this.conexionBD.crearConexion();

        
        String sentenciaSql = "DELETE from alumnos WHERE idAlumno =? ";

        PreparedStatement comandoSQL = conexion.prepareStatement(sentenciaSql);
            
        PreparedStatement preparedStatement = conexion.prepareStatement(sentenciaSql, Statement.RETURN_GENERATED_KEYS);
    
        // Establecer los valores para los parametros ed lse sentencia SQL
        preparedStatement.setInt(1, alum.getIdAlumno());
            
        // Ejecutar la sentencia SQL de insercion
        preparedStatement.executeUpdate();
        
        // Obtener los claves generadas automaticamente (por ejemplo, el ID del nuevo registro)
        ResultSet resultado = preparedStatement.getGeneratedKeys();
            while (resultado.next()){
            // Imprimir el ID generado para el nuevo registro
            System.out.println(resultado.getInt(1));
        }
               
            
        return alum;        
        
        
    }
        
        catch(SQLException ex){
             //Capturar y manejar cualquier excepcion SQL que ocurra
             System.out.println("Ocurrio un error " + ex.getMessage());
         }

        return null;
    }   
    

    
}
