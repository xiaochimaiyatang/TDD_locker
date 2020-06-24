import com.sun.istack.internal.NotNull;
import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.List;
import java.util.Optional;

public class LockerRobotManager extends BasicLockerRobot {
    private List<BasicLockerRobot> basicLockerRobots;

    public LockerRobotManager(List<BasicLockerRobot> lockerRobotList, List<Locker> lockerList) {
        super(lockerList);
        this.basicLockerRobots = lockerRobotList;
    }

    public Ticket vipSave(Bag bag) throws NoEmptyLockerException {
        Optional<BasicLockerRobot> robotOptional = basicLockerRobots.stream()
                .filter(r -> r.getAbility() > 0)
                .findFirst();
        if (robotOptional.isPresent()) {
            return robotOptional.get().save(bag);
        }
        return this.save(bag);
    }

    @Override
    public Ticket save(Bag bag) throws NoEmptyLockerException {
        return super.save(bag);
    }

    public Bag vipGet(Ticket ticket) throws InvalidTicketException {
        Bag bag = basicLockerRobots.stream()
                .map(robot -> {
                    try {
                        return robot.get(ticket);
                    } catch (InvalidTicketException e) {
                        return null;
                    }
                })
                .filter(b -> b != null)
                .findFirst().get();
        return bag;
    }
}
