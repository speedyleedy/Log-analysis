package loganalysis;

public class UrlStatCollector implements LogStatisticCollector {

    public String getKey(AccessLogEntry accessLogEntry) {
        return accessLogEntry.getPath();
    }
}
