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
 * Clase TipoRef.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar el dato de la recomendación emitido por la referencia.
 * @author Miguel Molina
 */
public class TipoRef {
    /**
     * Tipo de referencia emitida por la persona-referenciador
     */
    private String tiporef;
    /**
     * Constructor
     * @param tiporef Tipo de referencia
     */
    public TipoRef(String tiporef) {
        this.tiporef = tiporef;
    }
    /**
     * Constructor por defecto
     */
    public TipoRef() {
        this("");
    }    
    
    /**
     * @return Cadena tipo de referencia
     */
    public String getTiporef() {
        return tiporef;
    }

    /**
     * @param tiporef Tipo de referencia
     */
    public void setTiporef(String tiporef) {
        this.tiporef = tiporef;
    }
}
