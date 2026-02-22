import java.time.LocalDate;
import java.util.*;

class HotelService {

    List<Room> rooms;
    List<Booking> bookings;

    public HotelService() throws Exception {
        rooms = Storage.loadRooms();
        bookings = Storage.loadBookings();
    }

    public List<Room> search(RoomType type) {
        List<Room> result = new ArrayList<>();
        for (Room r : rooms)
            if (r.type == type && r.available)
                result.add(r);
        return result;
    }

    public String book(String name, int roomId, LocalDate in, LocalDate out) throws Exception {
        for (Room r : rooms) {
            if (r.id == roomId && r.available) {

                double total = r.price * (out.toEpochDay() - in.toEpochDay());
                if (Math.random() < 0.2)
                    return "Payment Failed";

                r.available = false;

                Booking b = new Booking(bookings.size()+1, name, roomId, in, out, PaymentStatus.PAID);
                bookings.add(b);

                Storage.saveRooms(rooms);
                Storage.saveBookings(bookings);

                return "Booking Successful. Total Rs." + total;
            }
        }
        return "Room not available";
    }

    public String cancel(int id) throws Exception {
        for (Booking b : bookings) {
            if (b.id == id) {
                for (Room r : rooms)
                    if (r.id == b.roomId) r.available = true;

                bookings.remove(b);
                Storage.saveRooms(rooms);
                Storage.saveBookings(bookings);
                return "Booking Cancelled";
            }
        }
        return "Booking Not Found";
    }

    public String viewAll() {
        StringBuilder sb = new StringBuilder();
        for (Booking b : bookings)
            sb.append("ID: ").append(b.id).append(" Name: ").append(b.name)
              .append(" Room: ").append(b.roomId).append("\n");
        return sb.toString();
    }
}
