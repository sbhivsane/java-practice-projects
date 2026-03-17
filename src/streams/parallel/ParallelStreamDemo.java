package streams.parallel;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 *  DEMO FLOW  (run each section one at a time):
 *    SECTION 1  — What is a Parallel Stream?          (~2 min)
 *    SECTION 2  — Real speed difference               (~3 min)
 *    SECTION 3  — Which thread does the work?         (~3 min)
 *    SECTION 4  — The ArrayList trap (common bug)     (~3 min)
 *    SECTION 5  — reduce() identity trap              (~2 min)
 *    SECTION 6  — When NOT to use parallel streams    (~2 min)
 */
public class ParallelStreamDemo {

    // ─── Shared data ──────────────────────────────────────────────────────────


    static class Employee {
        String name;
        int    age;
        String dept;
        double salary;

        Employee(String name, int age, String dept, double salary) {
            this.name   = name;
            this.age    = age;
            this.dept   = dept;
            this.salary = salary;
        }

        @Override public String toString() {
            return name + "(" + dept + ", ₹" + (int) salary + ")";
        }
    }

    static List<Employee> employees = Arrays.asList(
        new Employee("Aarav",   28, "Engineering",  95000),
        new Employee("Priya",   31, "Engineering",  102000),
        new Employee("Rahul",   24, "Sales",         48000),
        new Employee("Sneha",   26, "Engineering",   87000),
        new Employee("Kiran",   35, "Management",   130000),
        new Employee("Divya",   22, "HR",            42000),
        new Employee("Vikram",  29, "Engineering",   91000),
        new Employee("Ananya",  33, "Management",   125000),
        new Employee("Rohan",   27, "Sales",         52000),
        new Employee("Meera",   30, "Engineering",   98000)
    );

    // =========================================================================
    public static void main(String[] args) throws Exception {

        section1_WhatIsParallelStream();
        section2_SpeedComparison();
        section3_WhichThreadDoesTheWork();
        section4_ArrayListTrap();
        section5_ReduceIdentityTrap();
        section6_WhenNotToUseParallel();

    }


    static void section1_WhatIsParallelStream() {



        // ── How many cores does this machine have? ──
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("This machine has  " + cores + "  logical CPU cores\n");

        // ── Sequential stream ──
        System.out.println("  SEQUENTIAL stream — find engineers earning > 80,000:");
        List<String> seqResult = employees.stream()          // sequential
                .filter(e -> e.salary > 80_000)
                .filter(e -> e.dept.equals("Engineering"))
                .map(e -> e.name)
                .collect(Collectors.toList());

        System.out.println("     Result : " + seqResult);
        System.out.println("     Processed by: main thread only\n");

        // ── Parallel stream — literally one word change ──
        System.out.println("   PARALLEL stream — same logic, one word changed:");
        List<String> parResult = employees.parallelStream()  // ← only change!
                .filter(e -> e.salary > 80_000)
                .filter(e -> e.dept.equals("Engineering"))
                .map(e -> e.name)
                .collect(Collectors.toList());

        System.out.println("     Result : " + parResult);
        System.out.println("     Processed by: multiple CPU threads simultaneously");

        // ── isParallel() check ──
        boolean isParallel = employees.parallelStream().isParallel();
        System.out.println("\n  ℹ  parallelStream().isParallel() → " + isParallel);
        System.out.println("  ℹ  stream().isParallel()         → " + employees.stream().isParallel());


    }


    static void section2_SpeedComparison() throws InterruptedException {


        long N = 10_000_000L;

        // ── Sequential ──
        long t1 = System.currentTimeMillis();
        long seqSum = LongStream.rangeClosed(1, N)
                                .filter(n -> n % 2 == 0)
                                .sum();
        long seqMs = System.currentTimeMillis() - t1;

        // ── Parallel ──
        long t2 = System.currentTimeMillis();
        long parSum = LongStream.rangeClosed(1, N)
                                .parallel()
                                .filter(n -> n % 2 == 0)
                                .sum();
        long parMs = System.currentTimeMillis() - t2;

        System.out.printf("    Dataset   : %,d numbers%n", N);
        System.out.printf("    Sequential : %d ms  (sum = %,d)%n", seqMs, seqSum);
        System.out.printf("    Parallel   : %d ms  (sum = %,d)%n", parMs, parSum);
        System.out.printf("    Speedup    : %.1fx faster%n%n", (double) seqMs / parMs);
        System.out.println();
        /*
            "Same result. Parallel just got there much faster.",
            "RULE: Parallel wins when you have LARGE data + CPU-heavy work.",
            "For small data (< ~10,000 items), sequential is often faster",
            "because the overhead of splitting & merging threads costs more than the gain."
        */

    }


    static void section3_WhichThreadDoesTheWork() {


        System.out.println("  ── SEQUENTIAL (only main thread) ──");
        employees.stream()
                .filter(e -> {
                    System.out.printf("     filter  %-10s  → by: %s%n",
                            e.name, Thread.currentThread().getName());
                    return e.salary > 80_000;
                })
                .map(e -> {
                    System.out.printf("     map     %-10s  → by: %s%n",
                            e.name, Thread.currentThread().getName());
                    return e.name;
                })
                .collect(Collectors.toList());

        System.out.println();
        System.out.println("  ── PARALLEL (multiple ForkJoin worker threads) ──");
        employees.parallelStream()
                .filter(e -> {
                    System.out.printf("     filter  %-10s  → by: %s%n",
                            e.name, Thread.currentThread().getName());
                    return e.salary > 80_000;
                })
                .map(e -> {
                    System.out.printf("     map     %-10s  → by: %s%n",
                            e.name, Thread.currentThread().getName());
                    return e.name;
                })
                .collect(Collectors.toList());

        /*
            "Key observations:",
            "  1. Sequential → ALL lines say 'main'",
            "  2. Parallel   → lines say 'ForkJoinPool-1-worker-N' (different threads)",
            "  3. Same thread handles BOTH filter AND map for a given employee",
            "  4. Output order is unpredictable in parallel — that's normal!"
        */

    }


