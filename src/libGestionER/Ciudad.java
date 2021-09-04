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
 * Clase Ciudad.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar el dato de la ciudad del empleado o empresa.
 * @author Miguel Molina
 */
public class Ciudad {
    /**
     * Nombre de la ciudad
     */
    private String nombre;
    /**
     * Contructor
     * @param nombre Nombre de la ciudad
     */
    public Ciudad(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Contructor por defecto
     */
    public Ciudad() {
        this("");
    }
   
    /**
     * @return Retorna nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Nombre de la ciudad
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }    
}
