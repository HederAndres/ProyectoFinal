## Instrucciones para ejecutar la aplicación
1. Ingresar al enlace adjunto que lleva al Github donde se encuentra el código, esté se debe descargar.
2. Ingresar a IntelliJ, dar click en "Abrir" y buscar el repositorio.
3. buscar el archivo "FinalApplication" dentro de src/main/java/com/programacion/proyectofinal
4. Dar click derecho en el archivo y seleccionar ejecutar "FinalApplication.Main()"

## Evidencias

* Endpoint para ver mensaje en español:
```
curl --location 'http://localhost:8080/api/saludo' \
--header 'Accept-Language: es'
```
![img_2.png](src/main/resources/Imagenes/img_2.png)

* Endpoint para ver mensaje en inglés:
```
curl --location 'http://localhost:8080/api/saludo' \
--header 'Accept-Language: en' 
```
![img_1.png](src/main/resources/Imagenes/img_1.png)

* Endpoint para ver mensaje en francés:
```
curl --location 'http://localhost:8080/api/saludo' \
--header 'Accept-Language: fr' 
```
![img_3.png](src/main/resources/Imagenes/img_3.png)

* Endpoint para ver todos los productos disponibles en inglés:
```
curl --location 'http://localhost:8080/api/productos?lang=en'
```
![img_4.png](src/main/resources/Imagenes/img_4.png)

* Endpoint para ver todos los productos disponibles en francés:
```
curl --location 'http://localhost:8080/api/productos?lang=fr'
```
![img_5.png](src/main/resources/Imagenes/img_5.png)

* Endpoint para ver todos los productos disponibles en español:
```
curl --location 'http://localhost:8080/api/productos?lang=es'
```
![img_6.png](src/main/resources/Imagenes/img_6.png)

* Endpoint para ver un producto en español:
```
curl --location 'http://localhost:8080/api/productos/1?lang=es'
```
![img_7.png](src/main/resources/Imagenes/img_7.png)

También es posible verlo en inglés y francés de la siguiente forma:
```
curl --location 'http://localhost:8080/api/productos/1?lang=en'
```
```
curl --location 'http://localhost:8080/api/productos/1?lang=fr'
```

*Endpoint para crear un producto:
```
curl --location 'localhost:8080/api/productos' \
--header 'Content-Type: application/json' \
--data '{
"id": "4",
"nombre": "Audifonos Gaming",
"precio": 560.99
}'
```
![img_1.png](src/main/resources/Imagenes/img_10.png)

* Endpoint para modificar un producto:
```
curl --location --request PUT 'localhost:8080/api/productos/4' \
--header 'Content-Type: application/json' \
--data '{
"nombre": "Headphones Gaming",
"precio": 780.99
}'
```
![img.png](src/main/resources/Imagenes/img_2.png)

* Endpoint para eliminar un producto:
```
curl --location --request DELETE 'localhost:8080/api/productos/3' \
--data ''
```
![img_3.png](src/main/resources/Imagenes/img_12.png)

## Prueba Unitaria

![img_8.png](src/main/resources/Imagenes/img_8.png)