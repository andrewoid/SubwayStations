package schwimmer.subway;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Reads SubwayStations.json and SubwayLines.json and returns a Map which
 * contains all the connected Stations.
 */
public class StationService {

    public Map<Integer, Station> stations() throws IOException {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        InputStream in = getClass().getClassLoader().getResourceAsStream("SubwayStations.json");
        InputStreamReader reader = new InputStreamReader(in);
        SubwayStationsJson subwayStationsJson = gson.fromJson(reader, SubwayStationsJson.class);
        reader.close();

        Map<Integer, Station> stations = subwayStationsJson.toStations()
                .stream()
                .collect(Collectors.toMap(Station::getObjectId, station -> station));
        in = getClass().getClassLoader().getResourceAsStream("SubwayLines.json");
        reader = new InputStreamReader(in);
        SubwayLinesJson subwayLinesJson = gson.fromJson(reader, SubwayLinesJson.class);

        for (Map.Entry<String, List<Integer>> entry : subwayLinesJson.entrySet()) {
            List<Integer> line = entry.getValue();
            for (int i = 1; i < line.size(); i++) {
                Station a = stations.get(line.get(i - 1));
                Station b = stations.get(line.get(i));
                a.connect(b);
            }
        }

        return stations;
    }

}
