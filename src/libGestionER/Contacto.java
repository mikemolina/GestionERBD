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
 * Clase Contacto.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar los datos de contacto del  empleado o la referencia.
 * @author Miguel Molina
 */
public class Contacto {
    /**
     * Tipo de teléfono
     */
    private String tipotel;
    /**
     * Número de teléfono
     */
    private String numtel;
    /**
     * Correo electrónico
     */
    private String email;
    /**
     * Constructor
     * @param tipotel Tipo de teléfono
     * @param numtel Número de teléfono
     * @param email Correo electrónico
     */
    public Contacto(String tipotel, String numtel, String email) {
        this.tipotel = tipotel;
        this.numtel = numtel;
        this.email = email;
    }
    /**
     * Constructor por defecto
     */
    public Contacto() {
        this("", "", "");
    }
    
    /**
     * @return Retorna tipotel
     */
    public String getTipotel() {
        return tipotel;
    }

    /**
     * @param tipotel Tipo de teléfono
     */
    public void setTipotel(String tipotel) {
        this.tipotel = tipotel;
    }

    /**
     * @return Retorna numtel
     */
    public String getNumtel() {
        return numtel;
    }

    /**
     * @param numtel Número de teléfono
     */
    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }

    /**
     * @return Retorna email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    } 
}
