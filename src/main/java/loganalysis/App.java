package loganalysis;

import java.io.IOException;

public class App 
{
    public static void main( String[] args ) throws IOException {

        String[] logFile = new String[1];
        if (args.length == 0){
            System.out.println("Please specify a log file");
            return;
        } else{
            logFile[0] = args[0];
        }

        LogSearch l = new LogSearch();
        l.searchLogs(logFile[0]);

    }
}
