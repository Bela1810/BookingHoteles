import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Booking {
    private static List<Hotel> hotels = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String JSON_FILE_PATH = "hoteles.json";
    public static void main(String[] args) {
        System.out.println("Bienvenido a nuestro sistema de reservas.");
        load_hotels_json(); //Esta funcion es necesaria para que
                            // queden registrados los hoteles en el json y no se borren
                            // cuando se vuelve a correr el programa

        boolean running = true;
        while (running) {
            menu_manager();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register_hotel();
                    break;
                case 2:
                    ;
                    break;
                case 3:
                    ;
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del menú.");
            }
        }

        System.out.println("¡Gracias por usar nuestro sistema de reservas!");
    }


    private static void menu_manager(){
        System.out.println("\nMenú Administrador:");
        System.out.println("1. Registrar un nuevo Hotel");
        System.out.println("2. Visualizar Hoteles Registrados");
        System.out.println("3. Gestionar Hotel Registrado");
        System.out.println("4. Salir");
        System.out.print("Selecciona una opción: ");
    }

    private static void register_hotel(){

        boolean accept = true;
        while (accept) {
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
            scanner.nextLine();

            hotels.add(new Hotel(name, typeRoom, location, price, availability, totalRooms));
            save_hotel_json();

            System.out.print("¿Desea ingresar otro hotel? (SI/NO): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("SI")) {
                accept = false;
            }
        }

    }

    private static void save_hotel_json(){
        try {
            List<String> lines = Files.readAllLines(Paths.get(JSON_FILE_PATH));

            StringBuilder jsonStringBuilder = new StringBuilder();
            for (String line : lines) {
                jsonStringBuilder.append(line);
            }

            JSONArray hotelsJsonArray = new JSONArray(jsonStringBuilder.toString());

            for (Hotel hotel : hotels) {
                JSONObject hotelJson = new JSONObject();
                hotelJson.put("name", hotel.get_name());
                hotelJson.put("type_room", hotel.getTypeRoom());
                hotelJson.put("location", hotel.getLocation());
                hotelJson.put("price", hotel.getPrice());
                hotelJson.put("availability", hotel.is_available());
                hotelJson.put("total_rooms", hotel.get_total_rooms());
                hotelsJsonArray.put(hotelJson);
            }

            try (FileWriter file = new FileWriter(JSON_FILE_PATH)) {
                file.write(hotelsJsonArray.toString(4));
                System.out.println("Los datos de los hoteles han sido guardados");
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir en el archivo 'hoteles.json'");
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("Error de lectura del archivo 'hoteles.json': " + e.getMessage());
        }
    }

    private static void load_hotels_json() {
        try {
            // Lee el contenido del archivo como una cadena JSON
            //PROGRAMACION DECLARATIVA
            String jsonContent = Files.lines(Paths.get(JSON_FILE_PATH))
                    .reduce("", String::concat);

            JSONArray hotelsJsonArray = new JSONArray(jsonContent);

            hotelsJsonArray.forEach(json -> {
                JSONObject hotelJson = (JSONObject) json;
                String name = hotelJson.getString("name");
                String typeRoom = hotelJson.getString("type_room");
                String location = hotelJson.getString("location");
                int price = hotelJson.getInt("price");
                boolean availability = hotelJson.getBoolean("availability");
                int totalRooms = hotelJson.getInt("total_rooms");
                hotels.add(new Hotel(name, typeRoom, location, price, availability, totalRooms));
            });

            System.out.println("Hoteles cargados desde el archivo 'hoteles.json'");
        } catch (IOException e) {
            System.out.println("Error de lectura del archivo 'hoteles.json': " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
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




