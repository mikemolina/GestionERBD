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
 * Clase EstadoCivil.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar el dato del estado civil del empleado.
 * @author Miguel Molina
 */
public class EstadoCivil {
    /**
     * Estado civil de empleado.
     */
    private String tipo;
    /**
     * Constructor
     * @param tipo Estado civil de empleado.
     */
    public EstadoCivil(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Constructor por defecto
     */
    public EstadoCivil() {
        this("");    
    }
    
    /**
     * @return Cadena estado civil de empleado.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo Estado civil de empleado.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }    
}
