package frames;

import entity.Reservation;
import entity.Room;
import entity.User;
import interfaces.ReservationDAO;
import interfaces.RoomDAO;
import repository.ReservationDAOImpl;
import repository.RoomDAOImpl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

/**
 * Reservation form: enter guest details, choose a room, set dates,
 * and view existing reservations.
 */
public class ReservationFrame extends JFrame {

    private final ReservationDAO resDAO = new ReservationDAOImpl();
    private final RoomDAO roomDAO = new RoomDAOImpl();
    private final User currentUser;

    private JTextField guestField;
    private JComboBox<Room> roomBox;
    private JTextField checkInField;
    private JTextField checkOutField;

    private JTable table;
    private DefaultTableModel model;

    public ReservationFrame(User user) {
        this.currentUser = user;

        setTitle("Reservation Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel content = new JPanel(new BorderLayout(10, 10));
        content.setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentPane(content);

        // === Top Form Panel ===
        JPanel formPanel = new JPanel(new GridLayout(2, 4, 10, 10));

        guestField = new JTextField();
        roomBox = new JComboBox<>(roomDAO.getAvailableRooms().toArray(new Room[0]));
        checkInField = new JTextField("YYYY-MM-DD");
        checkOutField = new JTextField("YYYY-MM-DD");

        formPanel.add(new JLabel("Guest Name:"));
        formPanel.add(guestField);
        formPanel.add(new JLabel("Room:"));
        formPanel.add(roomBox);
        formPanel.add(new JLabel("Check-In Date:"));
        formPanel.add(checkInField);
        formPanel.add(new JLabel("Check-Out Date:"));
        formPanel.add(checkOutField);

        content.add(formPanel, BorderLayout.NORTH);

        // === Buttons ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addBtn = new JButton("Add Reservation");
        JButton refreshBtn = new JButton("Refresh List");

        addBtn.addActionListener(e -> addReservation());
        refreshBtn.addActionListener(e -> loadReservations());

        buttonPanel.add(addBtn);
        buttonPanel.add(refreshBtn);
        content.add(buttonPanel, BorderLayout.SOUTH);

        // === Table ===
        model = new DefaultTableModel(new String[]{"ResID", "Guest", "Room", "Check-In", "Check-Out"}, 0);
        table = new JTable(model);
        table.setRowHeight(26);
        content.add(new JScrollPane(table), BorderLayout.CENTER);

        // Load reservations initially
        loadReservations();

        setVisible(true);   // ✅ ensures the frame appears
    }

    private void addReservation() {
        try {
            String guest = guestField.getText().trim();
            Room room = (Room) roomBox.getSelectedItem();
            LocalDate checkIn = LocalDate.parse(checkInField.getText().trim());
            LocalDate checkOut = LocalDate.parse(checkOutField.getText().trim());

            if (guest.isEmpty() || room == null) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (checkOut.isBefore(checkIn)) {
                JOptionPane.showMessageDialog(this, "Check-out date must be after check-in date.", "Validation Error", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Reservation res = new Reservation();
            res.setGuestName(guest);
            res.setRoomId(room.getRoomId());
            res.setUserId(currentUser.getUserId());
            res.setCheckIn(checkIn);
            res.setCheckOut(checkOut);

            if (resDAO.addReservation(res)) {
                JOptionPane.showMessageDialog(this, "Reservation added successfully!");
                loadReservations();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add reservation.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (DateTimeParseException ex) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadReservations() {
        model.setRowCount(0);
        List<Reservation> reservations = resDAO.getAllReservations();
        for (Reservation r : reservations) {
            model.addRow(new Object[]{
                    r.getResId(),
                    r.getGuestName(),
                    r.getRoomId(),
                    r.getCheckIn(),
                    r.getCheckOut()
            });
        }
    }
}
