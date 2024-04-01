import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    public static void main(String[] args) {
        System.out.println("Bienvenido a nuestro sistema de reservas.");

        List<Hotel> hotels = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;
        while (continuar) {
            System.out.println("Ingrese los detalles del hotel:");
            System.out.print("Nombre del hotel: ");
            String name = scanner.nextLine();
            System.out.print("Tipo de habitación (Individual/Doble/Triple): ");
            String typeRoom = scanner.nextLine();
            System.out.print("Ubicación: ");
            String location = scanner.nextLine();
            System.out.print("Precio: ");
            int price = scanner.nextInt();
            System.out.print("Disponibilidad de habitacion (true/false): ");
            boolean availability = Boolean.parseBoolean(scanner.next()); // Leer como cadena y convertir a booleano
            System.out.print("Número total de habitaciones: ");
            int totalRooms = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea después de nextInt()

            hotels.add(new Hotel(name, typeRoom, location, price, availability, totalRooms));

            System.out.print("¿Desea ingresar otro hotel? (Sí/No): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("Sí")) {
                continuar = false;
            }
        }

        JSONArray hotelsJsonArray = new JSONArray();
        for (Hotel hotel : hotels) {
            JSONObject hotelJson = new JSONObject();
            hotelJson.put("name", hotel.get_name());
            hotelJson.put("type_room", hotel.getTypeRoom());
            hotelJson.put("location", hotel.getLocation());
            hotelJson.put("price", hotel.getPrice());
            hotelJson.put("availability", hotel.is_available()); // Utilizar el nombre de método correcto
            hotelJson.put("total_rooms", hotel.get_total_rooms());
            hotelsJsonArray.put(hotelJson);
        }

        try (FileWriter file = new FileWriter("hoteles.json")) {
            file.write(hotelsJsonArray.toString(4)); // 4 para una salida con sangría
            System.out.println("Los datos de los hoteles han sido guardados en el archivo 'hoteles.json'");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al escribir en el archivo 'hoteles.json'");
            e.printStackTrace();
        }
    }



    static class Hotel{

        private final String name;   //Nombre del Hotel
        private final String type_room; //Tipo de habitacion: individual, doble, triple
        private final String location; //Ubicacion
        private final Integer price; //Precio
        private final Boolean availability; //Disponibilidad
        private final Integer total_rooms; //Numero total de Habitaciones

        public Hotel(String name, String type_room, String location, int price, boolean availability, int total_rooms) {
            this.name = name;
            this.type_room = type_room;
            this.location = location;
            this.price = price;
            this.availability = availability;
            this.total_rooms = total_rooms;
        }

        public String is_available() {
            if (!this.availability) {
                return "Habitacion no disponible";
            } else {
                return "Habitacion Disponible";
            }
        }

        public int get_total_rooms() {
            return this.total_rooms;
        }

        public String get_name() {
            return this.name;
        }

        public String getTypeRoom() {
            return type_room;
        }

        public String getLocation() {
            return location;
        }

        public int getPrice() {
            return price;
        }



        @Override
        public String toString() {
            return "HOTEL {" +
                    "name='" + name + '\'' +
                    ", type_room='" + type_room + '\'' +
                    ", location='" + location + '\'' +
                    ", price=" + price +
                    ", availability=" + availability +
                    '}';
        }

    }
}




