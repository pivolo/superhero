# SuperHeros app services
API que permita hacer un mantenimiento de súper
héroes utilizando Spring Boot 2 y Java 11

Este mantenimiento permite:
- Consultar todos los súper héroes.
- Consultar un único súper héroe por id.
- Consultar todos los súper héroes que contienen, en su nombre, el valor de un parámetro
enviado en la petición. Por ejemplo, si enviamos “man” devolverá “Spiderman”, “Superman”,
“Manolito el fuerte”, etc.
- Modificar un súper héroe.
- Eliminar un súper héroe.
- Test unitarios de algún servicio.

Puntos a tener en cuenta:
- Los súper héroes se guardan en una base de datos H2 en memoria.
- URL Repositorio GIT: https://github.com/pivolo/superhero

Puntos opcionales de mejora tenidos en cuenta:
- Implementar una anotación personalizada que sirva para medir cuánto tarda en ejecutarse.
una petición. Se podría anotar alguno o todos los métodos de la API con esa anotación.
Funcionaría de forma similar al @Timed de Spring, pero imprimiendo la duración en un log.
- Gestión centralizada de excepciones.
- Presentar la aplicación dockerizada.
- Documentación de la API.
- Poder cachear peticiones.
- Test de integración (del repositorio y uno del controlador)
- Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos.

Puntos opcionales de mejora pendientes:
- Seguridad del API.



## Arranque aplicacion
./mvnw spring-boot:run

## Documentación APIS
Para documentar las APIS se ha usado Openapi (swagger)

Accesible desde
> http://localhost:8080/swagger-ui.html


## Instrucciones docker (ubuntu)
Para crear una imagen

> sudo docker build -t superheros-image .

Para crear un contenedor con la aplicacion:
> ./mvnw clean package
>
> sudo docker run -p8080:8080 --name superheros-container superheros-image:latest

Para borrar el contendor:
>sudo docker stop superheros-container

Para borrar el contendor:
>sudo docker rm superheros-container

