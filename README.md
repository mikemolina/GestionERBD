# GestionERBD

<img src="https://raw.githubusercontent.com/mikemolina/GestionERBD/master/misc/GestionERBD-tabEmp.png" height=66%>

## Description
**GestionERBD** is a software project in charge of managing the information
on _relationship_ between an _Employee_ and their _Reference(s) letter(s)_.

**GestionERBD** is developed from the implementation of object-oriented
programming, via JAVA language, together with an [entity-relationship
model](https://en.wikipedia.org/wiki/Entity%E2%80%93relationship_model) for
managing the database, via MySQL. Interaction with the user (GUI) is
achieved through a simple form that allows executing the four basic
functions of database persistence _create, read, update and delete_
(functions
[CRUD](https://en.wikipedia.org/wiki/Create,_read,_update_and_delete) for
its acronym). Both form and database are developed in an MVC architecture
([Model-view-controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller)).

Este README se encuentra en español en el archivo
[README_es.md](https://github.com/mikemolina/GestionERBD/blob/master/README_es.md).

## Using GestionERBD

### Dependencies
To run the project you need to have installed:
* A java development kit, for example [OpenJDK](https://openjdk.java.net/).
* [XAMPP](https://www.apachefriends.org/download.html), the open source
  cross-platform web server consisting of the Apache web server and the
  MySQL database.
* [JDBC driver for MySQL.](https://dev.mysql.com/downloads/connector/j/)
* The [GNU Make](https://www.gnu.org/software/make/) utility, to run the
  project or an IDE like to [NetBeans](https://netbeans.apache.org/).
* Optional: [doxygen](https://www.doxygen.nl/index.html) to compile the
  development documentation.

### How to run
1. Clone the repository
   ```
   $ git clone https://github.com/mikemolina/GestionERBD.git
   ```
2. Create the database with the scripts from the _scriptsSQL_ directory
   using **phpMyAdmin** (included in XAMPP).
3. Add the JDBC driver to your OS or IDE. Modify the username and password
   in the file **./src/MDLGestionER/dataGER.java** to access the database.
4. In a terminal run:
   ```
   $ make run
   ```

## GestionERBD Screenshots
Screenshots of the form.

<img src="https://raw.githubusercontent.com/mikemolina/GestionERBD/master/misc/GestionERBD-tabRef.png" height=50%>
<img src="https://raw.githubusercontent.com/mikemolina/GestionERBD/master/misc/GestionERBD-tabVinc.png" height=50%>

## License
See COPYING for more information.
