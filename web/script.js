const RELLENO = "*";
let longitudOriginal = 0;

function obtenerOrdenClave(clave) {
    let orden = [];

    for (let i = 0; i < clave.length; i++) {
        orden[i] = 1;
        for (let j = 0; j < clave.length; j++) {
            if (clave[j] < clave[i]) {
                orden[i]++;
            }
        }
    }
    return orden;
}


function cifrar() {
    limpiarMensaje();

    let texto = document.getElementById("texto").value;
    let clave = document.getElementById("clave").value;
    texto = texto.toUpperCase();
    clave = clave.toUpperCase();

    if (clave.length === 0) {
        mostrarMensaje("La clave no puede estar vacía.");
        return;
    }

    longitudOriginal = texto.length;
    const columnas = clave.length;
    const filas = Math.ceil(texto.length / columnas);
    const orden = obtenerOrdenClave(clave);
    let matriz = [];
    let posicion = 0;

    for (let i = 0; i < filas; i++) {
        matriz[i] = [];
        for (let j = 0; j < columnas; j++) {
            if (posicion < texto.length) {
                matriz[i][j] = texto[posicion];
            } else {
                matriz[i][j] = RELLENO;
            }
            posicion++;
        }
    }

    let resultado = "";

    for (let numero = 1; numero <= columnas; numero++) {
        let columna = -1;
        for (let i = 0; i < columnas; i++) {
            if (orden[i] == numero) {
                columna = i;
                break;
            }
        }
        
        for (let fila = 0; fila < filas; fila++) {
            resultado += matriz[fila][columna];
        }
    }

    document.getElementById("resultado").value = resultado;
    document.getElementById("longitud").textContent = "Longitud original: " + longitudOriginal;
}

function descifrar() {
    limpiarMensaje();

    let textoCifrado = document.getElementById("texto").value;
    let clave = document.getElementById("clave").value;
    let longitud = document.getElementById("longitudEntrada").value;
    textoCifrado = textoCifrado.toUpperCase();
    clave = clave.toUpperCase();

    if (clave.length === 0) {
        mostrarMensaje("La clave no puede estar vacía.");
        return;
    }

    if (textoCifrado.length === 0) {
        mostrarMensaje("Debe ingresar un texto cifrado.");
        return;
    }

    if (longitud === "") {
        mostrarMensaje("Debe ingresar la longitud original.");
        return;
    }

    longitudOriginal = Number(longitud);
    if (longitudOriginal <= 0) {
        mostrarMensaje("La longitud original debe ser mayor que cero.");
        return;
    }

    if (longitudOriginal > textoCifrado.length) {
        mostrarMensaje("La longitud original no puede ser mayor que la longitud del texto cifrado.");
        return;
    }

    const columnas = clave.length;
    const filas = textoCifrado.length / columnas;

    if (!Number.isInteger(filas)) {
        mostrarMensaje("La longitud del texto cifrado no es válida para esta clave.");
        return;
    }

    const orden = obtenerOrdenClave(clave);
    let matriz = [];
    for (let i = 0; i < filas; i++) {
        matriz[i] = [];
        for (let j = 0; j < columnas; j++) {
            matriz[i][j] = "";
        }
    }

    let posicion = 0;
    for (let numero = 1; numero <= columnas; numero++) {
        let columna = -1;
        for (let i = 0; i < columnas; i++) {
            if (orden[i] == numero) {
                columna = i;
                break;
            }
        }
        for (let fila = 0; fila < filas; fila++) {
            matriz[fila][columna] = textoCifrado[posicion];
            posicion++;
        }
    }

    let resultado = "";

    for (let fila = 0; fila < filas; fila++) {
        for (let columna = 0; columna < columnas; columna++) {
            resultado += matriz[fila][columna];
        }
    }

    resultado = resultado.substring(0, longitudOriginal);
    document.getElementById("resultado").value = resultado;
}

function mostrarMensaje(mensaje) {
    document.getElementById("mensaje").textContent = mensaje;
}

function limpiarMensaje() {
    document.getElementById("mensaje").textContent = "";
}