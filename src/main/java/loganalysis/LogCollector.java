package loganalysis;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.EnumSet;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class LogCollector implements Collector<AccessLogEntry, List<StatAccumulator>, List<StatAccumulator>> {

//    //change StringBuffer to HashMap<String, LogStat>
//    List<LogStatisticCollector> collectors = null;
//
//    public LogCollector() {
//        collectors = new ArrayList<>();
//        collectors.add(new IpStatCollector());


    @Override
    public Supplier<List<StatAccumulator>> supplier() {
        return () -> Arrays.asList(new StatAccumulator(new IpStatCollector()), new StatAccumulator(new UrlStatCollector()));
    }

    @Override
    public BiConsumer<List<StatAccumulator>, AccessLogEntry> accumulator() {
        return (hm, entry) -> hm.forEach(processor -> processor.processLine(entry));
    }

    @Override
    public BinaryOperator<List<StatAccumulator>> combiner() {
        return null;
    }

    @Override
    public Function<List<StatAccumulator>, List<StatAccumulator>> finisher() {
        return hm -> hm;
    }


    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
