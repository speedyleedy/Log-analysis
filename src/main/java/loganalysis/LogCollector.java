package loganalysis;

import sun.rmi.runtime.Log;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class LogCollector implements Collector<AccessLogEntry, HashMap<String, LogStat>, HashMap<String, LogStat>> {

    //change StringBuffer to HashMap<String, LogStat>
    List<LogStatisticCollector> collectors = null;

    public LogCollector() {
        collectors = new ArrayList<>();
        collectors.add(new IpStatCollector());
        collectors.add(new UrlStatCollector());
    }



    @Override
    public Supplier<HashMap<String, LogStat>> supplier() {
        return HashMap<String, LogStat>::new;
    }

    @Override
    public BiConsumer<HashMap<String, LogStat>, AccessLogEntry> accumulator() {

        return (hm, entry) -> {
            for (LogStatisticCollector collector : collectors) {
                collector.collect(hm, entry);
            }
        };
    }



    @Override
    public BinaryOperator<HashMap<String, LogStat>> combiner() {
        return null;
    }

    @Override
    public Function<HashMap<String, LogStat>, HashMap<String, LogStat>> finisher() {
        return (hm) -> hm; //autoboxing
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
