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
 * Clase Empresa.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar la información de la empresa.
 * @author Miguel Molina
 */
public class Empresa {
    /**
     * Nombre empresa
     */
    private String nomempr;
    /**
     * Número identificación tributaria de la empresa
     */
    private String identtrib;
    /**
     * Objeto Ciudad de ubicación de la empresa
     */
    private Ciudad ciudad_empr;
   /**
    * Constructor
    * @param nomempr Nombre empresa
    * @param identtrib Número identificación tributaria de la empresa
    * @param ciudad_empr Ciudad de ubicación de la empresa 
    */
    public Empresa(String nomempr, String identtrib, Ciudad ciudad_empr) {
        this.nomempr = nomempr;
        this.identtrib = identtrib;
        this.ciudad_empr = ciudad_empr;
    }
    /**
     * Constructor por defecto
     */
    public Empresa() {
        Ciudad perdida = new Ciudad();
        nomempr = "";
        identtrib = "";
        ciudad_empr = perdida;    
    }
    
    /**
     * @return Cadena nombre empresa
     */
    public String getNomempr() {
        return nomempr;
    }

    /**
     * @param nomempr Nombre empresa
     */
    public void setNomempr(String nomempr) {
        this.nomempr = nomempr;
    }

    /**
     * @return Cadena número identificación tributaria de la empresa
     */
    public String getIdenttrib() {
        return identtrib;
    }

    /**
     * @param identtrib Número identificación tributaria de la empresa
     */
    public void setIdenttrib(String identtrib) {
        this.identtrib = identtrib;
    }

    /**
     * @return Objeto Ciudad de ubicación de la empresa
     */
    public Ciudad getCiudad_empr() {
        return ciudad_empr;
    }

    /**
     * @param ciudad_empr Objeto Ciudad de ubicación de la empresa
     */
    public void setCiudad_empr(Ciudad ciudad_empr) {
        this.ciudad_empr = ciudad_empr;
    }
}
