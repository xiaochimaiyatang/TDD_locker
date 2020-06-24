import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.List;

public class PrimaryLockerRobot extends BasicLockerRobot {

    public PrimaryLockerRobot(List<Locker> lockers) {
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
