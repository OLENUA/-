import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SettingsDialog extends JDialog {

    private JSlider temperatureSlider;
    private JSlider lightIntensitySlider;
    private JLabel temperatureLabel;
    private JLabel lightIntensityLabel;

    private boolean heatingOn = false;
    private boolean airConditioningOn = false;

    public SettingsDialog(JFrame parent) {
        super(parent, "Налаштування", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);

        // Елементи керування для налаштування температури
        temperatureSlider = new JSlider(0, 40, 20);
        temperatureLabel = new JLabel("Temperature: " + temperatureSlider.getValue() + "°C");
        temperatureSlider.addChangeListener(new TemperatureChangeListener());

        // Елементи керування для налаштування освітлення
        lightIntensitySlider = new JSlider(0, 100, 50);
        lightIntensityLabel = new JLabel("Light Intensity: " + lightIntensitySlider.getValue() + "%");
        lightIntensitySlider.addChangeListener(e -> lightIntensityLabel.setText("Light Intensity: " + lightIntensitySlider.getValue() + "%"));

        // Кнопка для збереження налаштувань
        JButton saveButton = new JButton("Save Settings");
        saveButton.addActionListener(e -> saveSettings(temperatureSlider.getValue(), lightIntensitySlider.getValue()));

        // Розміщення елементів у GridLayout
        setLayout(new GridLayout(6, 1));
        add(new JLabel("Temperature:"));
        add(temperatureSlider);
        add(temperatureLabel);
        add(new JLabel("")); // Порожній рядок для вирівнювання
        add(new JLabel("Light Intensity:"));
        add(lightIntensitySlider);
        add(lightIntensityLabel);
        add(saveButton);
    }

    private void saveSettings(int temperature, int lightIntensity) {
        // Реалізація збереження налаштувань температури та освітлення
        System.out.println("Saving settings - Temperature: " + temperature + "°C, Light Intensity: " + lightIntensity + "%");
    }

    private class TemperatureChangeListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            int temperature = temperatureSlider.getValue();
            temperatureLabel.setText("Temperature: " + temperature + "°C");

            // Змінена логіка для включення/вимкнення опалення та кондиціонера
            if (temperature < 21 && !heatingOn) {
                System.out.println("Опалення включено - температура менше 21°C");
                heatingOn = true;
                airConditioningOn = false;
            } else if (temperature >= 21 && temperature <= 30 && (heatingOn || airConditioningOn)) {
                System.out.println("Опалення/Кондиціонер вимкнено - температура 21°C або вище, але менше 31°C");
                heatingOn = false;
                airConditioningOn = false;
            } else if (temperature > 30 && !airConditioningOn) {
                System.out.println("Увімкнено кондиціонер - температура 30°C або вище");
                heatingOn = false;
                airConditioningOn = true;
            }
        }
    }
}
