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

package MDLGestionER;

import java.util.Arrays;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

import libGestionER.Persona;
import libGestionER.Genero;
import libGestionER.EstadoCivil;
import libGestionER.Ciudad;
import libGestionER.Empresa;
import libGestionER.Contacto;
import libGestionER.TipoRef;


/**
 * Clase MDLTPModificar
 * Clase modelo para modificar datos en las tablas primarias en la base de datos.
 * Cada función gestiona una conexión con la base de datos para modificar los
 * datos de una tabla específica.
 * A su vez la función, retorna un valor booleano para confirmar la operación.
 * @author Miguel Molina
 */
public class MDLTPModificar {
    /**
     * Objeto dataGER para establecer conexión con BD y modificar tablas primarias
     */
    dataGER dataGERTP;
    /**
     * Constructor
     */
    public MDLTPModificar() {
        this.dataGERTP = new dataGER();
    }
    /**
     * Modifica los datos de la Persona hacia la BD
     * @param datoAnt Datos persona anterior a la modificación
     * @param datoPos Datos persona posterior a la modificación
     * @param ID ID de dato Persona
     * @return Confirmación de la modificación de datos Persona
     */
    public boolean Mod2TBLPersona(Persona datoAnt, Persona datoPos, int ID) {
        String NombreTBL = "persona";
        String [] ColTBL = {
            "persona_id",
            "persona_primernom",
            "persona_segundonom",
            "persona_primerapell",
            "persona_segundoapell",
            "persona_direccion" };
        int NumCol = 6;
        String [] DataAnt ={
            datoAnt.getPrimernom(),
            datoAnt.getSegundonom(),
            datoAnt.getPrimerapell(),
            datoAnt.getSegundoapell(),
            datoAnt.getDireccion() };
        String [] DataPos ={
            datoPos.getPrimernom(),
            datoPos.getSegundonom(),
            datoPos.getPrimerapell(),
            datoPos.getSegundoapell(),
            datoPos.getDireccion() };
        return ModificarTabla(NombreTBL, NumCol, ColTBL, DataAnt, DataPos, ID);
    }    
    /**
     * Modifica los datos de Genero hacia la BD
     * @param datoAnt Datos genero anterior a la modificación
     * @param datoPos Datos genero posterior a la modificación
     * @param ID ID de dato Genero
     * @return Confirmación de la modificación de datos Genero
     */
    public boolean Mod2TBLGenero(Genero datoAnt, Genero datoPos, int ID) {
        String NombreTBL = "genero";        
        String [] ColTBL = {
            "genero_id",
            "genero_sexo" };
        int NumCol = 2;
        String [] DataAnt ={
            datoAnt.getSexo() };
        String [] DataPos ={
            datoPos.getSexo() };
        return ModificarTabla(NombreTBL, NumCol, ColTBL, DataAnt, DataPos, ID);
    }
    /**
     * Modifica los datos de Estado Civil hacia la BD
     * @param datoAnt Datos EstadoCivil anterior a la modificación
     * @param datoPos Datos EstadoCivil posterior a la modificación
     * @param ID ID de dato EstadoCivil
     * @return Confirmación de la modificación de datos EstadoCivil
     */
    public boolean Mod2TBLEstadoCivil(EstadoCivil datoAnt, EstadoCivil datoPos, int ID) {
        String NombreTBL = "estadocivil";        
        String [] ColTBL = {
            "estadocivil_id",
            "estadocivil_tipo" };
        int NumCol = 2;
        String [] DataAnt ={
            datoAnt.getTipo() };
        String [] DataPos ={
            datoPos.getTipo() };
        return ModificarTabla(NombreTBL, NumCol, ColTBL, DataAnt, DataPos, ID);
    }
    /**
     * Modifica los datos de Ciudad hacia la BD
     * @param datoAnt Datos Ciudad anterior a la modificación
     * @param datoPos Datos Ciudad posterior a la modificación
     * @param ID ID de dato Ciudad
     * @return Confirmación de la modificación de datos Ciudad
     */
    public boolean Mod2TBLCiudad(Ciudad datoAnt, Ciudad datoPos, int ID) {
        String NombreTBL = "ciudad";        
        String [] ColTBL = {
            "ciudad_id",
            "ciudad_nombre" };
        int NumCol = 2;
        String [] DataAnt ={
            datoAnt.getNombre() };
        String [] DataPos ={
            datoPos.getNombre() };
        return ModificarTabla(NombreTBL, NumCol, ColTBL, DataAnt, DataPos, ID);
    }
    /**
     * Modifica los datos de Empresa hacia la BD
     * @param datoAnt Datos Empresa anterior a la modificación
     * @param datoPos Datos Empresa posterior a la modificación
     * @param ID ID de dato Empresa
     * @return Confirmación de la modificación de datos Empresa
     */
    public boolean Mod2TBLEmpresa(Empresa datoAnt, Empresa datoPos, int ID) {
        String NombreTBL = "empresa";
        String [] ColTBL = {
            "empresa_id",
            "empresa_nomempr",
            "empresa_identtrib" };
        int NumCol = 3;
        String [] DataAnt ={
            datoAnt.getNomempr(),
            datoAnt.getIdenttrib() };
        String [] DataPos ={
            datoPos.getNomempr(),
            datoPos.getIdenttrib() };        
        return ModificarTabla(NombreTBL, NumCol, ColTBL, DataAnt, DataPos, ID);
    }
    /**
     * Modifica los datos de Contacto hacia la BD
     * @param datoAnt Datos Contacto anterior a la modificación
     * @param datoPos Datos Contacto posterior a la modificación
     * @param ID ID de dato Contacto
     * @return Confirmación de la modificación de datos Contacto
     */
    public boolean Mod2TBLContacto(Contacto datoAnt, Contacto datoPos, int ID) {
        String NombreTBL = "contacto";
        String [] ColTBL = {
            "contacto_id",
            "contacto_tipotel",
            "contacto_numtel",
            "contacto_email" };
        int NumCol = 4;
        String [] DataAnt ={
            datoAnt.getTipotel(),
            datoAnt.getNumtel(),
            datoAnt.getEmail() };
        String [] DataPos ={
            datoPos.getTipotel(),
            datoPos.getNumtel(),
            datoPos.getEmail() };
        return ModificarTabla(NombreTBL, NumCol, ColTBL, DataAnt, DataPos, ID);
    }
    /**
     * Modifica los datos de TipoRef hacia la BD
     * @param datoAnt Datos genero anterior a la modificación
     * @param datoPos Datos genero posterior a la modificación
     * @param ID ID de dato TipoRef
     * @return Confirmación de la modificación de datos TipoRef
     */
    public boolean Mod2TBLTipoRef(TipoRef datoAnt, TipoRef datoPos, int ID) {
        String NombreTBL = "tiporef";
        String [] ColTBL = {
            "tiporef_id",
            "tiporef_tiporef" };
        int NumCol = 2;
        String [] DataAnt ={
            datoAnt.getTiporef() };
        String [] DataPos ={
            datoPos.getTiporef() };
        return ModificarTabla(NombreTBL, NumCol, ColTBL, DataAnt, DataPos, ID);
    }
    /**
     * Modifica los datos de Fecha Nacimiento del Empleado hacia la BD
     * @param datoAnt Dato Fecha Nacimiento anterior a la modificación
     * @param datoPos Dato Fecha Nacimiento posterior a la modificación
     * @param ID ID del Empleado
     * @return Confirmación de la modificación de datos Fecha Nacimiento
     */    
    public boolean Mod2TBLDataNac(String datoAnt, String datoPos, int ID) {
        String NombreTBL = "empleado";
        String [] ColTBL = {
            "empleado_id",
            "empleado_datanac" };
        int NumCol = 2;
        String [] DataAnt ={
            datoAnt };
        String [] DataPos ={
            datoPos };
        return ModificarTabla(NombreTBL, NumCol, ColTBL, DataAnt, DataPos, ID);    
    }
    
