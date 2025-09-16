package ar.edu.unahur.obj2.carpetas;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unahur.obj2.IElemento;
import ar.edu.unahur.obj2.archivos.Archivo;

public class Carpeta implements IElemento {
    private String nombre;
    private List<IElemento> elementos = new ArrayList<>();

    public Carpeta(String nombre, List<IElemento> elementos) {
        this.nombre = nombre;
        this.elementos = elementos;
    }

    public Carpeta(String nombre) {
        this.nombre = nombre;
    }

    public void agregarElemento(IElemento elemento) {
        elementos.add(elemento);
    }

    public String getNombre() {
        return nombre;
    }

    public List<IElemento> getElementos() {
        return elementos;
    }

    @Override
    public byte getPeso() {
        return (byte) elementos.stream().mapToInt(IElemento::getPeso).sum();
    }

    public StringBuilder sistemaDeArchivos() {
        StringBuilder resultado = new StringBuilder();
        resultado.append(this.toString()).append("\n");
        for (IElemento elemento : elementos) {
            if (elemento instanceof Carpeta carpeta) {
                resultado.append(carpeta.sistemaDeArchivos());
            } else {
                resultado.append(((Archivo) elemento).toString());
                resultado.append("\n");
            }
        }
        return resultado;
    }

    public Archivo archivoMasGrande() {
        Archivo archivoMasGrande = funcionAuxiliarParaBuscarArchivo(null, elementos);
        if (archivoMasGrande == null) {
            throw new RuntimeException("No hay archivos en la carpeta");
        }
        return archivoMasGrande;
    }

    private Archivo funcionAuxiliarParaBuscarArchivo(Archivo archivoMasGrandeHastaAhora, List<IElemento> elementos) {
        if (elementos.isEmpty()) {
            return archivoMasGrandeHastaAhora;
        }
        IElemento elementoActual = elementos.remove(0);
        if (elementoActual instanceof Archivo archivo) {
            if (archivoMasGrandeHastaAhora == null || archivo.getPeso() > archivoMasGrandeHastaAhora.getPeso()) {
                archivoMasGrandeHastaAhora = archivo;
            }
        } else if (elementoActual instanceof Carpeta carpeta) {
            Archivo archivoEnCarpeta = carpeta.archivoMasGrande();
            if (archivoMasGrandeHastaAhora == null
                    || archivoEnCarpeta.getPeso() > archivoMasGrandeHastaAhora.getPeso()) {
                archivoMasGrandeHastaAhora = archivoEnCarpeta;
            }
        }
        return funcionAuxiliarParaBuscarArchivo(archivoMasGrandeHastaAhora, elementos);
    }

    @Override
    public String toString() {
        return "Carpeta: " + nombre + " (Peso: " + getPeso() + ")";
    }

}
