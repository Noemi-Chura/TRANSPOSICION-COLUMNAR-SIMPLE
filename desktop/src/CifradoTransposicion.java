import java.util.Arrays;
import java.util.Locale;

/**
 * Implementación del algoritmo de Transposición Columnar Simple.
 *
 * Por defecto usa '*' como relleno para una salida limpia y consistente.
 * También admite un relleno alternativo.
 */
public class CifradoTransposicion {

    public static final char RELLENO_POR_DEFECTO = ' ';

    public static String cifrar(String textoOriginal, String clave) {
        return cifrar(textoOriginal, clave, RELLENO_POR_DEFECTO);
    }

    public static String cifrar(String textoOriginal, String clave, char relleno) {
        String texto = normalizarTexto(textoOriginal);
        String claveNormalizada = normalizarClave(clave);
        validar(texto, claveNormalizada);

        int columnas = claveNormalizada.length();
        int filas = (int) Math.ceil((double) texto.length() / columnas);
        int totalCeldas = filas * columnas;

        char[][] matriz = new char[filas][columnas];
        int indice = 0;
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (indice < texto.length()) {
                    matriz[fila][columna] = texto.charAt(indice++);
                } else {
                    matriz[fila][columna] = relleno;
                }
            }
        }

        int[] orden = calcularOrdenColumnas(claveNormalizada);
        StringBuilder resultado = new StringBuilder(totalCeldas);
        for (int columna : orden) {
            for (int fila = 0; fila < filas; fila++) {
                resultado.append(matriz[fila][columna]);
            }
        }

        return resultado.toString();
    }

    public static String descifrar(String textoCifrado, String clave) {
        return descifrar(textoCifrado, clave, RELLENO_POR_DEFECTO);
    }

    public static String descifrar(String textoCifrado, String clave, char relleno) {
        String texto = normalizarTexto(textoCifrado, relleno);
        String claveNormalizada = normalizarClave(clave);
        validar(texto, claveNormalizada);

        int columnas = claveNormalizada.length();
        int filas = (int) Math.ceil((double) texto.length() / columnas);
        int columnasLargas = texto.length() % columnas;
        if (columnasLargas == 0) {
            columnasLargas = columnas;
        }
        int[] orden = calcularOrdenColumnas(claveNormalizada);

        char[][] matriz = new char[filas][columnas];
        int indice = 0;
        for (int columna : orden) {
            int cantidadCaracteres = columna < columnasLargas ? filas : filas - 1;
            for (int fila = 0; fila < cantidadCaracteres; fila++) {
                matriz[fila][columna] = texto.charAt(indice++);
            }
        }

        StringBuilder resultado = new StringBuilder();
        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                if (matriz[fila][columna] != '\0') {
                    resultado.append(matriz[fila][columna]);
                }
            }
        }

        return eliminarRelleno(resultado.toString(), relleno);
    }

    private static int[] calcularOrdenColumnas(String clave) {
        int n = clave.length();
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        char[] letras = clave.toCharArray();
        Arrays.sort(indices, (a, b) -> {
            int comparacion = Character.compare(letras[a], letras[b]);
            return comparacion != 0 ? comparacion : Integer.compare(a, b);
        });

        int[] orden = new int[n];
        for (int i = 0; i < n; i++) {
            orden[i] = indices[i];
        }
        return orden;
    }

    private static String completarConRelleno(String texto, int columnas, char relleno) {
        int resto = texto.length() % columnas;
        if (resto == 0) {
            return texto;
        }

        int faltan = columnas - resto;
        StringBuilder sb = new StringBuilder(texto);
        for (int i = 0; i < faltan; i++) {
            sb.append(relleno);
        }
        return sb.toString();
    }

    private static String eliminarRelleno(String texto, char relleno) {
        int largo = texto.length();
        while (largo > 0 && texto.charAt(largo - 1) == relleno) {
            largo--;
        }
        return texto.substring(0, largo);
    }

    private static String normalizarTexto(String texto) {
        if (texto == null) {
            return "";
        }
        String limpio = texto.replace("\r", "").replace("\n", "").toUpperCase(Locale.ROOT);
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < limpio.length(); i++) {
            char c = limpio.charAt(i);
            if (Character.isLetter(c)) {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    private static String normalizarTexto(String texto, char relleno) {
        if (texto == null) {
            return "";
        }
        String limpio = texto.replace("\r", "").replace("\n", "").toUpperCase(Locale.ROOT);
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < limpio.length(); i++) {
            char c = limpio.charAt(i);
            if (Character.isLetter(c) || c == relleno) {
                resultado.append(c);
            }
        }
        return resultado.toString();
    }

    private static String normalizarClave(String clave) {
        if (clave == null) {
            return "";
        }
        StringBuilder salida = new StringBuilder();
        for (int i = 0; i < clave.length(); i++) {
            char c = clave.charAt(i);
            if (Character.isLetter(c)) {
                salida.append(Character.toUpperCase(c));
            }
        }
        return salida.toString();
    }

    private static void validar(String texto, String clave) {
        if (texto == null || texto.isEmpty()) {
            throw new IllegalArgumentException("El texto no puede estar vacio.");
        }
        if (clave == null || clave.isEmpty()) {
            throw new IllegalArgumentException("La clave no puede estar vacia.");
        }
        if (!clave.chars().allMatch(Character::isLetter)) {
            throw new IllegalArgumentException("La clave solo debe contener letras.");
        }
    }
}
