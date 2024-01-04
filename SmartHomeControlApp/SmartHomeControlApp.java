import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SmartHomeControlApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Smart Home Control");

        // Елементи керування
        Slider temperatureSlider = new Slider(0, 30, 20);
        Slider lightIntensitySlider = new Slider(0, 100, 50);

        // Мітки для слайдерів
        Label temperatureLabel = new Label("Temperature: " + temperatureSlider.getValue() + "°C");
        Label lightIntensityLabel = new Label("Light Intensity: " + lightIntensitySlider.getValue() + "%");

        // Обробники подій для слайдерів
        temperatureSlider.valueProperty().addListener((observable, oldValue, newValue) ->
                temperatureLabel.setText("Temperature: " + newValue.intValue() + "°C"));

        lightIntensitySlider.valueProperty().addListener((observable, oldValue, newValue) ->
                lightIntensityLabel.setText("Light Intensity: " + newValue.intValue() + "%"));

        // Кнопка для збереження налаштувань
        Button saveButton = new Button("Save Settings");
        saveButton.setOnAction(event -> saveSettings(temperatureSlider.getValue(), lightIntensitySlider.getValue()));

        // Меню
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(event -> primaryStage.close());
        fileMenu.getItems().add(exitMenuItem);
        menuBar.getMenus().add(fileMenu);

        // Розміщення елементів у VBox
        VBox vbox = new VBox(menuBar, temperatureSlider, temperatureLabel, lightIntensitySlider, lightIntensityLabel, saveButton);
        vbox.setSpacing(10);

        // Сцена
        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);

        // Показати вікно
        primaryStage.show();
    }

    private void saveSettings(double temperature, double lightIntensity) {
        // Реалізація збереження налаштувань
        System.out.println("Saving settings - Temperature: " + temperature + "°C, Light Intensity: " + lightIntensity + "%");
    }
}
