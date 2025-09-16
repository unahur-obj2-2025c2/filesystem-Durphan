# FileSystem o Sistema de Archivos

En un sistema de archivos digital, los elementos pueden ser archivos o carpetas.
Las carpetas pueden contener a su vez otros archivos o carpetas, formando una estructura jerárquica de tipo árbol.

Queremos modelar este sistema de forma tal que tanto archivos como carpetas implementen una interfaz común que permita tratarlos de manera uniforme.

## Archivos

De los archivos conocemos:

- nombre
- extension (por ejemplo .txt, .jpg, .mp4)
- tamaño, expresado en cantidad de bytes

## Carpetas

De las carpetas conocemos:

- nombre
- una lista de elementos del sistema de archivos, que pueden ser archivos o carpetas

## Requerimientos

La implementación que desarrollemos debe cumplir con los siguientes requisitos:

- **Obtener el tamaño total del sistema de archivos completo**, considerando archivos individuales y el contenido total de todas las carpetas (anidadas o no).
- **Obtener el archivo más pesado del sistema de archivos**.
- **Listar todo el sistema de archivos**, mostrando identaciones sobre las carpetas anidadas con su correspondiente tamaño`. Similar a lo siguiente

```bash
└ root ( 2590-Bytes )
   ├ Notas.txt
   ├ Dibujo-A.jpg
   └ Carpeta A Nivel 1 ( 540-Bytes )
      ├ Imagen-A.jpg
      ├ Rock.mp3
   └ Carpeta B Nivel 1 ( 1750-Bytes )
      ├ Video-Parte-II.mp4
      └ Carpeta A Nivel 2 ( 420-Bytes )
         ├ Notas Rapidas.txt
         ├ Rap.mp3
      ├ Video-Parte-I.mp4
      ├ Posticks.txt
```

## Sugerencias para la implementación

- Definir una interface común que permita operar de la misma forma sobre archivos y carpetas en los requerimientos anteriores.

```java
package ar.edu.unahur.obj2;

public interface IElemento {
    String nombre();

    Integer tamanio();

    void mostrar(Integer identacion);

    IElemento archivoMasPesado();

}
```

- Usar el patrón Composite para que carpetas puedan contener tanto archivos como otras carpetas.

![composite](./assets/composite.png)

- Incluir un objeto FileSystem como punto de entrada que represente el sistema completo y que delegue los requerimientos en la estructura interna.

```java
package ar.edu.unahur.obj2;

public class FileSystem {

}
```

- Utilizar con énfasis los principos de POO sobre todo el polimorfismo.
