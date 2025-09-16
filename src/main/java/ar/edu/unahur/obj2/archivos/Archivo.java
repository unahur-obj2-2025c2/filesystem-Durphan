package ar.edu.unahur.obj2.archivos;

import ar.edu.unahur.obj2.IElemento;

public class Archivo implements IElemento {
    private String nombre;
    private byte peso;
    private String extension;

    public Archivo(String nombre, byte peso, String extension) {
        this.nombre = nombre;
        this.peso = peso;
        this.extension = extension;
    }

    @Override
    public byte getPeso() {
        return peso;
    }

    public String getNombre() {
        return nombre;
    }

    public String getExtension() {
        return extension;
    }

    @Override
    public String toString() {
        return "Archivo: " + nombre + "." + extension + " (Peso: " + peso + ")";
    }

}
