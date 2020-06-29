import exception.InvalidTicketException;
import exception.NoEmptyLockerException;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private Map<Ticket, Bag> ticketBagMap = new HashMap<>();

    private Integer capacity;

    public Locker(Integer capacity) {
        this.capacity = capacity;
    }

    public Ticket save(Bag bag) throws NoEmptyLockerException {
        if (ticketBagMap.size() >= capacity) {
            throw new NoEmptyLockerException();
        }
        Ticket ticket = new Ticket();
        ticketBagMap.put(ticket, bag);
        return ticket;
    }

    public Integer getAvailableRoom() {
        return this.capacity - ticketBagMap.size();
    }

    public Bag get(Ticket ticket) throws InvalidTicketException {
        Bag bag = ticketBagMap.get(ticket);
        if (bag != null) {
            ticketBagMap.remove(ticket);
            return bag;
        }
        throw new InvalidTicketException();
    }

    public Integer getCapacity() {
        return capacity;
    }
}
