/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcapiz.inspira.vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jcapiz.inspira.shared.UnidadAprendizaje;

/**
 *
 * @author azaraf
 */
public class Fill {

    private List<UnidadAprendizaje> materias;
    private List<Button> botones;
    private JPanel panel;
    private JPanel mat;
    private String boleta;
    
    public Fill(JPanel panel, String boleta) {
        this.panel = panel;
        this.boleta = boleta;
        botones = new LinkedList<Button>();
    }

    public void setMaterias(List<UnidadAprendizaje> materias) {
        this.materias = materias;
    }

    public void setElements() {
        for (UnidadAprendizaje str : materias) {
            Button b = new Button(str,boleta);
            botones.add(b);
            mat.add(b.getMateria());
            mat.add(b.getRecurse());
            
        }
        mat.revalidate();
        mat.repaint();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanelLayout() {
        panel.setLayout(new BorderLayout(5, 1));
        mat = new JPanel(new GridLayout(materias.size(), 2));
        mat.setBackground(new Color(45, 45, 45));
        panel.add(mat, BorderLayout.WEST);
    }

    public List<Button> getBotones() {
        return botones;
    }

    public JPanel getMat() {
        return mat;
    }

    
}
