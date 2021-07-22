package loganalysis;

public class AccessLogMapper {
    private String ipAddress;
    private String path;

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public static AccessLogEntry StringToAccessLog (String line){
        AccessLogEntry al = new AccessLogEntry();
        String[] splitted = line.split(" ");
        al.setIpAddress(splitted[0]);
        al.setPath("TEST");
        return al;
    }

}
