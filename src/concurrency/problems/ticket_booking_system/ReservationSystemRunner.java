package concurrency.problems.ticket_booking_system;

public class ReservationSystemRunner {

    static void main() throws InterruptedException {
       ReservationSystem reservationSystem= new ReservationSystem(10);


       Thread t1 = new Thread(()->reservationSystem.bookSeat("user1",1));
        Thread t2 = new Thread(()->reservationSystem.bookSeat("user2",3));
        Thread t3 = new Thread(()->reservationSystem.bookSeat("user3",1));
        Thread t4 = new Thread(()->reservationSystem.bookSeat("user4",6));
        Thread t5 = new Thread(()->reservationSystem.bookSeat("user5",3));


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        System.out.println(reservationSystem.getAvailableSeats());


    }
}
