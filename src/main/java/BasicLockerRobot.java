import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.List;

public class BasicLockerRobot {
    protected List<Locker> lockers;

    public BasicLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws NoEmptyLockerException {

        return lockers.stream()
                .filter(locker -> locker.getAvailableRoom() != 0)
                .findFirst()
                .orElseThrow(() -> new NoEmptyLockerException())
                .save(bag);
    }

    public Bag get(Ticket ticket) throws InvalidTicketException {
        return lockers.stream()
                .map(locker -> {
                    try {
                        return locker.get(ticket);
                    } catch (InvalidTicketException e) {
                        return null;
                    }
                })
                .filter(b -> b != null)
                .findFirst()
                .orElseThrow(() -> new InvalidTicketException());
    }

    public Integer getAvailableRoom() {
        return lockers.stream()
                .mapToInt(Locker::getAvailableRoom)
                .sum();
    }

    public Integer getCapacity() {
        return lockers.stream()
                .mapToInt(Locker::getCapacity)
                .sum();
    }
}
