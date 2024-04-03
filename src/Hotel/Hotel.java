package Hotel;

public class Hotel {
    private int id;
    private String name;
    private String location;
    private String country;
    private String state;
    private String city;
    private ContactInfo contact;
    private Room[] rooms;

    public Hotel(int id, String name, String location, String country, String state, String city, ContactInfo contact, Room[] rooms) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.country = country;
        this.state = state;
        this.city = city;
        this.contact = contact;
        this.rooms = rooms;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public ContactInfo getContact() {
        return contact;
    }

    public Room[] getRooms() {
        return rooms;
    }

}
