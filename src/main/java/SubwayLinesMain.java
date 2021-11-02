import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class SubwayLinesMain {

    public static void main(String[] args) throws FileNotFoundException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        InputStream in = ClassLoader.getSystemResourceAsStream("SubwayStations.json");
        InputStreamReader reader = new InputStreamReader(in);
        SubwayStationsJson json = gson.fromJson(reader, SubwayStationsJson.class);
        StationGraphGenerator generator = new StationGraphGenerator(json.toStations());
        String jsonOut = gson.toJson(generator.getLineMap());
        PrintWriter out = new PrintWriter(new FileOutputStream("SubwayLines.json"));
        out.print(jsonOut);
        out.close();
    }

}
