# SuperHeros app services
Para ejecutar la aplicación:
./mvnw spring-boot:run

## Documentation
Se ha usado Openapi (swagger)

Accesible desde
> http://localhost:8080/swagger-ui.html


## Instrucciones docker
Para crear una imagen

> sudo docker build -t superheros-image .

Para crear un contenedor con la aplicacion:
> ./mvnw clean package
>
> sudo docker run -p8080:8080 --name superheros-container superheros-image:latest

sudo docker stop superheros-container
sudo docker rm superheros-container

