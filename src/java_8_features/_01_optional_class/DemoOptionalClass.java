package java_8_features._01_optional_class;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Consumer;

public class DemoOptionalClass {

    static void main() {
        // creation of optional object
       // createOptionalObject();

        // checking is value is present
       // presenceChecking();


        // retriving values from the optional object
       // retrivingValue();

        // transforming value
       // transforming();

        // if value is present then based on that we perform some action
        // actionBased();

        // we call next or till we find the value or else finally it return empty optional
        // alternative();
    }

    private static  void createOptionalObject(){
        try{
            String nonNullUserName = "Sagar";
            String nullUserName = null;

            Optional<String> userName1 = Optional.of(nonNullUserName);
            System.out.println("Created using Optional.of : "+userName1);

            try{
                Optional<String> userName2 = Optional.of(nullUserName); // will throw exception
                System.out.println(userName2);

            } catch (NullPointerException e) {
               e.printStackTrace();
            }

                Optional<String> nullUserName3 = Optional.ofNullable(nullUserName);// will return the empty static optional
                System.out.println("Optional.ofNullable(nullUserName) :"+nullUserName3);



            Optional<Object> emptyOptionalObject = Optional.empty(); // return the empty static optional object

            System.out.println("Created empty Optional.empty() : "+emptyOptionalObject);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void presenceChecking(){
        String nonNullUserName =  "Sagar";
        String nullUserName = null;
        Optional<String> optionalUserName = Optional.of(nonNullUserName);
        Optional<String> optionalNullUserName = Optional.ofNullable(nullUserName);// will return the empty static optional

        System.out.println("optionalUserName.isPresent() : "+optionalUserName.isPresent());
        System.out.println("optionalNullUserName.isEmpty() : "+optionalNullUserName.isEmpty());


    }

    private static void retrivingValue() {
        String nonNullUserName = "Sagar";
        String nullUserName = null;
        Optional<String> optionalUserName = Optional.of(nonNullUserName);
        Optional<String> optionalNullUserName = Optional.ofNullable(nullUserName);

        //1] Get
        System.out.println(" optionalUserName.get() :" + optionalUserName.get());
        try {

            System.out.println(" optionalNullUserName.get() :" + optionalNullUserName.get()); // No Such Element Exception
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            System.out.println("hence we should gaurd the get() call via isPresent() or isEmpty() to avoid this exception");
        }

        //2] orElse // if we have default direct value then we canb use this
        System.out.println("optionalNullUserName.orElse(Default value)" + optionalNullUserName.orElse("Default value"));

        //3] orElseGet // if we have some logic to execute and based on that compute the value then we should do that

        System.out.println("optionalNullUserName.orElseGet(()->\" return computed value\");" + optionalNullUserName.orElseGet(() -> " return computed value"));
        ;

        //4] orElseThrow
        try {

            System.out.println(" optionalNullUserName.orElseThrow() :" + optionalNullUserName.orElseThrow()); // No Such Element Exception
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }

        // 5] orElseThrow(Supplier) if we have to throw some other custome exception then we can use this
        try {

            System.out.println(" optionalNullUserName.orElseThrow() :" + optionalNullUserName.orElseThrow(() -> new NullPointerException("Value Not Present"))); // No Such Element Exception
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private static void transforming(){
        String nonNullUserName = "Sagar";
        String nullUserName = null;
        Optional<String> optionalUserName = Optional.of(nonNullUserName);
        Optional<String> optionalNullUserName = Optional.ofNullable(nullUserName);

        //1] map

        System.out.println("optionalUserName.map((val) -> val.length()) "+optionalUserName.map((val) -> val.length()));

        //2] flatMap
        System.out.println("optionalUserName.flatMap((val) -> Optional.of(val.length())) : "+optionalUserName.flatMap((val) -> Optional.of(val.length())));



        //3] Filter
        System.out.println("optionalUserName.filter((val) -> val.length() < 3) : "+optionalUserName.filter((val) -> val.length() < 3) );
    }

    private static void actionBased(){
        String nonNullUserName = "Sagar";
        String nullUserName = null;
        Optional<String> optionalUserName = Optional.of(nonNullUserName);
        Optional<String> optionalNullUserName = Optional.ofNullable(nullUserName);

        //1] ifPresent(Consumer) // perform some action if the value is presnet
        System.out.println("optionalUserName.ifPresent((val)-> System.out.println(\"value is present\")) : ");
        optionalUserName.ifPresent((val)-> System.out.println("value is present"));

        //2] ifPresentOrElse(Consumer,Runnable) // if value is present then consumer is executed if value is not present then runnable is executed
        Consumer<String> consumer = (val)-> System.out.println(val+" : value is present");
        Runnable runnable =()-> System.out.println("Value Not Present");
        System.out.println("optionalUserName.ifPresentOrElse(consumer,runnable) : ");
        optionalUserName.ifPresentOrElse(consumer,runnable);
        System.out.println("optionalNullUserName.ifPresentOrElse(consumer,runnable) : ");
        optionalNullUserName.ifPresentOrElse(consumer,runnable);





    }

    private static void alternative(){

        String nonNullUserName = "Sagar";
        String nullUserName = null;
        Optional<String> optionalUserName = Optional.of(nonNullUserName);
        Optional<String> optionalNullUserName = Optional.ofNullable(nullUserName);
        Optional<String> get = optionalNullUserName
                                    .or(() -> Optional.empty())
                                    .or(() -> optionalUserName)
                                     .or(() -> Optional.empty());

        System.out.println("We get the value which is not empty first "+get);

    }

}
