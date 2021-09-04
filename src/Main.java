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

import libGestionER.*;


/**
 * Clase Main.
 * Clase principal para pruebas de la librería libGestionER.
 * Salida vía CLI.
 * @author Miguel Molina
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        // Datos persona 1
        Persona ecpv = new Persona("Esperanza", "Camila", "Palacios", "Varela", "Jardins Mayor 65");
        DNI ecpvDNI = new DNI("Cedula de ciudadania", "72840186Z");
        Genero ecpvGen = new Genero("Femenino");
        EstadoCivil ecpvEC = new EstadoCivil("Soltera");
        Ciudad ecpvCity = new Ciudad("Sinarcas");
        Contacto ecpvCont = new Contacto("Celular", "5279770793", "91j3o10u@earthling.net");
        
        // Datos persona 2
        Persona pglp = new Persona("Pablo", "Gregorio", "Llorens", "Palma", "3096 Fleming Street");
        DNI pglpDNI = new DNI("Cedula extranjera", "LUL308136L");
        Ciudad pglpCity = new Ciudad("Dothan");
        Contacto pglpCont = new Contacto("Celular", "797546211", "g8ocegj9w@caramail.com");
        // Tipo referencia
        TipoRef pglpTRef = new TipoRef("Personal");
        
        // Datos empresa
        Ciudad ciudad_Chuzo = new Ciudad("California");
        Empresa Chuzo = new Empresa("Electronic Geek", "1980151133USA", ciudad_Chuzo);
        
        // Datos empleado 1
        Empleado ecpv_emp = new Empleado("1973-6-16", ecpv, ecpvDNI, ecpvGen, ecpvEC, ecpvCity, Chuzo, ecpvCont);
                
        // Datos referencia 1
        Referencia pglp_ref = new Referencia(pglp, pglpCity, pglpCont, pglpTRef);
        
        // Vinculacion laboral
        int [] IDVinculo = {1, 1, 1};
        GestionEmpRef vinculo = new GestionEmpRef(ecpv_emp, pglp_ref, pglpTRef, IDVinculo);
        vinculo.MostrarVinculoLaboral(ecpv_emp, pglp_ref, pglpTRef);
        System.out.println(ecpv_emp.NombreCompletoEmpl() + 
                           " es una persona tipo " + ecpv_emp.TipoObjeto() + 
                           " de la clase " + ecpv_emp.NombreClase());
        System.out.println(pglp_ref.NombreCompletoRef() +
                           " es una persona tipo " + pglp_ref.TipoObjeto() +
                           " de la clase " + pglp_ref.NombreClase());
        
        // Empleado Anonymus - prueba constructor copia
        Empleado AnonymusEmpl = new Empleado();
        System.out.println("Empleado Anonymus: " + AnonymusEmpl.NombreCompletoEmpl());
        AnonymusEmpl = ecpv_emp;
        System.out.println("Empleado Hackeado: " + AnonymusEmpl.NombreCompletoEmpl());
        
        // Referencia Anonymus - prueba constructor copia
        Referencia AnonymusRef = new Referencia();
        System.out.println("Referencia Anonymus: " + AnonymusRef.NombreCompletoRef());
        AnonymusRef = pglp_ref;
        System.out.println("Referencia Hackeada: " + AnonymusRef.NombreCompletoRef());
        
        // Vinculo inexistente - prueba constructor por defecto
        AnonymusEmpl = new Empleado();
        AnonymusRef = new Referencia();
        TipoRef CartaBlanca = new TipoRef();
        GestionEmpRef VinculoVacio = new GestionEmpRef();
        System.out.println("ID Empleado:   " + VinculoVacio.ImpIDVinculo(0));
        System.out.println("ID Referencia: " + VinculoVacio.ImpIDVinculo(1));
        System.out.println("ID Vinculo:    " + VinculoVacio.ImpIDVinculo(2));
        VinculoVacio.MostrarVinculoLaboral(AnonymusEmpl, AnonymusRef, CartaBlanca);
    }
}
