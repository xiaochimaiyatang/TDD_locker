import exception.InvalidTicketException;
import exception.NoEmptyLockerException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SmartLockerRobotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void should_save_in_1st_locker_when_locker1_has_2_locker2_has_1() throws NoEmptyLockerException {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(1, (int) locker1.getAvailableRoom());
    }

    @Test
    public void should_save_in_1st_locker_when_locker1_has_1_locker2_has_1() throws NoEmptyLockerException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(0, (int) locker1.getAvailableRoom());
    }

    @Test
    public void should_save_in_2nd_locker_when_locker1_has_1_locker2_has_2() throws NoEmptyLockerException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertNotNull(ticket);
        assertEquals(1, (int) locker2.getAvailableRoom());
    }

    @Test
    public void should_save_in_1st_locker_when_locker1_has_0_locker2_has_0() throws NoEmptyLockerException {
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        //Then:
        thrown.expect(NoEmptyLockerException.class);
        thrown.expectMessage("fail to save the bag, no Empty Box");

        smartLockerRobot.save(bag);
    }

    @Test
    public void should_get_bag_successfully_when_valid_ticket_from_PrimaryRobot() throws NoEmptyLockerException, InvalidTicketException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(0);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        BasicLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = primaryRobot.save(bag);

        Bag myBag = smartLockerRobot.get(ticket);

        assertEquals(bag, myBag);

    }
}
