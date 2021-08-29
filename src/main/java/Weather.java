import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Weather {
    //5ff74ab841441d0f2087a278b93a9fe8
    public static String getWeather(String message, Model model) throws IOException {
        URL url1 = new URL("");
        URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=" + message + "&units=metric&appid=ce06b87a5944aeb912f0631b20d33536");
        Scanner in = new Scanner((InputStream) url.getContent());
        String result = "";
        while (in.hasNext()) {
            result += in.nextLine();
        }
        System.out.println(result);
        JSONObject object = new JSONObject(result);
        model.setName(object.getString("name"));
        JSONObject main = object.getJSONObject("main");
        model.setTemp(main.getDouble("temp"));
        model.setHumidity(main.getDouble("humidity"));
        JSONArray jsonArray = object.getJSONArray("weather");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj =jsonArray.getJSONObject(i);
            model.setIcon((String)obj.get("icon"));
            model.setMain((String)obj.get("main"));

        }
        return "City: " + model.getName() +"\n" +
                "Temperature: " + model.getTemp() +"C"+ "\n" +
                "Humidity: " + model.getHumidity() +"%" + "\n" ;
    }
}
