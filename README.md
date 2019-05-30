# ResuelveFC
Prueba de RTD ingeniería

El código fuente se encuentra en source\src\main

La carpeta source se puede abrir como un proyecto de eclipse.

## Funcionalidad
Calcular el sueldo completo de un jugador de ResuelveFC a partir de un archivo json con las siguientes propiedades:

- **Nombre:** Nombre del jugador

- **Nivel:** Nivel de bono del jugador que calcula los goles minimos por defecto.

- **Goles_minimos:** Sustituye a Nivel. Si se especifica Nivel y Goles_minimos, el segundo tendrá prioridad.

- **Goles:** Goles marcados por el jugador

- **Sueldo:** Sueldo base del jugador

- **Bono:** Propio del jugador

- **Sueldo_completo:** Debe ser null

- **Equipo:** Nombre del equipo al que pertenece el jugador

## Ficheros fuente

Se encuentran en “source\src\main”. La carpeta source se puede importar como proyecto de Eclipse.

## Instrucciones de uso

### 1. Descargar ResuelveFC.jar

### 2. Windows + R > CMD > Enter

Se abrirá una terminal de Windows donde tendremos que ubicarnos con el comando cd en la carpeta de instalación de ResuelveFC.jar

### 3. java -jar ResuelveFC.jar [fichero.json]

En la propia carpeta hay un json que el programa coge por defecto. Pero también se le puede especificar un fichero.
