package frames;

import entity.User;
import repository.UserDAOImpl;
import interfaces.UserDAO;
import util.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Login screen with role-based access and registration link.
 */
public class LoginFrame extends JFrame {

    private final UserDAO userDAO = new UserDAOImpl();
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Hotel Reservation System - Login");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(buildLeftPanel(), BorderLayout.WEST);
        add(buildLoginPanel(), BorderLayout.CENTER);
    }

    /** Decorative left branding panel. */
    private JPanel buildLeftPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(380, 0));
        panel.setBackground(UITheme.PRIMARY);

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("\uD83C\uDFE8"); // hotel emoji
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 80));
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("Grand Hotel");
        title.setFont(new Font("Segoe UI", Font.BOLD, 34));
        title.setForeground(UITheme.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Reservation Management System");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(UITheme.ACCENT);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        inner.add(icon);
        inner.add(Box.createVerticalStrut(15));
        inner.add(title);
        inner.add(Box.createVerticalStrut(8));
        inner.add(subtitle);

        panel.add(inner);
        return panel;
    }

    /** Right side login form. */
    private JPanel buildLoginPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UITheme.WHITE);
        panel.setBorder(new EmptyBorder(40, 60, 40, 60));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel header = new JLabel("Welcome Back");
        header.setFont(UITheme.TITLE_FONT);
        header.setForeground(UITheme.TEXT_DARK);
        gbc.gridy = 0;
        panel.add(header, gbc);

        JLabel info = new JLabel("Please login to your account");
        info.setFont(UITheme.LABEL_FONT);
        info.setForeground(Color.GRAY);
        gbc.gridy = 1;
        panel.add(info, gbc);

        // Username
        gbc.gridy = 2;
        panel.add(UITheme.createLabel("Username"), gbc);
        usernameField = UITheme.createTextField();
        usernameField.setPreferredSize(new Dimension(280, 38));
        gbc.gridy = 3;
        panel.add(usernameField, gbc);

        // Password
        gbc.gridy = 4;
        panel.add(UITheme.createLabel("Password"), gbc);
        passwordField = new JPasswordField();
        passwordField.setFont(UITheme.LABEL_FONT);
        passwordField.setPreferredSize(new Dimension(280, 38));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                new EmptyBorder(8, 10, 8, 10)));
        gbc.gridy = 5;
        panel.add(passwordField, gbc);

        // Login button
        JButton loginBtn = UITheme.createButton("LOGIN", UITheme.ACCENT);
        loginBtn.setPreferredSize(new Dimension(280, 45));
        loginBtn.addActionListener(e -> handleLogin());
        gbc.gridy = 6;
        gbc.insets = new Insets(25, 10, 10, 10);
        panel.add(loginBtn, gbc);

        // Registration link
        JLabel registerLabel = new JLabel("<html><u>Create an account</u></html>");
        registerLabel.setForeground(UITheme.ACCENT);
        registerLabel.setFont(UITheme.LABEL_FONT);
        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new RegisterFrame().setVisible(true);
            }
        });
        gbc.gridy = 7;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(registerLabel, gbc);

        // Allow ENTER key to submit
        getRootPane().setDefaultButton(loginBtn);

        return panel;
    }

    /** Authenticates the user and opens the dashboard. */
    private void handleLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter both username and password.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        User user = userDAO.authenticate(username, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this,
                    "Welcome, " + user.getUsername() + " (" + user.getRole() + ")!",
                    "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new DashboardFrame(user).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Invalid username or password.",
                    "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
