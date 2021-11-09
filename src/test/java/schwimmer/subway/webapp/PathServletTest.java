package schwimmer.subway.webapp;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PathServletTest {

    @Test
    public void doGet() throws IOException {

        // given
        PathServlet servlet = new PathServlet();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter out = mock(PrintWriter.class);
        doReturn(out).when(response).getWriter();
        doReturn("40.5876923").when(request).getParameter("lat1");
        doReturn("-73.9543462").when(request).getParameter("lon1");
        doReturn("40.7701288").when(request).getParameter("lat2");
        doReturn("-73.9946557").when(request).getParameter("lon2");

        // when
        servlet.doGet(request, response);

        // then
        verify(out).println(anyString());
    }


}