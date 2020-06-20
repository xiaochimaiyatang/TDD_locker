import exception.NoEmptyLockerException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SmartLockerRobotTest {

    @Test
    public void should_save_in_1st_locker_when_locker1_has_2_locker2_has_1() throws NoEmptyLockerException {
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"));
        Locker locker2 = new Locker(boxes2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2 ));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertEquals("001", ticket.getBoxId());
        assertEquals(1L, locker1.getAvailableBox());
    }

    @Test
    public void should_save_in_1st_locker_when_locker1_has_1_locker2_has_1() throws NoEmptyLockerException {
        List<Box> boxes1 = Arrays.asList(new Box("001"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"));
        Locker locker2 = new Locker(boxes2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2 ));
        Bag bag = new Bag();

        Ticket ticket = smartLockerRobot.save(bag);

        assertEquals("001", ticket.getBoxId());
        assertEquals(0L, locker1.getAvailableBox());
    }

}
