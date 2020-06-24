import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.List;

public class LockerRobotManager extends BasicLockerRobot {
    private List<BasicLockerRobot> basicLockerRobots;

    public  LockerRobotManager(List<BasicLockerRobot> lockerRobotList, List<Locker> lockerList) {
        super(lockerList);
        this.basicLockerRobots = lockerRobotList;
    }

    @Override
    public Ticket save(Bag bag) throws NoEmptyLockerException {
        Ticket ticket = basicLockerRobots.stream()
                .filter(r -> r.getAbility() > 0)
                .findFirst()
                .get()
                .save(bag);
        return ticket;
    }

    @Override
    public Bag get(Ticket ticket) throws InvalidTicketException {
        return super.get(ticket);
    }
}
