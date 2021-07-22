package loganalysis;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern pattern = Pattern.compile("(?<=GET |POST |PUT |HEAD |DELETE |PATCH |OPTIONS).*?\\s");
        AccessLogEntry al = new AccessLogEntry();
        String[] splitted = line.split(" ");
        al.setIpAddress(splitted[0]);

        Matcher matcher = pattern.matcher(line);
        if (matcher.find()){
            al.setPath(matcher.group(0).trim());
        }
        return al;
    }

}
