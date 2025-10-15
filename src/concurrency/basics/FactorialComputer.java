package concurrency.basics;

import java.math.BigInteger;

public class FactorialComputer implements Runnable{

    private long number;
    private  BigInteger factorial=BigInteger.valueOf(0);
    FactorialComputer(long number){
        this.number=number;
    }

    @Override
    public void run() {
        //Business Logic
        factorial = factorial(number);
    }

    BigInteger factorial(long n){
        BigInteger ans = BigInteger.ONE;
        for(long i=2; i<=n; i++){
            ans = ans.multiply(BigInteger.valueOf(i));
        }
        return ans;
    }

    public void getFactorial(){
        System.out.println("Factorial of : "+this.number+"  is : "+this.factorial);
    }
}
