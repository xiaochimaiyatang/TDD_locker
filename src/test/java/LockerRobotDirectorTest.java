import exception.NoEmptyLockerException;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class LockerRobotDirectorTest {


    @Test
    public void should_report_when_print_given_manage_primary_robot() throws NoEmptyLockerException {
        PrimaryLockerRobot primaryLockerRobot = buildPrimaryLockerRobot();
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(primaryLockerRobot), Arrays.asList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Arrays.asList(lockerRobotManager));

        String report = lockerRobotDirector.report();
        String expectReport = "M 1 2\n" +
                "\tR 1 2\n" +
                "\t\tL 1 2";
        assertEquals(expectReport, report);
    }

    @Test
    public void should_report_when_print_given_manage_smart_robot() throws NoEmptyLockerException {
        SmartLockerRobot smartLockerRobot = buildSmartLockerRobot();
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(smartLockerRobot), Arrays.asList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Arrays.asList(lockerRobotManager));

        String report = lockerRobotDirector.report();
        String expectReport = "M 2 3\n" +
                "\tR 2 3\n" +
                "\t\tL 2 3";
        assertEquals(expectReport, report);
    }

    @Test
    public void should_report_when_print_given_manage_locker() {
        Locker locker1 = new Locker(2);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(), Arrays.asList(locker1));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Arrays.asList(lockerRobotManager));

        String report = lockerRobotDirector.report();
        String expectReport = "M 2 2\n" +
                "\tL 2 2";
        assertEquals(expectReport, report);
    }


    private PrimaryLockerRobot buildPrimaryLockerRobot() throws NoEmptyLockerException {
        Locker locker1 = new Locker(2);
        PrimaryLockerRobot primaryRobot = new PrimaryLockerRobot(Arrays.asList(locker1));
        primaryRobot.save(new Bag());
        return primaryRobot;
    }

    private SmartLockerRobot buildSmartLockerRobot() throws NoEmptyLockerException {
        Locker locker1 = new Locker(3);
        SmartLockerRobot smartRobot = new SmartLockerRobot(Arrays.asList(locker1));
        smartRobot.save(new Bag());
        return smartRobot;
    }


}
