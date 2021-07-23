package loganalysis.processor;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.*;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LogProcessorTest {

    private Stream<String> stream;

    @Before
    public void setup() throws IOException {
        stream = Files.lines(
                Paths.get("src/test/java/loganalysis/log.txt"), StandardCharsets.UTF_8);
    }

    @Test
    public void checkThatTopThreeUrlsAreReturned() {

        LogProcessor processor = new LogProcessor(stream);
        List<String> testList = processor.getTopThreePaths();
        assertThat(testList, contains("/hosting/", "/intranet-analytics/", "/docs/manage-websites/"));
    }

    @Test
    public void checkThatTopThreeIpssAreReturned() {

        LogProcessor processor = new LogProcessor(stream);
        List<String> testList = processor.getTopThreeIps();
        assertThat(testList, contains("168.41.191.40", "50.112.00.11", "177.71.128.21"));
    }

    @Test
    public void countTheNumerOfUniqueIps() {

        LogProcessor processor = new LogProcessor(stream);
        Long uniqueIpAddresses = processor.size();
        assertThat(uniqueIpAddresses, is(11L));
    }

}
