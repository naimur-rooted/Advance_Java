import frames.LoginFrame;

public class Start {
    public static void main(String[] args) {
        // Ensure Swing UI runs on Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}
