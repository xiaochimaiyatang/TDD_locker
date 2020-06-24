import exception.InvalidTicketException;
import exception.NoEmptyLockerException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


//Given: 三个储物柜都未满 When：使用Primary Robot存包 then： 包存在储物柜1 得到ticket 
//Given: 三个储物柜，储物柜1满 储物柜2 有空闲  储物柜3有空闲 When：使用Primary Robot存包 then： 包存在储物柜2 得到ticket 
//Given: 三个储物柜 一个有效ticket When：使用Primary Robot取包 then： 得到存的包且ticket作废 
//Given: 三个储物柜 且都存满了 When：使用Primary Robot存包 then： 存包失败，柜箱已满 
//Given: 三个储物柜 一个非法ticket When：使用Primary Robot取包 then： 票据不合法，取包失败 
//Given: 三个储物柜 一个合法ticket When：使用Primary Robot取包 ， 再次取包then： 第一次成功，第二次失败  
public class PrimaryRobotTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void should_save_in_first_locker_when_every_locker_is_not_full() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        List<Box> boxes3 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker3 = new Locker(boxes3);
        BasicLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2, locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = primaryRobot.save(bag);

        //Then:
        assertEquals("001", ticket.getBoxId());
        assertEquals(1L, (long) locker1.getAvailableBox());
    }


    @Test
    public void should_save_in_second_locker_when_1st_is_full_but_2ndAnd3rd_locker_is_not_full() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        List<Box> boxes3 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker3 = new Locker(boxes3);
        BasicLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2, locker3));
        Bag bag = new Bag();
        primaryRobot.save(bag);
        primaryRobot.save(bag);
        //When:
        Ticket ticket = primaryRobot.save(bag);

        //Then:
        assertEquals("003", ticket.getBoxId());
        assertEquals(1L, (long) locker2.getAvailableBox());
    }

    @Test
    public void should_get_my_bag_successfully_when_ticket_is_valid() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        BasicLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = primaryRobot.save(bag);
        //When:
        Bag myBag = primaryRobot.get(ticket);
        //Then:
        assertEquals(bag, myBag);
    }

    @Test
    public void should_get_my_bag_successfully_when_ticket_is_valid_from_smart_robot() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        BasicLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.save(bag);
        //When:
        Bag myBag = primaryRobot.get(ticket);
        //Then:
        assertEquals(bag, myBag);
    }


    @Test
    public void should_fail_to_save_in_when_lockers_are_full() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        BasicLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        primaryRobot.save(bag);
        primaryRobot.save(bag);
        primaryRobot.save(bag);
        primaryRobot.save(bag);

        //Then:
        thrown.expect(NoEmptyLockerException.class);
        thrown.expectMessage("fail to save the bag, no Empty Box");
        //When:
        primaryRobot.save(bag);

    }

    @Test
    public void should_fail_to_get_my_bag_when_ticket_is_invalid() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        BasicLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        primaryRobot.save(bag);
        Ticket invalidTicket = new Ticket("invalid ticket");

        //Then:
        thrown.expect(InvalidTicketException.class);
        thrown.expectMessage("fail to get the bag, invalid ticket");

        //When:
        primaryRobot.get(invalidTicket);
    }
}
