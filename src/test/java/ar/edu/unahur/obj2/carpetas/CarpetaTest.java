package ar.edu.unahur.obj2.carpetas;

import ar.edu.unahur.obj2.IElemento;
import ar.edu.unahur.obj2.archivos.Archivo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

class CarpetaTest {

    private Carpeta carpetaVacia;
    private Carpeta carpetaConArchivos;
    private Archivo archivo1;
    private Archivo archivo2;
    private Archivo archivo3;

    @BeforeEach
    void init() {
        carpetaVacia = new Carpeta("Vacia");

        archivo1 = new Archivo("a.txt", (byte) 10, "txt");
        archivo2 = new Archivo("b.txt", (byte) 20, "txt");
        archivo3 = new Archivo("c.txt", (byte) 5, "txt");

        List<IElemento> elementos = new ArrayList<>();
        elementos.add(archivo1);
        elementos.add(archivo2);
        carpetaConArchivos = new Carpeta("ConArchivos", elementos);
    }

    @Test
    void testGetNombre() {
        assertEquals("Vacia", carpetaVacia.getNombre());
        assertEquals("ConArchivos", carpetaConArchivos.getNombre());
    }

    @Test
    void testAgregarElemento() {
        assertEquals(0, carpetaVacia.getElementos().size());
        carpetaVacia.agregarElemento(archivo1);
        assertEquals(1, carpetaVacia.getElementos().size());
        assertTrue(carpetaVacia.getElementos().contains(archivo1));
    }

    @Test
    void testGetPesoVacia() {
        assertEquals(0, carpetaVacia.getPeso());
    }

    @Test
    void testGetPesoConArchivos() {
        assertEquals(30, carpetaConArchivos.getPeso());
    }

    @Test
    void testSistemaDeArchivos() {
        carpetaVacia.agregarElemento(archivo1);
        carpetaVacia.agregarElemento(archivo2);
        StringBuilder esperado = new StringBuilder();
        esperado.append(carpetaVacia.toString()).append("\n");
        esperado.append(archivo1.toString()).append("\n");
        esperado.append(archivo2.toString()).append("\n");
        assertEquals(esperado.toString(), carpetaVacia.sistemaDeArchivos().toString());
    }

    @Test
    void testSistemaDeArchivosConSubcarpeta() {
        Carpeta subCarpeta = new Carpeta("Sub");
        subCarpeta.agregarElemento(archivo3);
        carpetaVacia.agregarElemento(subCarpeta);

        StringBuilder esperado = new StringBuilder();
        esperado.append(carpetaVacia.toString()).append("\n");
        esperado.append(subCarpeta.toString()).append("\n");
        esperado.append(archivo3.toString()).append("\n");
        assertEquals(esperado.toString(), carpetaVacia.sistemaDeArchivos().toString());
    }

    @Test
    void testArchivoMasGrande() {
        carpetaVacia.agregarElemento(archivo1);
        carpetaVacia.agregarElemento(archivo2);
        assertEquals(archivo2, carpetaVacia.archivoMasGrande());
    }

    @Test
    void testArchivoMasGrandeConSubcarpetas() {
        Carpeta subCarpeta = new Carpeta("Sub");
        subCarpeta.agregarElemento(archivo3);
        carpetaVacia.agregarElemento(archivo1);
        carpetaVacia.agregarElemento(subCarpeta);
        assertEquals(archivo1, carpetaVacia.archivoMasGrande());
    }

    @Test
    void testArchivoMasGrandeSinArchivos() {
        assertThrows(RuntimeException.class, () -> {
            carpetaVacia.archivoMasGrande();
        });
    }

    @Test
    void testToString() {
        assertEquals("Carpeta: Vacia (Peso: 0)", carpetaVacia.toString());
        assertEquals("Carpeta: ConArchivos (Peso: 30)", carpetaConArchivos.toString());
    }

    @Test
    void testConstructorConElementos() {
        List<IElemento> elementos = new ArrayList<>();
        elementos.add(archivo1);
        elementos.add(archivo2);
        Carpeta carpeta = new Carpeta("Test", elementos);
        assertEquals("Test", carpeta.getNombre());
        assertEquals(2, carpeta.getElementos().size());
        assertTrue(carpeta.getElementos().contains(archivo1));
        assertTrue(carpeta.getElementos().contains(archivo2));
    }

    @Test
    void testAgregarCarpetaComoElemento() {
        Carpeta subCarpeta = new Carpeta("Sub");
        carpetaVacia.agregarElemento(subCarpeta);
        assertEquals(1, carpetaVacia.getElementos().size());
        assertTrue(carpetaVacia.getElementos().contains(subCarpeta));
    }

    @Test
    void testGetPesoConSubcarpetas() {
        Carpeta subCarpeta = new Carpeta("Sub");
        subCarpeta.agregarElemento(archivo3); // peso 5
        carpetaVacia.agregarElemento(archivo1); // peso 10
        carpetaVacia.agregarElemento(subCarpeta);
        assertEquals(15, carpetaVacia.getPeso());
    }

    @Test
    void testSistemaDeArchivosAnidado() {
        Carpeta subCarpeta = new Carpeta("Sub");
        Carpeta subSubCarpeta = new Carpeta("SubSub");
        subSubCarpeta.agregarElemento(archivo3);
        subCarpeta.agregarElemento(subSubCarpeta);
        carpetaVacia.agregarElemento(archivo1);
        carpetaVacia.agregarElemento(subCarpeta);

        StringBuilder esperado = new StringBuilder();
        esperado.append(carpetaVacia.toString()).append("\n");
        esperado.append(archivo1.toString()).append("\n");
        esperado.append(subCarpeta.toString()).append("\n");
        esperado.append(subSubCarpeta.toString()).append("\n");
        esperado.append(archivo3.toString()).append("\n");
        assertEquals(esperado.toString(), carpetaVacia.sistemaDeArchivos().toString());
    }

}