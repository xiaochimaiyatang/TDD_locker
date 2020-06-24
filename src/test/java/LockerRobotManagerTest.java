import exception.InvalidTicketException;
import exception.NoEmptyLockerException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

//1. 存:
//- 1Given：ABCD四个储物柜分别有1，2，3，4个存储空间，RM管理PR（管理AB储物柜），SR（管理BC储物柜） 以及 D储物柜。When vip用户找RM存包。 Then：包由PR管理的A柜子存储 且得到ticket。
//- 2Given：ABCD四个储物柜分别有0，2，3，4个存储空间，RM管理PR（管理A储物柜），SR（管理BC储物柜） 以及 D储物柜。When vip用户找RM存包。 Then：包由SR管理的C柜子存储 且得到ticket。
//- 3Given：ABCD四个储物柜分别有0，0，0，4个存储空间，RM管理PR（管理A储物柜），SR（管理BC储物柜） 以及 D储物柜。When vip用户找RM存包。 Then：包由RM管理的D柜子存储 且得到ticket。
//- 5Given：有储物柜A具有3个空间，RM管理储物柜A。When vip用户找RM存包。 Then：包存储在A柜子存储 且得到ticket。
//- 6Given：ABCD四个储物柜分别有0，0，0，0个存储空间，RM管理PR（管理A储物柜），SR（管理BC储物柜） 以及 D储物柜。When vip用户找RM存包。 Then：存包失败，柜箱已满。
//- 7Given：有储物柜A具有0个空间，RM管理储物柜A。When vip用户找RM存包。 Then：存包失败，柜箱已满。
//
//2. 取:
//- 8Given: 有一个由RM存储包后得到的ticket。 When vip用户使用该ticket找RM取包。 Then：得到存的包且ticket作废。
//- 9Given: 有一个由RM存储包后得到的ticket。 When vip用户使用该ticket找PR取包。 Then：票据不合法，取包失败。
//- 10Given: 有一个由RM存储包后得到的ticket。 When vip用户使用该ticket找SR取包。 Then：票据不合法，取包失败。
//- 11Given: 有一个非法ticket。 When vip用户使用该ticket找RM取包。 Then：票据不合法，取包失败。

public class LockerRobotManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    //PR:Primary Robot, SR：smartLockerRobot, RM: Locker Robot Manager
    @Test
    public void should_save_in_locker1_when_store_by_RM_given_RM_manage_PR_notFull_SR_notFull_locker() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        List<Box> boxes3 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker3 = new Locker(boxes3);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot, smartLockerRobot), Arrays.asList(locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = lockerRobotManager.vipSave(bag);

        //Then:
        assertEquals("001", ticket.getBoxId());
        assertEquals(1, (int) locker1.getAvailableBox());

    }

    @Test
    public void should_save_in_locker2_when_store_by_RM_given_RM_manage_PR_full_SR_notFull_locker() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList();
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        List<Box> boxes3 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker3 = new Locker(boxes3);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot, smartLockerRobot), Arrays.asList(locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = lockerRobotManager.vipSave(bag);

        //Then:
        assertEquals("003", ticket.getBoxId());
        assertEquals(1, (int) locker2.getAvailableBox());

    }

    @Test
    public void should_save_in_locker3_when_store_by_RM_given_RM_manage_PR_full_SR_full_locker() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList();
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList();
        Locker locker2 = new Locker(boxes2);
        List<Box> boxes3 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker3 = new Locker(boxes3);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot, smartLockerRobot), Arrays.asList(locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = lockerRobotManager.vipSave(bag);

        //Then:
        assertEquals("005", ticket.getBoxId());
        assertEquals(1, (int) locker3.getAvailableBox());

    }

    @Test
    public void should_save_in_locker3_when_store_by_RM_given_RM_manage_3locker() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList();
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList();
        Locker locker2 = new Locker(boxes2);
        List<Box> boxes3 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker3 = new Locker(boxes3);

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(), Arrays.asList(locker1, locker2, locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = lockerRobotManager.vipSave(bag);

        //Then:
        assertEquals("005", ticket.getBoxId());
        assertEquals(1, (int) locker3.getAvailableBox());

    }

    @Test
    public void should_fail_to_save_when_store_by_RM_given_RM_manage_PR_full_SR_full_locker_full() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList();
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList();
        Locker locker2 = new Locker(boxes2);
        List<Box> boxes3 = Arrays.asList();
        Locker locker3 = new Locker(boxes3);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot, smartLockerRobot), Arrays.asList(locker3));
        Bag bag = new Bag();

        thrown.expect(NoEmptyLockerException.class);
        thrown.expectMessage("fail to save the bag, no Empty Box");
        //When:
        lockerRobotManager.vipSave(bag);

    }

    @Test
    public void should_fail_to_save_when_store_by_RM_given_RM_manage_lockers_full() throws NoEmptyLockerException {
        //Given:
        List<Box> boxes1 = Arrays.asList();
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList();
        Locker locker2 = new Locker(boxes2);

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(), Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        //then:
        thrown.expect(NoEmptyLockerException.class);
        thrown.expectMessage("fail to save the bag, no Empty Box");
        //When:
        lockerRobotManager.vipSave(bag);
    }

    @Test
    public void should_get_bag_when_get_by_RM_given_ticket_is_from_RM() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker2 = new Locker(boxes2);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot), Arrays.asList(locker2));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.vipSave(bag);
        //When:
        Bag myBag = lockerRobotManager.vipGet(ticket);
        //Then:
        assertEquals(myBag, bag);
    }

    @Test
    public void should_fail_to_get_bag_when_get_by_PR_given_ticket_is_from_RM() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker2 = new Locker(boxes2);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot), Arrays.asList(locker2));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.vipSave(bag);
        //Then:
        thrown.expect(InvalidTicketException.class);
        thrown.expectMessage("fail to get the bag, please get bag from Locker Manager Robot");
        //When:
        primaryRobot.get(ticket);
        //Then:
    }

    @Test
    public void should_fail_to_get_bag_when_get_by_SR_given_ticket_is_from_RM() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker2 = new Locker(boxes2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(smartLockerRobot), Arrays.asList(locker2));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.vipSave(bag);
        //Then:
        thrown.expect(InvalidTicketException.class);
        thrown.expectMessage("fail to get the bag, please get bag from Locker Manager Robot");
        //When:
        smartLockerRobot.get(ticket);
        //Then:
    }

}
