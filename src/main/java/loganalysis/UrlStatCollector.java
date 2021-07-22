package loganalysis;

import java.util.HashMap;

public class UrlStatCollector implements LogStatisticCollector{
    @Override
    public void collect(HashMap<String, LogStat> map, AccessLogEntry entry) {

        LogStat l = map.get(entry.getPath());
        if (l == null){
            l = new LogStat();
            l.setPath(entry.getPath());
            l.setCount(0);
            l.setType("URL");
        }
        l.setCount(l.getCount() + 1 );
        map.put(l.getPath(), l);
    }
}
