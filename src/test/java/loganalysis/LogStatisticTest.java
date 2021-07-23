package loganalysis;

import loganalysis.collector.AccessLogEntry;
import loganalysis.collector.IpStatCollector;
import loganalysis.collector.StatAccumulator;
import loganalysis.collector.UrlStatCollector;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogStatisticTest {

    @Test
    public void IPStatsAccumulateWillAccumulate() {
        StatAccumulator accumulator = new StatAccumulator(new IpStatCollector());
        AccessLogEntry entry = new AccessLogEntry();
        entry.setIpAddress("111.111.11.11");
        entry.setPath("/testPath");

        accumulator.processLine(entry);
        assertThat(accumulator.getStats().get("111.111.11.11"), is(1));

        accumulator.processLine(entry);
        assertThat(accumulator.getStats().get("111.111.11.11"), is(2));
    }

    @Test
    public void checkThatIPStatsAccumulate() {

        StatAccumulator accumulator = new StatAccumulator(new IpStatCollector());
        AccessLogEntry entry = new AccessLogEntry();
        entry.setIpAddress("111.111.11.11");
        entry.setPath("/testPath");

        accumulator.processLine(entry);

        assertThat(accumulator.getStats().get("111.111.11.11"), is(1));
        accumulator.processLine(entry);
        assertThat(accumulator.getStats().get("111.111.11.11"), is(2));

    }

    @Test
    public void checkThatURLStatsAccumulate() {

        StatAccumulator accumulator = new StatAccumulator(new UrlStatCollector());
        AccessLogEntry entry = new AccessLogEntry();
        entry.setIpAddress("111.111.11.11");
        entry.setPath("/testPath");

        accumulator.processLine(entry);

        assertThat(accumulator.getStats().get("/testPath"), is(1));

        //process same entry, confirm count is 2
        accumulator.processLine(entry);
        assertThat(accumulator.getStats().get("/testPath"), is(2));


    }


}
