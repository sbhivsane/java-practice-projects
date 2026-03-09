package concurrency.problems.ticket_booking_system;

import java.util.concurrent.atomic.AtomicInteger;

public class ReservationSystem{

    AtomicInteger availableSeats=new AtomicInteger();

        ReservationSystem( Integer totalSeats){
            this.availableSeats.set(totalSeats);
        }

        public void bookSeat(String user, Integer numberOfSeats){
            if(availableSeats.get()>=numberOfSeats){
                availableSeats.getAndAdd(-numberOfSeats);
                System.out.println(user+" Booked : "+numberOfSeats+" Seats");
            }else{
                System.out.println(numberOfSeats+" No Of Seats Not Avialable to Book");
            }
        }

        public Integer getAvailableSeats(){
            return availableSeats.get();
        }


}
