package loganalysis;

import java.util.HashMap;
import java.util.Map;

public class StatAccumulator {

    private final LogStatisticCollector collector;
    private final Map<String, Integer> stats = new HashMap();

    public StatAccumulator(LogStatisticCollector collector) {
        this.collector = collector;
    }

    public LogStatisticCollector getCollector() {
        return collector;
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public void processLine(AccessLogEntry entry) {
        this.collector.collect(stats, entry);
    }

}
