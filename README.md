# CORAL

## Introducción
Aplicación web para la gestión y realización de pedidos desarrollada en Java JDK 17
utilizando el framework Spring, Maven para la construcción y compilación del proyecto
y una conexión MySql a base de datos.

## Requisitos
Antes de poder desarrollar y/o desplegar la aplicación en local es necesario tener instalado:

- Java JDK 17 - https://www.oracle.com/java/technologies/downloads/
- Maven 3 - https://maven.apache.org/download.cgi
- MySql 8 - https://dev.mysql.com/downloads/mysql/

## Importar proyecto
Clonar en Intellij IDEA Community el proyecto desde el repositorio de GitHub - https://github.com/CarlosGGDev/TFG_GestionPedidos.git <br />
Será necesario también importar la base de datos desde el archivo .sql

## Compilación
Una vez importado el proyecto recargar los proyectos Maven haciendo click en 'Reload' 
en la pestaña de Maven situada en la parte superior derecha.

Verificar que está configurado para JDK 17:
- Settings/Build, Execution, Deployment/Compiler/Java Compiler
- File/Project Structure/Modules/GestionPedidos

Compilar la aplicación con el siguiente comando de Maven desde la raíz del proyecto.
También se puede ejecutar desde la propia consola de Maven, situada en la pestaña superior derecha.
```
mvn clean install
```

## Configuración de ejecución
Antes de iniciar la aplicación es necesario crear una configuración de arranque en la pestaña Run/Edit configurations.
En el cuadro de opciones configurar el JDK utilizado y seleccionar la clase principal de la aplicación:
- Seleccionar JDK 17
- En Main class: dev.gestionpedidos.GestionPedidosApplication

Además es necesario modificar en el archivo application.yml las credenciales de acceso a la base de datos.

## Ejecutar la aplicación
Al arrancar la aplicación Spring añade un servidor Tomcat en la compilación escuchando en el puerto :8080.
Introduciendo la ruta http://localhost:8080 en el navegador se nos abrirá la aplicación desplegada.

En la base de datos hay guardados dos usuarios para hacer las pruebas necesarias y cuatro pedidos con sus respectivos detalles:
- Admin:
    - Usuario: admin@gmail.com
    - Contraseña: 123
- User:
    - Usuario: user@gmail.com
    - Contraseña: 123