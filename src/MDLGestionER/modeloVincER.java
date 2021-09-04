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

import libGestionER.Empleado;
import libGestionER.TipoRef;
import libGestionER.Referencia;
import libGestionER.GestionEmpRef;

/**
 * Clase modeloVincER.
 * Clase modelo para entregar/recibir la información de la Vinculación entre 
 * el Empleado y la Referencia hacia/desde la base de datos.
 * @author Miguel Molina
 */
public class modeloVincER {
    /**
     * Objeto dataGER para establecer conexión con BD
     */
    dataGER dataGERRef;
    /**
     * Constructor
     */
    public modeloVincER() {
        this.dataGERRef = new dataGER();
    }
    
    /**
     * Función modelo para Crear registro de Vinculación entre empleado y referencia
     * @param Vinculo Objeto GestionEmpRef
     * @return salida booleana
     */
    public boolean CrearGER(GestionEmpRef Vinculo) {        
        int [] IDsEmpleado;
        int [] IDsReferencia;
        modeloEmpl mdlEmpl = new modeloEmpl();
        modeloRef mdlRef = new modeloRef();
        IDsEmpleado = mdlEmpl.BuscarIDsEmpleado( Vinculo.getTrabajador().getDni_emp().getNumident() );
        IDsReferencia = mdlRef.BuscarIDsReferencia( Vinculo.getRecomendador().getContacto_ref().getEmail() );
        try( Connection conexVinculo = (Connection)DriverManager.getConnection(
                dataGERRef.getURLBD(),
                dataGERRef.getUSER(),
                dataGERRef.getPASSWD() ) ) {
            // Mandar objeto GestionEmpRef a la BD y confirmar
            String SQLqueryGuardarVinculo = "INSERT " +
                    "INTO gestionempref (gestionempref_fk_empleado, gestionempref_fk_referencia, gestionempref_fk_tiporef) " +
                    "VALUES (?, ?, ?)";
            try( PreparedStatement estadoVinculo = (PreparedStatement)conexVinculo.prepareStatement(
                    SQLqueryGuardarVinculo,
                    Statement.RETURN_GENERATED_KEYS) ) {
                estadoVinculo.setInt(1, IDsEmpleado[8]);
                estadoVinculo.setInt(2, IDsReferencia[4]);
                estadoVinculo.setInt(3, IDsReferencia[3]);
                int FilaIns = estadoVinculo.executeUpdate();
                if( FilaIns > 0 ) {
                    System.out.println("Pase por aqui! Dentro de try (modeloVincER: crear).");
                    return true;
                }
                estadoVinculo.close();
            }
            conexVinculo.close();
            return false;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return false;
        }
    }    
    /**
     * Función modelo para Buscar vínculo entre Empleado y Referencia
     * @param NumDoc Cadena con número de documento
     * @param Email Cadena con correo electrónico
     * @return Devuelve objeto GestionEmpRef desde la BD
     */
    public GestionEmpRef BuscarGER(String NumDoc, String Email) {
        int [] IDsVinculoBD = new int[3];
        GestionEmpRef varVinculo = null;
        try( Connection conexVinculo = (Connection)DriverManager.getConnection(
                dataGERRef.getURLBD(),
                dataGERRef.getUSER(),
                dataGERRef.getPASSWD()) ) {
            String SQLqueryBuscar = "SELECT " +
                    "gestionempref.gestionempref_id, empleado.empleado_id, dni.dni_numident, referencia.referencia_id, contacto.contacto_email, tiporef.tiporef_tiporef " +
                    "FROM " +
                    "gestionempref " +
                    "JOIN " +
                    "empleado " +
                    "ON gestionempref.gestionempref_fk_empleado = empleado.empleado_id " +
                    "JOIN " +
                    "dni " +
                    "ON empleado.empleado_fk_dni = dni.dni_id " +
                    "JOIN " +
                    "tiporef " +
                    "ON gestionempref.gestionempref_fk_tiporef = tiporef.tiporef_id " +
                    "JOIN " +
                    "referencia " +
                    "ON gestionempref.gestionempref_fk_referencia = referencia.referencia_id " +
                    "JOIN " +
                    "contacto " +
                    "ON referencia.referencia_fk_contacto = contacto.contacto_id " +
                    "WHERE (dni_numident=? AND contacto_email=?)";
            try( PreparedStatement estadoVinc = (PreparedStatement)conexVinculo.prepareStatement(SQLqueryBuscar) ) {
                estadoVinc.setString(1, NumDoc);
                estadoVinc.setString(2, Email);
                ResultSet resultado = estadoVinc.executeQuery();
                while( resultado.next() ) {
                    int gestionempref_id = resultado.getInt(1);
                    int empleado_id = resultado.getInt(2);
                    String dni_numident = resultado.getString(3);
                    int referencia_id = resultado.getInt(4);
                    String contacto_email = resultado.getString(5);
                    String tiporef_tiporef = resultado.getString(6);                    
                    Empleado varEmpl = new Empleado();
                    if( !dni_numident.equals("") ) {                        
                        modeloEmpl varMdlEmpl = new modeloEmpl();
                        varEmpl = varMdlEmpl.BuscarGER(dni_numident);
                    }
                    Referencia varRef = new Referencia();
                    if( !contacto_email.equals("") ) {                        
                        modeloRef varMdlRef = new modeloRef();
                        varRef = varMdlRef.BuscarGER(contacto_email);
                    }
                    TipoRef varTipRef = new TipoRef();
                    if( !tiporef_tiporef.equals("") ) {
                        varTipRef.setTiporef(tiporef_tiporef);
                    }
                    // IDs del vinculo
                    IDsVinculoBD[0] = empleado_id;
                    IDsVinculoBD[1] = referencia_id;
                    IDsVinculoBD[2] = gestionempref_id;                            
                    // Clase GestionEmpRef
                    varVinculo = new GestionEmpRef(varEmpl, varRef, varTipRef, IDsVinculoBD);
                }
                estadoVinc.close();
            }                   
            System.out.println("Pase por aqui! Dentro de try (modeloVinvcER: buscar).");
            conexVinculo.close();
            return varVinculo;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return varVinculo;        
        }
    }
    /**
     * Función modelo parar Eliminar el vínculo entre Empleado y Referencia
     * @param vincSupr Objeto GestionEmpRef a eliminar
     * @return salida booleana
     */
    public boolean BorrarGER(GestionEmpRef vincSupr) {
        int IDVinculo;
        boolean status = false;
        IDVinculo = BuscarIDVinculo( vincSupr.getTrabajador().getDni_emp().getNumident(), vincSupr.getRecomendador().getContacto_ref().getEmail() );
        try {
            MDLTPRemover DataVinculo = new MDLTPRemover();
            boolean RMVinculo = DataVinculo.RemoverDatos("gestionempref", "gestionempref_id", IDVinculo);
            if( RMVinculo ) {
                status = true;            
            }
            System.out.println("Pase por aqui! Dentro de try (modeloVincER: borrar).");
            return status;
        } catch(Exception excp){
            return false;
        }
    }
    /**
     * Función para buscar el ID de la vinculación entre Empleado y Referencia.
     * @param NumDoc Cadena con el número de documento
     * @param Email Cadena con correo electrónico
     * @return Entero con ID de la vinculación
     */
    public int BuscarIDVinculo(String NumDoc, String Email){
        int IDparam = -1;
        try( Connection conexVinculo = (Connection)DriverManager.getConnection(
                dataGERRef.getURLBD(),
                dataGERRef.getUSER(),
                dataGERRef.getPASSWD()) ) {
            // Obtencion del ID de la Vinculacion
            String SQLqueryBuscar = "SELECT " +
                    "gestionempref.gestionempref_id " +
                    "FROM " +
                    "gestionempref " +
                    "JOIN " +
                    "empleado " +
                    "ON gestionempref.gestionempref_fk_empleado = empleado.empleado_id " +
                    "JOIN " +
                    "dni " +
                    "ON empleado.empleado_fk_dni = dni.dni_id " +
                    "JOIN " +
                    "referencia " +
                    "ON gestionempref.gestionempref_fk_referencia = referencia.referencia_id " +
                    "JOIN " +
                    "contacto " +
                    "ON referencia.referencia_fk_contacto = contacto.contacto_id " +
                    "WHERE (dni_numident=? AND contacto_email=?)";
            try( PreparedStatement estadoVinculo = (PreparedStatement)conexVinculo.prepareStatement(SQLqueryBuscar) ) {
                estadoVinculo.setString(1, NumDoc);
                estadoVinculo.setString(2, Email);
                ResultSet resultado = estadoVinculo.executeQuery();
                while( resultado.next() ) {
                    //gestionempref_id
                    IDparam = resultado.getInt(1);                   
                }
                estadoVinculo.close();
            }
            System.out.println("Pase por aqui! Dentro de try (modeloVincER: buscar ID).");
            conexVinculo.close();
            return IDparam;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return -1;
        }
    }    
}
