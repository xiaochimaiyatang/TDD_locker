import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class LockerTest {
    Locker locker =new Locker();
    @Test
    public void should_save_bag_success(){
        Boolean Result=locker.save_bag(10,2);
        assert  TRUE == Result;
    }

    @Test
    public void should_save_bag_fail(){
        Boolean Result=locker.save_bag(10,0);
        assert  FALSE == Result;
    }
}
