public class Room {
    int id;
    RoomType type;
    double price;
    boolean available;

    public Room(int id, RoomType type, double price, boolean available) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.available = available;
    }

    public String toCSV() {
        return id + "," + type + "," + price + "," + available;
    }

    public static Room fromCSV(String line) {
        String[] p = line.split(",");
        return new Room(
                Integer.parseInt(p[0]),
                RoomType.valueOf(p[1]),
                Double.parseDouble(p[2]),
                Boolean.parseBoolean(p[3])
        );
    }
    @Override
public String toString() {
    return "Room ID: " + id + " | Type: " + type + " | Price: " + price;
}
}



