import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LockerTest {

    @Test
    public void should_save_bag_success_when_have_empty_box(){
        List<Box> box= Arrays.asList(new Box(),new Box());
        Locker locker=new Locker(box);
        Bag bag=new Bag();
        Ticket ticket = locker.save(bag);
        assertNotNull(ticket);
        assertEquals(1,locker.getAvailableBox());

    }
}