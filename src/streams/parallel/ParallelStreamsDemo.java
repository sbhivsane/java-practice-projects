package streams.parallel;

import java.util.stream.LongStream;

public class ParallelStreamsDemo {

    static void main() {

        System.out.println("avialable COre: "+Runtime.getRuntime().availableProcessors());

        long sum = LongStream.rangeClosed(1, 10).sum(); // sequential Stream

        long sum1 = LongStream.rangeClosed(1, 10).parallel().sum(); // sequential Stream




    }
    public void performanceComparision(){
        ;
    }
}
