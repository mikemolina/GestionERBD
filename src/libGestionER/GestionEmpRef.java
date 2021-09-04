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
 * Clase GestionEmpRef.
 * Clase de la librería Gestion Empleado-Referencia (motor del MVC) para
 * registrar la información de la vinculación entre el empleado y la 
 * referencia.
 * @author Miguel Molina
 */
public class GestionEmpRef {
    /**
     * Objeto persona-empleado
     */
    private Empleado trabajador;
    /**
     * Objeto persona-referencia
     */
    private Referencia recomendador;
    /**
     * Objeto carta de referencia
     */
    private TipoRef carta;
    /**
     * Vector entero con tripleta de ID correspondiente al
     * vinculo creado en la BD. La asignación del arreglo es:
     * [0] -> ID Empleado
     * [1] -> ID Referencia
     * [2] -> ID Vinculo
     */
    private int [] IDVinculo = new int[3];
    /**
     * Constructor
     * @param trabajador Objeto Empleado
     * @param recomendador Objeto Referencia
     * @param carta Objeto carta de referencia;
     * @param IDVinculo Vector entero con tripleta de ID
     */
    public GestionEmpRef(Empleado trabajador, Referencia recomendador, TipoRef carta, int [] IDVinculo) {
        this.trabajador = trabajador;
        this.recomendador = recomendador;
        this.carta = carta;
        this.IDVinculo = IDVinculo;
    }
    /**
     * Constructor por defecto
     */
    public GestionEmpRef() {
        Empleado anonymusEmpl = new Empleado();
        Referencia anonymusRef = new Referencia();
        TipoRef NA = new TipoRef();
        int [] sinvinculo = {0, 0, 0};
    }
    
    /**
     * @return Objeto Empleado
     */
    public Empleado getTrabajador() {
        return trabajador;
    }

    /**
     * @param trabajador Objeto Empleado
     */
    public void setTrabajador(Empleado trabajador) {
        this.trabajador = trabajador;
    }

    /**
     * @return Objeto Referencia
     */
    public Referencia getRecomendador() {
        return recomendador;
    }

    /**
     * @param recomendador Objeto Referencia
     */
    public void setRecomendador(Referencia recomendador) {
        this.recomendador = recomendador;
    }

    /**
     * @return Objeto carta de referencia
     */
    public TipoRef getCarta() {
        return carta;
    }

    /**
     * @param carta Objeto tipo de referencia
     */
    public void setCarta(TipoRef carta) {
        this.carta = carta;
    }
    
    /**
     * Función para mostrar el vínculo entre empleado y referencia
     * @param emp Objeto Empleado
     * @param ref Objeto Referencia
     * @param tref Objeto tipo de referencia
     */
    public void MostrarVinculoLaboral(Empleado emp, Referencia ref, TipoRef tref){
        System.out.println(emp.NombreCompletoEmpl() + " es un empleado de la empresa " + 
                           emp.NombreEmpresa() + " y tiene una referencia " + 
                           tref.getTiporef() + " de " + ref.NombreCompletoRef());
    }
    /**
     * Función para retornar el ID de algún actor de la vinculación
     * @param Actor Designación del ID del actor
     * @return ID del actor almacenado en la BD
     */
    public int ImpIDVinculo(int Actor) {
        return IDVinculo[Actor];
    }
}
