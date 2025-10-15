package concurrency.basics;

public class PrintNumber implements Runnable{

    private  Integer number;

    PrintNumber(Integer number){
        this.number = number;
    }


    @Override
    public void run( ) {
        System.out.println("Thread : "+Thread.currentThread().getName()+" Printing Number :"+number);
    }
}
