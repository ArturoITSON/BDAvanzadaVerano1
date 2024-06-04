/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import dtos.AlumnoEntidadDTO;
import entidad.AlumnoEntidad;
import java.util.List;

/**
 *
 * @author Arturo ITSON
 */
public interface IAlumnoDAO {
    
    public List<AlumnoEntidad> buscarAlumnosTabla() throws PersistenciaException;
    
    public AlumnoEntidad insertar(AlumnoEntidadDTO alumnoEntidadDTO) throws PersistenciaException;
    
    public AlumnoEntidad obtenerPorId(int id) throws PersistenciaException;
    
    public AlumnoEntidad editar(AlumnoEntidad alumno) throws PersistenciaException;
    
    public AlumnoEntidad eliminar(AlumnoEntidad alumno) throws PersistenciaException;
}
