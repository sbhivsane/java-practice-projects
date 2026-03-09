package misc;

import java.util.Objects;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String dept;

    public Employee(int id,String name,int age,String dept) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.dept = dept;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Employee other = (Employee) obj;

        return this.id == other.id
                && Objects.equals(this.name, other.name)
                && Objects.equals(this.dept, other.dept);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id,this.name,this.age,this.dept);
    }
}
