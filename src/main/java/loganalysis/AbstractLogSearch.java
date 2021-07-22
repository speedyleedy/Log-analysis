package loganalysis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AbstractLogSearch {

    List<StatAccumulator> getData(Stream<String> stream) {

        return  stream
                .map(AccessLogMapper::StringToAccessLog)
                .collect(new LogCollector());
    }


    void getTopThreeIps(List<StatAccumulator> collect) {

        Map<String, Integer> ipData = collect.get(0).getStats();

        Map<String, Integer> topThreeIp = ipData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        topThreeIp.keySet().stream()
                .forEach(System.out::println);


    }

    void getTopThreePaths(List<StatAccumulator> collect) {

        Map<String, Integer> pathData = collect.get(1).getStats();

        Map<String, Integer> topThreePaths = pathData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));

        topThreePaths.keySet().stream()
                .forEach(System.out::println);

    }


}
