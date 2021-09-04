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

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import libGestionER.Persona;
import libGestionER.DNI;
import libGestionER.Genero;
import libGestionER.EstadoCivil;
import libGestionER.Ciudad;
import libGestionER.Empresa;
import libGestionER.Contacto;
import libGestionER.TipoRef;


/**
 * Clase MDLTPGuardar
 * Clase para Guardar los datos de tablas primarias. Cada función gestiona una
 * conexión con la base de datos para guardar los datos de una tabla específica.
 * A su vez la función, retorna un entero equivaliente al ID del registro
 * guardado.
 * @author Miguel Molina
 */
public class MDLTPGuardar {
    /**
     * Objeto dataGER para establecer conexión con BD y guardar tablas primarias
     */
    dataGER dataGERTP;
    /**
     * Constructor
     */
    public MDLTPGuardar() {
        this.dataGERTP = new dataGER();
    }
    /**
     * Guarda los datos de la Persona hacia la BD
     * @param DatosPersona Objeto Persona
     * @return ID Persona
     */
    public int Push2TBLPersona(Persona DatosPersona) {
        int IDtp = -1;        
        try( Connection conexPersona = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryGuardarPersona = "INSERT " +
                    "INTO persona (persona_primernom, persona_segundonom, persona_primerapell, persona_segundoapell, persona_direccion) " +
                    "VALUES (?, ?, ?, ?, ?)";
            try( PreparedStatement estadoPersona = (PreparedStatement)conexPersona.prepareStatement(
                    SQLqueryGuardarPersona,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoPersona.setString(1, DatosPersona.getPrimernom());
                estadoPersona.setString(2, DatosPersona.getSegundonom());
                estadoPersona.setString(3, DatosPersona.getPrimerapell());
                estadoPersona.setString(4, DatosPersona.getSegundoapell());
                estadoPersona.setString(5, DatosPersona.getDireccion());
                int FilaIns = estadoPersona.executeUpdate();
                ResultSet ClaveGen = null;
                if( FilaIns > 0 ) {
                    ClaveGen = estadoPersona.getGeneratedKeys();
                }
                if( ClaveGen.next() ) {
                    IDtp = ClaveGen.getInt(1);
                }
                estadoPersona.close();
            }
            conexPersona.close();
            return IDtp;
        } catch(SQLException excp) {
            System.err.println("Mensaje de error SQL (Push2TBLPersona): " + excp.getMessage());
            return IDtp;
        }
    }
    /**
     * Guarda los datos de identifiación DNI hacia la BD
     * @param DatosDNI Objeto DNI
     * @return ID DNI
     */
    public int Push2TBLDNI(DNI DatosDNI) {
        int IDtp = -1;        
        try( Connection conexDNI = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryGuardarDNI = "INSERT " +
                    "INTO dni (dni_tipodoc, dni_numident) " +
                    "VALUES (?, ?)";
            try( PreparedStatement estadoDNI = (PreparedStatement)conexDNI.prepareStatement(
                    SQLqueryGuardarDNI,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoDNI.setString(1, DatosDNI.getTipodoc());
                estadoDNI.setString(2, DatosDNI.getNumident());
                int FilaIns = estadoDNI.executeUpdate();
                ResultSet ClaveGen = null;
                if( FilaIns > 0 ) {
                    ClaveGen = estadoDNI.getGeneratedKeys();
                }
                if( ClaveGen.next() ) {
                    IDtp = ClaveGen.getInt(1);
                }
                estadoDNI.close();                
            }
            conexDNI.close();
            return IDtp;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL (Push2TBLDNI): " + excp.getMessage());
            return IDtp;
        }
    }
    /**
     * Guarda el dato de Genero hacia la BD
     * @param DatoSexo Objeto Genero
     * @return ID Genero
     */
    public int Push2TBLGenero(Genero DatoSexo) {
        int IDtp = -1;
        try( Connection conexSexo = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryGuardarSexo = "INSERT " +
                    "INTO genero (genero_sexo) " +
                    "VALUES (?)";
            try( PreparedStatement estadoGenero = (PreparedStatement)conexSexo.prepareStatement(
                    SQLqueryGuardarSexo,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoGenero.setString(1, DatoSexo.getSexo());                
                int FilaIns = estadoGenero.executeUpdate();
                ResultSet ClaveGen = null;
                if( FilaIns > 0 ) {
                    ClaveGen = estadoGenero.getGeneratedKeys();
                }
                if( ClaveGen.next() ) {
                    IDtp = ClaveGen.getInt(1);
                }
                estadoGenero.close();
            }
            conexSexo.close();
            return IDtp;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL (Push2TBLGenero): " + excp.getMessage());
            return IDtp;
        }
    }
    /**
     * Guarda el dato de Estado Civil hacia la BD
     * @param DatoEstCiv Objeto EstadoCivil
     * @return ID Estado civil
     */
    public int Push2TBLEstadoCivil(EstadoCivil DatoEstCiv) {
        int IDtp = -1;
        try( Connection conexEstCiv = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryGuardarEstCiv = "INSERT " +
                    "INTO estadocivil (estadocivil_tipo) " +
                    "VALUES (?)";
            try( PreparedStatement estadoEstCiv = (PreparedStatement)conexEstCiv.prepareStatement(
                    SQLqueryGuardarEstCiv,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoEstCiv.setString(1, DatoEstCiv.getTipo());
                int FilaIns = estadoEstCiv.executeUpdate();
                ResultSet ClaveGen = null;
                if( FilaIns > 0 ) {
                    ClaveGen = estadoEstCiv.getGeneratedKeys();
                }
                if( ClaveGen.next() ) {
                    IDtp = ClaveGen.getInt(1);
                }
                estadoEstCiv.close();
            }
            conexEstCiv.close();
            return IDtp;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL (Push2TBLEstadoCivil): " + excp.getMessage());
            return IDtp;
        }
    }
    /**
     * Guarda el dato de Ciudad hacia la BD
     * @param DatoCiudad Objeto Ciudad
     * @return ID Ciudad
     */
    public int Push2TBLCiudad(Ciudad DatoCiudad) {
        int IDtp = -1;
        try( Connection conexPueblo = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryGuardarCiudad = "INSERT " +
                    "INTO ciudad (ciudad_nombre) " +
                    "VALUES (?)";            
            try( PreparedStatement estadoCiudad = (PreparedStatement)conexPueblo.prepareStatement(
                    SQLqueryGuardarCiudad,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoCiudad.setString(1, DatoCiudad.getNombre());
                int FilaIns = estadoCiudad.executeUpdate();
                ResultSet ClaveGen = null;
                if( FilaIns > 0 ) {
                    ClaveGen = estadoCiudad.getGeneratedKeys();
                }
                if( ClaveGen.next() ) {
                    IDtp = ClaveGen.getInt(1);
                }
                estadoCiudad.close();
            }
            conexPueblo.close();
            return IDtp;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL (Push2TBLCiudad): " + excp.getMessage());
            return IDtp;
        }
    }
    /**
     * Guarda los datos de la Empresa hacia la BD. Los datos de la Empresa
     * se guardan en una tabla cuasi-primaria pues requiere el ID de la Ciudad-Empresa.
     * @param DatosEmpresa Objeto Empresa
     * @param IDCiudad ID Ciudad
     * @return ID Empresa
     */
    public int Push2TBLEmpresa(Empresa DatosEmpresa, int IDCiudad) {
        int IDtp = -1;
        try( Connection conexChuzo = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryGuardarEmpresa = "INSERT " +
                    "INTO empresa (empresa_nomempr, empresa_identtrib, empresa_fk_ciudad) " +
                    "VALUES (?, ?, ?)";
            try( PreparedStatement estadoEmpresa = (PreparedStatement)conexChuzo.prepareStatement(
                    SQLqueryGuardarEmpresa,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoEmpresa.setString(1, DatosEmpresa.getNomempr());
                estadoEmpresa.setString(2, DatosEmpresa.getIdenttrib());
                estadoEmpresa.setInt(3, IDCiudad);
                int FilaIns = estadoEmpresa.executeUpdate();
                ResultSet ClaveGen = null;
                if( FilaIns > 0 ) {
                    ClaveGen = estadoEmpresa.getGeneratedKeys();
                }
                if( ClaveGen.next() ) {
                    IDtp = ClaveGen.getInt(1);
                }
                estadoEmpresa.close();
            }
            conexChuzo.close();
            return IDtp;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL (Push2TBLEmpresa): " + excp.getMessage());
            return IDtp;
        }
    }
    /**
     * Guarda los datos de Contacto hacia la BD
     * @param DatosContacto Objeto Contacto
     * @return ID Contacto
     */
    public int Push2TBLContacto(Contacto DatosContacto) {
        int IDtp = -1;
        try( Connection conexMSJ = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryGuardarContacto = "INSERT " +
                    "INTO contacto (contacto_tipotel, contacto_numtel, contacto_email) " +
                    "VALUES (?, ?, ?)";
            try( PreparedStatement estadoContacto = (PreparedStatement)conexMSJ.prepareStatement(
                    SQLqueryGuardarContacto,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoContacto.setString(1, DatosContacto.getTipotel());
                estadoContacto.setString(2, DatosContacto.getNumtel());
                estadoContacto.setString(3, DatosContacto.getEmail());                
                int FilaIns = estadoContacto.executeUpdate();
                ResultSet ClaveGen = null;
                if( FilaIns > 0 ) {
                    ClaveGen = estadoContacto.getGeneratedKeys();
                }
                if( ClaveGen.next() ) {
                    IDtp = ClaveGen.getInt(1);
                }
                estadoContacto.close();
            }
            conexMSJ.close();
            return IDtp;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL (Push2TBLContacto): " + excp.getMessage());
            return IDtp;
        }
    }
    /**
     * Guarda el dato de Tipo de Referencia hacia la BD
     * @param DatoTipoRef Objeto TipoRef
     * @return ID Tipo referencia
     */
    public int Push2TBLTipoRef(TipoRef DatoTipoRef) {
        int IDtp = -1;
        try( Connection conexCarta = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryGuardarTipoRef = "INSERT " +
                    "INTO tiporef (tiporef_tiporef) " +
                    "VALUES (?)";
            try( PreparedStatement estadoTipoRef = (PreparedStatement)conexCarta.prepareStatement(
                    SQLqueryGuardarTipoRef,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoTipoRef.setString(1, DatoTipoRef.getTiporef());
                int FilaIns = estadoTipoRef.executeUpdate();
                ResultSet ClaveGen = null;
                if( FilaIns > 0 ) {
                    ClaveGen = estadoTipoRef.getGeneratedKeys();
                }
                if( ClaveGen.next() ) {
                    IDtp = ClaveGen.getInt(1);
                }
                estadoTipoRef.close();
            }
            conexCarta.close();
            return IDtp;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL (Push2TBLTipoRef): " + excp.getMessage());
            return IDtp;
        }
    }
}
