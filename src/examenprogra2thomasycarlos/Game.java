/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examenprogra2thomasycarlos;

import java.util.ArrayList;
import java.util.Calendar;
/**
 *
 * @author adrianaguilar
 */


public class Game extends RentItem implements MenuActions {
    private Calendar fechaPublicacion;
    private ArrayList<String> especificaciones;

    public Game(String codigo, String nombre, double precioBase) {
        super(codigo, nombre, precioBase); // precioBase no se usa directamente, es fijo: 20
        this.fechaPublicacion = Calendar.getInstance();
        this.especificaciones = new ArrayList<>();
    }

    public void setFechaPublicacion(int year, int mes, int dia) {
        this.fechaPublicacion.set(year, mes - 1, dia); // mes 0-indexado
    }

    public Calendar getFechaPublicacion() {
        return fechaPublicacion;
    }
    
    public ArrayList<String> getEspecificaciones() {
    return especificaciones;
}

    public void agregarEspecificacion(String espec) {
        especificaciones.add(espec);
    }

    public void listEspecificaciones() {
        listEspecificacionesRecursivo(0);
    }

    private void listEspecificacionesRecursivo(int index) {
        if (index >= especificaciones.size()) return;
        System.out.println("- " + especificaciones.get(index));
        listEspecificacionesRecursivo(index + 1);
    }

    @Override
    public double pagoRenta(int dias) {
        return 20 * dias;
    }

    @Override
    public void submenu() {
        System.out.println("SUBMENÚ DEL JUEGO:");
        System.out.println("1. Actualizar fecha de publicación");
        System.out.println("2. Agregar especificación");
        System.out.println("3. Ver especificaciones");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> {
                // Este método se usará desde GUI, así que solo se define el comportamiento esperado
                System.out.println("Actualizar fecha de publicación...");
            }
            case 2 -> {
                System.out.println("Agregar nueva especificación...");
            }
            case 3 -> {
                System.out.println("Especificaciones:");
                listEspecificaciones();
            }
            default -> System.out.println("Opción inválida.");
        }
    }

    @Override
    public String toString() {
        return super.toString() + "\nFecha de publicación: " + 
               fechaPublicacion.getTime().toString() + "\n– PS3 Game";
    }
}
