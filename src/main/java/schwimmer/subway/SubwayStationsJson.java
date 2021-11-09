package schwimmer.subway;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SubwayStationsJson {

    List<SubwayStationJson> features;

    static class SubwayStationJson {

        PropertiesJson properties;
        GeometryJson geometry;

        public Station toStation() {
            return new Station(
                    properties.objectid,
                    properties.name,
                    Arrays.asList(properties.line.split("-")),
                    new Coordinates(
                            // this is reversed
                            geometry.coordinates[1],
                            geometry.coordinates[0])
            );
        }

    }

    static class PropertiesJson {
        String name;
        String line;
        int objectid;
    }

    static class GeometryJson {
        double[] coordinates;
    }

    public List<Station> toStations() {
        return features.stream()
                .map(SubwayStationJson::toStation)
                .collect(Collectors.toList());
    }

}
