import com.google.gson.Gson;
import lombok.Setter;
import model.FullWhetherInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.stream.Collectors;

public class WeatherClient {

    @Setter
    private String API_KEY;

    {
        try {
            API_KEY = getKeyFromResourceFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getKeyFromResourceFile() throws IOException {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("API_KEY.txt")) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }
        }
    }

    private static final HttpClient httpClient = HttpClient.newBuilder().build();

    public String getCityWeatherJSON(String city) throws IOException, InterruptedException {

        String basicWeatherTemplate = "https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s";
        String fullURL = String.format(basicWeatherTemplate, city, API_KEY);

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(fullURL))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status CODE: " + response.statusCode());
        return response.body();

    }

    public FullWhetherInfo parseWeatherJson(String weatherJson) {
        Gson gson = new Gson();
        return gson.fromJson(weatherJson, FullWhetherInfo.class);
    }


}
