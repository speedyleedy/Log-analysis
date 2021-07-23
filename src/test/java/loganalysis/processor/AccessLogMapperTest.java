package loganalysis.processor;

import loganalysis.collector.AccessLogEntry;
import loganalysis.processor.AccessLogMapper;
import org.testng.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccessLogMapperTest {

    @Test
    public void extractValuesFromLogFile() {
        String testValue = "50.112.00.11 - admin [11/Jul/2018:17:31:56 +0200] \"GET /asset.js HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6\"";

        AccessLogEntry entry = AccessLogMapper.StringToAccessLog(testValue);

        Assert.assertEquals("50.112.00.11", entry.getIpAddress());
        Assert.assertEquals("/asset.js", entry.getPath());

        testValue = "50.112.00.44 - admin [11/Jul/2018:17:31:56 +0200] \"POST /faces.js HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.6 (KHTML, like Gecko) Chrome/20.0.1092.0 Safari/536.6\"";

        entry = AccessLogMapper.StringToAccessLog(testValue);

        Assert.assertEquals("50.112.00.44", entry.getIpAddress());
        Assert.assertEquals("/faces.js", entry.getPath());

    }

}
