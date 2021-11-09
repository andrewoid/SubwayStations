package schwimmer.subway;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShortestPathTest {

    @Test
    void findShortestPath() throws IOException {

        // given
        StationService service = new StationService();
        Map<Integer, Station> stations = service.stations();
        ShortestPath shortestPath = new ShortestPath(stations);
        // Sheepshead Bay
        Station a = stations.get(328);
        // Ave J
        Station b = stations.get(343);

        // when
        List<Station> path = shortestPath.findShortestPath(a, b);

        // then
        assertEquals(a, path.get(0));
        assertEquals(stations.get(330), path.get(1));
        assertEquals(stations.get(340), path.get(2));
        assertEquals(b, path.get(3));
    }


    @Test
    void findShortestPath2() throws IOException {
        // given
        StationService service = new StationService();
        Map<Integer, Station> stations = service.stations();
        ShortestPath shortestPath = new ShortestPath(stations);
        // Stillwell Ave
        Station a = stations.get(469);
        // 59th St - Columbus Circle
        Station b = stations.get(353);

        // when
        List<Station> path = shortestPath.findShortestPath(a, b);

        // then
        assertEquals(20, path.size());
        assertEquals(a, path.get(0));
        // Brighton Beach
        assertEquals(stations.get(327), path.get(3));
        assertEquals(b, path.get(path.size()-1));
    }

    @Test
    public void findByCoordinates() throws IOException {
        StationService service = new StationService();
        Map<Integer, Station> stations = service.stations();
        ShortestPath shortestPath = new ShortestPath(stations);
        // 59th St
        Station expected = stations.get(353);

        // when
        Station actual  = shortestPath.findByCoordinates(
                expected.getCoords().getLat(),
                expected.getCoords().getLon());

        // then
        assertEquals(expected, actual);
    }

    @Test
    public void distance() throws IOException {
        StationService service = new StationService();
        Map<Integer, Station> stations = service.stations();
        ShortestPath shortestPath = new ShortestPath(stations);
        Station columbusCircle = stations.get(353);
        Station lincolnCenter = stations.get(88);
        Coordinates coordinates = new Coordinates(40.7694134,-73.9851112);

        // when
        double distance1 = columbusCircle.getCoords().distance(coordinates);
        double distance2 = lincolnCenter.getCoords().distance(coordinates);

        // then
        assertTrue(distance1 < distance2);
    }

}