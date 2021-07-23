package loganalysis;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

public class LogStatisticTest {

    @Test
    public void checkThatIPStatsAccumulate(){

        StatAccumulator accumulator = new StatAccumulator(new IpStatCollector());
        AccessLogEntry entry = new AccessLogEntry();
        entry.setIpAddress("111.111.11.11");
        entry.setPath("/testPath");

        accumulator.processLine(entry);

        Assert.assertEquals(Optional.of(1), Optional.ofNullable(accumulator.getStats().get("111.111.11.11")));

        //process same entry, confirm count is 2
        accumulator.processLine(entry);
        Assert.assertEquals(Optional.of(2), Optional.ofNullable(accumulator.getStats().get("111.111.11.11")));

    }

    @Test
    public void checkThatURLStatsAccumulate(){

        StatAccumulator accumulator = new StatAccumulator(new UrlStatCollector());
        AccessLogEntry entry = new AccessLogEntry();
        entry.setIpAddress("111.111.11.11");
        entry.setPath("/testPath");

        accumulator.processLine(entry);

        Assert.assertEquals(Optional.of(1), Optional.ofNullable(accumulator.getStats().get("/testPath")));

        //process same entry, confirm count is 2
        accumulator.processLine(entry);
        Assert.assertEquals(Optional.of(2), Optional.ofNullable(accumulator.getStats().get("/testPath")));

    }


}
