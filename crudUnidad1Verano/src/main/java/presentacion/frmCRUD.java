/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package presentacion;

import dtos.AlumnoEntidadDTO;
import dtos.AlumnoTablaDTO;
import entidad.AlumnoEntidad;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import negocio.AlumnoLecturaDTO;
import negocio.EditarAlumnoDTO;
import negocio.GuardarAlumnoDTO;
import negocio.IAlumnoNegocio;
import negocio.NegocioException;
import persistencia.AlumnoDAO;
import persistencia.ConexionBD;
import persistencia.IAlumnoDAO;
import persistencia.IConexionBD;
import persistencia.PersistenciaException;
import utilerias.JButtonCellEditor;
import utilerias.JButtonRenderer;

/**
 *
 * @author Arturo ITSON
 */
public class frmCRUD extends javax.swing.JFrame {

    /**
     * Creates new form frmCRUD
     */
    
    private int pagina = 1;
    private final int LIMITE = 2;
    private IAlumnoNegocio alumnoNegocio;
    private IConexionBD ConexionBD = new ConexionBD();
    private IAlumnoDAO alumnoDAO = new AlumnoDAO(ConexionBD);
    private GuardarAlumnoDTO alumno = new GuardarAlumnoDTO();
    
    public frmCRUD(IAlumnoNegocio alumnoNegocio) {
        initComponents();
        
        this.alumnoNegocio = alumnoNegocio;
        this.cargarMetodosIniciales();
        
        
        lblPagina.setText("pagina " + Integer.toString(pagina));
        btnAtras.setEnabled(false);
        btnGuardar.setEnabled(false);
        btnEliminar.setVisible(false);
        btnHecho.setVisible(false);
    }

    private void cargarMetodosIniciales(){
        this.cargarConfiguracionInicialTablaAlumnos();
        this.cargarAlumnosEnTabla();
        
        
        btnCancelarRegistro.setVisible(false);
        btnGuardar.setVisible(false);
        jblMal.setVisible(false);
        jblMal1.setVisible(false);
        jblMal2.setVisible(false);
    }
    
    
    
