import homework.taxi.model.Car;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainSpec {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    @Test
    public void should_return_start_price_when_car_run_in_starting_distance() throws Exception {
        provideInput(LocalDateTime.now().toString() + ";2");
        new Car().run();

        assertEquals("cost: 3\n", outContent.toString());
    }

    @Test
    public void should_include_traffic_compensation_given_not_over_speed_at_day_time_after_starting_distance() throws Exception {
        provideInput(Constant.DAY_TIME.toString() + ";2," + Constant.OVER_SPEED_DISTANCE.subtract(BigDecimal.valueOf(0.01d)).doubleValue());
        new Car().run();

        assertEquals("cost: 6\n", outContent.toString());
    }

    @Test
    public void should_include_traffic_compensation_and_at_night_given_over_speed_at_day_time_after_starting_distance() throws Exception {
        provideInput(Constant.NIGHT_TIME.toString() + ";2," + Constant.OVER_SPEED_DISTANCE.doubleValue());
        new Car().run();

        assertEquals("cost: 6\n", outContent.toString());
    }

    private void provideInput(String data) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(data.getBytes());
        System.setIn(inContent);
    }
}
