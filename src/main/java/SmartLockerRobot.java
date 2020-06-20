import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.List;

public class SmartLockerRobot extends PrimaryRobot{
    public SmartLockerRobot(List<Locker> lockers) {
        super(lockers);
    }

    @Override
    public Ticket save(Bag bag) throws NoEmptyLockerException {
        return super.save(bag);
    }

    @Override
    public Bag get(Ticket ticket) throws InvalidTicketException {
        return super.get(ticket);
    }
}
