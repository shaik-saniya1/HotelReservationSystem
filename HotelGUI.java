import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class HotelGUI extends JFrame {

    private JTextField nameField, roomField, checkinField, checkoutField;
    private JTextArea output;
    private HotelService service;

    public HotelGUI() throws Exception {

        service = new HotelService();

        setTitle("Hotel Reservation System");
        setSize(550, 500);
        JLabel title = new JLabel("HOTEL RESERVATION SYSTEM", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setForeground(Color.BLUE);
        add(title, BorderLayout.PAGE_START);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // -------- TOP INPUT PANEL --------
        JPanel form = new JPanel(new GridLayout(4,2,5,5));

        form.add(new JLabel("Customer Name:"));
        nameField = new JTextField();
        form.add(nameField);

        form.add(new JLabel("Room ID:"));
        roomField = new JTextField();
        form.add(roomField);

        form.add(new JLabel("Check-in (yyyy-mm-dd):"));
        checkinField = new JTextField();
        form.add(checkinField);

        form.add(new JLabel("Check-out (yyyy-mm-dd):"));
        checkoutField = new JTextField();
        form.add(checkoutField);

        add(form, BorderLayout.NORTH);

        // -------- BUTTON PANEL --------
        JPanel buttons = new JPanel();

        JButton search = new JButton("Search Deluxe");
        JButton book = new JButton("Book Room");
        JButton cancel = new JButton("Cancel Booking");
        JButton view = new JButton("View Bookings");
        JButton clear = new JButton("Clear");
       buttons.add(clear);


        buttons.add(search);
        buttons.add(book);
        buttons.add(cancel);
        buttons.add(view);

        add(buttons, BorderLayout.CENTER);

        // -------- OUTPUT AREA --------
        output = new JTextArea();
        output.setEditable(false);
        JScrollPane scroll = new JScrollPane(output);
        scroll.setPreferredSize(new Dimension(500,250));
        add(scroll, BorderLayout.SOUTH);

        // -------- BUTTON ACTIONS --------

        // SEARCH
        search.addActionListener(e -> {
            try {
                output.setText("");
                for(Room r : service.search(RoomType.DELUXE))
                    output.append(r.toString() + "\n");
            } catch(Exception ex){
                output.setText("Error searching rooms");
            }
        });

        // BOOK
        book.addActionListener(e -> {
            try {
                String name = nameField.getText();
                int id = Integer.parseInt(roomField.getText());
                LocalDate in = LocalDate.parse(checkinField.getText());
                LocalDate out = LocalDate.parse(checkoutField.getText());

              String result = service.book(name,id,in,out);
output.setText(result);

if(result.contains("Successful")){
    nameField.setText("");
    roomField.setText("");
    checkinField.setText("");
    checkoutField.setText("");
}


            } catch(Exception ex){
                output.setText("Invalid input! Use correct format.");
            }
        });

        // CANCEL
        cancel.addActionListener(e -> {
            try {
                int id = Integer.parseInt(roomField.getText());
                output.setText(service.cancel(id));
            } catch(Exception ex){
                output.setText("Enter valid booking ID");
            }
        });

        // VIEW BOOKINGS
        view.addActionListener(e -> {
            output.setText(service.viewAll());
        });

        clear.addActionListener(e -> {
        nameField.setText("");
        roomField.setText("");
       checkinField.setText("");
       checkoutField.setText("");
        output.setText("");
    });


        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new HotelGUI();
    }
}
