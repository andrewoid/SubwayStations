import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Station {

    private final int objectId;
    private final String name;
    private final Coordinates coords;
    private final List<String> lines;

    private final List<Station> connections;
    private int distance;

    private Station toStart;

    public Station(int objectId, String name, List<String> lines, Coordinates coords) {
        this.objectId = objectId;
        this.name = name;
        this.lines = lines;
        this.coords = coords;
        connections = new ArrayList<>();
    }

    public double distance(Station station) {
        return coords.distance(station.coords);
    }

    public void connect(Station station) {
        connections.add(station);
        station.connections.add(this);
    }

    public String getName() {
        return name;
    }

    public int getObjectId() {
        return objectId;
    }

    public List<String> getLines() {
        return lines;
    }

    public Coordinates getCoords() {
        return coords;
    }

    public List<Station> getConnections() {
        return connections;
    }

    @Override
    public String toString() {
        return "Station{" +
                "objectId='" + objectId + '\'' +
                ", name='" + name + '\'' +
                ", lines=" + lines +
                ", coords=" + coords +
                '}';
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Station getToStart() {
        return toStart;
    }

    public void setToStart(Station toStart) {
        this.toStart = toStart;
    }
}
