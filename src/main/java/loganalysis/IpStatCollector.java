package loganalysis;

import java.util.HashMap;
import java.util.List;

public class IpStatCollector implements LogStatisticCollector{
    @Override
    public void collect(HashMap<String, LogStat> map, AccessLogEntry entry) {


        LogStat l = map.get(entry.getIpAddress());
        if (l == null){
            l = new LogStat();
            l.setIpAddress(entry.getIpAddress());
            l.setCount(0);
            l.setType("IP");
        }
        l.setCount(l.getCount() + 1 );
        map.put(l.getIpAddress(), l);
    }
}
