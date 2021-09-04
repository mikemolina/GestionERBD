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
 * Clase Persona.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar los datos básicos del empleado o la referencia.
 * @author Miguel Molina
 */
public class Persona { 
    /**
     * Primer nombre de la persona
     */
    private String primernom;
    /**
     * Segundo nombre de la persona
     */
    private String segundonom;
    /**
     * Primer apellido de la persona
     */
    private String primerapell;
    /**
     * Segundo apellido de la persona
     */
    private String segundoapell;
    /**     
     * Dirección de ubicación de la persona
     */
    private String direccion;
    /**
     * Constructor Persona
     * @param primernom Primer nombre de la persona
     * @param segundonom Segundo nombre de la persona
     * @param primerapell Primer apellido de la persona
     * @param segundoapell Segundo apellido de la persona
     * @param direccion Dirección de ubicación de la persona
     */
    public Persona (String primernom, String segundonom, String primerapell, String segundoapell, String direccion){
        this.primernom = primernom;
        this.segundonom = segundonom;
        this.primerapell = primerapell;
        this.segundoapell = segundoapell;
        this.direccion = direccion;
    }
    /**
     * Constructor por defecto.
     * Objeto inicia con persona nula.
     */
    public Persona() {
	this("", "", "", "", "");
    }
    /**
     * @return Cadena primer nombre de la persona
     */
    public String getPrimernom() {
        return primernom;
    }
    /**
     * @param primernom Primer nombre de la persona
     */
    public void setPrimernom(String primernom) {
        this.primernom = primernom;
    }
    /**
     * @return Cadena segundo nombre de la persona
     */
    public String getSegundonom() {
        return segundonom;
    }
    /**
     * @param segundonom Segundo nombre de la persona
     */
    public void setSegundonom(String segundonom) {
        this.segundonom = segundonom;
    }
    /**
     * @return Cadena primer apellido de la persona
     */
    public String getPrimerapell() {
        return primerapell;
    }
    /**
     * @param primerapell Primer apellido de la persona
     */
    public void setPrimerapell(String primerapell) {
        this.primerapell = primerapell;
    }
    /**
     * @return Segundo apellido de la persona
     */
    public String getSegundoapell() {
        return segundoapell;
    }
    /**
     * @param segundoapell Segundo apellido de la persona
     */
    public void setSegundoapell(String segundoapell) {
        this.segundoapell = segundoapell;
    }
    /**
     * @return Cadena dirección de ubicación de la persona
     */
    public String getDireccion() {
        return direccion;
    }
    /**
     * @param direccion Dirección de ubicación de la persona
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }   
    /**
     * Función para retornar tipo de de persona
     * @return Cadena con tipo de de persona
     */
    public String TipoPersona() {
        return "Persona";
    }
}
