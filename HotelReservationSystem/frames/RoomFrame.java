package frames;

import entity.Room;
import entity.User;
import interfaces.RoomDAO;
import repository.RoomDAOImpl;
import util.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

/**
 * Room management: view rooms in a table, add new rooms, update status.
 */
public class RoomFrame extends JFrame {

    private final RoomDAO roomDAO = new RoomDAOImpl();
    private final User currentUser;
    private JTable table;
    private DefaultTableModel model;

    // Form fields
    private JTextField typeField;
    private JTextField priceField;
    private JComboBox<String> statusBox;

    public RoomFrame(User user) {
        this.currentUser = user;

        setTitle("Room Management");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(UITheme.BG_LIGHT);

        add(buildHeader(), BorderLayout.NORTH);
        add(buildTablePanel(), BorderLayout.CENTER);

        // Only Admin can add/manage rooms via the form
        if (currentUser.getRole().equalsIgnoreCase("ADMIN")) {
            add(buildFormPanel(), BorderLayout.SOUTH);
        }

        loadRooms();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);   // ✅ ensures the frame shows up
    }

    private JPanel buildHeader() {
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT));
        header.setBackground(UITheme.PRIMARY);
        header.setBorder(new EmptyBorder(15, 20, 15, 20));

        JLabel title = new JLabel("\uD83D\uDECF  Room Management");
        title.setFont(UITheme.HEAD_FONT);
        title.setForeground(UITheme.WHITE);
        header.add(title);
        return header;
    }

    private JScrollPane buildTablePanel() {
        String[] cols = {"Room ID", "Type", "Price", "Status"};
        model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        table = new JTable(model);
        table.setRowHeight(28);
        table.setFont(UITheme.LABEL_FONT);
        table.getTableHeader().setFont(UITheme.BTN_FONT);
        table.getTableHeader().setBackground(UITheme.ACCENT);
        table.getTableHeader().setForeground(UITheme.WHITE);
        table.setSelectionBackground(UITheme.ACCENT);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new EmptyBorder(10, 10, 10, 10));
        return scroll;
    }

    /** Add / update room form (Admin only). */
    private JPanel buildFormPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 15));
        panel.setBackground(UITheme.WHITE);
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        panel.add(UITheme.createLabel("Type:"));
        typeField = UITheme.createTextField();
        typeField.setPreferredSize(new Dimension(140, 32));
        panel.add(typeField);

        panel.add(UITheme.createLabel("Price:"));
        priceField = UITheme.createTextField();
        priceField.setPreferredSize(new Dimension(90, 32));
        panel.add(priceField);

        panel.add(UITheme.createLabel("Status:"));
        statusBox = new JComboBox<>(new String[]{"AVAILABLE", "OCCUPIED", "MAINTENANCE"});
        panel.add(statusBox);

        JButton addBtn = UITheme.createButton("Add Room", UITheme.ACCENT);
        addBtn.addActionListener(e -> addRoom());
        panel.add(addBtn);

        JButton updateBtn = UITheme.createButton("Update Status", new Color(52, 152, 219));
        updateBtn.addActionListener(e -> updateStatus());
        panel.add(updateBtn);

        JButton deleteBtn = UITheme.createButton("Delete", UITheme.DANGER);
        deleteBtn.addActionListener(e -> deleteRoom());
        panel.add(deleteBtn);

        return panel;
    }

    private void loadRooms() {
        model.setRowCount(0);
        List<Room> rooms = roomDAO.getAllRooms();
        for (Room r : rooms) {
            model.addRow(new Object[]{
                    r.getRoomId(), r.getType(),
                    "₱" + r.getPrice(), r.getStatus()
            });
        }
    }

    private void addRoom() {
        String type = typeField.getText().trim();
        String priceStr = priceField.getText().trim();
        String status = (String) statusBox.getSelectedItem();

        if (type.isEmpty() || priceStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all fields.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceStr);
            Room room = new Room(0, type, price, status);
            if (roomDAO.addRoom(room)) {
                JOptionPane.showMessageDialog(this, "Room added successfully!");
                typeField.setText("");
                priceField.setText("");
                loadRooms();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add room.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Price must be a valid number.",
                    "Validation", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void updateStatus() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room from the table.");
            return;
        }
        int roomId = (int) model.getValueAt(row, 0);
        String status = (String) statusBox.getSelectedItem();

        if (roomDAO.updateStatus(roomId, status)) {
            JOptionPane.showMessageDialog(this, "Status updated!");
            loadRooms();
        } else {
            JOptionPane.showMessageDialog(this, "Update failed.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteRoom() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a room to delete.");
            return;
        }
        int roomId = (int) model.getValueAt(row, 0);
        int confirm = JOptionPane.showConfirmDialog(this,
                "Delete Room " + roomId + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            if (roomDAO.deleteRoom(roomId)) {
                JOptionPane.showMessageDialog(this, "Room deleted.");
                loadRooms();
            }
        }
    }
}
