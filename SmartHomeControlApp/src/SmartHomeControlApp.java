import javax.swing.*;
import java.awt.*;

public class SmartHomeControlApp extends JFrame {

    private boolean heatingOn = false;
    private boolean airConditioningOn = false;

    public SmartHomeControlApp() {
        setTitle("Smart Home Control");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Головне меню
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Меню "Файл"
        JMenu fileMenu = new JMenu("Файл");

        // Пункт меню "Налаштування"
        JMenuItem settingsMenuItem = new JMenuItem("Налаштування");
        settingsMenuItem.addActionListener(e -> openSettingsDialog());
        fileMenu.add(settingsMenuItem);

        // Пункт меню "Вихід"
        JMenuItem exitMenuItem = new JMenuItem("Вихід");
        exitMenuItem.addActionListener(e -> System.exit(0));
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        // Додайте кнопки до головного вікна
        JButton settingsButton = new JButton("Налаштування");
        JButton exitButton = new JButton("Вихід");

        settingsButton.addActionListener(e -> openSettingsDialog());
        exitButton.addActionListener(e -> System.exit(0));

        // Налаштування розміру кнопок
        Dimension buttonSize = new Dimension(150, 50);
        settingsButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Відстань між кнопками

        add(settingsButton, gbc);

        gbc.gridy = 1;
        add(exitButton, gbc);

        pack();
        setLocationRelativeTo(null); // Центрує вікно на екрані
    }

    private void openSettingsDialog() {
        // Логіка відкриття вікна налаштувань
        SwingUtilities.invokeLater(() -> {
            SettingsDialog settingsDialog = new SettingsDialog(this);
            settingsDialog.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SmartHomeControlApp app = new SmartHomeControlApp();
            app.setVisible(true);
        });
    }
}
