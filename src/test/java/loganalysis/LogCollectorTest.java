package loganalysis;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import org.testng.annotations.Test;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogCollectorTest
{


    @Test
    public void TopThreeIpAddressesFromFileIntegrationTest() throws IOException {

        Stream<String> stream = Files.lines(
                Paths.get("src/test/java/loganalysis/log.txt"), StandardCharsets.UTF_8);

        List<StatAccumulator> collect = stream
                .map(AccessLogMapper::StringToAccessLog)
                .collect(new LogCollector());

        Map<String, Integer> ipData = collect.get(0).getStats();

        Map<String, Integer> topThreeIp = ipData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        Assert.assertEquals(Optional.of(4), Optional.ofNullable(topThreeIp.get("168.41.191.40")));
        Assert.assertEquals(Optional.of(3), Optional.ofNullable(topThreeIp.get("50.112.00.11")));
        Assert.assertEquals(Optional.of(3), Optional.ofNullable(topThreeIp.get("177.71.128.21")));

    }

    @Test
    public void TopThreeUrlsFromFileIntegrationTest() throws IOException {

        Stream<String> stream = Files.lines(
                Paths.get("src/test/java/loganalysis/log.txt"), StandardCharsets.UTF_8);

        List<StatAccumulator> collect = stream
                .map(AccessLogMapper::StringToAccessLog)
                .collect(new LogCollector());

        Map<String, Integer> urlData = collect.get(1).getStats();

        Map<String, Integer> topThreeUrls = urlData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        Assert.assertEquals(Optional.of(3), Optional.ofNullable(topThreeUrls.get("/hosting/")));
        Assert.assertEquals(Optional.of(2), Optional.ofNullable(topThreeUrls.get("/intranet-analytics/")));
        Assert.assertEquals(Optional.of(2), Optional.ofNullable(topThreeUrls.get("/docs/manage-websites/")));


    }

    @Test
    public void FindMostCommonIpAddressIntegrationTest() throws IOException {

        Stream<String> stream = Files.lines(
                Paths.get("src/test/java/loganalysis/log.txt"), StandardCharsets.UTF_8);

        List<StatAccumulator> collect = stream
                .map(AccessLogMapper::StringToAccessLog)
                .collect(new LogCollector());

        Map<String, Integer> ipData = collect.get(0).getStats();

        Map<String, Integer> mostCommonIp = ipData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        Assert.assertEquals(Optional.of(4), Optional.ofNullable(mostCommonIp.get("168.41.191.40")));

    }

    @Test
    public void FindNumberOfUniqueIpAddresses() throws IOException {

        Stream<String> stream = Files.lines(
                Paths.get("src/test/java/loganalysis/log.txt"), StandardCharsets.UTF_8);

        List<StatAccumulator> collect = stream
                .map(AccessLogMapper::StringToAccessLog)
                .collect(new LogCollector());

        Assert.assertEquals(Optional.of(11), Optional.ofNullable(collect.get(0).getStats().size()));

    }




}
