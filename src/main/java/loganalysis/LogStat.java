package loganalysis;

import java.util.HashMap;
import java.util.Map;

public class LogStat {
    private String ipAddress;
    private String path;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private Integer count;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "LogStat{" +
                "ipAddress='" + ipAddress + '\'' +
                ", path='" + path + '\'' +
                ", type='" + type + '\'' +
                ", count=" + count +
                '}';
    }
}
