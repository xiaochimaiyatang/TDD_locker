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
            return robotOptional.get().save(bag).prime();
        }
        return this.save(bag).prime();
    }

    @Override
    public Ticket save(Bag bag) throws NoEmptyLockerException {
        return super.save(bag);
    }

    public Bag vipGet(Ticket ticket) throws InvalidTicketException {
        if (ticket.getTicketType() != TicketType.PRIME) {
            throw new InvalidTicketException("fail to get the bag, please get bag from Locker Manager Robot");
        }
        Optional<Bag> bagOptional = basicLockerRobots.stream()
                .map(robot -> {
                    try {
                        return robot.get(ticket);
                    } catch (InvalidTicketException e) {
                        return null;
                    }
                })
                .filter(b -> b != null)
                .findFirst();
        if (bagOptional.isPresent()) {
            return bagOptional.get();
        }
        return this.get(ticket);
    }
}
