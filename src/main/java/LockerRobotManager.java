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
                .filter(r -> r.getAvailableRoom() > 0)
                .findFirst();
        if (robotOptional.isPresent()) {
            return robotOptional.get().save(bag).setTicketType(TicketType.PRIME);
        }
        return this.save(bag).setTicketType(TicketType.PRIME);
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
                        return robot.get(ticket.setTicketType(TicketType.GENERAL));
                    } catch (InvalidTicketException e) {
                        return null;
                    }
                })
                .filter(b -> b != null)
                .findFirst();
        if (bagOptional.isPresent()) {
            return bagOptional.get();
        }
        return this.get(ticket.setTicketType(TicketType.PRIME));
    }

public String report() {

        String report = "M " + availableRooms() + " " + capability();
        for (Locker locker : lockers) {
            report += "\n\tL " + locker.getAvailableRoom() + " " + locker.getCapacity();
        }
        for (BasicLockerRobot robot : basicLockerRobots) {
            report += "\n\tR " + robot.getAvailableRoom() + " " + robot.getCapacity() ;
            for (Locker locker : robot.lockers) {
                report += "\n\t\tL " + locker.getAvailableRoom() + " " + locker.getCapacity();
            }
        }
        return report;
    }

    private Integer capability() {
        return this.lockers.stream().mapToInt(Locker::getCapacity).sum() +
                this.basicLockerRobots.stream().mapToInt(BasicLockerRobot::getCapacity).sum();
    }

    private Integer availableRooms() {
        return this.lockers.stream().mapToInt(Locker::getAvailableRoom).sum() +
                this.basicLockerRobots.stream().mapToInt(BasicLockerRobot::getAvailableRoom).sum();
    }
}
