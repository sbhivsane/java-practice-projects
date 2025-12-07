package misc;

import java.util.Objects;

public class HashCodeAndEquals {
    static void main() {

        Employee e1 = new Employee(1,"sagar",27,"A");
        Employee e2 = new Employee(1,"sagar",27,"B");

        System.out.println(e1==e2);
        System.out.println(e2);
        System.out.println(Objects.hash(1));


    }
}
