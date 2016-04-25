package org.jcapiz.inspira.shared;

import java.io.Serializable;

public class UnidadAprendizaje implements Serializable {

    /**
     * *
     *
     **
     */
    private static final long serialVersionUID = -427367987779118564L;

    private final String unidadAprendizaje;
    private boolean isRecurse;

    public UnidadAprendizaje(String unidadAprendizaje) {
        this.unidadAprendizaje = unidadAprendizaje;
    }

    public String getUnidadAprendizaje() {
        return unidadAprendizaje;
    }

    public boolean getIsRecurse() {
        return isRecurse;
    }

    public void setIsRecurse(boolean isRecurse) {
        this.isRecurse = isRecurse;
    }
}
