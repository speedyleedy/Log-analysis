package loganalysis.processor;

import loganalysis.collector.AccessLogEntry;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessLogMapper {

    public static AccessLogEntry StringToAccessLog(String line) {
        /*For a given line in the file, extract the AccessLogMapper object, IP address is the first string
        Use regext to get the URL
        */
        Pattern pattern = Pattern.compile("(?<=GET |POST |PUT |HEAD |DELETE |PATCH |OPTIONS).*?\\s");
        AccessLogEntry al = new AccessLogEntry();
        String[] splitted = line.split(" ");
        al.setIpAddress(splitted[0]);

        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            al.setPath(matcher.group(0).trim());
        }
        return al;
    }

}
