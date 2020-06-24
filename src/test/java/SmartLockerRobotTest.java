import exception.InvalidTicketException;
import exception.NoEmptyLockerException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SmartLockerRobotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void should_save_in_1st_locker_when_locker1_has_2_locker2_has_1() throws NoEmptyLockerException {
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"));
        Locker locker2 = new Locker(boxes2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertEquals("001", ticket.getBoxId());
        assertEquals(1, (int) locker1.getAvailableBox());
    }

    @Test
    public void should_save_in_1st_locker_when_locker1_has_1_locker2_has_1() throws NoEmptyLockerException {
        List<Box> boxes1 = Arrays.asList(new Box("001"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"));
        Locker locker2 = new Locker(boxes2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertEquals("001", ticket.getBoxId());
        assertEquals(0, (int) locker1.getAvailableBox());
    }

    @Test
    public void should_save_in_1st_locker_when_locker1_has_1_locker2_has_2() throws NoEmptyLockerException {
        List<Box> boxes1 = Arrays.asList(new Box("001"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("002"), new Box("003"));
        Locker locker2 = new Locker(boxes2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertEquals("002", ticket.getBoxId());
        assertEquals(1, (int) locker2.getAvailableBox());
    }

    @Test
    public void should_save_in_1st_locker_when_locker1_has_0_locker2_has_0() throws NoEmptyLockerException {
        List<Box> boxes1 = Arrays.asList();
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList();
        Locker locker2 = new Locker(boxes2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();

        //Then:
        thrown.expect(NoEmptyLockerException.class);
        thrown.expectMessage("fail to save the bag, no Empty Box");

        smartLockerRobot.save(bag);
    }

    @Test
    public void should_get_bag_successfully_when_valid_ticket_from_PrimaryRobot() throws NoEmptyLockerException, InvalidTicketException {
        List<Box> boxes1 = Arrays.asList(new Box("001"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList();
        Locker locker2 = new Locker(boxes2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        BasicLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = primaryRobot.save(bag);

        Bag myBag = smartLockerRobot.get(ticket);

        assertEquals(bag, myBag);

    }
}
