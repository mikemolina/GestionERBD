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
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import libGestionER.Persona;
import libGestionER.Ciudad;
import libGestionER.Contacto;
import libGestionER.TipoRef;
import libGestionER.Referencia;


/**
 * Clase modeloRef.
 * Clase modelo para entregar/recibir la información de la Referencia
 * hacia/desde la base de datos.
 * @author Miguel Molina
 */
public class modeloRef {
    /**
     * Objeto dataGER para establecer conexión con BD
     */
    dataGER dataGERRef;
    /**
     * Constructor
     */
    public modeloRef() {
        this.dataGERRef = new dataGER();
    }
    
    /**
     * Función modelo para Crear registro de la Referencia
     * @param referencia Objeto Referencia
     * @return salida booleana
     */
    public boolean CrearGER(Referencia referencia) {
        MDLTPGuardar DataPersona = new MDLTPGuardar();
        MDLTPGuardar DataCiudad = new MDLTPGuardar();
        MDLTPGuardar DataContacto = new MDLTPGuardar();
        MDLTPGuardar DataTipoRef = new MDLTPGuardar();
        int IDPersona = DataPersona.Push2TBLPersona(referencia.getPersona_ref());
        int IDCiudadRef = DataCiudad.Push2TBLCiudad(referencia.getCiudad_ref());
        int IDContacto = DataContacto.Push2TBLContacto(referencia.getContacto_ref());
        int IDTipoRef = DataTipoRef.Push2TBLTipoRef(referencia.getTiporef_ref());        
        try( Connection conexRef = (Connection)DriverManager.getConnection(
                dataGERRef.getURLBD(),
                dataGERRef.getUSER(),
                dataGERRef.getPASSWD() ) ) {
            // Mandar objeto Referencia a la BD y confirmar
            String SQLqueryGuardarReferencia = "INSERT " +
                    "INTO referencia (referencia_fk_persona, referencia_fk_ciudad, referencia_fk_contacto, referencia_fk_tiporef) " +
                    "VALUES (?, ?, ?, ?)";
            try( PreparedStatement estadoReferencia = (PreparedStatement)conexRef.prepareStatement(SQLqueryGuardarReferencia) ) {
                estadoReferencia.setInt(1, IDPersona);
                estadoReferencia.setInt(2, IDCiudadRef);
                estadoReferencia.setInt(3, IDContacto);
                estadoReferencia.setInt(4, IDTipoRef);
                int FilaIns = estadoReferencia.executeUpdate();
                if( FilaIns > 0 ) {
                    System.out.println("Pase por aqui! Dentro de try (modeloRef: crear).");
                    return true;
                }
                estadoReferencia.close();            
            }
            conexRef.close();
            return false;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return false;
        }
    }
    /**
     * Función modelo para Actualizar registro de la Referencia
     * @param refDataPos Objeto Referencia con datos (nuevos) para modificar
     * @return salida booleana
     */
    public boolean ActualizarGER(Referencia refDataPos) {
        int [] IDsReferencia;
        boolean status = false;
        String Email = refDataPos.getContacto_ref().getEmail();
        Referencia refDataAnt = null;
        refDataAnt = BuscarGER(Email);
        IDsReferencia = BuscarIDsReferencia(Email);
        // Imprimir IDsReferencia
        //System.out.println(Arrays.toString(IDsReferencia));
        try{
            if( refDataAnt != null ) {
                MDLTPModificar DataPersona = new MDLTPModificar();
                MDLTPModificar DataCiudad = new MDLTPModificar();
                MDLTPModificar DataContacto = new MDLTPModificar();
                MDLTPModificar DataTipoRef = new MDLTPModificar();
                // IDsReferencia[0] -> persona_id
                boolean UpgPersona = DataPersona.Mod2TBLPersona(refDataAnt.getPersona_ref(), refDataPos.getPersona_ref(), IDsReferencia[0]);
                // IDsReferencia[1] -> ciudad_id
                boolean UpgCiuRef = DataCiudad.Mod2TBLCiudad(refDataAnt.getCiudad_ref(), refDataPos.getCiudad_ref(), IDsReferencia[1]);
                // IDsReferencia[2] -> contacto_id
                boolean UpgContac = DataContacto.Mod2TBLContacto(refDataAnt.getContacto_ref(), refDataPos.getContacto_ref(), IDsReferencia[2]);
                // IDsReferencia[3] -> tiporef_id
                boolean UpgTipRef = DataTipoRef.Mod2TBLTipoRef(refDataAnt.getTiporef_ref(), refDataPos.getTiporef_ref(), IDsReferencia[3]);
                if( UpgPersona && UpgCiuRef && UpgContac && UpgTipRef ) {
                    status = true;
                }
                System.out.println("Pase por aqui! Dentro de try (modeloRef: actualizar).");
            }
            return status;           
        } catch(Exception excp){
            System.err.println("Mensaje de error : " + excp.getMessage());
            return false;
        }
    }
    /**
     * Función modelo parar Eliminar registro de la Referencia
     * @param refSupr Objeto Referencia a eliminar
     * @return salida booleana
     */
    public boolean BorrarGER(Referencia refSupr) {
        int [] IDsReferencia;
        boolean status = false;
        IDsReferencia = BuscarIDsReferencia( refSupr.getContacto_ref().getEmail() );
        try{
            MDLTPRemover DataPersona = new MDLTPRemover();
            MDLTPRemover DataCiudad = new MDLTPRemover();
            MDLTPRemover DataContacto = new MDLTPRemover();
            MDLTPRemover DataTipoRef = new MDLTPRemover();
            MDLTPRemover DataReferencia = new MDLTPRemover();
            // IDsReferencia[4] -> referencia_id
            boolean RMReferencia = DataReferencia.RemoverDatos("referencia", "referencia_id", IDsReferencia[4]);
            // IDsReferencia[3] -> tiporef_id
            boolean RMTipoRef = DataTipoRef.RemoverDatos("tiporef", "tiporef_id", IDsReferencia[3]);
            // IDsReferencia[2] -> contacto_id
            boolean RMContac = DataContacto.RemoverDatos("contacto", "contacto_id", IDsReferencia[2]);
            // IDsReferencia[1] -> ciudad_id (Referencia)
            boolean RMCiudad = DataCiudad.RemoverDatos("ciudad", "ciudad_id", IDsReferencia[1]);
            // IDsReferencia[0] -> persona_id
            boolean RMPersona = DataPersona.RemoverDatos("persona", "persona_id", IDsReferencia[0]);
            if( RMReferencia && RMTipoRef && RMContac && RMCiudad && RMPersona ) {
                status = true;            
            }
            System.out.println("Pase por aqui! Dentro de try (modeloRef: borrar).");
            return status;
        } catch(Exception excp){
            return false;
        }
    }
    /**
     * Función modelo para Buscar registro de la Referencia
     * @param Email Cadena con correo electrónico
     * @return Devuelve objeto Referencia desde la BD
     */
    public Referencia BuscarGER(String Email) {
        Referencia varRef = null;
        try( Connection conexRef = (Connection)DriverManager.getConnection(
                dataGERRef.getURLBD(),
                dataGERRef.getUSER(),
                dataGERRef.getPASSWD()) ) {
            String SQLqueryBuscar = "SELECT " +
                    "persona.persona_primernom, persona.persona_segundonom, persona.persona_primerapell, persona.persona_segundoapell, persona.persona_direccion, " +
                    "ciudad.ciudad_nombre, contacto.contacto_tipotel, contacto.contacto_numtel, contacto.contacto_email, tiporef.tiporef_tiporef " +
                    "FROM persona " +
                    "JOIN referencia " +
                    "ON persona.persona_id = referencia.referencia_fk_persona " +
                    "JOIN ciudad " +
                    "ON ciudad.ciudad_id = referencia.referencia_fk_ciudad " +
                    "JOIN contacto " +
                    "ON contacto.contacto_id = referencia.referencia_fk_contacto " +
                    "JOIN tiporef " +
                    "ON tiporef.tiporef_id = referencia.referencia_fk_tiporef " +
                    "WHERE contacto_email=?";
            try( PreparedStatement estadoRef = (PreparedStatement)conexRef.prepareStatement(SQLqueryBuscar) ) {
                // setString(numero ordinal del ? (prim, sec, ...), cadena)
                estadoRef.setString(1, Email);
                ResultSet resultado = estadoRef.executeQuery();
                while( resultado.next() ) {
                    // Clase Persona
                    String persona_primernom = resultado.getString(1);
                    String persona_segundonom = resultado.getString(2);
                    String persona_primerapell = resultado.getString(3);
                    String persona_segundoapell = resultado.getString(4);
                    String persona_direccion = resultado.getString(5);
                    Persona varPer = new Persona(persona_primernom, persona_segundonom, persona_primerapell, persona_segundoapell, persona_direccion);
                    // Clase Ciudad
                    String ciudad_nombre = resultado.getString(6);
                    Ciudad varCiu = new Ciudad(ciudad_nombre);
                    // Clase Contacto
                    String contacto_tipotel = resultado.getString(7);
                    String contacto_numtel = resultado.getString(8);
                    String contacto_email = resultado.getString(9);
                    Contacto varCont = new Contacto(contacto_tipotel, contacto_numtel, contacto_email);
                    // Clase TipoRef
                    String tiporef_tiporef = resultado.getString(10);
                    TipoRef varTipRef = new TipoRef(tiporef_tiporef);
                    // Clase Referencia
                    varRef = new Referencia(varPer, varCiu, varCont, varTipRef);
                }
                estadoRef.close();
            }
            System.out.println("Pase por aqui! Dentro de try (modeloRef: buscar).");
            conexRef.close();
            return varRef;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return varRef;
        }
    }
    /**
     * Función para buscar los IDs de los parámetros de la Referencia.
     * @param Email Cadena con correo electrónico
     * @return Vector con IDs de los parámetros de la Referencia
     */
    public int [] BuscarIDsReferencia(String Email){
        int [] IDparam = new int[5];
        try( Connection conexRef = (Connection)DriverManager.getConnection(
                dataGERRef.getURLBD(),
                dataGERRef.getUSER(),
                dataGERRef.getPASSWD()) ) {
            // Obtencion de IDs de la Referencia
            String SQLqueryBuscar = "SELECT " +
                    "persona.persona_id, ciudad.ciudad_id, contacto.contacto_id, tiporef.tiporef_id, referencia.referencia_id " +
                    "FROM referencia " +
                    "JOIN contacto " +
                    "ON referencia.referencia_fk_contacto = contacto.contacto_id "+
                    "JOIN persona " +
                    "ON referencia.referencia_fk_persona = persona.persona_id " +
                    "JOIN ciudad " +
                    "ON referencia.referencia_fk_ciudad = ciudad.ciudad_id " +
                    "JOIN tiporef " +
                    "ON referencia.referencia_fk_tiporef = tiporef.tiporef_id " +
                    "WHERE contacto.contacto_email=?";
            try( PreparedStatement estadoRef = (PreparedStatement)conexRef.prepareStatement(SQLqueryBuscar) ) {
                estadoRef.setString(1, Email);
                ResultSet resultado = estadoRef.executeQuery();
                while( resultado.next() ) {
                    //persona_id
                    IDparam[0] = resultado.getInt(1);
                    //ciudad_id (Referencia)
                    IDparam[1] = resultado.getInt(2);
                    //contacto_id
                    IDparam[2] = resultado.getInt(3);
                    //tiporef_id
                    IDparam[3] = resultado.getInt(4);
                    //referencia_id
                    IDparam[4] = resultado.getInt(5);
                }
                estadoRef.close();
            }
            System.out.println("Pase por aqui! Dentro de try (modeloRef: buscar IDs).");
            conexRef.close();
            return IDparam;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return null;
        }
    }
}
