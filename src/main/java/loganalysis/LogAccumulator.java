package loganalysis;

import java.util.HashMap;
import java.util.Map;

public class LogAccumulator {
    private Map<String, String> context;

    public LogAccumulator(){
        context = new HashMap<>();

    }

    public Map<String, String> getContext(){
        return context;
    }

    public void  setContext(Map<String, String> context){
        this.context = context;
    }


}