    /**
     * Función generica para actualizar una tabla primaria.Para asegurar que la
     * modificación sea correcta, se buscan dos campos en la consulta: el ID y
     * el valor del parametro anterior a la modificación.
     * @param NombreTBL Nombre de la tabla
     * @param NumCol Número de columnas de la tabla
     * @param DescCol Describe el nombre de cada columna
     * @param DatosAnt Datos anteriores para modificar
     * @param DatosPos Datos posteriores para actualizar
     * @param ID Número ID del parámetro
     * @return Confirmacion de la modificacion en la BD
     */
    public boolean ModificarTabla(String NombreTBL, int NumCol, String [] DescCol, String [] DatosAnt, String [] DatosPos, int ID){
        int i, FilaUpg;
        boolean status = false;
        boolean [] StatusUpg = new boolean[NumCol-1];
        Arrays.fill(StatusUpg, false);
        try( Connection conexTBL = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            for(i=1; i<NumCol; i++) {
                String SQLqueryModificarTBL = "UPDATE " + NombreTBL + " " +
                        "SET " + DescCol[i] + "=? " +
                        "WHERE (" + DescCol[i] + "=? AND " + DescCol[0] +"=?)";                
                try( PreparedStatement estadoTBL =
                        (PreparedStatement)conexTBL.prepareStatement(SQLqueryModificarTBL) ) {
                    estadoTBL.setString(1, DatosPos[i-1]);
                    estadoTBL.setString(2, DatosAnt[i-1]);
                    estadoTBL.setInt(3, ID);                            
                    FilaUpg = estadoTBL.executeUpdate();
                    if( FilaUpg > 0 ) {
                        StatusUpg[i-1] = true;
                    }
                    estadoTBL.close();
                }
            }
            conexTBL.close();
            // ¿Todas las modificaciones fueros exitosas?
            for(i=0; i<NumCol-1; i++) {
                if ( StatusUpg[i] == true ) {
                    status = true;
                } else {
                    status = false;
                    break;
                }
            }
            return status;
        } catch(SQLException excp) {
            System.err.println("Mensaje de error SQL (ModificarTabla): " + excp.getMessage());
            return false;
        }
    }

}
