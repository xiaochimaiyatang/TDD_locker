import exception.InvalidTicketException;
import exception.NoEmptyLockerException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LockerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_save_bag_successfully_when_have_empty_box() throws NoEmptyLockerException {
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);
        assertEquals("001", ticket.getBoxId());
        assertEquals(1L, (long) locker.getAvailableBox());
    }


    @Test
    public void should_get_bag_successfully_when_have_correct_ticket() throws NoEmptyLockerException, InvalidTicketException {
        //given
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag myBag = new Bag();
        Ticket ticket = locker.save(myBag);

        //when
        Bag bag = locker.get(ticket);

        //then
        assertEquals(bag, myBag);
        assertEquals(2L, (long) locker.getAvailableBox());

    }

    @Test
    public void should_save_unsuccessfully_when_have_no_empty_box() throws NoEmptyLockerException {
//        given:
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag myBag1 = new Bag();
        Bag myBag2 = new Bag();
        locker.save(myBag1);
        locker.save(myBag2);
        Bag bag = new Bag();

//        then:
        thrown.expect(NoEmptyLockerException.class);
        thrown.expectMessage("fail to save the bag, no Empty Box");

//        when:
        locker.save(bag);
    }

    @Test
    public void should_get_bag_unsuccessfully_when_have_wrong_ticket() throws NoEmptyLockerException, InvalidTicketException {
//        given:
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag bag = new Bag();
        locker.save(bag);
        Ticket wrongTicket = new Ticket("wrong id");

//        then:
        thrown.expect(InvalidTicketException.class);
        thrown.expectMessage("fail to get the bag, invalid ticket");
//        when:
        locker.get(wrongTicket);
    }

    @Test
    public void should_fail_get_bag_when_use_ticket_twice() throws NoEmptyLockerException, InvalidTicketException {
        //given
        List<Box> box = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker = new Locker(box);
        Bag myBag = new Bag();
        Ticket ticket = locker.save(myBag);
        locker.get(ticket);


        //then
        thrown.expect(InvalidTicketException.class);
        thrown.expectMessage("fail to get the bag, invalid ticket");

        //when
        locker.get(ticket);

    }
}
