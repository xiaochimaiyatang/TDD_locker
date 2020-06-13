import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LockerTest {

    @Test
    public void should_save_bag_successfully_when_have_empty_box() {
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);
        assertEquals("001", ticket.getBoxId());
        assertEquals(1, locker.getAvailableBox());
    }
}
