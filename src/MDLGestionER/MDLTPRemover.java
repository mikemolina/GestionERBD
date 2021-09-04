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
import java.sql.SQLException;

/**
 * Clase MDLTPRemover
 * Clase modelo para remover datos en las tablas primarias en la base de datos.
 * Cada función gestiona una conexión con la base de datos para remover los
 * datos de una tabla específica.
 * A su vez la función, retorna un valor booleano para confirmar la operación.
 * @author Miguel Molina
 */
public class MDLTPRemover {
    /**
     * Objeto dataGER para establecer conexión con BD y modificar tablas primarias
     */
    dataGER dataGERTP;
    /**
     * Constructor
     */
    public MDLTPRemover() {
        this.dataGERTP = new dataGER();
    }
        
    /**
     * Función génerica para remover los datos de una tabla
     * @param NombreTBL Nombre de la tabla
     * @param ColID Nombre columna ID
     * @param ID Número ID del parámetro
     * @return Confirmación de la eliminación en la BD
     */
    public boolean RemoverDatos(String NombreTBL, String ColID, int ID) {
        boolean status = false;
        int Filas;
        try( Connection conexTBL = (Connection)DriverManager.getConnection(
                dataGERTP.getURLBD(),
                dataGERTP.getUSER(),
                dataGERTP.getPASSWD() ) ) {
            String SQLqueryRemdatTBL = "DELETE FROM " +  NombreTBL + " WHERE " + ColID + "=?";            
            try( PreparedStatement estadoTBL =(PreparedStatement)conexTBL.prepareStatement(SQLqueryRemdatTBL) ){
                    estadoTBL.setInt(1, ID);
                    Filas = estadoTBL.executeUpdate();                    
                    if( Filas > 0 ) {
                        status = true;
                    }
                    estadoTBL.close();
                }
            conexTBL.close();
            return status;
        } catch(SQLException excp) {
            System.err.println("Mensaje de error SQL (RemoverDatos): " + excp.getMessage());
            return false;
        }
    }    
}
