import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LockerTest {
    @Rule
    public ExpectedException thrown=ExpectedException.none();

    @Test
    public void should_save_bag_successfully_when_have_empty_box() throws Exception {
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);
        assertEquals("001", ticket.getBoxId());
        assertEquals(1, locker.getAvailableBox());
    }


    @Test
    public void should_get_bag_successfully_when_have_correct_ticket() throws Exception {
        //given
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag myBag = new Bag();
        Ticket ticket = locker.save(myBag);

        //when
        Bag bag = locker.get(ticket);

        //then
        assertEquals(bag, myBag);
        assertEquals(2, locker.getAvailableBox());

    }

    @Test
    public void should_save_unsuccessfully_when_have_no_empty_box() throws Exception {
//        given:
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag myBag1 = new Bag();
        Bag myBag2 = new Bag();
        locker.save(myBag1);
        locker.save(myBag2);
        Bag bag=new Bag();

//        then:
        thrown.expect(Exception.class);
        thrown.expectMessage("fail to save the bag, no Empty Box");

//        when:
        locker.save(bag);
    }
}
