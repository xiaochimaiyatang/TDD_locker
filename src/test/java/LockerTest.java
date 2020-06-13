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


}
