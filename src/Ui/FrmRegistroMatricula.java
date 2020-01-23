/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ui;

import Bll.GestionCurso;
import Bll.GestionEstudiante;
import Bll.GestionMatricula;
import Chisaguano.Util;
import beu.Curso;
import beu.Estudiante;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author labctr
 */
public class FrmRegistroMatricula extends javax.swing.JInternalFrame {
    
    private final String titulo= "Registro de Matricula";
    //Servicio //bussiness logic layer
    private GestionCurso cursoBll= new GestionCurso();
    private GestionEstudiante estudianteBll= new GestionEstudiante();
    private GestionMatricula matriculaBll = new GestionMatricula();
    private List<Curso> cursos;
    private List<Estudiante> estudiantes;
    
    private boolean Validar(){
        Curso cr=(Curso)this.cmbCursos.getSelectedItem();
        Estudiante est = (Estudiante) this.cmbEstudiantes.getSelectedItem();
        if(cr.getTitulo().equals("--Seleccione--")){
            vtnPrincipal.verMensaje("Curso no Valido", titulo, JOptionPane.WARNING_MESSAGE);
            return false;
        }
       
         if(est.getCedula()== null){
            vtnPrincipal.verMensaje("Estudiante no Valido", titulo, JOptionPane.WARNING_MESSAGE);
            return false;
        }
         return true;
    }
    
    private void CrearMatricula() throws IOException{
            Curso cr=(Curso)this.cmbCursos.getSelectedItem();
            Estudiante est = (Estudiante) this.cmbEstudiantes.getSelectedItem();
            matriculaBll.crear();
            matriculaBll.Configurar(cr, est);
            matriculaBll.archivar();
    }
    
    private void leerCursos() throws IOException{
        try{
            Curso ficticio = new Curso("--Seleccione--"," ",0.0f);
            this.cmbCursos.addItem(ficticio);
            cursos = cursoBll.getCursos();
            cursos.forEach((c) ->{
            this.cmbCursos.addItem(c);
            });
        }catch(Exception e){
            Util.imprimir("Error"+e.toString());
            vtnPrincipal.verMensaje("Error al leer los cursos",titulo,JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void leerEstudiantes() throws IOException{
        try{ 
            Estudiante ficticio=new Estudiante();
            ficticio.setNombre("--Seleccione--");
            ficticio.setApellido(" ");
            this.cmbEstudiantes.addItem(ficticio);
            estudiantes = estudianteBll.leerEstudiantes();
            estudiantes.forEach((est) ->{
            this.cmbEstudiantes.addItem(est);
            });
        }catch(Exception e){
            Util.imprimir("Error"+e.toString());
            vtnPrincipal.verMensaje("Error al leer los estudiantes",titulo,JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Creates new form FrmRegistroMatricula
     */
    public FrmRegistroMatricula() {
        initComponents();
        try {
            leerCursos();
        } catch (IOException ex) {
            Logger.getLogger(FrmRegistroMatricula.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            leerEstudiantes();
        } catch (IOException ex) {
            Logger.getLogger(FrmRegistroMatricula.class.getName()).log(Level.SEVERE, null, ex);
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

        lblRegistroMatricula = new javax.swing.JLabel();
        lblEstudiante = new javax.swing.JLabel();
        cmbEstudiantes = new javax.swing.JComboBox<>();
        lblCurso = new javax.swing.JLabel();
        cmbCursos = new javax.swing.JComboBox<>();
        btnGuardarRegistro = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        lblRegistroMatricula.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        lblRegistroMatricula.setText("Registro de Matricula");

        lblEstudiante.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblEstudiante.setText("Estudiante:");

        lblCurso.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        lblCurso.setText("Curso:");

        btnGuardarRegistro.setIcon(new javax.swing.ImageIcon("/home/labctr/Documentos/POO/3 parcial/iconos/save.png")); // NOI18N
        btnGuardarRegistro.setText("Guardar");
        btnGuardarRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarRegistroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnGuardarRegistro)
                            .addGap(170, 170, 170))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(lblCurso)
                            .addGap(0, 361, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblRegistroMatricula)
                        .addGap(127, 127, 127))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblEstudiante)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbCursos, 0, 256, Short.MAX_VALUE)
                    .addComponent(cmbEstudiantes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblRegistroMatricula)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstudiante)
                    .addComponent(cmbEstudiantes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCurso)
                    .addComponent(cmbCursos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnGuardarRegistro)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarRegistroActionPerformed
        try{
            if(Validar()){
                CrearMatricula();
                vtnPrincipal.verMensaje("Matricula Creada correctamente", titulo, JOptionPane.INFORMATION_MESSAGE);
            }
        }catch(Exception e){
            Util.imprimir("Error"+e.toString());
            vtnPrincipal.verMensaje("Error al crear la Matricula",titulo,JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarRegistroActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarRegistro;
    private javax.swing.JComboBox<Curso> cmbCursos;
    private javax.swing.JComboBox<Estudiante> cmbEstudiantes;
    private javax.swing.JLabel lblCurso;
    private javax.swing.JLabel lblEstudiante;
    private javax.swing.JLabel lblRegistroMatricula;
    // End of variables declaration//GEN-END:variables
}