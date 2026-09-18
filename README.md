# Cifrado de Transposición Columnar Simple

Proyecto académico para la implementación del algoritmo de **Cifrado de Transposición Columnar Simple** en tres plataformas: escritorio, web y móvil.

## Descripción

La Transposición Columnar Simple es un método de cifrado que reorganiza los caracteres de un texto de acuerdo con una clave previamente establecida.

En este proyecto se desarrollaron tres aplicaciones que implementan el mismo algoritmo:

* Aplicación de escritorio
* Aplicación web
* Aplicación móvil

El objetivo es comprobar el funcionamiento del cifrado y descifrado del algoritmo en diferentes plataformas, manteniendo la misma lógica general de procesamiento.

## Estructura del proyecto

```text
cifrado-transposicion-columnar/
│
├── desktop/
│   ├── src/
│   │   ├── AplicativoEscritorio.java
│   │   └── CifradoTransposicion.java
│   ├── app/
│   │   └── TransposicionColumnar/
│   └── README.md
│
├── web/
│   ├── index.html
│   ├── script.js
│   ├── style.css
│   └── README.md
│
├── mobile/
│   ├── apk/
│   │   └── TransposicionColumnar.apk
│   └── README.md
│
├── docs/
│   ├── informe/
│   ├── capturas/
│   └── resultados/
│
├── .gitignore
└── README.md
```

## Aplicación de escritorio

La versión de escritorio fue desarrollada en **Java** y cuenta con una interfaz gráfica para ingresar el texto y la clave, realizar el cifrado y posteriormente realizar el descifrado.

### Código fuente

El código fuente se encuentra en:

```text
desktop/src/
```

Archivos principales:

```text
AplicativoEscritorio.java
CifradoTransposicion.java
```

### Aplicación ejecutable

La aplicación empaquetada para Windows se encuentra en:

```text
desktop/app/TransposicionColumnar/
```

El archivo principal para ejecutar la aplicación es:

```text
TransposicionColumnar.exe
```

## Aplicación web

La versión web fue desarrollada utilizando:

* HTML
* CSS
* JavaScript

Los archivos principales se encuentran en:

```text
web/
```

Archivos:

```text
index.html
script.js
style.css
```

La aplicación puede ejecutarse desde el archivo `index.html` en un navegador web.

## Aplicación móvil

La versión móvil corresponde a una aplicación para Android.

La aplicación compilada se encuentra en:

```text
mobile/apk/
```

Archivo:

```text
TransposicionColumnar.apk
```

El APK permite instalar y probar la implementación móvil del algoritmo.

## Ejemplo de prueba

Como prueba común para las tres aplicaciones se utiliza:

**Texto:**

```text
transposicioncolumnar
```

**Clave:**

```text
CLAVE
```

El proceso de cifrado genera el texto cifrado de acuerdo con la lógica de la Transposición Columnar Simple.

Posteriormente, al utilizar la opción de descifrado con la misma clave, se debe recuperar nuevamente el texto original:

```text
transposicioncolumnar
```

## Cifrado y descifrado

Las aplicaciones incluyen las operaciones:

### Cifrar

Recibe un texto y una clave, y genera el texto cifrado mediante la reorganización de los caracteres en columnas.

### Descifrar

Recibe el texto cifrado y la misma clave, reconstruye la distribución de caracteres y permite recuperar el texto original.

## Documentación

La documentación y las evidencias del desarrollo se encuentran en:

```text
docs/
```

Esta carpeta puede contener:

* Informe del proyecto
* Capturas de las aplicaciones
* Resultados de las pruebas
* Evidencias de funcionamiento

## Integrantes

* Katherin Neysha Quispe Turpo
* Integrante 2
* Integrante 3
* Integrante 4
* Integrante 5
* Integrante 6

## Curso

**Seguridad Informática**

**Tema:** Cifrado de Transposición Columnar Simple
