package frames;

import entity.User;
import util.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Main dashboard. Buttons shown depend on the logged-in user's role.
 */
public class DashboardFrame extends JFrame {

    private final User currentUser;

    public DashboardFrame(User user) {
        this.currentUser = user;

        setTitle("Hotel Reservation System - Dashboard");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(buildSidebar(), BorderLayout.WEST);
        add(buildMainContent(), BorderLayout.CENTER);
    }

    /** Left navigation sidebar. */
    private JPanel buildSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(260, 0));
        sidebar.setBackground(UITheme.PRIMARY);
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBorder(new EmptyBorder(30, 20, 30, 20));

        // Hotel logo + title
        ImageIcon logoIcon = new ImageIcon("icons/hotel.png");
        Image scaledLogo = logoIcon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(" Grand Hotel", new ImageIcon(scaledLogo), SwingConstants.LEFT);
        logo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        logo.setForeground(UITheme.WHITE);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel roleLbl = new JLabel(currentUser.getRole());
        roleLbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        roleLbl.setForeground(UITheme.ACCENT);
        roleLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(5));
        sidebar.add(roleLbl);
        sidebar.add(Box.createVerticalStrut(40));

        // Role-based navigation buttons
        String role = currentUser.getRole();

        if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("RECEPTIONIST")) {
            sidebar.add(navButton("View Rooms", "icons/house-chimney.png", e -> openRoomFrame()));
            sidebar.add(Box.createVerticalStrut(15));
            sidebar.add(navButton("Make Reservation", "icons/reservation-table.png", e -> openReservationFrame()));
            sidebar.add(Box.createVerticalStrut(15));
        }

        if (role.equalsIgnoreCase("ADMIN")) {
            sidebar.add(navButton("Add Room", "icons/apps-add.png", e -> new RoomFrame(currentUser).setVisible(true)));
            sidebar.add(Box.createVerticalStrut(15));
        }

        if (role.equalsIgnoreCase("GUEST")) {
            sidebar.add(navButton("View Rooms", "icons/house-chimney.png", e -> openRoomFrame()));
            sidebar.add(Box.createVerticalStrut(15));
            sidebar.add(navButton("Request Booking", "icons/reservation-table.png", e -> openReservationFrame()));
            sidebar.add(Box.createVerticalStrut(15));
        }

        sidebar.add(Box.createVerticalGlue());

        JButton logout = navButton("Logout", "icons/user-logout.png", e -> {
            dispose();
            new LoginFrame().setVisible(true);
        });
        logout.setBackground(UITheme.DANGER);
        sidebar.add(logout);

        return sidebar;
    }

    /** Creates a uniform sidebar navigation button with icon support. */
    private JButton navButton(String text, String iconPath, java.awt.event.ActionListener action) {
        JButton btn = new JButton(text);
        btn.setFont(UITheme.BTN_FONT);
        btn.setForeground(UITheme.WHITE);
        btn.setBackground(UITheme.ACCENT_DK);
        btn.setFocusPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setMaximumSize(new Dimension(220, 45));
        btn.setPreferredSize(new Dimension(220, 45));
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 15, 8, 15));
        btn.addActionListener(action);

        // Load and scale icon
        ImageIcon icon = new ImageIcon(iconPath);
        Image scaled = icon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(scaled));
        btn.setIconTextGap(10);

        // Hover effect
        Color base = btn.getBackground();
        Color hover = base.darker();
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(base); }
        });
        return btn;
    }

    /** Central welcome / summary panel. */
    private JPanel buildMainContent() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(UITheme.BG_LIGHT);
        main.setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel welcome = new JLabel("Welcome, " + currentUser.getUsername() + "!");
        welcome.setFont(UITheme.TITLE_FONT);
        welcome.setForeground(UITheme.TEXT_DARK);

        JLabel sub = new JLabel("Use the navigation menu on the left to manage the hotel.");
        sub.setFont(UITheme.LABEL_FONT);
        sub.setForeground(Color.GRAY);

        JPanel header = new JPanel();
        header.setOpaque(false);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.add(welcome);
        header.add(Box.createVerticalStrut(8));
        header.add(sub);

        main.add(header, BorderLayout.NORTH);

        // Quick info cards
        JPanel cards = new JPanel(new GridLayout(1, 3, 20, 0));
        cards.setOpaque(false);
        cards.setBorder(new EmptyBorder(40, 0, 0, 0));
        cards.add(infoCard("Rooms", "Manage room inventory", UITheme.ACCENT));
        cards.add(infoCard("Reservations", "Handle bookings", new Color(52, 152, 219)));
        cards.add(infoCard("Reports", "View hotel statistics", new Color(155, 89, 182)));

        main.add(cards, BorderLayout.CENTER);
        return main;
    }

    private JPanel infoCard(String title, String desc, Color color) {
        JPanel card = new JPanel();
        card.setBackground(UITheme.WHITE);
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 4, 0, color),
                new EmptyBorder(25, 20, 25, 20)));

        JLabel t = new JLabel(title);
        t.setFont(UITheme.HEAD_FONT);
        t.setForeground(color);

        JLabel d = new JLabel("<html>" + desc + "</html>");
        d.setFont(UITheme.LABEL_FONT);
        d.setForeground(Color.GRAY);

        card.add(t);
        card.add(Box.createVerticalStrut(10));
        card.add(d);
        return card;
    }

    private void openRoomFrame() {
        new RoomFrame(currentUser).setVisible(true);
    }

    private void openReservationFrame() {
        new ReservationFrame(currentUser).setVisible(true);
    }
}
