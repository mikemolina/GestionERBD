/*
 * Copyright (C) 2021 Miguel Molina
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package libGestionER;

/**
 * Clase DNI.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar los datos de identificación del empleado.
 * @author Miguel Molina
 */
public class DNI {
    /**
     * Tipo de documento
     */
    private String tipodoc;
    /**
     * Número de documento; formato de cadena de texto
     */
    private String numident;
    /**
     * Constructor
     * @param tipodoc Tipo de documento
     * @param numident Número de documento
     */
    public DNI (String tipodoc, String numident) {
        this.tipodoc = tipodoc;
        this.numident = numident;        
    }
    /**
     * Constructor por defecto
     */
    public DNI () {
        this("", "");
    }
        /**
     * @return Tipo de documento
     */
    public String getTipodoc() {
        return tipodoc;
    }

    /**
     * @param tipodoc Tipo de documento
     */
    public void setTipodoc(String tipodoc) {
        this.tipodoc = tipodoc;
    }

    /**
     * @return Número de documento
     */
    public String getNumident() {
        return numident;
    }

    /**
     * @param numident Número de documento
     */
    public void setNumident(String numident) {
        this.numident = numident;
    }
}
