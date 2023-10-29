import java.util.HashMap;
import java.util.Map;

class Hotel {
    private final Map<Integer, Room> rooms;

    public Hotel() {
        this.rooms = new HashMap<>();
    }

    public void addRoom(int roomNumber, int capacity) {
        rooms.put(roomNumber, new Room(roomNumber, capacity));
    }

    public void listAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms.values()) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public void reserveRoom(int roomNumber, String guestName, String checkinDate, String checkoutDate) {
        Room room = rooms.get(roomNumber);
        if (room == null) {
            System.out.println("Invalid room number. Please choose a valid room.");
            return;
        }

        if (!room.isAvailableForDates(checkinDate, checkoutDate)) {
            System.out.println("The room is not available for the selected dates. Please choose different dates.");
            return;
        }

        Reservation reservation = new Reservation(guestName, room, checkinDate, checkoutDate);
        room.addReservation(reservation);
    }
}

class Room {
    private final int roomNumber;
    private final int capacity;
    private final Map<Integer, Reservation> reservations;

    public Room(int roomNumber, int capacity) {
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.reservations = new HashMap<>();
    }

    public boolean isAvailable() {
        return reservations.isEmpty();
    }

    public boolean isAvailableForDates(String checkinDate, String checkoutDate) {
        for (Reservation reservation : reservations.values()) {
            if (!(checkoutDate.compareTo(reservation.getCheckinDate()) <= 0) &&
                    !(checkinDate.compareTo(reservation.getCheckoutDate()) >= 0)) {
                return false;
            }
        }
        return true;
    }

    public void addReservation(Reservation reservation) {
        reservations.put(reservation.getId(), reservation);
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (Capacity: " + capacity + ")";
    }
}

class Reservation {
    private static int nextId = 1;

    private final int id;
    private final String checkinDate;
    private final String checkoutDate;

    public Reservation(String ignoredGuestName, Room ignoredRoom, String checkinDate, String checkoutDate) {
        this.id = nextId++;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
    }

    public int getId() {
        return id;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

}

class HotelManagement {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        hotel.addRoom(101, 2);
        hotel.addRoom(102, 3);
        hotel.addRoom(103, 2);

        hotel.listAvailableRooms();

        hotel.reserveRoom(101, "John Doe", "2023-11-01", "2023-11-05");
        hotel.reserveRoom(102, "Jane Doe", "2023-11-03", "2023-11-06");

        hotel.listAvailableRooms();
    }
}
