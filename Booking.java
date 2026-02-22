import java.time.LocalDate;

class Booking {
    int id;
    String name;
    int roomId;
    LocalDate checkin;
    LocalDate checkout;
    PaymentStatus status;

    public Booking(int id, String name, int roomId, LocalDate checkin, LocalDate checkout, PaymentStatus status) {
        this.id = id;
        this.name = name;
        this.roomId = roomId;
        this.checkin = checkin;
        this.checkout = checkout;
        this.status = status;
    }

    public String toCSV() {
        return id + "," + name + "," + roomId + "," + checkin + "," + checkout + "," + status;
    }

    public static Booking fromCSV(String line) {
        String[] p = line.split(",");
        return new Booking(
                Integer.parseInt(p[0]),
                p[1],
                Integer.parseInt(p[2]),
                LocalDate.parse(p[3]),
                LocalDate.parse(p[4]),
                PaymentStatus.valueOf(p[5])
        );
    }
}

