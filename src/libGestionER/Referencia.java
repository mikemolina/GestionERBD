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
 * Clase Referencia.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar la información de la referencia.
 * @author Miguel Molina
 */
public class Referencia {
    /**
     * Persona referenciador
     */
    private Persona persona_ref;
    /**
     * Ciudad del referenciador
     */
    private Ciudad ciudad_ref;
    /**
     * Contacto del referenciador
     */
    private Contacto contacto_ref;
    /**
     * Tipo de referencia emitida por el referenciador
     */
    private TipoRef tiporef_ref;
    /**
     * Constructor
     * @param persona_ref Persona referenciador
     * @param ciudad_ref Ciudad del referenciador
     * @param contacto_ref Contacto del referenciador
     * @param tiporef_ref Tipo de referencia emitida por el referenciador
     */
    public Referencia(Persona persona_ref, Ciudad ciudad_ref, Contacto contacto_ref, TipoRef tiporef_ref) {
        this.persona_ref = persona_ref;
        this.ciudad_ref = ciudad_ref;
        this.contacto_ref = contacto_ref;
        this.tiporef_ref = tiporef_ref;
    }
    /**
     * Constructor por defecto
     */
    public Referencia() {
        Persona anonimus = new Persona();
        Ciudad perdida = new Ciudad();
        Contacto vacio = new Contacto();
        TipoRef NA = new TipoRef();       
        persona_ref = anonimus;
        ciudad_ref = perdida;
        contacto_ref = vacio;
        tiporef_ref = NA;
    }
    /**
     * Constructor de copia
     * @param cpReferencia Objeto Referencia para copiar
     */
    public Referencia(Referencia cpReferencia) {
        this.persona_ref = cpReferencia.persona_ref;
        this.ciudad_ref = cpReferencia.ciudad_ref;
        this.contacto_ref = cpReferencia.contacto_ref;
        this.tiporef_ref = cpReferencia.tiporef_ref;
    }
    
    /**
     * @return Objeto Persona referenciador
     */
    public Persona getPersona_ref() {
        return persona_ref;
    }

    /**
     * @param persona_ref Persona referenciador
     */
    public void setPersona_ref(Persona persona_ref) {
        this.persona_ref = persona_ref;
    }

    /**
     * @return Objeto Ciudad del referenciador
     */
    public Ciudad getCiudad_ref() {
        return ciudad_ref;
    }

    /**
     * @param ciudad_ref Ciudad del referenciador
     */
    public void setCiudad_ref(Ciudad ciudad_ref) {
        this.ciudad_ref = ciudad_ref;
    }

    /**
     * @return Objeto Contacto del referenciador
     */
    public Contacto getContacto_ref() {
        return contacto_ref;
    }

    /**
     * @param contacto_ref Contacto del referenciador
     */
    public void setContacto_ref(Contacto contacto_ref) {
        this.contacto_ref = contacto_ref;
    }

    /**
     * @return Objeto tipo de referencia emitida por el referenciador
     */
    public TipoRef getTiporef_ref() {
        return tiporef_ref;
    }

    /**
     * @param tiporef_ref Tipo de referencia emitida por el referenciador
     */
    public void setTiporef_ref(TipoRef tiporef_ref) {
        this.tiporef_ref = tiporef_ref;
    }    
    
    /**
     * Función para entregar nombre completo de la referencia
     * @return Cadena con nombre completo.
     */
    public String NombreCompletoRef() {
        String nombre = this.persona_ref.getPrimernom() + " " + this.persona_ref.getSegundonom() + " " +
                        this.persona_ref.getPrimerapell() + " " + this.persona_ref.getSegundoapell();
        return nombre;
    }
    /**
     * Función para retornar tipo de objeto
     * @return Cadena con tipo de objeto
     */
    public String TipoObjeto() {
        return "Referencia";
    }
    /**
     * Función para retornar tipo de clase
     * @return 
     */
    public String NombreClase() {
        return this.getClass().getSimpleName();
    }
}
