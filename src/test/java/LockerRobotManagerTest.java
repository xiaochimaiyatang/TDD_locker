import exception.InvalidTicketException;
import exception.NoEmptyLockerException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

//1. 存:
//- 1Given：ABCD四个储物柜分别有1，2，3，4个存储空间，RM管理PR（管理AB储物柜），SR（管理BC储物柜） 以及 D储物柜。When vip用户找RM存包。 Then：包由PR管理的A柜子存储 且得到ticket。
//- 2Given：ABCD四个储物柜分别有0，2，3，4个存储空间，RM管理PR（管理A储物柜），SR（管理BC储物柜） 以及 D储物柜。When vip用户找RM存包。 Then：包由SR管理的C柜子存储 且得到ticket。
//- 3Given：ABCD四个储物柜分别有0，0，0，4个存储空间，RM管理PR（管理A储物柜），SR（管理BC储物柜） 以及 D储物柜。When vip用户找RM存包。 Then：包由RM管理的D柜子存储 且得到ticket。
//- 4Given：ABCD四个储物柜分别有0，0，0，4个存储空间，RM管理PR 以及 D储物柜。When vip用户找RM存包。 Then：包由RM管理的D柜子存储 且得到ticket。
//- 5Given：有储物柜A具有3个空间，RM管理储物柜A。When vip用户找RM存包。 Then：包存储在A柜子存储 且得到ticket。
//- 6Given：ABCD四个储物柜分别有0，0，0，0个存储空间，RM管理PR（管理A储物柜），SR（管理BC储物柜） 以及 D储物柜。When vip用户找RM存包。 Then：存包失败，柜箱已满。
//- 7Given：有储物柜A具有0个空间，RM管理储物柜A。When vip用户找RM存包。 Then：存包失败，柜箱已满。
//
//2. 取:
//- 8Given: 有一个由RM交给PR存储包后得到的ticket。 When vip用户使用该ticket找RM取包。 Then：得到存的包且ticket作废。
//- 8Given: 有一个由RM自己存储包后得到的ticket。 When vip用户使用该ticket找RM取包。 Then：得到存的包且ticket作废。
//- 9Given: 有一个由RM存储包后得到的ticket。 When vip用户使用该ticket找PR取包。 Then：票据不合法，取包失败。
//- 10Given: 有一个由RM存储包后得到的ticket。 When vip用户使用该ticket找SR取包。 Then：票据不合法，取包失败。
//- 10Given: 有一个由PR存储包后得到的ticket。 When vip用户使用该ticket找SR取包。 Then：票据不合法，取包失败。
//- 11Given: 有一个非法ticket。 When vip用户使用该ticket找RM取包。 Then：票据不合法，取包失败。

public class LockerRobotManagerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    //PR:Primary Robot, SR：smartLockerRobot, RM: Locker Robot Manager
    @Test
    public void should_save_in_locker1_when_store_by_RM_given_RM_manage_PR_notFull_SR_notFull_locker() throws NoEmptyLockerException {
        //Given:
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        Locker locker3 = new Locker(2);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot, smartLockerRobot), Arrays.asList(locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = lockerRobotManager.vipSave(bag);

        //Then:
        assertNotNull(ticket);
        assertEquals(1, (int) locker1.getAvailableRoom());

    }

    @Test
    public void should_save_in_locker2_when_store_by_RM_given_RM_manage_PR_full_SR_notFull_locker() throws NoEmptyLockerException {
        //Given:
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(2);
        Locker locker3 = new Locker(2);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot, smartLockerRobot), Arrays.asList(locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = lockerRobotManager.vipSave(bag);

        //Then:
        assertNotNull(ticket);
        assertEquals(1, (int) locker2.getAvailableRoom());

    }

    @Test
    public void should_save_in_locker3_when_store_by_RM_given_RM_manage_PR_full_SR_full_locker() throws NoEmptyLockerException {
        //Given:
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);
        Locker locker3 = new Locker(2);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryRobot, smartLockerRobot), Arrays.asList(locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = lockerRobotManager.vipSave(bag);

        //Then:
        assertNotNull(ticket);
        assertEquals(1, (int) locker3.getAvailableRoom());

    }

    @Test
    public void should_save_in_locker3_when_store_by_RM_given_RM_manage_3locker() throws NoEmptyLockerException {
        //Given:
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);
        Locker locker3 = new Locker(2);

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(), Arrays.asList(locker1, locker2, locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = lockerRobotManager.vipSave(bag);

        //Then:
        assertNotNull(ticket);

        assertEquals(1, (int) locker3.getAvailableRoom());

    }

    @Test
    public void should_fail_to_save_when_store_by_RM_given_RM_manage_PR_full_SR_full_locker_full() throws NoEmptyLockerException {
        //Given:
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);
        Locker locker3 = new Locker(0);
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
        Locker locker1 = new Locker(0);
        Locker locker2 = new Locker(0);

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(), Arrays.asList(locker1, locker2));
        Bag bag = new Bag();
        //then:
        thrown.expect(NoEmptyLockerException.class);
        thrown.expectMessage("fail to save the bag, no Empty Box");
        //When:
        lockerRobotManager.vipSave(bag);
    }

    @Test
    public void should_get_bag_when_get_by_RM_given_ticket_is_from_PR_managed_by_RM() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
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
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
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
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
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

    @Test
    public void should_get_bag_when_get_by_RM_given_ticket_is_from_RM() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        Locker locker3 = new Locker(2);

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(), Arrays.asList(locker1, locker2, locker3));
        Bag bag = new Bag();
        Ticket ticket = lockerRobotManager.vipSave(bag);
        //When:
        Bag myBag = lockerRobotManager.vipGet(ticket);
        //Then:
        assertEquals(myBag, bag);

    }

    @Test
    public void should_fail_to_get_bag_when_get_by_RM_given_ticket_is_from_SR() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(smartLockerRobot), Arrays.asList(locker2));
        Bag bag = new Bag();
        Ticket ticket = smartLockerRobot.save(bag);
        //Then:
        thrown.expect(InvalidTicketException.class);
        thrown.expectMessage("fail to get the bag, please get bag from Locker Manager Robot");
        //When:
        lockerRobotManager.vipGet(ticket);
        //Then:
    }

    @Test
    public void should_fail_to_get_bag_when_get_by_RM_given_ticket_is_invalid() throws NoEmptyLockerException, InvalidTicketException {
        //Given:
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(2);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1));

        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(smartLockerRobot), Arrays.asList(locker2));
        Bag bag = new Bag();
        smartLockerRobot.save(bag);
        Ticket ticket = new Ticket().setTicketType(TicketType.PRIME);
        //Then:
        thrown.expect(InvalidTicketException.class);
        thrown.expectMessage("fail to get the bag, invalid ticket");
        //When:
        lockerRobotManager.vipGet(ticket);
        //Then:
    }

}


