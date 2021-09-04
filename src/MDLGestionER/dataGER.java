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

/**
 * Clase dataGER.
 * Clase para realizar la integración de conexión entre lenguaje JAVA con el
 * motor de la base de datos MySQL, via API JDBC.
 * Info: <https://docs.oracle.com/javase/tutorial/jdbc/overview/index.html>.
 * @author Miguel Molina
 */
public class dataGER {
    /**
     * Nombre de la clase del controlador
     */
    private static final String DRIVERJDBC = "com.mysql.jdbc.Driver";
    /**
     * URL de la base de datos
     */
    private static final String URLBD = "jdbc:mysql://localhost:3306/GestionERBD";
    // !!! ALERTA DE SEGURIDAD !!!
    // Cadenas para el acceso a la BD
    /**
     * Campo usuario; por lo general es "root"
     */
    private static final String USER = "suUSUARIO";
    /**
     * Campo clave; por lo general es ""
     */
    private static final String PASSWD = "suCLAVE";
    
     /**
     * @return DRIVERJDBC
     */
    public static String getDRIVERJDBC() {
        return DRIVERJDBC;
    }

    /**
     * @return URLBD
     */
    public static String getURLBD() {
        return URLBD;
    }

    /**
     * @return USER
     */
    public static String getUSER() {
        return USER;
    }

    /**
     * @return PASSWD
     */
    public static String getPASSWD() {
        return PASSWD;
    }
}
