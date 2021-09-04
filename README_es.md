# GestionERBD

<img src="https://raw.githubusercontent.com/mikemolina/GestionERBD/master/misc/GestionERBD-tabEmp.png" height=66%>

## Descripción
**GestionERBD** es un proyecto de software encargado de gestionar la
información de _Vinculación laboral_ entre un _Empleado_ y su(s)
_carta(s) de Referencia(s)_.

**GestionERBD** está desarrollado desde la implementación de la
programación orientada a objetos, vía lenguje JAVA, junto con un
[modelo
entidad-relación](https://es.wikipedia.org/wiki/Modelo_entidad-relaci%C3%B3n)
para el manejo de la base de datos, via MySQL. La interacción con el
usuario (GUI) es lograda a través de un simple formulario que permite
ejecutar las cuatro funciones básicas de la persistencia de bases de
datos _crear, leer, actualizar y eliminar_ (funciones
[CRUD](https://es.wikipedia.org/wiki/CRUD) por su acrónimo en
inglés). Tanto formulario como base datos están desarrollados en una
arquitectura MVC
([Modelo-vista-controlador](https://es.wikipedia.org/wiki/Modelo%E2%80%93vista%E2%80%93controlador)).

## Usando GestionERBD

### Dependencias
Para ejecutar el proyecto requiere tener instalado:
* Un kit de desarrollo java, por ejemplo [OpenJDK](https://openjdk.java.net/).
* [XAMPP](https://www.apachefriends.org/download.html), el servidor
  web multiplataforma de código abierto que consta del servidor web
  Apache y la base de datos MySQL.
* [Controlador JDBC para MySQL.](https://dev.mysql.com/downloads/connector/j/)
* La utilidad [GNU Make](https://www.gnu.org/software/make/), para
  correr el proyecto o en su defecto un IDE como
  [NetBeans](https://netbeans.apache.org/).
* Opcional: [doxygen](https://www.doxygen.nl/index.html) para compilar
  la documentación de desarrollo.

### Cómo correr
1. Clonar el repositorio
   ```
   $ git clone https://github.com/mikemolina/GestionERBD.git
   ```
2. Crear la base de datos con los scripts del directorio _scriptsSQL_
   usando **phpMyAdmin** (incluido en XAMPP).
3. Adicionar el controlador JDBC a su OS o IDE.
4. En una terminal ejecutar:
   ```
   $ make run
   ```

## GestionERBD Screenshots
Capturas de pantalla del formulario.
<img src="https://raw.githubusercontent.com/mikemolina/GestionERBD/master/misc/GestionERBD-tabRef.png" height=66%>
<img src="https://raw.githubusercontent.com/mikemolina/GestionERBD/master/misc/GestionERBD-tabVinc.png" height=66%>

## Licencia
Ver COPYING para más información.
