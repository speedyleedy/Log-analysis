package loganalysis.processor;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import loganalysis.collector.StatAccumulator;
import org.testng.Assert;

import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogCollectorTest {

    private Stream<String> stream;
    private List<StatAccumulator> collect;

    @Before
    public void setup() throws IOException {
        stream = Files.lines(
                Paths.get("src/test/java/loganalysis/log.txt"), StandardCharsets.UTF_8);

        collect = stream
                .map(AccessLogMapper::StringToAccessLog)
                .collect(new LogCollector());

    }

    @Test
    public void TopThreeIpAddressesFromFileIntegrationTest() throws IOException {


        Map<String, Integer> ipData = collect.get(0).getStats();

        Map<String, Integer> topThreeIp = ipData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        assertThat(topThreeIp.get("168.41.191.40"), is(4));
        assertThat(topThreeIp.get("50.112.00.11"), is(3));
        assertThat(topThreeIp.get("177.71.128.21"), is(3));

    }

    @Test
    public void TopThreeUrlsFromFileIntegrationTest() throws IOException {


        Map<String, Integer> urlData = collect.get(1).getStats();

        Map<String, Integer> topThreeUrls = urlData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        assertThat(topThreeUrls.get("/hosting/"), is(3));
        assertThat(topThreeUrls.get("/intranet-analytics/"), is(2));
        assertThat(topThreeUrls.get("/docs/manage-websites/"), is(2));


    }

    @Test
    public void FindMostCommonIpAddressIntegrationTest() throws IOException {


        Map<String, Integer> ipData = collect.get(0).getStats();

        Map<String, Integer> mostCommonIp = ipData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        assertThat(mostCommonIp.get("168.41.191.40"), is(4));

    }

    @Test
    public void FindNumberOfUniqueIpAddresses() throws IOException {


        assertThat(collect.get(0).getStats().size(), is(11));

    }


}
