package util;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Centralized UI theme constants and helper methods
 * to keep all frames visually consistent and attractive.
 */
public class UITheme {

    // 🎨 Refined Color Palette
    public static final Color PRIMARY    = new Color(30, 30, 45);    // Sleek dark charcoal (sidebar background)
    public static final Color ACCENT     = new Color(26, 188, 156);  // Teal (main accent)
    public static final Color ACCENT_DK  = new Color(22, 160, 133);  // Darker teal (buttons)
    public static final Color BG_LIGHT   = new Color(245, 247, 250); // Softer light grey background
    public static final Color WHITE      = Color.WHITE;
    public static final Color TEXT_DARK  = new Color(44, 62, 80);    // Dark text
    public static final Color DANGER     = new Color(231, 76, 60);   // Red (logout button)

    // 🖋 Fonts
    public static final Font TITLE_FONT  = new Font("Segoe UI", Font.BOLD, 26);
    public static final Font HEAD_FONT   = new Font("Segoe UI", Font.BOLD, 18);
    public static final Font LABEL_FONT  = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font BTN_FONT    = new Font("Segoe UI", Font.BOLD, 14);

    /** Creates a modern flat styled button. */
    public static JButton createButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setFont(BTN_FONT);
        btn.setForeground(WHITE);
        btn.setBackground(bg);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(12, 20, 12, 20));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Improved hover effect: lighter teal instead of just darker
        Color hover = bg.brighter();
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) { btn.setBackground(hover); }
            public void mouseExited(java.awt.event.MouseEvent e)  { btn.setBackground(bg); }
        });
        return btn;
    }

    /** Creates a styled text field. */
    public static JTextField createTextField() {
        JTextField tf = new JTextField();
        tf.setFont(LABEL_FONT);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(189, 195, 199)),
                new EmptyBorder(8, 10, 8, 10)));
        return tf;
    }

    /** Creates a styled label. */
    public static JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(LABEL_FONT);
        lbl.setForeground(TEXT_DARK);
        return lbl;
    }
}
