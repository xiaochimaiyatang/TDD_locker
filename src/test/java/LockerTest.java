import exception.InvalidTicketException;
import exception.NoEmptyLockerException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LockerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void should_save_bag_successfully_when_have_empty_box() throws NoEmptyLockerException {
        Locker locker = new Locker(4);
        Bag bag = new Bag();
        Ticket ticket = locker.save(bag);
        assertNotNull(ticket);
        assertEquals(3, (int) locker.getAvailableRoom());
    }


    @Test
    public void should_get_bag_successfully_when_have_correct_ticket() throws NoEmptyLockerException, InvalidTicketException {
        //given
        Locker locker = new Locker(2);
        Bag myBag = new Bag();
        Ticket ticket = locker.save(myBag);

        //when
        Bag bag = locker.get(ticket);

        //then
        assertEquals(bag, myBag);

    }

    @Test
    public void should_save_unsuccessfully_when_have_no_empty_box() throws NoEmptyLockerException {
//        given:
        Locker locker = new Locker(2);
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
        Locker locker = new Locker(2);
        Bag bag = new Bag();
        locker.save(bag);
        Ticket wrongTicket = new Ticket();

//        then:
        thrown.expect(InvalidTicketException.class);
        thrown.expectMessage("fail to get the bag, invalid ticket");
//        when:
        locker.get(wrongTicket);
    }

    @Test
    public void should_fail_get_bag_when_use_ticket_twice() throws NoEmptyLockerException, InvalidTicketException {
        //given
        Locker locker = new Locker(2);
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
