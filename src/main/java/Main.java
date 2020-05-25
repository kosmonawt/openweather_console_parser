import model.FullWhetherInfo;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.println("Choose command : [weather/quit]");
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("weather")) {
                getWeatherFromUser(scanner);
            }
        }
        while (!command.equalsIgnoreCase("quit"));

    }

    private static void getWeatherFromUser(Scanner scanner) {
        System.out.println("Enter city: ");
        String userInput = scanner.nextLine();
        showWeather(userInput);
    }


    private static void showWeather(String input) {
        try {
            WeatherClient weatherClient = new WeatherClient();
            String weather = weatherClient.getCityWeatherJSON(input);
            FullWhetherInfo fullWhetherInfo = weatherClient.parseWeatherJson(weather);
            System.out.println(fullWhetherInfo.toString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }


}
