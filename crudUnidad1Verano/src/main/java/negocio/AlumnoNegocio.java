/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dtos.AlumnoEntidadDTO;
import dtos.AlumnoTablaDTO;
import entidad.AlumnoEntidad;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import persistencia.IAlumnoDAO;
import persistencia.PersistenciaException;

/**
 *
 * @author Arturo ITSON
 */
public class AlumnoNegocio implements IAlumnoNegocio {
    
    private IAlumnoDAO alumnoDAO;
    
    
    public AlumnoNegocio(IAlumnoDAO alumnoDAO){
        this.alumnoDAO = alumnoDAO;
    }

    
    
    @Override
    public List<AlumnoTablaDTO> buscarAlumnosTabla() throws NegocioException {
        
        try{
        
            List<AlumnoEntidad> alumnos = this.alumnoDAO.buscarAlumnosTabla();
            return this.convertirAlumnoTablaDTO(alumnos);
        }
            
        catch(PersistenciaException ex){
            // hacer uso del Logger
            System.out.println(ex.getMessage());
            throw new NegocioException(ex.getMessage());
        }   
    }
    
    
    private List<AlumnoTablaDTO> convertirAlumnoTablaDTO(List<AlumnoEntidad> alumnos) throws NegocioException{
    
        if(alumnos == null){
            throw new NegocioException("No se pudieron obtener los alumnos");
        }
        
        List<AlumnoTablaDTO> alumnosDTO = new ArrayList<>();
        for (AlumnoEntidad alumno : alumnos){
            AlumnoTablaDTO dto = new AlumnoTablaDTO();
            dto.setIdAlumno(alumno.getIdAlumno());
            dto.setNombres(alumno.getNombres());
            dto.setApellidoPaterno(alumno.getApellidoPaterno());
            dto.setApellidoMaterno(alumno.getApellidoMaterno());
            dto.setEstatus(alumno.isActivo() == true ? "Activo" : "Inactivo");
            alumnosDTO.add(dto);
        }
        
        return alumnosDTO;
        
        }

    @Override
    public GuardarAlumnoDTO insertar(GuardarAlumnoDTO alumno) throws NegocioException {
        
        AlumnoEntidadDTO alu = new AlumnoEntidadDTO();
        String nombre = alumno.getNombres();
        String apellidoP = alumno.getApellidoPaterno();
        String apellidoM = alumno.getApellidoMaterno(); 
        
        
        
        if(validarNombre(nombre) == true){
            if(validarApellido(apellidoP) == true){
                if(validarApellido(apellidoM) == true){
                    alu.setApellidoMaterno(apellidoM);
                    alu.setApellidoPaterno(apellidoP);
                    alu.setNombres(nombre);
                    alu.setActivo(alumno.isActivo());
                    
                    try {
                        alumnoDAO.insertar(alu);
                    } catch (PersistenciaException ex) {
                        
                    }
                }
            }
        }
        
      return alumno;
        
    }

    @Override
    public AlumnoLecturaDTO obtenerPorId(int id) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public EditarAlumnoDTO editar(EditarAlumnoDTO alumno) throws NegocioException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
        
        
    public boolean validarNombre(String nombre){
         Pattern pattern = Pattern.compile("^[\\s\\S]{1,30}$");
         
         Matcher matcher = pattern.matcher(nombre);

         return matcher.find();
      }  
      
      
      public boolean validarApellido(String apellido){
         Pattern pattern = Pattern.compile("^[\\s\\S]{1,20}$");
         
         Matcher matcher = pattern.matcher(apellido);

         return matcher.find(); 
      }
      
      
      
      
    }