    static void section4_ArrayListTrap() throws InterruptedException {


        System.out.println("  ── WRONG: ArrayList + forEach in parallel stream ──");
        for (int i = 1; i <= 5; i++) {
            List<Integer> buggyList = new ArrayList<>();   // NOT thread-safe!

            IntStream.rangeClosed(1, 1000)
                     .parallel()
                     .forEach(buggyList::add);             // RACE CONDITION!

            System.out.printf("     Run %d → expected 1000, got %d  %s%n",
                    i, buggyList.size(),
                    buggyList.size() == 1000 ? "✓" : "✗ LOST " + (1000 - buggyList.size()) + " elements!");
        }

        /*
            "WHY does this happen?",
            "Two threads see the same slot in ArrayList as 'empty' at the same time.",
            "Both write to it. One overwrites the other. Element is LOST.",
            "No exception. No warning. Just silently wrong data. Very dangerous in production!"
       */

        System.out.println("\n  ── CORRECT: Use Collectors.toList() ──");
        for (int i = 1; i <= 5; i++) {
            List<Integer> safeList = IntStream.rangeClosed(1, 1000)
                    .parallel()
                    .boxed()
                    .collect(Collectors.toList());         // ✓ always safe

            System.out.printf("     Run %d → expected 1000, got %d  ✓%n",
                    i, safeList.size());
        }

        /*
            "WHY is Collectors.toList() safe?",
            "Each thread builds its OWN separate list internally.",
            "At the end, all mini-lists are MERGED into one.",
            "No two threads ever touch the same list object. Zero conflict."
        */

        System.out.println("\n  ── ALSO CORRECT: CopyOnWriteArrayList ──");
        List<Integer> cowList = new CopyOnWriteArrayList<>();
        IntStream.rangeClosed(1, 1000).parallel().forEach(cowList::add);
        System.out.println("     CopyOnWriteArrayList size → " + cowList.size() + "  ✓");
        System.out.println();

    }


    static void section5_ReduceIdentityTrap() {

        /*
            "reduce() takes two things: an identity value and a combiner function.",
            "In parallel, each thread STARTS with the identity value.",
            "So the identity MUST be the neutral element for the operation.",
            "0 for addition, 1 for multiplication."
        */

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("  Numbers: " + numbers + "  (expected sum = 36)\n");

        // ── Correct ──
        int correct = numbers.parallelStream()
                             .reduce(0, Integer::sum);     // identity = 0 ✓
        System.out.println("  ✓  .reduce(0, Integer::sum)   → " + correct + "  (correct!)");

        // ── Wrong ──
        int wrong = numbers.parallelStream()
                           .reduce(100, Integer::sum);     // identity = 100 ✗
        System.out.println("  ✗  .reduce(100, Integer::sum) → " + wrong
                + "  (WRONG! Each thread added 100 as its starting value)");

        int threads = Runtime.getRuntime().availableProcessors();
        System.out.println("\n  💡  With " + threads + " threads, identity 100 was added "
                + threads + " times → extra " + (threads * 100) + " added to the result");

        /*
            "This bug is SILENT — no exception, just wrong numbers.",
            "In sequential mode reduce(100,...) only adds 100 once so it works fine.",
            "This is why it only breaks in parallel — extremely hard to catch in testing!"
        */

    }


    static void section6_WhenNotToUseParallel() throws InterruptedException {

        //"Case 1: Small datasets — parallel overhead costs MORE than it saves.";

        List<Integer> small = List.of(1, 2, 3, 4, 5);

        long t1 = System.nanoTime();
        int seqResult = small.stream().mapToInt(Integer::intValue).sum();
        long seqNs = System.nanoTime() - t1;

        long t2 = System.nanoTime();
        int parResult = small.parallelStream().mapToInt(Integer::intValue).sum();
        long parNs = System.nanoTime() - t2;

        System.out.printf("  5 elements: sequential=%d ns,  parallel=%d ns  — sequential wins!%n%n",
                seqNs, parNs);

        // "Case 2: IO-bound work — threads just sit and wait. Parallelism wasted.";

        System.out.println("  Simulating a DB call (50ms) per employee in parallel stream...");
        long t3 = System.currentTimeMillis();
        employees.parallelStream()
                 .map(e -> simulateDbCall(e.name))
                 .collect(Collectors.toList());
        System.out.println("  Parallel stream + IO-bound: " + (System.currentTimeMillis() - t3) + " ms");
        System.out.println("  (Threads blocked waiting. CPU cores sat idle. No real benefit.)\n");
        System.out.println();
        /*
            "For IO-bound parallel work, use CompletableFuture with a custom thread pool.",
            "Case 3: When you need strict output ORDER — use sequential or forEachOrdered().",
            "",
            "  USE parallel streams when:",
            "    ✓  Large dataset  (100,000+ elements)",
            "    ✓  CPU-intensive operations  (calculations, parsing, sorting)",
            "    ✓  Stateless operations  (filter, map — no shared state)",
            "    ✓  Order doesn't matter",
            "",
            "  AVOID parallel streams when:",
            "    ✗  Small dataset",
            "    ✗  IO-bound work  (DB, HTTP, file)",
            "    ✗  You need ordered output",
            "    ✗  Adding to a shared non-thread-safe collection"
        */


    }

    static String simulateDbCall(String name) {
        try { Thread.sleep(50); } catch (InterruptedException ignored) {}
        return name.toUpperCase();
    }


}