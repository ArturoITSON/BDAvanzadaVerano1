/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author Arturo ITSON
 */
public class EditarAlumnoDTO {
    
    private int id;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private boolean eliminado;
    private boolean activo;

    public EditarAlumnoDTO() {
    }

    public EditarAlumnoDTO(String nombres, String apellidoPaterno, String apellidoMaterno, boolean eliminado, boolean activo) {
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.eliminado = eliminado;
        this.activo = activo;
    }

    public EditarAlumnoDTO(int id, String nombres, String apellidoPaterno, String apellidoMaterno, boolean eliminado, boolean activo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.eliminado = eliminado;
        this.activo = activo;
    }

    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public boolean isEliminado() {
        return eliminado;
    }

    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    
    
}
