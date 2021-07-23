package loganalysis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class LogSearch extends AbstractLogSearch {

    public void searchLogs(String inputFle) throws IOException {

        Stream<String> stream = Files.lines(
                Paths.get(inputFle), StandardCharsets.UTF_8);

        List<StatAccumulator> collect = getData(stream);

        System.out.println("**************************");
        System.out.println("Most top 3 most active IP addresses are : ");
        System.out.println("**************************");

        getTopThreeIps(collect);

        System.out.println("**************************");
        System.out.println("The top 3 most visited URLs are : ");
        System.out.println("**************************");

        getTopThreePaths(collect);

        System.out.println("**************************");
        System.out.println("Total Number of unique IPs are : " + collect.get(0).getStats().size());
        System.out.println("**************************");

    }


}
