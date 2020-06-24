import exception.NoEmptyLockerException;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
//- 8Given: 有一个由RM存储包后得到的ticket。 When vip用户使用该ticket找RM取包。 Then：得到存的包且ticket作废。
//- 9Given: 有一个由RM存储包后得到的ticket。 When vip用户使用该ticket找PR取包。 Then：票据不合法，取包失败。
//- 10Given: 有一个由RM存储包后得到的ticket。 When vip用户使用该ticket找SR取包。 Then：票据不合法，取包失败。
//- 11Given: 有一个非法ticket。 When vip用户使用该ticket找RM取包。 Then：票据不合法，取包失败。

public class LockerRobotManagerTest {
    //PR:Primary Robot, SR：smartLockerRobot, RM: Locker Robot Manager
    @Test
    public void should_save_in_lockerA_when_store_by_RM_given_RM_manage_PR_SR_locker() throws NoEmptyLockerException {
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
        Ticket ticket = lockerRobotManager.save(bag);

        //Then:
        assertEquals("001", ticket.getBoxId());
        assertEquals(1, (int)locker1.getAvailableBox());

    }
}
