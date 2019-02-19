# Categorías y eventos en StubHub

En StubHub sus eventos están categorizados, de forma que un evento pertenece a una **única** categoría. De la misma forma, las categorías pueden pertenecer a **una o ninguna** categoría.

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
* **No uses frameworks ni librerías externas (no uses Grails, Rails, Django ni similares. No uses ninguna bbdd). Queremos ver tu código, no el de los frameworks xD.**
* Valoramos mucho las buenas prácticas de diseño, por lo que ten en cuenta factores como la modularización, extensibilidad, mantenimiento, etc.
* Comenta tu código cuando lo consideres necesario.
* Implementa las estructuras de datos y algoritmos más eficientes que se te ocurran.
* Haznos saber cómo debemos ejecutar tu código.
* Extra ball: testing es más que bienvenido si te queda tiempo.