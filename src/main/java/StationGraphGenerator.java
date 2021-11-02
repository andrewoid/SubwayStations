import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StationGraphGenerator {

    Map<String, List<Station>> lineToStations = new HashMap<>();

    public StationGraphGenerator(List<Station> allStations) {

        for (Station s : allStations) {
            for (String line : s.getLines()) {
                List<Station> stationsInLine = lineToStations.computeIfAbsent(line, k -> new ArrayList<>());
                stationsInLine.add(s);
            }
        }

        for (Map.Entry<String,List<Station>> entry : lineToStations.entrySet()) {
            List<Station> unorderedList = entry.getValue();
            List<Station> orderedList = new ArrayList<>();
            orderedList.add(unorderedList.remove(0));

            while (!unorderedList.isEmpty()) {
                double smallestDistance = Double.MAX_VALUE;
                Station closestStation = null;
                Station head = orderedList.get(0);
                Station tail = orderedList.get(orderedList.size()-1);

                for (Station s : unorderedList) {
                    double d1 = head.distance(s);
                    double d2 = tail.distance(s);
                    if (d1 < smallestDistance) {
                        closestStation = s;
                        smallestDistance = d1;
                    }
                    if (d2 < smallestDistance) {
                        closestStation = s;
                        smallestDistance = d2;
                    }
                }

                unorderedList.remove(closestStation);
                double d1 = head.distance(closestStation);
                double d2 = tail.distance(closestStation);
                if (d1 < d2) {
                    head.connect(closestStation);
                    orderedList.add(0, closestStation);
                } else {
                    tail.connect(closestStation);
                    orderedList.add(closestStation);
                }
            }

            entry.setValue(orderedList);
        }
    }

    public Map<String, List<Integer>> getLineMap() {
        Map<String, List<Integer>> map = new HashMap<>();
        for (Map.Entry<String,List<Station>> entry : lineToStations.entrySet()) {
            List<Integer> ids = entry.getValue().stream().map(Station::getObjectId).collect(Collectors.toList());
            map.put(entry.getKey(), ids);
        }
        return map;
    }

}
