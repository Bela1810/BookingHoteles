package json;

import  Hotel.Hotel;
import Hotel.Room;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Parser {
    public static Hotel[] parseJson(String filePath) {
        try {
            // Leer el archivo JSON y parsearlo en un array de objetos Hotel
            return new Gson().fromJson(new FileReader(filePath), Hotel[].class);

        }catch (Error e){
            e.printStackTrace();
            return new Hotel[0];

        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printHotels(Hotel[] hotels) {

        // Iterar sobre los hoteles y mostrar su información
        for (Hotel hotel : hotels) {
            System.out.println("Hotel: " + hotel.getName());
            System.out.println("Ubicación: " + hotel.getLocation());
            System.out.println("País: " + hotel.getCountry());
            System.out.println("Estado: " + hotel.getState());
            System.out.println("Ciudad: " + hotel.getCity());
            System.out.println("Contacto: " + hotel.getContact().getPhone());
            System.out.println("Habitaciones:");
            for (Room room : hotel.getRooms()) {
                System.out.println("  Número de habitación: " + room.getRoomNumber());
                System.out.println("  Precio: " + room.getPrice());
                System.out.println("  Metros cuadrados: " + room.getSquares());
                System.out.println("  Descripción: ");
                for (String desc : room.getDescription()) {
                    System.out.println("    " + desc);
                }
                System.out.println("  Número de huéspedes: " + room.getNumberOfGuests());
                System.out.println("  Total de habitaciones: " + room.getTotalRooms());
            }
        }
    }
}
