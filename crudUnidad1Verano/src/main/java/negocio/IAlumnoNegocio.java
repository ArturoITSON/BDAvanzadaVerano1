/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package negocio;

import dtos.AlumnoTablaDTO;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IAlumnoNegocio {
 
    public List<AlumnoTablaDTO> buscarAlumnosTabla() throws NegocioException;
    
    public GuardarAlumnoDTO insertar(GuardarAlumnoDTO alumno) throws NegocioException;
    
    public AlumnoLecturaDTO obtenerPorId(int id) throws NegocioException;
    
    public EditarAlumnoDTO editar(EditarAlumnoDTO alumno) throws NegocioException;
    
    public EliminarAlumnoDTO eliminar(EliminarAlumnoDTO alumno) throws NegocioException;
    
    
}
