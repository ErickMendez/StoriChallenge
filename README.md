# StoriChallenge
## Tabla de contenido
1. [Información general](#información-general)
2. [Tecnologías](#tecnologías)
3. [Instalación](#instalación)
4. [Pruebas con AWS](#pruebas-con-aws)

## Información general

Desarrollo desafío Stori, para este desafío se debe crear un sistema que procese un archivo desde un directorio montado. 

El archivo contendrá una lista de transacciones de débito y crédito en una cuenta.

La función debe procesar el archivo y enviar información resumida a un usuario en forma de correo electrónico.

### El proyecto realiza las siguientes funciones:
* Lee un archivo .csv de manera local o de manera remota montado en AWS S3.
* Procesa y envía la información  a un correo proporcionado con un template html.

### El proyecto provee 4 endpoints

* POST

*enviá un archivo al repositorio S3 de AWS*
```
/s3/upload-file
```
* GET

*solicita el estado de cuenta, parámetros de entrada (email a donde se enviara) (nombre del archivo a procesar)*
```
/transaction/statement-of-account/{to}/{fileName}
```
*solicita una lista con los nombres de los archivos que existen en  el repositorio S3 de AWS*
```
/s3/name-files
```
*descarga un archivo que exista en el repositorio S3 de AWS, parámetros de entrada (nombre del archivo con su extencion)*
```
/s3/download-file/{fileName}
```

## Tecnologías
* SpringBoot 2.6.3
* Java 11
* Gradle 7.3
* AWS (IAM)(S3)(Elastic Beanstalk)

## Instalación
* Clonar el repositorio y abrir el proyecto con el IDE de tu preferencia
```
$ git clone https://github.com/ErickMendez/StoriChallenge.git
```
- Modificar el archivo ***application.properties*** y agregar las propiedades necesarias
	* ***si unicamente se ejecutara de manera local omitir las propiedades con “aws.”***
	* ***ningún endpoint con el prefijo /s3/ podrá ser utilizado*** 
* Abrir y ejecutar el archivo ***build.gradle*** para descargar las dependencias necesarias
* Ejecutar el archivo ***StoriChallenge11Application***

## Pruebas con AWS
