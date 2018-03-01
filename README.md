# Primer Proyecto en JAVA - Aplicación que gestiona las reparaciones de ordenadores, clientes y facturas.
Se describe la creación de una aplicación o programa que puede ser implementada en un pequeño comercio que se dedique a la reparación de productos informáticos.

[![img.jpg](https://s13.postimg.org/57yi2ciw7/img.jpg)](https://postimg.org/image/e2zccv7oj/)

Un elemento que se quiso tener en cuenta durante el desarrollo, es la intención de que la interfaz tuviera un carácter amigable y visual.

##Enunciado
Se interesa llevar un control de la facturación, de las facturas interesa saber:
Los datos del cliente (código cliente), importe (subtotal), fecha de emisión, código de reparación.
Un empleado puede ser técnico o comercial, pero no ambos a la vez.
De los clientes interesa saber: Código cliente, nombre, apellidos, teléfono, código postal.
Se necesita llevar un control de las reparaciones que se lleva en el taller y que técnico las lleva a cabo. De las reparaciones interesa saber:
Código de reparación, fecha de entrada, fecha de entrega, código del cliente, el problema que describe dicho técnico y la solución.

##Perfiles de usuario
Para la seguridad de los datos, los usuarios se identificarán mediante usuario y contraseña en la aplicación.
Al iniciar la aplicación aparecerá el menú login. Al crear el usuario se seleccionará un rol específico de administración: comercial o técnico.
La contraseña deberá empezar por una o varias mayúsculas, dos minúsculas, 0 o más caracteres alfanuméricos, 2 o más dígitos y terminar con el símbolo del dólar. Además por lo menos debe tener 8 caracteres.
Habrá acceso restringido a determinadas características.

##Funciones de la aplicación
La aplicación permitirá una serie de opciones a los empleados y la realización de informes dentro de la aplicación. Estas opciones e informes estarán enfocados en su primera versión a:

**Técnico**

El técnico puede añadir, modificar, dar de baja reparaciones. También puede añadir Clientes.
El técnico tendrá la opción de cargar o guardar desde un .txt las reparaciones.

**Comercial**

El comercial puede añadir, modificar, dar de baja Clientes. También puede añadir facturas.
Además, podrá buscar clientes por dni, realizar búsquedas de facturas por código postal del cliente, por fechas, localizar las reparaciones que no estén facturadas y exportar los datos en xml.

##Especificaciones a tener en cuenta

**Facturas**

Al insertar una nueva factura, el código de reparación (identificador) dará error sino existe la reparación en la tabla reparaciones.
Al insertar una factura, el código de reparación (identificador) dará error si éste ya ha sido facturado (true) en la tabla reparaciones.
Al insertar una factura, el código cliente (identificador) dará error sino existe el cliente en la tabla clientes.
Al realizarse una factura, automáticamente el código de reparación correspondiente se pone en facturado(true).

**Clientes**

Al insertar un cliente con un dni duplicado en la tabla clientes dará error.
Al insertar un cliente con un dni no válido dará error.

**Comercial**

El comercial puede exportar en un fichero .xml las reparaciones no facturadas facturarlas.

**Técnico**

El técnico puede importar y exportar datos en un fichero .txt de la misma estructura.
NOTA: Al importar las reparaciones sólo se importarán aquellas de los clientes que existan.

**Opción modificar**

Al pulsar sobre una fila de la tabla, los datos de la tabla clientes o reparaciones aparecerán en los campos del formulario para ser modificados, salvo aquellos que no deban serlo. 


:)

