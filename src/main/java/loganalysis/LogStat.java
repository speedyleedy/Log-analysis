package loganalysis;

public class LogStat {

    private final String ipAddress;
    private final String path;
    private final String type;
    private int count;

    public LogStat(String ipAddress, String path, String type) {
        this.ipAddress = ipAddress;
        this.path = path;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getPath() {
        return path;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        this.count++;
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
