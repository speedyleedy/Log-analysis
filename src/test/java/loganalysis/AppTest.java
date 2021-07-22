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

public class AppTest 
{

    @BeforeMethod
    public void setUp() {

    }

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
    public void extractValuesFromLogFile(){
        String testValue = "50.112.00.11 - admin [11/Jul/2018:17:31:56 +0200] \"GET /asset.js HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6\"";

        AccessLogEntry entry = AccessLogMapper.StringToAccessLog(testValue);

        Assert.assertEquals("50.112.00.11", entry.getIpAddress());
        Assert.assertEquals("/asset.js", entry.getPath());

        testValue = "50.112.00.44 - admin [11/Jul/2018:17:31:56 +0200] \"POST /faces.js HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6\"";

        entry = AccessLogMapper.StringToAccessLog(testValue);

        Assert.assertEquals("50.112.00.44", entry.getIpAddress());
        Assert.assertEquals("/faces.js", entry.getPath());

    }





}
