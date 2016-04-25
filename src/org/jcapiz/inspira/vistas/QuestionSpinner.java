package org.jcapiz.inspira.vistas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.jcapiz.inspira.networking.HandleResponseToServer;
import org.jcapiz.inspira.shared.Chunk;
import org.jcapiz.inspira.shared.UnidadAprendizaje;

public class QuestionSpinner extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = -7434241008906746843L;

    private final JPanel mainPanel;
    private final JLabel header;
    private final JComboBox<String> academiaPicker;
    private final JPanel northernArea;
    private final JScrollPane centerArea;
    private final JPanel centerAreaContent;
    private final JPanel southernArea;
    private final JButton aceptar;

    private List<UnidadAprendizaje> unidadesSeleccionadas;
    private Chunk unidadesAprendizaje;

    public QuestionSpinner() {
        super("Consulta de materias");
        //this.unidadesAprendizaje = unidadesAprendizaje;
        unidadesSeleccionadas = new ArrayList<UnidadAprendizaje>();

        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 5, 10, 5));
        mainPanel.setPreferredSize(new Dimension(500, 400));

        header = new JLabel("Seleccione carrera", JLabel.LEFT);
        header.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        header.setOpaque(true);
        header.setHorizontalTextPosition(JLabel.LEFT);
        header.setBorder(BorderFactory.createEmptyBorder(1, 15, 1, 1));

        String[] academiasStrings = {"Biónica", "Mecatrónica", "Telemática"};
        academiaPicker = new JComboBox<String>(academiasStrings);
        academiaPicker.setSelectedIndex(0);
        academiaPicker.setBorder(BorderFactory.createEmptyBorder(1, 15, 1, 15));
        academiaPicker.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                // Fiil in the field with the UA texts
            }
        });

        northernArea = new JPanel(new BorderLayout(5, 0));
        northernArea.add(header, BorderLayout.NORTH);
        northernArea.add(academiaPicker, BorderLayout.CENTER);

        centerArea = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        centerArea.setPreferredSize(new Dimension(300, 200));
        centerAreaContent = new JPanel(new GridLayout());
        /*	for(int i =0; i<unidadesAprendizaje.getUnidadesAprendizaje().length;i++){
         JPanel row = new JPanel(new BorderLayout(0,10));
         JCheckBox unidadAprendizajeLabel = new JCheckBox(unidadesAprendizaje.getUnidadesAprendizaje()[i].getUnidadAprendizaje());
         unidadAprendizajeLabel.addItemListener(new ItemListener(){
         @Override
         public void itemStateChanged(ItemEvent evt){
         ItemSelectable item = evt.getItemSelectable();
         String label = item.toString();
         QuestionSpinner.this.unidadesSeleccionadas.add(new UnidadAprendizaje(label));
         }
         });
         JToggleButton isRecurse = new JToggleButton("Recurse");
         isRecurse.addItemListener(new ItemListener() {
         public void itemStateChanged(ItemEvent ev) {
         if(ev.getStateChange()==ItemEvent.SELECTED){
         System.out.println("button is selected");
         } else if(ev.getStateChange()==ItemEvent.DESELECTED){
         System.out.println("button is not selected");
         }
         }
         });
         row.add(unidadAprendizajeLabel, BorderLayout.WEST);
         row.add(isRecurse, BorderLayout.EAST);
         centerAreaContent.add(row);
         }
         */

        southernArea = new JPanel(new BorderLayout());
        aceptar = new JButton("Aceptar");
        aceptar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                int resp = JOptionPane.showConfirmDialog(null, "¿Ha concluido su selección de materias?");
                switch (resp) {
                    case JOptionPane.YES_OPTION:
                        new HandleResponseToServer(
                                new Chunk(unidadesSeleccionadas.toArray(
                                                new UnidadAprendizaje[0]), QuestionSpinner.this.unidadesAprendizaje.getBoleta()))
                                .start();
                        break;
                }
            }
        });
        southernArea.add(aceptar);

        mainPanel.add(centerArea, BorderLayout.CENTER);
        mainPanel.add(southernArea, BorderLayout.SOUTH);
    }

    public void start() {
        mainPanel.add(northernArea, BorderLayout.NORTH);
        setContentPane(mainPanel);
        pack();
        setLocation(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
