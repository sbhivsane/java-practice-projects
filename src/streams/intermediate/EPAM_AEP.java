package streams.intermediate;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class EPAM_AEP {

    static void main() {
        String str = "Z B B A A";
        // op-> t

        Optional<Map.Entry<String, Long>> max = Arrays.stream(str.split(" "))
                .collect(Collectors.groupingBy(s -> s,
                        TreeMap::new,
                        Collectors.counting()
                ))
                .entrySet().stream()
                .max((w1, w2) -> w1.getValue().compareTo(w2.getValue()));

        if(max.isPresent())
            System.out.println(max.get().getKey());

    }
}
