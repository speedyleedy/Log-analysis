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

import java.util.stream.Stream;

public class AppTest 
{

    @BeforeMethod
    public void setUp() {
        HashMap<String, String> logStore = new HashMap<>();
    }

    @Test
    public void testTest() throws IOException {

        Stream<String> stream = Files.lines(
                Paths.get("C:\\dev\\loganalysis\\log.txt"), StandardCharsets.UTF_8);

        List<StatAccumulator> collect = stream
                .map(AccessLogMapper::StringToAccessLog)
                .collect(new LogCollector());

        for (StatAccumulator key : collect){
           System.out.println(key + " " + key.getStats());

       }



        Assert.assertEquals(1,1);
    }




}
