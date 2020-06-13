import org.junit.Test;
import static java.lang.Boolean.TRUE;

public class LockerTest {
    Locker locker =new Locker();
    Boolean Flag=TRUE;
    @Test
    public void should_save_bag_success(){
        Boolean Result=locker.save_bag(10,2);
        assert  Flag == Result;


    }

}
