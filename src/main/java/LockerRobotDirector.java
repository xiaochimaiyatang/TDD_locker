import java.util.List;

public class LockerRobotDirector {
    private List<LockerRobotManager> lockerRobotManagerList;
    public LockerRobotDirector(List<LockerRobotManager> lockerRobotManagerList) {
        this.lockerRobotManagerList = lockerRobotManagerList;
    }

    public String report() {
        String report = "";
        for (LockerRobotManager lockerRobotManager : lockerRobotManagerList) {
            report += lockerRobotManager.report();
        }
        return report;
    }
}
