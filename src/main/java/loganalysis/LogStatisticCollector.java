package loganalysis;

import java.util.HashMap;
import java.util.List;

public interface LogStatisticCollector {

    void collect(HashMap<String, LogStat> list, AccessLogEntry entry);

}
