import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


//Given: 三个储物柜都未满 When：使用Primary Robot存包 then： 包存在储物柜1 得到ticket 
//Given: 三个储物柜，储物柜1满 储物柜2 有空闲  储物柜3有空闲 When：使用Primary Robot存包 then： 包存在储物柜2 得到ticket 
//Given: 三个储物柜 一个有效ticket When：使用Primary Robot取包 then： 得到存的包且ticket作废 
//Given: 三个储物柜 且都存满了 When：使用Primary Robot存包 then： 存包失败，柜箱已满 
//Given: 三个储物柜 一个非法ticket When：使用Primary Robot取包 then： 票据不合法，取包失败 
//Given: 三个储物柜 一个合法ticket When：使用Primary Robot取包 ， 再次取包then： 第一次成功，第二次失败  
public class PrimaryRobotTest {
    @Test
    public void should_save_in_first_locker_when_every_locker_is_not_full() throws Exception {
        //Given:
        List<Box> boxes1 = Arrays.asList(new Box("001"), new Box("002"));
        Locker locker1 = new Locker(boxes1);
        List<Box> boxes2 = Arrays.asList(new Box("003"), new Box("004"));
        Locker locker2 = new Locker(boxes2);
        List<Box> boxes3 = Arrays.asList(new Box("005"), new Box("006"));
        Locker locker3 = new Locker(boxes3);
        PrimaryRobot primaryRobot = new PrimaryRobot(Arrays.asList(locker1, locker2, locker3));
        Bag bag = new Bag();
        //When:
        Ticket ticket = primaryRobot.save(bag);

        //Then:
        assertEquals("001", ticket.getBoxId());
        assertEquals(1L, locker1.getAvailableBox());
    }
}
