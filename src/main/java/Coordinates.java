public class Coordinates {
    private final double lat;
    private final double lon;

    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double distance(Coordinates c) {
        return Math.sqrt((c.lat - lat) * (c.lat - lat) + (c.lon - lon) * (c.lon - lon));
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "lat=" + lat +
                ", lon=" + lon +
                '}';
    }
}
