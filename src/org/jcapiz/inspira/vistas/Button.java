/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcapiz.inspira.vistas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JCheckBox;
import javax.swing.JToggleButton;
import org.jcapiz.inspira.database.DatabaseConnection;
import org.jcapiz.inspira.shared.UnidadAprendizaje;

/**
 *
 * @author azaraf
 */
public class Button {

    private UnidadAprendizaje ua;
    private JCheckBox materia;
    private JToggleButton recurse;
    private String boleta;

    public Button(UnidadAprendizaje ua, String boleta) {
        this.ua = ua;
        this.boleta = boleta;
        crearBotones();
        formatearBotones();
    }

    private void crearBotones() {
        materia = new JCheckBox(ua.getUnidadAprendizaje());
        recurse = new JToggleButton();
        recurse.setVisible(false);
        materia.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                if ( e.getStateChange() == ItemEvent.SELECTED ) {
                    recurse.setVisible(true);
                 try{
                     DatabaseConnection db = new DatabaseConnection();
                     PreparedStatement stmnt = db.getConnection().prepareCall("{call insertaAlumno_Cursa_Unidad_Aprendizaje(?,?,?)}");
                     stmnt.setString(1, boleta);
                     stmnt.setString(2, ua.getUnidadAprendizaje());
                     stmnt.setInt(3, ua.getIsRecurse() ? 1 : 0);
                     stmnt.executeUpdate();
                 }catch(SQLException ex){}
                }else if (e.getStateChange() == ItemEvent.DESELECTED ){
                    recurse.setVisible(false);
                 try{
                     DatabaseConnection db = new DatabaseConnection();
                     PreparedStatement stmnt = db.getConnection().prepareCall("{call eliminaAlumno_Cursa_Unidad_Aprendizaje(?,?)}");
                     stmnt.setString(1, boleta);
                     stmnt.setString(2, ua.getUnidadAprendizaje());
                     stmnt.executeUpdate();
                 }catch(SQLException ex){}
                }
            }
        });
        
        recurse.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try{
                     DatabaseConnection db = new DatabaseConnection();
                     PreparedStatement stmnt = db.getConnection().prepareCall("{call actualizaRecurse(?,?,?)}");
                     stmnt.setString(1, boleta);
                     stmnt.setString(2, ua.getUnidadAprendizaje());
                     int bandita = 1;
                     if (e.getStateChange() == 2) bandita = 0;
                     stmnt.setInt(3, bandita);
                     stmnt.executeUpdate();
                 }catch(SQLException ex){}
            }
        });
    }

    private void formatearBotones() {

        materia.setForeground(Color.white);
        materia.setFont(new Font("Verdana", 1, 10));

        recurse.setText("La pienso a recursar");
        recurse.setForeground(Color.black);
        recurse.setFont(new Font("Verdana", 1, 10));
    }

    public void setUa(UnidadAprendizaje ua) {
        this.ua = ua;
    }

    public UnidadAprendizaje getUa() {
        return ua;
    }

    public JCheckBox getMateria() {
        return materia;
    }

    public JToggleButton getRecurse() {
        return recurse;
    }

    public void setMateria(JCheckBox materia) {
        this.materia = materia;
    }

    public void setRecurse(JToggleButton recurse) {
        this.recurse = recurse;
    }
}
