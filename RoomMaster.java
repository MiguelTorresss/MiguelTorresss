/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.roommaster;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Hotel {
    private Map<Integer, String> habitaciones;

    public Hotel(int numHabitaciones) {
        habitaciones = new HashMap<>();
        for (int i = 1; i <= numHabitaciones; i++) {
            habitaciones.put(i, "disponible");
        }
    }

    public void asignarHabitacion(int numeroHabitacion, String nombrePersona) {
        if (habitaciones.containsKey(numeroHabitacion) && habitaciones.get(numeroHabitacion).equals("disponible")) {
            habitaciones.put(numeroHabitacion, "ocupada");
            System.out.println("Habitación " + numeroHabitacion + " asignada a " + nombrePersona);
        } else {
            System.out.println("La habitación " + numeroHabitacion + " no está disponible.");
        }
    }

    public void liberarHabitacion(int numeroHabitacion) {
        if (habitaciones.containsKey(numeroHabitacion) && habitaciones.get(numeroHabitacion).equals("ocupada")) {
            habitaciones.put(numeroHabitacion, "disponible");
            System.out.println("Habitación " + numeroHabitacion + " liberada.");
        } else {
            System.out.println("La habitación " + numeroHabitacion + " no está ocupada.");
        }
    }

    public void mostrarEstadoHabitaciones() {
        System.out.println("Estado de las habitaciones:");
        for (Map.Entry<Integer, String> entry : habitaciones.entrySet()) {
            System.out.println("Habitación " + entry.getKey() + ": " + entry.getValue());
        }
    }
}

public class RoomMaster {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de gestión de habitaciones de un hotel.");
        System.out.print("Ingrese la cantidad de habitaciones en el hotel: ");
        int numHabitaciones = scanner.nextInt();

        Hotel hotel = new Hotel(numHabitaciones);

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Asignar habitación");
            System.out.println("2. Liberar habitación");
            System.out.println("3. Mostrar estado de las habitaciones");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el número de habitación: ");
                    int numeroHabitacion = scanner.nextInt();
                    scanner.nextLine(); // Consumir la nueva línea
                    System.out.print("Ingrese el nombre de la persona: ");
                    String nombrePersona = scanner.nextLine();
                    hotel.asignarHabitacion(numeroHabitacion, nombrePersona);
                    break;
                case 2:
                    System.out.print("Ingrese el número de habitación a liberar: ");
                    int habitacionALiberar = scanner.nextInt();
                    hotel.liberarHabitacion(habitacionALiberar);
                    break;
                case 3:
                    hotel.mostrarEstadoHabitaciones();
                    break;
                case 4:
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }
}