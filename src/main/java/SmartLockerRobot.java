import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot extends BasicLockerRobot {
    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket save(Bag bag) throws NoEmptyLockerException {
        Locker locker = lockers.stream().max(Comparator.comparing(Locker::getAvailableRoom)).get();
        return locker.save(bag);
    }

    @Override
    public Bag get(Ticket ticket) throws InvalidTicketException {
        if (ticket.getTicketType() != TicketType.GENERAL) {
            throw new InvalidTicketException("fail to get the bag, please get bag from Locker Manager Robot");
        }
        return super.get(ticket);
    }
}
