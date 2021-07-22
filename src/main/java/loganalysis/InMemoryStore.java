package loganalysis;

import java.util.HashMap;

public class InMemoryStore {

    private HashMap<String, String> logStore;


    public InMemoryStore(HashMap<String, String> logStore) {
        this.logStore = logStore;
    }


    public HashMap<String, String> getLogStore() {
        return logStore;
    }

}
