import java.util.List;

public class PrimaryRobot {
    private List<Locker> lockers;

    public PrimaryRobot(List<Locker> lockers) {
        this.lockers = lockers;

    }

    public Ticket save(Bag bag) throws Exception {
        Ticket ticket = lockers.get(0).save(bag);
        return ticket;
    }
}
