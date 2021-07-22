package loganalysis;

import static org.junit.Assert.assertTrue;

import org.testng.annotations.BeforeMethod;
import org.testng.Assert;

import org.testng.annotations.Test;
import sun.rmi.runtime.Log;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.*;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AppTest 
{

    InMemoryStore logStore;

    @BeforeMethod
    public void setUp() {
        HashMap<String, String> logStore = new HashMap<>();
    }

    @Test
    public void testTest() throws IOException {

        Stream<String> stream = Files.lines(
                Paths.get("C:\\dev\\loganalysis\\log.txt"), StandardCharsets.UTF_8);

        LogCollector a = new LogCollector();
        Map<String, LogStat> logs = stream.map(AccessLogMapper::StringToAccessLog).collect(a);



        for (String key : logs.keySet()) {
            System.out.println("s " + key + logs.get(key));

        }

        Assert.assertEquals(1,1);
    }




}
