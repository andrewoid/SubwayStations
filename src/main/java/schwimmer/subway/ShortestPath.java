package schwimmer.subway;

import java.util.*;

public class ShortestPath {

    private Map<Integer, Station> stations;

    public ShortestPath(Map<Integer, Station> stations) {
        this.stations = stations;
    }

    /**
     * Finds the closest schwimmer.subway.Station to the coordinates lat, lon
     * @param lat
     * @param lon
     * @return
     */
    public Station findByCoordinates(double lat, double lon) {
        Coordinates c = new Coordinates(lat, lon);

        Station closest = null;
        double minDistance = Double.MAX_VALUE;
        for (Station s : stations.values()) {
            double distance = s.getCoords().distance(c);
            if (distance < minDistance) {
                minDistance = distance;
                closest = s;
            }
        }

        return closest;
    }

    /**
     * Finds the shortest path using Dijkstra's algorithm
     * @param a
     * @param b
     * @return
     */
    public List<Station> findShortestPath(Station a, Station b) {
        // setup
        Collection<Station> collection = stations.values();
        Set<Station> unvisited = new HashSet<>(collection);
        collection.forEach(station -> {
            station.setDistance(Integer.MAX_VALUE);
        });

        a.setDistance(0);

        // https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
        while (unvisited.contains(b)) {
            Station current = unvisited.stream()
                    .min(Comparator.comparingDouble(Station::getDistance))
                    .get();
            int newDistance = current.getDistance() + 1;
            for (Station s : current.getConnections()) {
                if (unvisited.contains(s)) {
                    if (s.getDistance() > newDistance) {
                        s.setDistance(newDistance);
                    }
                }
            }
            unvisited.remove(current);
        }

        // find the path given the neighbors data
        return shortestPath(a, b);
    }

    /**
     * Using the distance values find the shortest path between a and b.
     * @param a
     * @param b
     * @return
     */
    private List<Station> shortestPath(Station a, Station b) {
        List<Station> path = new ArrayList<>();
        Station current = b;

        while (current != a) {
            path.add(current);
            current = current.getConnections().stream()
                    .min(Comparator.comparingDouble(Station::getDistance))
                    .get();
        }
        path.add(a);

        // path is backwards so we need to reverse it.
        Collections.reverse(path);
        return path;
    }

}
