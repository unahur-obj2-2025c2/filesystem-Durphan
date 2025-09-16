package ar.edu.unahur.obj2.archivos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ArchivoTest {

    @Test
    public void testConstructorAndGetters() {
        Archivo archivo = new Archivo("documento", (byte) 10, "txt");
        assertEquals("documento", archivo.getNombre());
        assertEquals((byte) 10, archivo.getPeso());
        assertEquals("txt", archivo.getExtension());
    }

    @Test
    public void testToString() {
        Archivo archivo = new Archivo("foto", (byte) 5, "jpg");
        String esperado = "Archivo: foto.jpg (Peso: 5)";
        assertEquals(esperado, archivo.toString());
    }

    @Test
    public void testDiferentesExtensiones() {
        Archivo archivo = new Archivo("planilla", (byte) 20, "xlsx");
        assertEquals("xlsx", archivo.getExtension());
        assertEquals("Archivo: planilla.xlsx (Peso: 20)", archivo.toString());
    }

    @Test
    public void testPeso() {
        Archivo archivo = new Archivo("max", (byte) 127, "bin");
        assertEquals((byte) 127, archivo.getPeso());
    }

    @Test
    public void testNombreVacio() {
        Archivo archivo = new Archivo("", (byte) 1, "dat");
        assertEquals("", archivo.getNombre());
        assertEquals("Archivo: .dat (Peso: 1)", archivo.toString());
    }
}
