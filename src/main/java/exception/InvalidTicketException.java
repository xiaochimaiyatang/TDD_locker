package exception;

public class InvalidTicketException extends Exception {

    public InvalidTicketException() {
        super("fail to get the bag, invalid ticket");
    }
}
