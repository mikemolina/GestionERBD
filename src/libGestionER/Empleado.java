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
 * Clase Empleado.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar la información del empleado.
 * @author Miguel Molina
 */
public class Empleado {
    /**
     * Fecha nacimiento empleado en formato YYYY-MM-DD
     */
    private String datanac;
    /**
     * Objeto persona del empleado
     */
    private Persona persona_emp;
    /**
     * Objeto documento del empleado
     */
    private DNI dni_emp;
    /**
     * Objeto genero del empleado
     */
    private Genero genero_emp;
    /**
     * Objeto estado civil del empleado
     */
    private EstadoCivil estciv_emp;
    /**
     * Objeto ciudad del empleado
     */
    private Ciudad ciudad_emp;
    /**
     * Objeto empresa del empleado
     */
    private Empresa empresa_emp;
    /**
     * Objeto contacto del empleado
     */
    private Contacto contacto_emp;
    /**
     * Constructor
     * @param datanac Fecha nacimiento empleado
     * @param persona_emp Persona, del empleado
     * @param dni_emp Documento del empleado
     * @param genero_emp Género del empleado
     * @param estciv_emp Estado civil del empleado
     * @param ciudad_emp La ciudad del empleado
     * @param empresa_emp Empresa del empleado
     * @param contacto_emp Contacto del empleado
     */
    public Empleado(String datanac, Persona persona_emp, DNI dni_emp, Genero genero_emp, EstadoCivil estciv_emp, Ciudad ciudad_emp, Empresa empresa_emp, Contacto contacto_emp) {
        this.datanac = datanac;
        this.persona_emp = persona_emp;
        this.dni_emp = dni_emp;
        this.genero_emp = genero_emp;
        this.estciv_emp = estciv_emp;
        this.ciudad_emp = ciudad_emp;
        this.empresa_emp = empresa_emp;
        this.contacto_emp = contacto_emp;
    }
    /**
     * Constructor por defecto
     */
    public Empleado() {        
        Persona anonimus = new Persona();
        DNI indocumentado = new DNI();
        Genero noiden = new Genero();
        EstadoCivil vago = new EstadoCivil();
        Ciudad perdida = new Ciudad();
        Empresa ficticia = new Empresa();
        Contacto vacio = new Contacto();        
        datanac = "0001-01-01";
        persona_emp = anonimus;
        dni_emp = indocumentado;
        genero_emp = noiden;
        estciv_emp = vago;
        ciudad_emp = perdida;
        empresa_emp = ficticia;
        contacto_emp = vacio;       
    }
    /**
     * Constructor de copia
     * @param cpEmpleado Objeto Empleado a copiar
     */
    public Empleado(Empleado cpEmpleado) {
        this.datanac = cpEmpleado.datanac;
        this.persona_emp = cpEmpleado.persona_emp;
        this.dni_emp = cpEmpleado.dni_emp;
        this.genero_emp = cpEmpleado.genero_emp;
        this.estciv_emp = cpEmpleado.estciv_emp;
        this.ciudad_emp = cpEmpleado.ciudad_emp;
        this.empresa_emp = cpEmpleado.empresa_emp;
        this.contacto_emp = cpEmpleado.contacto_emp;        
    }
    
    /**
     * @return Fecha nacimiento empleado
     */
    public String getDatanac() {
        return datanac;
    }

    /**
     * @param datanac Fecha nacimiento empleado
     */
    public void setDatanac(String datanac) {
        this.datanac = datanac;
    }

    /**
     * @return Objeto Persona del empleado
     */
    public Persona getPersona_emp() {
        return persona_emp;
    }

    /**
     * @param persona_emp Objeto Persona del empleado
     */
    public void setPersona_emp(Persona persona_emp) {
        this.persona_emp = persona_emp;
    }

    /**
     * @return Objeto DNI con documento del empleado
     */
    public DNI getDni_emp() {
        return dni_emp;
    }

    /**
     * @param dni_emp Objeto DNI con documento del empleado
     */
    public void setDni_emp(DNI dni_emp) {
        this.dni_emp = dni_emp;
    }

    /**
     * @return Objeto Genero del empleado
     */
    public Genero getGenero_emp() {
        return genero_emp;
    }

    /**
     * @param genero_emp Objeto Genero del empleado
     */
    public void setGenero_emp(Genero genero_emp) {
        this.genero_emp = genero_emp;
    }

    /**
     * @return Objeto estado civil del empleado
     */
    public EstadoCivil getEstciv_emp() {
        return estciv_emp;
    }

    /**
     * @param estciv_emp Objeto estado civil del empleado
     */
    public void setEstciv_emp(EstadoCivil estciv_emp) {
        this.estciv_emp = estciv_emp;
    }

    /**
     * @return Objeto ciudad del empleado
     */
    public Ciudad getCiudad_emp() {
        return ciudad_emp;
    }

    /**
     * @param ciudad_emp Objeto ciudad del empleado
     */
    public void setCiudad_emp(Ciudad ciudad_emp) {
        this.ciudad_emp = ciudad_emp;
    }

    /**
     * @return Objeto Empresa del empleado
     */
    public Empresa getEmpresa_emp() {
        return empresa_emp;
    }

    /**
     * @param empresa_emp Objeto Empresa del empleado
     */
    public void setEmpresa_emp(Empresa empresa_emp) {
        this.empresa_emp = empresa_emp;
    }

    /**
     * @return Objeto Contacto del empleado
     */
    public Contacto getContacto_emp() {
        return contacto_emp;
    }

    /**
     * @param contacto_emp Objeto Contacto del empleado
     */
    public void setContacto_emp(Contacto contacto_emp) {
        this.contacto_emp = contacto_emp;
    }
    /**
     * Función para entregar nombre completo del empleado
     * @return Cadena con nombre completo.
     */
    public String NombreCompletoEmpl() {
        String nombre = this.persona_emp.getPrimernom() + " " + this.persona_emp.getSegundonom() + " " +
                        this.persona_emp.getPrimerapell() + " " + this.persona_emp.getSegundoapell();
        return nombre;
    }
    /**
     * Función para devolver el numero DNI del empleado
     * @return cadena con numero de identificacion
     */
    public String NumeroIdentEmpl() {
        String numdoc = this.dni_emp.getNumident();
        return numdoc;
    }
    /**
     * Función para devolver nombre de la empresa del empleado
     * @return Cadena nombre empresa
     */
    public String NombreEmpresa() {
        String compania = this.empresa_emp.getNomempr();
        return compania;
    }
    /**
     * Función para retornar tipo de objeto
     * @return Cadena con tipo de objeto
     */
    public String TipoObjeto() {
        return "Empleado";
    }
    /**
     * Función para retornar tipo de clase
     * @return 
     */
    public String NombreClase() {
        return this.getClass().getSimpleName();
    }
}
