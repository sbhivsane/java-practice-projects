package misc;

import java.util.HashSet;
import java.util.Objects;

public class HashCodeAndEquals {
    static void main() {

        // == check for reffrence are same or not
        // if override equals check if values of object are same or not

        Employee e1 = new Employee(1,"sagar",27,"A");
        Employee e2 = new Employee(1,"sagar",27,"A");

        System.out.println(e1.equals(e2));

        HashSet<Employee> hashSet = new HashSet<>();
        hashSet.add(e1);
        hashSet.add(e2);
        System.out.println(hashSet.size()); // 2 here hash set size is 2 :(
        // Like from our example e1 and e2 are equal object and
        // as per hashset defination it does not store duplicate values
        // still our hasset size is 2 i.e it stored duplicate values  to avoid such type
        // of inconsistency we should override hascode method whenever we override equals method


        // after overriding hascode method
        System.out.println(hashSet.size()); // size is 1






    }
}
