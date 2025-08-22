/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenprogra2thomasycarlos;

import javax.swing.*;
import java.io.Serializable;
/**
 *
 * @author adrianaguilar
 */


public abstract class RentItem implements Serializable {
    protected String codigo;
    protected String nombre;
    protected double precioBase;
    protected int cantidadCopias;
    protected ImageIcon imagen;

    public RentItem(String codigo, String nombre, double precioBase) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioBase = precioBase;
        this.cantidadCopias = 0;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public int getCantidadCopias() {
        return cantidadCopias;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + "\n" +
               "Nombre: " + nombre + "\n" +
               "Precio Base: Lps. " + precioBase + "\n" +
               "Copias disponibles: " + cantidadCopias;
    }

    public abstract double pagoRenta(int dias);
}