    private void cargarConfiguracionInicialTablaAlumnos() {
        ActionListener onEditarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para editar un alumno
                editar();
            }
        };
        int indiceColumnaEditar = 5;
        TableColumnModel modeloColumnas = this.tblAlumnos.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellRenderer(new JButtonRenderer("----"));
        modeloColumnas.getColumn(indiceColumnaEditar)
                .setCellEditor(new JButtonCellEditor("----",
                        onEditarClickListener));

        ActionListener onEliminarClickListener = new ActionListener() {
            final int columnaId = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                //Metodo para eliminar un alumno
                eliminar();
            }
        };
        int indiceColumnaEliminar = 6;
        modeloColumnas = this.tblAlumnos.getColumnModel();
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellRenderer(new JButtonRenderer("Eliminar"));
        modeloColumnas.getColumn(indiceColumnaEliminar)
                .setCellEditor(new JButtonCellEditor("Eliminar",
                        onEliminarClickListener));
    }

    private int getIdSeleccionadoTablaAlumnos() {
        int indiceFilaSeleccionada = this.tblAlumnos.getSelectedRow();
        if (indiceFilaSeleccionada != -1) {
            DefaultTableModel modelo = (DefaultTableModel) this.tblAlumnos.getModel();
            int indiceColumnaId = 0;
            int idSocioSeleccionado = (int) modelo.getValueAt(indiceFilaSeleccionada,
                    indiceColumnaId);
            return idSocioSeleccionado;
        } else {
            return 0;
        }
    }

    private void editar() {
        //Metodo para regresar el alumno seleccionado
        int id = this.getIdSeleccionadoTablaAlumnos();
        btnActualizar.setVisible(false);

            
        
    }

    private void eliminar() {
        //Metodo para regresar el alumno seleccionado
        int id = this.getIdSeleccionadoTablaAlumnos();
    }
    
    private void llenarTablaAlumnos(List<AlumnoTablaDTO> alumnosLista) {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblAlumnos.getModel();

        if (modeloTabla.getRowCount() > 0) {
            for (int i = modeloTabla.getRowCount() - 1; i > -1; i--) {
                modeloTabla.removeRow(i);
            }
        }

        if (alumnosLista != null) {
            alumnosLista.forEach(row -> {
                Object[] fila = new Object[5];
                fila[0] = row.getIdAlumno();
                fila[1] = row.getNombres();
                fila[2] = row.getApellidoPaterno();
                fila[3] = row.getApellidoMaterno();
                fila[4] = row.getEstatus();

                modeloTabla.addRow(fila);
            });
        }
    }
     
    private void cargarAlumnosEnTabla() {
        try {
            List<AlumnoTablaDTO> alumnos = this.alumnoNegocio.buscarAlumnosTabla();
            this.llenarTablaAlumnos(alumnos);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Información", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoTextoNombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoTextoApellidoP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        campoTextoApellidoM = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        btnNuevoRegistro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAlumnos = new javax.swing.JTable();
        btnAtras = new javax.swing.JToggleButton();
        btnSiguiente = new javax.swing.JToggleButton();
        lblPagina = new javax.swing.JLabel();
        btnCancelarRegistro = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        jblMal = new javax.swing.JLabel();
        jblMal1 = new javax.swing.JLabel();
        jblMal2 = new javax.swing.JLabel();
        btnActualizar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnHecho = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Administracion de alumnos");

        jLabel2.setText("Nombres");

        campoTextoNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoNombreActionPerformed(evt);
            }
        });
        campoTextoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoTextoNombreKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoTextoNombreKeyReleased(evt);
            }
        });

        jLabel3.setText("Apellido Paterno");

        campoTextoApellidoP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoTextoApellidoPKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoTextoApellidoPKeyReleased(evt);
            }
        });

        jLabel4.setText("Apellido Materno");

        campoTextoApellidoM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoTextoApellidoMActionPerformed(evt);
            }
        });
        campoTextoApellidoM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                campoTextoApellidoMKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campoTextoApellidoMKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campoTextoApellidoMKeyTyped(evt);
            }
        });

        jCheckBox1.setText("Activo");

        btnNuevoRegistro.setText("Nuevo Registro");
        btnNuevoRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoRegistroActionPerformed(evt);
            }
        });

        tblAlumnos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombres", "A. Paterno", "A. Materno", "Estatus", "----", "Eliminar"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAlumnos);

        btnAtras.setText("Anterior");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnSiguiente.setText("Siguiente");

        lblPagina.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPagina.setText("Pagina 1");

        btnCancelarRegistro.setText("Cancelar");
        btnCancelarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarRegistroActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        jblMal.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jblMal.setForeground(new java.awt.Color(255, 0, 0));
        jblMal.setText("*");

        jblMal1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jblMal1.setForeground(new java.awt.Color(255, 0, 0));
        jblMal1.setText("*");

        jblMal2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jblMal2.setForeground(new java.awt.Color(255, 0, 0));
        jblMal2.setText("*");

        btnActualizar.setText("Editar");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");

        btnHecho.setText("Hecho");
        btnHecho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHechoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jblMal, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(39, 39, 39)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jblMal1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(campoTextoApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jblMal2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(campoTextoApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnNuevoRegistro))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(btnGuardar))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnCancelarRegistro))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnActualizar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnHecho)
                                                .addGap(32, 32, 32)
                                                .addComponent(btnEliminar)))
                                        .addGap(23, 23, 23))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(204, 204, 204)
                                .addComponent(lblPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 861, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnGuardar)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jblMal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jblMal1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jblMal2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(campoTextoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTextoApellidoP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(campoTextoApellidoM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1)
                            .addComponent(btnNuevoRegistro)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(btnCancelarRegistro)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnActualizar)
                    .addComponent(btnEliminar)
                    .addComponent(btnHecho))
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras)
                    .addComponent(btnSiguiente)
                    .addComponent(lblPagina))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campoTextoApellidoMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoApellidoMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoApellidoMActionPerformed

    private void campoTextoNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoTextoNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoTextoNombreActionPerformed

    private void btnNuevoRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoRegistroActionPerformed
        // TODO add your handling code here:
        btnCancelarRegistro.setVisible(true);
        btnGuardar.setVisible(true);
    }//GEN-LAST:event_btnNuevoRegistroActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        // TODO add your handling code here:
        
        if(pagina < 1){
            btnAtras.isSelected();
            btnAtras.setEnabled(false);
        }
        lblPagina.setText("pagina " + Integer.toString(pagina));
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnCancelarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarRegistroActionPerformed
        // TODO add your handling code here:
        btnGuardar.setVisible(false);
        btnCancelarRegistro.setVisible(false);
    }//GEN-LAST:event_btnCancelarRegistroActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        boolean activo = false;
        boolean eliminado = true;
        
        
        if(jCheckBox1.isSelected()){
            activo = true;
            eliminado = false;
        }
        
        GuardarAlumnoDTO alumno = new GuardarAlumnoDTO();
        alumno.setApellidoMaterno(campoTextoApellidoM.getText());
        alumno.setApellidoPaterno(campoTextoApellidoP.getText());
        alumno.setNombres(campoTextoNombre.getText());
        alumno.setActivo(activo);
        
           
        
        try {
            
            alumnoNegocio.insertar(alumno);
        }
        
         catch (NegocioException ex) {
             JOptionPane.showMessageDialog(this, "Datos incorrectos");
            Logger.getLogger(frmCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            
        this.cargarAlumnosEnTabla();
        }   
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void campoTextoNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTextoNombreKeyPressed
        // TODO add your handling code here:
 
    }//GEN-LAST:event_campoTextoNombreKeyPressed

    private void campoTextoApellidoPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTextoApellidoPKeyPressed
        // TODO add your handling code here:       
        
    }//GEN-LAST:event_campoTextoApellidoPKeyPressed

    private void campoTextoApellidoMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTextoApellidoMKeyPressed
        // TODO add your handling code here:
     
    }//GEN-LAST:event_campoTextoApellidoMKeyPressed

    private void campoTextoApellidoMKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTextoApellidoMKeyTyped
          
        
    }//GEN-LAST:event_campoTextoApellidoMKeyTyped

    private void campoTextoApellidoMKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTextoApellidoMKeyReleased
        // TODO add your handling code here:
        if(validarApellido(campoTextoApellidoM.getText()) == true){
            jblMal2.setVisible(false);
        }
        else{
            jblMal2.setVisible(true);
            btnGuardar.setEnabled(false);
        }
        
        if(validarNombre(campoTextoNombre.getText()) == true &&
                validarApellido(campoTextoApellidoM.getText()) == true &&
                validarApellido(campoTextoApellidoP.getText()) == true){
                btnGuardar.setEnabled(true);
        
        }   
    }//GEN-LAST:event_campoTextoApellidoMKeyReleased

    private void campoTextoApellidoPKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTextoApellidoPKeyReleased
        // TODO add your handling code here:
                if(validarApellido(campoTextoApellidoP.getText()) == true){
            jblMal1.setVisible(false);
        }
        else{
            jblMal1.setVisible(true);
            btnGuardar.setEnabled(false);
        }
        
        if(validarNombre(campoTextoNombre.getText()) == true &&
                validarApellido(campoTextoApellidoM.getText()) == true &&
                validarApellido(campoTextoApellidoP.getText()) == true){
                btnGuardar.setEnabled(true);
                
        }       
    }//GEN-LAST:event_campoTextoApellidoPKeyReleased

    private void campoTextoNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campoTextoNombreKeyReleased
        // TODO add your handling code here:
        
        if(validarNombre(campoTextoNombre.getText()) == true){
            jblMal.setVisible(false);
        }
        else{
            jblMal.setVisible(true);
            btnGuardar.setEnabled(false);
        }
        
        if(validarNombre(campoTextoNombre.getText()) == true &&
                validarApellido(campoTextoApellidoM.getText()) == true &&
                validarApellido(campoTextoApellidoP.getText()) == true){
                btnGuardar.setEnabled(true);
        
        }
    }//GEN-LAST:event_campoTextoNombreKeyReleased

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        // TODO add your handling code here:
        
        int id = 0;
        AlumnoLecturaDTO al = new AlumnoLecturaDTO();
        
        String texto = JOptionPane.showInputDialog("Ingrese el id");
        
        if (validarNumero(texto) == true){
        
        id = Integer.parseInt(texto);
        
        
        
        try{
           
            al = alumnoNegocio.obtenerPorId(id);
            
            if(al == null){
                JOptionPane.showMessageDialog(this, "No se encontro el id");
            }
            else{
            String textoNombres = al.getNombres();
           
            campoTextoApellidoM.setText(al.getApellidoMaterno());
            campoTextoApellidoP.setText(al.getApellidoPaterno());
            campoTextoNombre.setText(textoNombres);
            
            btnNuevoRegistro.setEnabled(false);
            btnEliminar.setVisible(true);
            
            }
           
        }
        
        catch(NegocioException ex){
            Logger.getLogger(frmCRUD.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Datos incorrectos");
            
            
                 }
        

            }
        
        
        
        else{
            JOptionPane.showMessageDialog(this, "id incorrecto");
        }
        
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnHechoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHechoActionPerformed
        // TODO add your handling code here:
        boolean activo = false;
        boolean eliminado = true;
        
        
        if(jCheckBox1.isSelected()){
            activo = true;
            eliminado = false;
        }
        
        EditarAlumnoDTO alumno = new EditarAlumnoDTO();
        alumno.setApellidoMaterno(campoTextoApellidoM.getText());
        alumno.setApellidoPaterno(campoTextoApellidoP.getText());
        alumno.setNombres(campoTextoNombre.getText());
        alumno.setActivo(activo);
        
           
        
        try {
            
            alumnoNegocio.editar(alumno);
        }
        
         catch (NegocioException ex) {
             JOptionPane.showMessageDialog(this, "Datos incorrectos");
            Logger.getLogger(frmCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            
        this.cargarMetodosIniciales();
        }          
        
    }//GEN-LAST:event_btnHechoActionPerformed

    
    public boolean validarNombre(String nombre){
         Pattern pattern = Pattern.compile("^[\\s\\S]{0,20}\\D$");
         
         Matcher matcher = pattern.matcher(nombre);

         return matcher.find();
      }  
      
      
      public boolean validarApellido(String apellido){
         Pattern pattern = Pattern.compile("^[\\s\\S]{0,20}\\D$");
         
         Matcher matcher = pattern.matcher(apellido);

         return matcher.find(); 
      }    
    
      public boolean validarNumero(String string){
         Pattern pattern = Pattern.compile("^[0-9]*$");
         
         
         Matcher matcher = pattern.matcher(string);

         return matcher.find();
      }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmCRUD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JToggleButton btnAtras;
    private javax.swing.JButton btnCancelarRegistro;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnHecho;
    private javax.swing.JButton btnNuevoRegistro;
    private javax.swing.JToggleButton btnSiguiente;
    private javax.swing.JTextField campoTextoApellidoM;
    private javax.swing.JTextField campoTextoApellidoP;
    private javax.swing.JTextField campoTextoNombre;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jblMal;
    private javax.swing.JLabel jblMal1;
    private javax.swing.JLabel jblMal2;
    private javax.swing.JLabel lblPagina;
    private javax.swing.JTable tblAlumnos;
    // End of variables declaration//GEN-END:variables
}
