import org.junit.Test;

import static org.junit.Assert.*;

public class LockerTest {
    @Test
    public void should_save_bag_success(){
        Locker locker=new Locker(10);
        Pack pack=new Pack();
        Ticket ticket=locker.save(pack);
        assertNotNull(ticket) ;
    }

    @Test
    public void should_save_bag_fail(){
        Locker locker=new Locker(0);
        Pack pack=new Pack();
        Ticket ticket=locker.save(pack);
        assertNull(ticket) ;
    }

    @Test
    public void should_save_bag_success_and_pickup_success(){
        Locker locker=new Locker(10);
        Pack pack=new Pack();
        Ticket ticket=locker.save(pack);
        Pack getPack=locker.pickup(ticket);
        assertNotNull(getPack) ;
    }

    @Test
    public void should_pickup_success_given_ticket_is_used_once(){
        Locker locker=new Locker(10);
        Pack pack=new Pack();
        Ticket ticket=locker.save(pack);
        Pack getPack=locker.pickup(ticket);
        locker.pickup(ticket);
        Pack getPackAgain=locker.pickup(ticket);
        assertNull(getPackAgain) ;
    }

}
