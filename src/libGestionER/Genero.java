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
 * Clase Genero.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar el dato del género del empleado.
 * @author Miguel Molina
 */
public class Genero {
    /**
     * Sexo biológico de la persona
     */
    private String sexo;
    /**
     * Constructor
     * @param sexo El sexo biológico de la persona
     */
    public Genero(String sexo) {
        this.sexo = sexo;        
    }
    /**
     * Constructor por defecto
     */
    public Genero() {
        this("");        
    }
    
    /**
     * @return Cadena sexo biológico de la persona
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo Sexo biológico de la persona
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
