package loganalysis.processor;

import loganalysis.collector.AccessLogEntry;
import loganalysis.collector.IpStatCollector;
import loganalysis.collector.StatAccumulator;
import loganalysis.collector.UrlStatCollector;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class LogCollector implements Collector<AccessLogEntry, List<StatAccumulator>, List<StatAccumulator>> {

    /*Collector takes a supplier (a statAccumulator) and creates objects for each of my collectors.
     * the accumulator method is called for each stream which will process each line twice, once for the IP collector
    * and once for the URL collector
    * */

    @Override
    public Supplier<List<StatAccumulator>> supplier() {
        return () -> Arrays.asList(new StatAccumulator(new IpStatCollector()), new StatAccumulator(new UrlStatCollector()));
    }

    @Override
    public BiConsumer<List<StatAccumulator>, AccessLogEntry> accumulator() {
        return (l, entry) -> l.forEach(processor -> processor.processLine(entry));
    }

    @Override
    public BinaryOperator<List<StatAccumulator>> combiner() {
        /*not using combiner. this is for multiple threads.*/
        return null;
    }

    @Override
    public Function<List<StatAccumulator>, List<StatAccumulator>> finisher() {
        return l -> l;
    }


    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}
