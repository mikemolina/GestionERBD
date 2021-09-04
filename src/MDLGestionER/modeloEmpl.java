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
import java.util.Arrays;

import libGestionER.Persona;
import libGestionER.DNI;
import libGestionER.Genero;
import libGestionER.EstadoCivil;
import libGestionER.Ciudad;
import libGestionER.Empresa;
import libGestionER.Contacto;
import libGestionER.Empleado;


/**
 * Clase modeloEmpl.
 * Clase modelo para entregar/recibir la información del Empleado hacia/desde
 * la base de datos.
 * @author Miguel Molina
 */
public class modeloEmpl {
    /**
     * Objeto dataGER para establecer conexión con BD
     */
    dataGER dataGEREmpl;
    /**
     * Constructor
     */
    public modeloEmpl() {
        this.dataGEREmpl = new dataGER();
    }
     
    /**
     * Función modelo para Crear registro Empleado
     * @param empleado Objeto clase Empleado
     * @return salida booleana
     */
    public boolean CrearGER(Empleado empleado) {
        MDLTPGuardar DataPersona = new MDLTPGuardar();
        MDLTPGuardar DataDNI = new MDLTPGuardar();
        MDLTPGuardar DataGenero = new MDLTPGuardar();
        MDLTPGuardar DataEstCiv = new MDLTPGuardar();
        MDLTPGuardar DataCiudad = new MDLTPGuardar();
        MDLTPGuardar DataEmpresa = new MDLTPGuardar();
        MDLTPGuardar DataContacto = new MDLTPGuardar();
        int IDPersona = DataPersona.Push2TBLPersona(empleado.getPersona_emp());
        int IDDNI = DataDNI.Push2TBLDNI(empleado.getDni_emp());
        int IDGenero = DataGenero.Push2TBLGenero(empleado.getGenero_emp());
        int IDEstCiv = DataEstCiv.Push2TBLEstadoCivil(empleado.getEstciv_emp());
        int IDCiudadEmpl = DataCiudad.Push2TBLCiudad(empleado.getCiudad_emp());
        int IDCiudadEmpr = DataCiudad.Push2TBLCiudad(empleado.getEmpresa_emp().getCiudad_empr());
        int IDEmpresa = DataEmpresa.Push2TBLEmpresa(empleado.getEmpresa_emp(), IDCiudadEmpr);
        int IDContacto = DataContacto.Push2TBLContacto(empleado.getContacto_emp());
        try( Connection conexEmpl = (Connection)DriverManager.getConnection(
                dataGEREmpl.getURLBD(),
                dataGEREmpl.getUSER(),
                dataGEREmpl.getPASSWD() ) ) {            
            // Mandar objeto Empleado y Empresa a la BD y confirmar
            String SQLqueryGuardarEmpleado = "INSERT " +
                    "INTO empleado (empleado_datanac, empleado_fk_persona, empleado_fk_dni, empleado_fk_genero, empleado_fk_estadocivil, empleado_fk_ciudad, empleado_fk_empresa, empleado_fk_contacto) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try( PreparedStatement estadoEmpleado = (PreparedStatement)conexEmpl.prepareStatement(SQLqueryGuardarEmpleado) ) {
                estadoEmpleado.setString(1, empleado.getDatanac());
                estadoEmpleado.setInt(2, IDPersona);
                estadoEmpleado.setInt(3, IDDNI);
                estadoEmpleado.setInt(4, IDGenero);
                estadoEmpleado.setInt(5, IDEstCiv);
                estadoEmpleado.setInt(6, IDCiudadEmpl);
                estadoEmpleado.setInt(7, IDEmpresa);
                estadoEmpleado.setInt(8, IDContacto);
                int FilaIns = estadoEmpleado.executeUpdate();                
                if( FilaIns > 0 ) {
                    System.out.println("Pase por aqui! Dentro de try (modeloEmpl: crear).");
                    return true;
                }
                estadoEmpleado.close();
            }
            conexEmpl.close();
            return false;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return false;
        }
    }
    /**
     * Función modelo para Actualizar registro Empleado
     * @param emplDataPos Objeto Empleado con datos (nuevos) para modificar
     * @return salida booleana
     */
    public boolean ActualizarGER(Empleado emplDataPos) {
        int [] IDsEmpleado;
        boolean status = false;
        String NumDoc = emplDataPos.getDni_emp().getNumident();
        Empleado emplDataAnt = null;
        emplDataAnt = BuscarGER(NumDoc);
        IDsEmpleado = BuscarIDsEmpleado(NumDoc);
        // Imprimir IDsEmpleado
        System.out.println(Arrays.toString(IDsEmpleado));
        try{
            if( emplDataAnt != null ) {
                MDLTPModificar DataPersona = new MDLTPModificar();
                MDLTPModificar DataGenero = new MDLTPModificar();
                MDLTPModificar DataEstCiv = new MDLTPModificar();
                MDLTPModificar DataCiudad = new MDLTPModificar();
                MDLTPModificar DataDataNac = new MDLTPModificar();
                MDLTPModificar DataEmpresa = new MDLTPModificar();
                MDLTPModificar DataContacto = new MDLTPModificar();
                // IDsEmpleado[0] -> persona_id
                boolean UpgPersona = DataPersona.Mod2TBLPersona(emplDataAnt.getPersona_emp(), emplDataPos.getPersona_emp(), IDsEmpleado[0]);
                // IDsEmpleado[1] -> genero_id
                boolean UpgGenero = DataGenero.Mod2TBLGenero(emplDataAnt.getGenero_emp(), emplDataPos.getGenero_emp(), IDsEmpleado[1]);
                // IDsEmpleado[2] -> estadocivil_id
                boolean UpgEstCiv = DataEstCiv.Mod2TBLEstadoCivil(emplDataAnt.getEstciv_emp(), emplDataPos.getEstciv_emp(), IDsEmpleado[2]);
                // IDsEmpleado[3] -> ciudad_id (Empleado)
                boolean UpgCiuEmpl = DataCiudad.Mod2TBLCiudad(emplDataAnt.getCiudad_emp(), emplDataPos.getCiudad_emp(), IDsEmpleado[3]);
                // IDsEmpleado[8] -> empleado_id
                boolean UpgFechaN = DataDataNac.Mod2TBLDataNac(emplDataAnt.getDatanac(), emplDataPos.getDatanac(), IDsEmpleado[8]);
                // IDsEmpleado[5] -> contacto_id
                boolean UpgContac = DataContacto.Mod2TBLContacto(emplDataAnt.getContacto_emp(), emplDataPos.getContacto_emp(), IDsEmpleado[5]);
                // IDsEmpleado[6] -> empresa_id
                boolean UpgEmprEmpl = DataEmpresa.Mod2TBLEmpresa(emplDataAnt.getEmpresa_emp(), emplDataPos.getEmpresa_emp(), IDsEmpleado[6]);
                // IDsEmpleado[7] -> ciudad_id (Empresa)
                boolean UpgCiuEmpr = DataCiudad.Mod2TBLCiudad(emplDataAnt.getEmpresa_emp().getCiudad_empr(), emplDataPos.getEmpresa_emp().getCiudad_empr(), IDsEmpleado[7]);
                if( UpgPersona && UpgGenero && UpgEstCiv && UpgCiuEmpl && UpgFechaN && UpgContac && UpgEmprEmpl && UpgCiuEmpr) {
                    status = true;
                }
                System.out.println("Pase por aqui! Dentro de try (modeloEmpl: actualizar).");
            }
            return status;
        } catch(Exception excp){
            System.err.println("Mensaje de error : " + excp.getMessage());
            return false;
        }
    }
    /**
     * Función modelo para Eliminar registro Empleado
     * @param emplSupr Objeto Empleado a eliminar
     * @return salida booleana
     */
    public boolean BorrarGER(Empleado emplSupr) {
        int [] IDsEmpleado;
        boolean status = false;
        IDsEmpleado = BuscarIDsEmpleado( emplSupr.getDni_emp().getNumident() );
        try{
            MDLTPRemover DataDNI = new MDLTPRemover();
            MDLTPRemover DataPersona = new MDLTPRemover();            
            MDLTPRemover DataGenero = new MDLTPRemover();
            MDLTPRemover DataEstCiv = new MDLTPRemover();
            MDLTPRemover DataCiuEmpl = new MDLTPRemover();            
            MDLTPRemover DataContacto = new MDLTPRemover();
            MDLTPRemover DataCiuEmpr = new MDLTPRemover();
            MDLTPRemover DataEmpresa = new MDLTPRemover();
            MDLTPRemover DataEmpleado = new MDLTPRemover();
            // IDsEmpleado[8] -> empleado_id
            boolean RMEmpleado = DataEmpleado.RemoverDatos("empleado", "empleado_id", IDsEmpleado[8]);
            // IDsEmpleado[6] -> empresa_id
            boolean RMEmpresa = DataEmpresa.RemoverDatos("empresa", "empresa_id", IDsEmpleado[6]);
            // IDsEmpleado[7] -> ciudad_id (Empresa)
            boolean RMCiuEmpr = DataCiuEmpr.RemoverDatos("ciudad", "ciudad_id", IDsEmpleado[7]);
            // IDsEmpleado[5] -> contacto_id
            boolean RMContaco = DataContacto.RemoverDatos("contacto", "contacto_id", IDsEmpleado[5]);
            // IDsEmpleado[3] -> ciudad_id (Empleado)
            boolean RMCiuEmpl = DataCiuEmpl.RemoverDatos("ciudad", "ciudad_id", IDsEmpleado[3]);
            // IDsEmpleado[2] -> estadocivil_id
            boolean RMEstCiv = DataEstCiv.RemoverDatos("estadocivil", "estadocivil_id", IDsEmpleado[2]);
            // IDsEmpleado[1] -> genero_id
            boolean RMGenero = DataGenero.RemoverDatos("genero", "genero_id", IDsEmpleado[1]);
            // IDsEmpleado[0] -> persona_id
            boolean RMPersona = DataPersona.RemoverDatos("persona", "persona_id", IDsEmpleado[0]);
            // IDsEmpleado[9] -> dni_id
            boolean RMDNI = DataDNI.RemoverDatos("dni", "dni_id", IDsEmpleado[9]);
            if( RMEmpleado && RMEmpresa && RMCiuEmpr && RMContaco && RMCiuEmpl && RMEstCiv && RMGenero && RMPersona && RMDNI ) {
                status = true;            
            }
            System.out.println("Pase por aqui! Dentro de try (modeloEmpl: borrar).");
            return status;
        } catch(Exception excp){
            return false;
        }
    }
    /**
     * Función modelo para Buscar registro Empleado
     * @param NumDoc Cadena con número de documento
     * @return Devuelve objeto Empleado desde la BD
     */
    public Empleado BuscarGER(String NumDoc) {
        Empleado varEmpl = null;
        int IDCiudadEMpr = 0;
        // Variables del empleado vacias
        String empleado_datanac = "";
        Persona varPer = new Persona();
        Ciudad varCiuEmpl = new Ciudad();
        DNI varDNI = new DNI();
        EstadoCivil varEstCiv = new EstadoCivil();
        Genero varGenr = new Genero();
        Contacto varCont = new Contacto();
        // Mas variables del empleado vacias!
        Ciudad varCiuEmpr = new Ciudad();
        Empresa varEmpr = new Empresa();
        try( Connection conexEmpl = (Connection)DriverManager.getConnection(
                dataGEREmpl.getURLBD(),
                dataGEREmpl.getUSER(),
                dataGEREmpl.getPASSWD()) ) {
            // Obtencion de datos "Empleado - Empresa"
            String SQLquery1Buscar = "SELECT " +
                    "persona.persona_primernom, persona.persona_segundonom, persona.persona_primerapell, persona.persona_segundoapell, persona.persona_direccion, " +
                    "ciudad.ciudad_nombre, " +
                    "empleado.empleado_datanac, " +
                    "dni.dni_tipodoc, dni.dni_numident, " +
                    "estadocivil.estadocivil_tipo, " +
                    "genero.genero_sexo, " +
                    "contacto.contacto_tipotel, contacto.contacto_numtel, contacto.contacto_email, " +
                    "empresa.empresa_fk_ciudad " +
                    "FROM empleado " +
                    "JOIN persona " +
                    "ON empleado.empleado_fk_persona = persona.persona_id " +
                    "JOIN ciudad " +
                    "ON empleado.empleado_fk_ciudad = ciudad.ciudad_id " +
                    "JOIN dni " +
                    "ON empleado.empleado_fk_dni = dni.dni_id " +
                    "JOIN estadocivil " +
                    "ON empleado.empleado_fk_estadocivil = estadocivil.estadocivil_id " +
                    "JOIN genero " +
                    "ON empleado.empleado_fk_genero = genero.genero_id " +
                    "JOIN contacto " +
                    "ON empleado.empleado_fk_contacto = contacto.contacto_id " +
                    "JOIN empresa " +
                    "ON empleado.empleado_fk_empresa = empresa.empresa_id " +
                    "WHERE dni.dni_numident=?";
            boolean status1 = false;
            try( PreparedStatement estadoEmpl1 = (PreparedStatement)conexEmpl.prepareStatement(SQLquery1Buscar) ) {
                // setString(numero ordinal del ? (prim, sec, ...), cadena)
                estadoEmpl1.setString(1, NumDoc);
                ResultSet resultado1 = estadoEmpl1.executeQuery();
                while( resultado1.next() ) {
                    // Clase Persona
                    String persona_primernom = resultado1.getString(1);
                    String persona_segundonom = resultado1.getString(2);
                    String persona_primerapell = resultado1.getString(3);
                    String persona_segundoapell = resultado1.getString(4);
                    String persona_direccion = resultado1.getString(5);                    
                    varPer = new Persona(persona_primernom, persona_segundonom, persona_primerapell, persona_segundoapell, persona_direccion);
                    // Clase Ciudad
                    String ciudad_nombre = resultado1.getString(6);
                    varCiuEmpl = new Ciudad(ciudad_nombre);
                    empleado_datanac = resultado1.getString(7);
                    // Clase DNI
                    String dni_tipodoc = resultado1.getString(8);
                    String dni_numident = resultado1.getString(9);
                    varDNI = new DNI(dni_tipodoc, dni_numident);
                    // Clase EstadoCivil
                    String estadocivil_tipo = resultado1.getString(10);
                    varEstCiv = new EstadoCivil(estadocivil_tipo);
                    // Clase Genero
                    String genero_sexo = resultado1.getString(11);
                    varGenr = new Genero(genero_sexo);
                    // Clase Contacto
                    String contacto_tipotel = resultado1.getString(12);
                    String contacto_numtel = resultado1.getString(13);
                    String contacto_email = resultado1.getString(14);
                    varCont = new Contacto(contacto_tipotel, contacto_numtel, contacto_email);
                    // Hmmm ... ID de la ciudad de la Empresa
                    IDCiudadEMpr = resultado1.getInt(15);
                    status1 = true;
                }                
                estadoEmpl1.close();
            }
            // Obtencion de datos "Empresa"
            String SQLquery2Buscar = "SELECT " +
                    "empresa.empresa_nomempr, empresa.empresa_identtrib, ciudad.ciudad_nombre, empresa.empresa_fk_ciudad " +
                    "FROM empresa " +
                    "JOIN ciudad " +
                    "ON empresa.empresa_fk_ciudad = ciudad.ciudad_id " +
                    "WHERE empresa.empresa_fk_ciudad=?";
            boolean status2 = false;
            try( PreparedStatement estadoEmpl2 = (PreparedStatement)conexEmpl.prepareStatement(SQLquery2Buscar) ) {
                estadoEmpl2.setString(1, String.valueOf(IDCiudadEMpr));
                ResultSet resultado2 = estadoEmpl2.executeQuery();
                while( resultado2.next() ) {
                    int empresa_fk_ciudad= resultado2.getInt(4);
                    // Clase Ciudad
                    String empresa_ciudad_nombre = resultado2.getString(3);
                    varCiuEmpr = new Ciudad(empresa_ciudad_nombre);
                    // Clase Empresa
                    String empresa_identtrib= resultado2.getString(2);
                    String empresa_nomempr = resultado2.getString(1);
                    varEmpr = new Empresa(empresa_nomempr, empresa_identtrib, varCiuEmpr);
                    status2 = true;
                }                
                estadoEmpl2.close();
            }            
            // Clase Empleado
            // Obtenida con los datos: "Empleado - Empresa" U "Empresa"
            if( status1 && status2 ) {
                varEmpl = new Empleado(empleado_datanac, varPer, varDNI, varGenr, varEstCiv, varCiuEmpl, varEmpr, varCont);
            }
            System.out.println("Pase por aqui! Dentro de try (modeloEmpl: buscar).");
            conexEmpl.close();
            return varEmpl;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return varEmpl;
        }
    }
    /**
     * Función para buscar los IDs de los parámetros del Empleado.
     * @param NumDoc Cadena con número de documento
     * @return Vector con IDs de los parámetros del Empleado
     */
    public int [] BuscarIDsEmpleado(String NumDoc){
        int [] IDparam = new int[10];
        try( Connection conexEmpl = (Connection)DriverManager.getConnection(
                dataGEREmpl.getURLBD(),
                dataGEREmpl.getUSER(),
                dataGEREmpl.getPASSWD()) ) {
            // Obtencion de IDs del Empleado
            String SQLquery1Buscar = "SELECT " +
                    "persona.persona_id, genero.genero_id, estadocivil.estadocivil_id, ciudad.ciudad_id, empleado.empleado_fk_empresa, contacto.contacto_id, empleado.empleado_id, dni.dni_id " +
                    "FROM empleado " +
                    "JOIN dni " +
                    "ON empleado.empleado_fk_dni = dni.dni_id " +
                    "JOIN persona " +
                    "ON empleado.empleado_fk_persona = persona.persona_id " +
                    "JOIN genero " +
                    "ON empleado.empleado_fk_genero = genero.genero_id " +
                    "JOIN estadocivil " +
                    "ON empleado.empleado_fk_estadocivil = estadocivil.estadocivil_id " +
                    "JOIN ciudad " +
                    "ON empleado.empleado_fk_ciudad = ciudad.ciudad_id " +
                    "JOIN empresa " +
                    "ON empleado.empleado_fk_empresa = empresa.empresa_id " +
                    "JOIN contacto " +
                    "ON empleado.empleado_fk_contacto = contacto.contacto_id " +
                    "WHERE dni.dni_numident=?";
            try( PreparedStatement estadoEmpl1 = (PreparedStatement)conexEmpl.prepareStatement(SQLquery1Buscar) ) {
                estadoEmpl1.setString(1, NumDoc);
                ResultSet resultado1 = estadoEmpl1.executeQuery();
                while( resultado1.next() ) {
                    //persona_id
                    IDparam[0] = resultado1.getInt(1);                    
                    //genero_id
                    IDparam[1] = resultado1.getInt(2);
                    //estadocivil_id
                    IDparam[2] = resultado1.getInt(3);
                    //ciudad_id (Empleado)
                    IDparam[3] = resultado1.getInt(4);
                    //empleado_fk_empresa
                    IDparam[4] = resultado1.getInt(5);
                    //contacto_id
                    IDparam[5] = resultado1.getInt(6);
                    //empleado_id
                    IDparam[8] = resultado1.getInt(7);
                    //dni_id
                    IDparam[9] = resultado1.getInt(8);
                }
                estadoEmpl1.close();
            }
            // Obtencion de IDs del Empresa
            String SQLquery2Buscar = "SELECT " +
                    "empresa.empresa_id, ciudad.ciudad_id " +
                    "FROM empresa " +
                    "JOIN ciudad " +
                    "ON empresa.empresa_fk_ciudad = ciudad.ciudad_id " +
                    "WHERE empresa.empresa_id=?";
            try( PreparedStatement estadoEmpl2 = (PreparedStatement)conexEmpl.prepareStatement(SQLquery2Buscar) ) {
                estadoEmpl2.setString(1, String.valueOf(IDparam[4]));            
                ResultSet resultado2 = estadoEmpl2.executeQuery();
                while( resultado2.next() ) {
                    //empresa_id
                    IDparam[6] = resultado2.getInt(1);
                    //ciudad_id (Empresa)
                    IDparam[7] = resultado2.getInt(2);
                }
                estadoEmpl2.close();
            }
            System.out.println("Pase por aqui! Dentro de try (modeloEmpl: buscar IDs).");
            conexEmpl.close();
            return IDparam;
        } catch(SQLException excp){
            System.err.println("Mensaje de error SQL: " + excp.getMessage());
            return null;
        }
    }
}
