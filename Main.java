import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        HotelService service = new HotelService();

        while(true){
            System.out.println("\n1.Search 2.Book 3.Cancel 4.View 5.Exit");
            int ch = sc.nextInt();

            switch(ch){

                case 1 -> {
                    System.out.println("1.STANDARD 2.DELUXE 3.SUITE");
                    int t = sc.nextInt();
                    System.out.println(service.search(RoomType.values()[t-1]));
                }

                case 2 -> {
                    sc.nextLine();
                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Room ID: ");
                    int id = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Checkin yyyy-mm-dd: ");
                    LocalDate in = LocalDate.parse(sc.nextLine());

                    System.out.print("Checkout yyyy-mm-dd: ");
                    LocalDate out = LocalDate.parse(sc.nextLine());

                    System.out.println(service.book(name,id,in,out));
                }

                case 3 -> {
                    System.out.print("Booking ID: ");
                    int id = sc.nextInt();
                    System.out.println(service.cancel(id));
                }

                case 4 -> System.out.println(service.viewAll());

                case 5 -> {
                    sc.close();
                    return;
                }
            }
        }
    }
}

