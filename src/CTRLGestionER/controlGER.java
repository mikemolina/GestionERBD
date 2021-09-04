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

package CTRLGestionER;

import libGestionER.Empleado;
import libGestionER.Referencia;
import libGestionER.GestionEmpRef;

import MDLGestionER.modeloEmpl;
import MDLGestionER.modeloRef;
import MDLGestionER.modeloVincER;


/**
 * Clase controlGER.
 * Clase de control entre el GUI y el modelo para gestionar la
 * información de la base de datos.
 * @author Miguel Molina
 */
public class controlGER {
    /**
     * Variable modelo Empleado
     */
    private modeloEmpl mdlEmpleado;
    /**
     * Variable modelo Referencia
     */
    private modeloRef mdlReferencia;
    /**
     * Variable modelo Vinculo entre Empleado y Referencia
     */
    private modeloVincER mdlVinculo;
    /**
     * Constructor
     */
    public controlGER() {
        this.mdlEmpleado = new modeloEmpl();
        this.mdlReferencia = new modeloRef();
        this.mdlVinculo = new modeloVincER();
    }
    
    /**
     * Función para controlar Crear registro
     * @param objeto "Objeto" de alguna clase
     * @return salida booleana
     */
    public boolean CrearGER(Object objeto) {
        boolean rta = false;
        try{
            switch( objeto.getClass().getSimpleName() ){
                case "Referencia":
                    rta = this.mdlReferencia.CrearGER( Referencia.class.cast(objeto) );                    
                    break;
                case "Empleado":
                    rta = this.mdlEmpleado.CrearGER( Empleado.class.cast(objeto) );
                    break;
                case "GestionEmpRef":
                    rta = this.mdlVinculo.CrearGER( GestionEmpRef.class.cast(objeto) );
            }
            return rta;
        } catch(Exception excp){
            System.err.println("Mensaje de excepción (CrearGER): " + excp.getMessage());
            return false;
        }
    }
    /**
     * Función para controlar Actualizar registro
     * @param objeto "Objeto" de alguna clase
     * @return salida booleana
     */
    public boolean ActualizarGER(Object objeto) {
        boolean rta = false;
        try{
            switch( objeto.getClass().getSimpleName() ){
                case "Referencia":
                    rta = this.mdlReferencia.ActualizarGER( Referencia.class.cast(objeto) );
                    break;
                case "Empleado":
                    rta = this.mdlEmpleado.ActualizarGER( Empleado.class.cast(objeto) );
                    break;
            }
            return rta;
        } catch(Exception excp){
            System.err.println("Mensaje de excepción (ActualizarGER): " + excp.getMessage());
            return false;
        }
    }
    /**
     * Función para controlar Eliminar registro
     * @param objeto "Objeto" de alguna clase
     * @return salida booleana
     */
    public boolean BorrarGER(Object objeto) {
        boolean rta = false;
        try{
            switch( objeto.getClass().getSimpleName() ){
                case "Referencia":
                    rta = this.mdlReferencia.BorrarGER( Referencia.class.cast(objeto) );
                    break;
                case "Empleado":
                    rta = this.mdlEmpleado.BorrarGER( Empleado.class.cast(objeto) );
                    break;
                case "GestionEmpRef":
                    rta = this.mdlVinculo.BorrarGER( GestionEmpRef.class.cast(objeto) );
            }
            return rta;
        } catch(Exception excp){
            System.err.println("Mensaje de excepción (BorrarGER): " + excp.getMessage());
            return false;
        }
    }
    /**
     * Función para controlar Buscar registro
     * @param objeto "Objeto" de alguna clase
     * @param NumDoc Cadena número de documento
     * @param Email Cadena correo electrónico
     * @return Retorna algún "Objeto"
     */
    public Object BuscarGER(Object objeto, String NumDoc, String Email) {
        Object variable = null;
        try{
            switch( objeto.getClass().getSimpleName() ){
                case "Referencia":
                    variable = this.mdlReferencia.BuscarGER(Email);
                    break;
                case "Empleado":                    
                    variable = this.mdlEmpleado.BuscarGER(NumDoc);
                    break;
                case "GestionEmpRef":
                    variable = this.mdlVinculo.BuscarGER(NumDoc, Email);
                    break;
            }
            return variable;
        } catch(Exception excp){
            System.err.println("Mensaje de excepción (BuscarGER): " + excp.getMessage());
            return null;
        }
    }    
}
