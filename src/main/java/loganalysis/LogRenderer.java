package loganalysis;

import loganalysis.processor.LogProcessor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class LogRenderer {

    public void searchLogs(String inputFle) throws IOException {
        Stream<String> stream = Files.lines(
                Paths.get(inputFle), StandardCharsets.UTF_8);

        LogProcessor processor = new LogProcessor(stream);

        System.out.println("**************************");
        System.out.println("Most top 3 most active IP addresses are");
        System.out.println("**************************");

        processor.getTopThreeIps().forEach(System.out::println);

        System.out.println("**************************");
        System.out.println("The top 3 most visited URLs are");
        System.out.println("**************************");

        processor.getTopThreePaths().forEach(System.out::println);

        System.out.println("**************************");
        System.out.println("Total Number of unique IPs are");
        System.out.println("**************************");

        System.out.println(processor.size());

    }


}
