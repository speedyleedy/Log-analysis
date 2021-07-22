package loganalysis;

public class IpStatCollector implements LogStatisticCollector{

    public String getKey(AccessLogEntry accessLogEntry) {
        return accessLogEntry.getIpAddress();
    }
}
