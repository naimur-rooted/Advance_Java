package frames;

import interfaces.UserDAO;
import repository.UserDAOImpl;
import util.UITheme;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Registration screen for new guest accounts.
 */
public class RegisterFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private final UserDAO userDAO = new UserDAOImpl();

    public RegisterFrame() {
        setTitle("Hotel Reservation System - Register");
        setSize(900, 550);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        add(buildLeftPanel(), BorderLayout.WEST);
        add(buildRegisterPanel(), BorderLayout.CENTER);
    }

    /** Left branding panel. */
    private JPanel buildLeftPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(380, 0));
        panel.setBackground(UITheme.PRIMARY);

        JPanel inner = new JPanel();
        inner.setOpaque(false);
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));

        JLabel icon = new JLabel("\uD83C\uDFE8");
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

    /** Right side registration form. */
    private JPanel buildRegisterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(UITheme.WHITE);
        panel.setBorder(new EmptyBorder(40, 60, 40, 60));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel header = new JLabel("Create Account");
        header.setFont(UITheme.TITLE_FONT);
        header.setForeground(UITheme.TEXT_DARK);
        gbc.gridy = 0;
        panel.add(header, gbc);

        // Username
        gbc.gridy = 1;
        panel.add(UITheme.createLabel("Username"), gbc);
        usernameField = UITheme.createTextField();
        usernameField.setPreferredSize(new Dimension(280, 38));
        gbc.gridy = 2;
        panel.add(usernameField, gbc);

        // Password
        gbc.gridy = 3;
        panel.add(UITheme.createLabel("Password"), gbc);
        passwordField = new JPasswordField();
        passwordField.setFont(UITheme.LABEL_FONT);
        passwordField.setPreferredSize(new Dimension(280, 38));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                new EmptyBorder(8, 10, 8, 10)));
        gbc.gridy = 4;
        panel.add(passwordField, gbc);

        // Register button
        JButton registerBtn = UITheme.createButton("REGISTER", UITheme.ACCENT);
        registerBtn.setPreferredSize(new Dimension(280, 45));
        registerBtn.addActionListener(e -> handleRegister());
        gbc.gridy = 5;
        gbc.insets = new Insets(25, 10, 10, 10);
        panel.add(registerBtn, gbc);

        // Back to login link
        JLabel backLabel = new JLabel("<html><u>Back to Login</u></html>");
        backLabel.setForeground(UITheme.ACCENT);
        backLabel.setFont(UITheme.LABEL_FONT);
        backLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                new LoginFrame().setVisible(true);
            }
        });
        gbc.gridy = 6;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(backLabel, gbc);

        return panel;
    }

    /** Handles registration logic. */
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please fill in both username and password.",
                    "Validation Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Register as guest account
        boolean success = userDAO.registerGuest(username, password);
        if (success) {
            JOptionPane.showMessageDialog(this,
                    "Account created successfully! You can now log in.",
                    "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new LoginFrame().setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Registration failed. Username may already exist.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RegisterFrame().setVisible(true);
    }
}
