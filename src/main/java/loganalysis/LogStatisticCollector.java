package loganalysis;

import java.util.Map;

public interface LogStatisticCollector {

    default void collect(Map<String, Integer> map, AccessLogEntry entry) {
        String key = this.getKey(entry);
        Integer count = map.get(key);

        if (count == null) {
            map.put(key, 1);
        } else {
            map.put(key, count + 1);
        }
    }

    String getKey(AccessLogEntry entry);
}
