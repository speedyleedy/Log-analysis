package loganalysis.processor;

import loganalysis.collector.StatAccumulator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogProcessor {

    private final List<StatAccumulator> results;

    public LogProcessor(Stream<String> stream) {
        results = stream
                .map(AccessLogMapper::StringToAccessLog)
                .collect(new LogCollector());
    }

    public List<String> getTopThreeIps() {
        Map<String, Integer> ipData = results.get(0).getStats();
        return getTopThree(ipData);
    }

    public List<String> getTopThreePaths() {
        Map<String, Integer> pathData = results.get(1).getStats();
        return getTopThree(pathData);
    }

    public long size() {
        return results.get(0).getStats().size();
    }

    private List<String> getTopThree(Map<String, Integer> pathData) {

        return pathData.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

    }
}
