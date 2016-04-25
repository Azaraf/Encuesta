/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jcapiz.inspira.vistas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import org.jcapiz.inspira.database.DatabaseConnection;
import org.jcapiz.inspira.shared.UnidadAprendizaje;

/**
 *
 * @author azaraf
 */
public class Boleta {

    private String boleta;
    private List<Button> boton;
    private List<UnidadAprendizaje> materias;
    private int boletaCorrecta;
    private DatabaseConnection db = new DatabaseConnection("189.232.84.11", "Demanda_Unidades_Aprendizaje", "jirachi", "sharPedo319");

    public Boleta() {
        materias = new LinkedList<UnidadAprendizaje>();
    }

    public void enviar(String boleta) {
        this.boleta = boleta;
        if (boleta != null) {
            try {
                PreparedStatement cstmnt = db.getConnection().prepareCall("{call getMaterias(?)}");
                cstmnt.setString(1, boleta);
                cstmnt.executeUpdate();
                ResultSet rs = cstmnt.getResultSet();
                boletaCorrecta = 0;
                while (rs.next()) {
                    String respuesta = rs.getString(1);
                    if (respuesta.equals("Usted ya ha participado")){
                        boletaCorrecta = 2;
                    }else{
                        boletaCorrecta = 1;

                        UnidadAprendizaje ua = new UnidadAprendizaje(respuesta);
                        ua.setIsRecurse(false);
                        materias.add(ua);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void checkMaterias(List<Button> boton) {
        UnidadAprendizaje ua;
        ListIterator<Button> iterador = this.boton.listIterator();
        while (iterador.hasNext()) {
            Button b = iterador.next();
            ua = b.getUa();
            if (b.getMateria().isSelected()) {
                if (b.getRecurse().isSelected()) {
                    ua.setIsRecurse(true);
                } else {
                    ua.setIsRecurse(false);
                }
                materias.add(ua);
            }
        }
    }

    public void sendMaterias() {
        UnidadAprendizaje ua;
        ListIterator<UnidadAprendizaje> iterator = materias.listIterator();

        while (iterator.hasNext()) {
            ua = iterator.next();
            try {
                PreparedStatement cstmnt = db.getConnection().prepareCall("{call insertaAlumno_Cursa_Unidad_Aprendizaje(?,?,?,@tul)}");
                cstmnt.setString(1, boleta);
                cstmnt.setString(2, ua.getUnidadAprendizaje());
                int valid = (ua.getIsRecurse()) ? 1 : 0;
                cstmnt.setInt(3, valid);
            } catch (SQLException e) {
                System.err.println(e);
            }
        }

    }

    public int isBoletaCorrecta() {
        return boletaCorrecta;
    }

    public List<UnidadAprendizaje> getMaterias() {
        return materias;
    }

    public String getBoleta() {
        return boleta;
    }

    public List<Button> getBoton() {
        return boton;
    }

    public void setBoton(List<Button> boton) {
        this.boton = boton;
    }

    public DatabaseConnection getDb() {
        return db;
    }

    public void setDb(DatabaseConnection db) {
        this.db = db;
    }

}
