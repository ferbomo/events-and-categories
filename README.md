# Categorías y eventos

En la empresa, sus eventos están categorizados, de forma que un evento pertenece a una **única** categoría. De la misma forma, las categorías pueden pertenecer a **una o ninguna** categoría.

Por ejemplo, el evento `Real Madrid - Barcelona` pertenece a la categoría `Liga BBVA`, que a su vez pertenece a la categoría `fútbol`, y esta por último a la categoría `deportes`.

## Se pide:

* Crear las estructuras de datos necesarias para soportar este modelo de datos. Supón que los eventos y categorías tienen **id** y **nombre**.
* Implementar los siguientes métodos:
    * Categoría:
        * Crear / modificar / eliminar (el borrado sólo se permitirá en categorías que no tengan categorías hijas ni eventos asociados)
    * Evento:
        * Crear
        * **getBreadcrumb**. Ejemplo: `Deportes -> Fútbol -> Liga BBVA`. Ten en cuenta a la hora de crear tus estructuras de datos, que el breadcrumb del evento es crítico debido al alto índice de acceso que tiene (lo mostramos millones de veces a lo largo del día ;))
    * Imprimir el directorio de categorías y sus eventos. Ejemplo:

            [1] Deportes
                    [2] Fútbol
                        [4] Liga BBVA
                            [ev. 1] Real Madrid - Barcelona
                    [3] Baloncesto
                        [5] Euroliga
                            [ev. 2] Final Four

    * Dado un listado de IDs de evento, listar el nombre de los eventos y su correspondiente breadcrumb. Ejemplo:

            [1] Real Madrid - Barcelona: Deportes -> Fútbol -> Liga BBVA
            [2] Final Four: Deportes -> Baloncesto -> Euroliga
    
    * Una carga inicial de categorías y eventos para realizar las pruebas (con unos pocos nos vale). Usa las estructuras de datos que consideres oportunas para almacenar estos listados. Ten en cuenta que el origen de datos de un sistema como este en un entorno real sería una base de datos, por lo que el acceso a cada item de la lista podría traducirse en una consulta a la bbdd. *¡OJO! NO pedimos la implementación de una base de datos ni el acceso a una. Simplemente queremos reflejar el coste que supone acceder a la información de una categoría en un entorno real*

* El método principal (ejecutable) debe hacer lo siguiente:
    1. carga inicial de categorías y eventos
    2. imprimir el directorio de categorías y sus eventos
    3. pedir por input un listado de ids de eventos separados por comas.
    4. listar los breadcrumbs de esos eventos
    5. pedir por input el id de una categoría para eliminarla.
    6. Eliminar la categoría indicada.

## Observaciones:

* Usa el lenguaje en el que te sientas más cómodo.
* **No uses frameworks ni librerías externas (no uses Grails, Rails, Django ni similares. No uses ninguna bbdd). Queremos ver tu código, no el de los frameworks.**
* Valoramos mucho las buenas prácticas del código y de diseño, por lo que ten en cuenta factores como seguir las convenciones del lenguaje, conocimiento del lenguaje, la legibilidad del código, modularización, extensibilidad, facilidad de mantenimiento, gestión de errores, etc.
* Comenta tu código cuando lo consideres necesario.
* Implementa las estructuras de datos y algoritmos más eficientes que se te ocurran.
* Haznos saber cómo debemos compilar y ejecutar el código del programa (y los teses unitarios si los hubiera). Si quieres, utiliza alguna herramienta que facilite la ejecución con independencia del entorno de desarrollo.
* Extra ball: testing es más que bienvenido si te queda tiempo.

## Estrategia:

Para realizar esta prueba he usado un MacBook Pro con MacOS 12.0.1 Monterey. 
Como lenguaje he usado Java 11, junto con Maven 3.8.2 como gestor de dependencias.

Para los tests he tenido que añadir JUnit, ya que no se me ocurria como integrar tests automáticos en fase de 
construcción, o para ver la cobertura de los mismos. Me ha sido más útil dada la naturaleza de la prueba.

Como he creido que la estructura de datos era muy parecida a un árbol general, he implementado las clases usando el 
patrón composite, usando la interfaz Sport que cumplen todos, la clase Event como Hoja del árbol y la clase Category 
como nodo intermedio. Me ha sido útil a la hora de pintar el directorio completo y los breadcrumbs de los eventos.

Para el almacenamiento he creado un servicio, SportsStorage, que almacena y recupera los objetos Sport de un Mapa. 
He elegido un mapa porque la recuperación es muy rápida.

## Ejecución
Para ejecutar hay que tener instalado Docker. https://docs.docker.com/desktop/mac/install/
Una vez instalado, en la raíz del proyecto, ejecutar: ./runDocker.sh

## Prueba
Lo primero sería seleccionar la opcion 1, por lo que escribiriamos 1 y pulsamos Enter.
Ésto cargará datos en el mapa que usamos como almacenamiento.

Escribiendo 2 y pulsando Enter nos mostrará el directorio completo.
```
[1] Deportes
├── [2] Fútbol
│   └── [3] Liga BBVA
│       ├── [4] Real Madrid - Barcelona
│       └── [5] Real Madrid - Villarreal
└── [6] Baloncesto
    └── [7] Euroliga
        └── [8] Final Four

[9] DeleteMe

```

Para la opción 3, escribimos 3 y Enter. Nos pedirá los Eventos separados por coma. Y nos devolvera los respectivos 
ids separados por comas.
```
Please introduce the events separated by comma:
Real Madrid - Barcelona,Real Madrid - Villarreal,Final Four
4,5,8

```

Para la opción 4, escribimos 4 y Enter. Nos pedirá que escribamos los ids separados por coma.
Y nos devolvera lo siguiente:
```
Please introduce the events ids separated by comma:
4,5,8
[4] Real Madrid - Barcelona: Deportes -> Fútbol -> Liga BBVA 
[5] Real Madrid - Villareal: Deportes -> Fútbol -> Liga BBVA 
[8] Final Four: Deportes -> Baloncesto -> Euroliga 

```
Para la opción 5, escribimos 5 y Enter. Nos pedirá el nombre de la categoría que queremos recuperar su id. He 
preparado una categoría sin hijos, en la carga inicial para probar el borrado.
```
Please introduce the category name:
DeleteMe
9
```
Para la opción 6, escribimos 6 y Enter. Nos pedirá el id de la categoría que queremos borrar.
```
Please introduce the category id:
9
Category deleted.

Si volvemos a ejecutar la opción 2, vemos que ha borrado la categoria 9

[1] Deportes
├── [2] Fútbol
│   └── [3] Liga BBVA
│       ├── [4] Real Madrid - Barcelona
│       └── [5] Real Madrid - Villareal
└── [6] Baloncesto
    └── [7] Euroliga
        └── [8] Final Four

```
Si intentamos borrar una categoría con hijos no nos dejará:
```
Please introduce the category id:
7
You can't remove a category with child categories or associated events.
```

Con la opción 7 mas Enter salimos de la aplicación.
