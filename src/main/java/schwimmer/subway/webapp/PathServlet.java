package schwimmer.subway.webapp;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import schwimmer.subway.ShortestPath;
import schwimmer.subway.Station;
import schwimmer.subway.StationService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

public class PathServlet extends HttpServlet {

    private final Gson gson;
    private final Map<Integer, Station> stations;
    private final ShortestPath shortestPath;
    private final StationService stationService;

    public PathServlet() throws IOException {
        stationService = new StationService();
        stations = stationService.stations();
        shortestPath = new ShortestPath(stations);
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    public void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        PrintWriter out = response.getWriter();

        double lat1 = Double.parseDouble(request.getParameter("lat1"));
        double lon1 = Double.parseDouble(request.getParameter("lon1"));
        double lat2 = Double.parseDouble(request.getParameter("lat2"));
        double lon2 = Double.parseDouble(request.getParameter("lon2"));
        Station start = shortestPath.findByCoordinates(lat1, lon1);
        Station end = shortestPath.findByCoordinates(lat2, lon2);

        List<Station> stationList = shortestPath.findShortestPath(start, end);
        String json = gson.toJson(stationList);

        out.println(json);
    }

}
