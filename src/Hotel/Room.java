package Hotel;

import Hotel.Reservation;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {
    private int hotelId;
    private int roomNumber;
    private String[] beds;
    private double price;
    private int squares;
    private String[] description;
    private int numberOfGuests;
    private int totalRooms;
    private Map<Integer, RoomStatus> rooms;

    public Room(int hotelId, int roomNumber, String[] beds, double price, int squares, String[] description, int numberOfGuests, int totalRooms) {
        this.hotelId = hotelId;
        this.roomNumber = roomNumber;
        this.beds = beds;
        this.price = price;
        this.squares = squares;
        this.description = description;
        this.numberOfGuests = numberOfGuests;
        this.totalRooms = totalRooms;
        this.rooms = new HashMap<>();
        for (int i = 1; i <= totalRooms; i++) {
            rooms.put(i, new RoomStatus("available", new Reservation[0]));
        }
    }

    public int getHotelId() {
        return hotelId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String[] getBeds() {
        return beds;
    }

    public double getPrice() {
        return price;
    }

    public int getSquares() {
        return squares;
    }

    public String[] getDescription() {
        return description;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public int getTotalRooms() {
        return totalRooms;
    }

    public Map<Integer, RoomStatus> getRooms() {
        return rooms;
    }

    public class RoomStatus {
        private String status;
        private Reservation[] reservations;

        public RoomStatus(String status, Reservation[] reservations) {
            this.status = status;
            this.reservations = reservations;
        }

        public String getStatus() {
            return status;
        }

        public Reservation[] getReservations() {
            return reservations;
        }

        public boolean dateReserved(Date from, Date to) {
            return Arrays.stream(reservations).noneMatch(reservation ->
                    (from.before(reservation.getTo()) || from.equals(reservation.getTo()))
                            && (to.after(reservation.getFrom()) || to.equals(reservation.getFrom())));
        }


    }
}
