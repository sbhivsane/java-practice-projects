package generics;

import java.util.ArrayList;
import java.util.List;

public class GenericsExample {
    static void main() {

        List<Animal> animals = new ArrayList<>();
        List<Dog> dogs = new ArrayList<>();
         //animals = dogs; // give us compilation error;
        animals.add(new Dog("a",4));
        animals.add(new Bird("b",2));
        //printAnimal(dogs); gives compile time error;
        printGenericAnimal(dogs);
        printGenericAnimal(animals);
        // checkIsLeaving(dogs)// gives compile time error;
        List<LivingCreature> livingCreatures = new ArrayList<>();
        checkIsLeaving(livingCreatures);
        checkIsLeaving(animals);

        int[] arr ={1,2};
        SampleArray<Integer> sampleArray = new SampleArray<>();

    }


    private static  void printAnimal(List<Animal> animals){
        for (Animal a : animals){
            System.out.println(a);
        }
    }
    // upper bound
    private static <E> void printGenericAnimal(List<? extends Animal> animals){
       // animal and its sub types
        for (Animal a : animals){
            System.out.println(a);
        }
    }

    // lower bound
    private static <E> void checkIsLeaving(List<? super Animal> animals){
        // animal and its parent types;
    }

}
