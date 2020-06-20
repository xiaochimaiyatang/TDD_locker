import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot extends PrimaryRobot {
    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket save(Bag bag) throws NoEmptyLockerException {
        Locker locker = lockers.stream().max(Comparator.comparing(Locker::getAvailableBox)).get();
        return locker.save(bag);
    }

    @Override
    public Bag get(Ticket ticket) throws InvalidTicketException {
        return super.get(ticket);
    }
}
