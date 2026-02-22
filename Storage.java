import java.io.*;
import java.util.*;

class Storage {

    private static final String ROOM_FILE = "rooms.txt";
    private static final String BOOK_FILE = "bookings.txt";

    public static List<Room> loadRooms() throws Exception {
        List<Room> list = new ArrayList<>();
        File f = new File(ROOM_FILE);

        if (!f.exists()) seedRooms();

        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null)
            list.add(Room.fromCSV(line));
        br.close();

        return list;
    }

    public static void saveRooms(List<Room> rooms) throws Exception {
        PrintWriter pw = new PrintWriter(ROOM_FILE);
        for (Room r : rooms) pw.println(r.toCSV());
        pw.close();
    }

    public static List<Booking> loadBookings() throws Exception {
        List<Booking> list = new ArrayList<>();
        File f = new File(BOOK_FILE);
        if (!f.exists()) return list;

        BufferedReader br = new BufferedReader(new FileReader(f));
        String line;
        while ((line = br.readLine()) != null)
            list.add(Booking.fromCSV(line));
        br.close();

        return list;
    }

    public static void saveBookings(List<Booking> bookings) throws Exception {
        PrintWriter pw = new PrintWriter(BOOK_FILE);
        for (Booking b : bookings) pw.println(b.toCSV());
        pw.close();
    }

    private static void seedRooms() throws Exception {
        List<Room> seed = List.of(
                new Room(101, RoomType.STANDARD, 1500, true),
                new Room(102, RoomType.STANDARD, 1500, true),
                new Room(201, RoomType.DELUXE, 2500, true),
                new Room(202, RoomType.DELUXE, 2500, true),
                new Room(301, RoomType.SUITE, 4500, true)
        );
        saveRooms(new ArrayList<>(seed));
    }
}

